package SimpleNC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerReceiver extends Thread{

    Socket socket;
    BufferedReader in;

    public ServerReceiver(Socket socket) {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Error : "+e);
        }
    }

    public void run() {
        while (in != null) {
            try {
                System.out.println(in.readLine());
            } catch (IOException e) {
                System.exit(1);
            }
        }
    }
}