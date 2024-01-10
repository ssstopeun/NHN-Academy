package SelfStudy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam4_ServerSocket {
    public static void main(String[] args) {
        int port = 1234;

        try(ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept()){
            System.out.println("hello");
            socket.getOutputStream().write("Hello".getBytes());
            socket.getOutputStream().flush();

        } catch (IOException e) {
            System.err.println("e");
        }
    }
}
