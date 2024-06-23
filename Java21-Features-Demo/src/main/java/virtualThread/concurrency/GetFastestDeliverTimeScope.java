package virtualThread.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.StructuredTaskScope;

/**
 * Customized StructuredTaskScope -> config your own logic for structured concurrency task
 *
 * @author Roylic
 * 2023/9/25
 */
public class GetFastestDeliverTimeScope extends StructuredTaskScope<Integer> {

    private Integer fastest;
    private final List<Throwable> exceptions = Collections.synchronizedList(new ArrayList<>());

    @Override
    protected void handleComplete(Subtask<? extends Integer> subtask) {
        switch (subtask.state()) {
            // btw, JDK12 already remove the break's necessity, enhanced-switch will only execute one case's logic
            case UNAVAILABLE -> {
                // Ignore
            }
            case SUCCESS -> {
                Integer deliverTime = subtask.get();
                // update & compare under synchronized
                synchronized (this) {
                    if (fastest == null || deliverTime < fastest) {
                        fastest = deliverTime;
                    }
                }
            }
            case FAILED -> exceptions.add(subtask.exception());
        }
    }

    /**
     * Similar to ShutdownOnSuccess, we need result() to determine what result we need from sub-tasks
     */
    public Integer result() throws RuntimeException {
        if (fastest != null) {
            return fastest;
        } else {
            var exception = new RuntimeException();
            exceptions.forEach(exception::addSuppressed);
            throw exception;
        }
    }

    public static void main(String[] args) {

        // single product -> find the fastest one
        singleDeliverProduct();

        // multi products -> find the fastest one for each of them
        System.out.println(multiDeliverProducts(Arrays.asList(1, 2, 2, 5)));
    }


    private static List<Integer> multiDeliverProducts(List<Integer> productIds) {
        try (
                var scope = new StructuredTaskScope<Integer>()
        ) {

            List<Subtask<Integer>> subtasks = productIds.stream()
                    .map(id -> scope.fork(
                            () -> {
                                System.out.println("Haha");
                                return id + 100;
                            }))
                    .toList();

            scope.join();

            // for the result, only return successfully finished subtask's result
            return subtasks.stream()
                    .filter(subtask -> subtask.state() == Subtask.State.SUCCESS)
                    .map(Subtask::get)
                    .toList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }


    private static void singleDeliverProduct() {
        try (
                // by using custom scope
                var customScope = new GetFastestDeliverTimeScope()
        ) {
            customScope.fork(() -> {
                System.out.println("Haha");
                return 100;
            });
            customScope.fork(() -> {
                System.out.println("Haha");
                return 50;
            });
            customScope.fork(() -> {
                System.out.println("Haha");
                return 100;
            });

            // must call join() first
            customScope.join();

            // this would go to customized result logic
            Integer result = customScope.result();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
