package loom;

public class LoomTest {

    // need to add '--enable-preview' as additional command line in compiler -> then    enable the JDK21 features
    public static void main(String[] args) {
        Thread.startVirtualThread(() -> System.out.println("Hello Fiber")).start();
    }
}
