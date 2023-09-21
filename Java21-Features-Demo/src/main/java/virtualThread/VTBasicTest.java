package virtualThread;

public class VTBasicTest {

    // need to add '--enable-preview' as additional command line in compiler -> then    enable the JDK21 features
    // or set -> 21 in Target bytecode version setting in compiler
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.ofVirtual()
                .start(() -> System.out.println("This one is the correct using"));
        thread.join();
    }
}
