package SelfStudy;

import java.io.IOException;
import java.net.Socket;

public class Exam1_ConnectServer {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",123);
            System.out.println("Complete connection");
            socket.close();
        } catch (IOException e) {
            System.err.println("Error : "+e);
        }
    }
}
