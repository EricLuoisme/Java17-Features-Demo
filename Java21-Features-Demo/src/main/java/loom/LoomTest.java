package loom;

public class LoomTest {

    // need to add '--enable-preview' as additional command line in compiler -> then    enable the JDK21 features
    // or set -> 21 in Target bytecode version setting in compiler
    public static void main(String[] args) {
        Thread.startVirtualThread(() -> System.out.println("Hello Fiber")).start();
    }
}
