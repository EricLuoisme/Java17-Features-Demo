package virtualThread.csDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {

    public static void main(String[] args) {

        int portNum = 10110;
        try (
                ServerSocket serverSocket = new ServerSocket(portNum);
                ExecutorService ownExecutor = Executors.newVirtualThreadPerTaskExecutor()
        ) {
            while (true) {
                // accept incoming connections
                Socket clientSocket = serverSocket.accept();
                // start server thread
                ownExecutor.submit(
                        () -> {
                            try (
                                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                            ) {
                                String inputLine;
                                while ((inputLine = in.readLine()) != null) {
                                    System.out.println(inputLine);
                                    out.println(inputLine);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
            }
        } catch (IOException e) {
            System.out.println("Exception caught:");
            System.out.println(e.getMessage());
        }
    }

}
