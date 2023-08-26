public class LoomTest {

    public static void main(String[] args) {
        Thread.startVirtualThread(() -> System.out.println("Hello Fiber")).start();
    }
}
