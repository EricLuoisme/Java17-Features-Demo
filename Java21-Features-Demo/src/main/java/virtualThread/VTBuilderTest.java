package virtualThread;

public class VTBuilderTest {

    public static void main(String[] args) throws InterruptedException {

        // use Virtual-Thread builder
        Thread.Builder.OfVirtual builder = Thread.ofVirtual().name("virtual-worker-", 0);
        Runnable task = () ->
                System.out.println("Thread ID: " + Thread.currentThread().threadId());

        Thread t1 = builder.start(task);
        t1.join();

        Thread t2 = builder.start(task);
        t2.join();
    }
}
