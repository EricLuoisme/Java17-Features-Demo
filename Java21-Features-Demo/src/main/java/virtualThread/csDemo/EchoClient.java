package virtualThread.csDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) {

        int portNum = 10110;
        try (
                final Socket echoSocket = new Socket("localhost", portNum);
                final PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                final BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()))
        ) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
                if (userInput.equals("bye"))
                    break;
            }
        } catch (UnknownHostException e) {
            System.out.println("Don't know about host");
        } catch (IOException e) {
            System.out.println("Could not get I/O for the connection");
        }

    }
}
