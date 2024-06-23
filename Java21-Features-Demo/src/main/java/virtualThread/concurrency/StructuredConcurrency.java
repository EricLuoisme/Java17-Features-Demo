package virtualThread.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

/**
 * StructuredTaskScope: provide a hierarchy on tasks by using VirtualThread
 *
 * @author Roylic
 * 2023/9/25
 */
public class StructuredConcurrency {

    public static void main(String[] args) {

        // when we need multiple time-consuming task's result and combine them together to form the response
        System.out.println("Try ShutdownOnFailure");
        taskScope_combineResults();

        // when we only need one of the result could form the response
        System.out.println("Try ShutdownOnSuccess");
        Object o = taskScope_singleResults();
        if (null != o) {
            System.out.println(o);
        }
    }

    private static Object taskScope_singleResults() {
        try (
                // the virtualThreadPool will be shut down when it met one of them success
                var scope = new StructuredTaskScope.ShutdownOnSuccess<>()
        ) {
            // split the task -> by using scope.fork()
            for (int i = 0; i < 3; i++) {
                scope.fork(() -> getAccountDetails(10086));
            }

            // return
            System.out.println("Finished");
            return scope.join().result();
        } catch (InterruptedException e) {
            // populate the interruption
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    private static void taskScope_combineResults() {
        try (
                // the virtualThreadPool will be shut down when it met failure
                var scope = new StructuredTaskScope.ShutdownOnFailure()
        ) {
            // split the task -> by using scope.fork()
            // originally as type: StructuredTaskScope.Subtask<Integer>
            Supplier<Integer> accountDetailFuture = scope.fork(() -> getAccountDetails(10086));
            Supplier<String> linkedAccountsFuture = scope.fork(() -> fetchLinkedAccounts(10086));
            Supplier<String> userDetailsFuture = scope.fork(() -> fetchUserDetails(10086));

            // join all subtasks
            scope.join();
            scope.throwIfFailed(_ -> new RuntimeException("Exception")); // handle error when subtask fails

            // return
            Integer accountDetail = accountDetailFuture.get();
            String linkedAccounts = linkedAccountsFuture.get();
            String userDetails = userDetailsFuture.get();
            System.out.println(accountDetail);
            System.out.println(linkedAccounts);
            System.out.println(userDetails);

        } catch (InterruptedException e) {
            // populate the interruption
            Thread.currentThread().interrupt();
        }
    }


    public static Integer getAccountDetails(int id) {
        return id + 10;
    }

    public static String fetchLinkedAccounts(int id) {
        return id + "_id";
    }

    public static String fetchUserDetails(int id) {
        return id + "_name";
    }
}
