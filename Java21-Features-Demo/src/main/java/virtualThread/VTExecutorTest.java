package virtualThread;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class VTExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        simpleUsing();
        intUsing();
    }

    private static void simpleUsing() {
        // use virtual thread task executor
        try (ExecutorService ownExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<?> future = ownExecutor.submit(() -> System.out.println("Running Thread"));
            future.get();
            System.out.println("Task completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void intUsing() {
        try (
                var virtualEx = Executors.newVirtualThreadPerTaskExecutor()
        ) {
            IntStream.range(0, 10_000).forEach(i ->
                    virtualEx.submit(() -> {
                        try {
                            Thread.sleep(Duration.ofSeconds(1));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Finished");
                        return i;
                    }));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // executor.close() is called implicitly, and waits
    }
}
