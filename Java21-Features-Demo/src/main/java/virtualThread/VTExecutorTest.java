package virtualThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VTExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        // use virtual thread task executor
        try (ExecutorService ownExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<?> future = ownExecutor.submit(() -> System.out.println("Running Thread"));
            future.get();
            System.out.println("Task completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
