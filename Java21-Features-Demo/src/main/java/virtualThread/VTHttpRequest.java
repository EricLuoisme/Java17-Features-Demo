package virtualThread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VTHttpRequest {

    public static void main(String[] args) {

        // by making a virtual thread executor as a resource -> declare it as a local instance
        // below is one of the easiest way to run multiple task with Virtual Threads
        try (
                var service = Executors.newVirtualThreadPerTaskExecutor()
        ) {

            List<Future<?>> futureList = new LinkedList<>();

            // usually works on Network IO heavy tasks
            futureList.add(service.submit(VTHttpRequest::sleepTask));
            futureList.add(service.submit(VTHttpRequest::sleepTask));
            futureList.add(service.submit(VTHttpRequest::sleepTask));

            // combine them together
            int sum = 0;
            for (Future<?> future : futureList) {
                sum += (int) future.get();
            }
            System.out.println(sum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int sleepTask() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return 1;
    }
}
