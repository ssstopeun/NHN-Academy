package SimpleNC;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerSender extends Thread{
    Socket socket;
    PrintWriter printWriter;

    public ServerSender(Socket socket) {
        this.socket = socket;

        try {
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (Exception e){
            System.err.println("Error : "+e);
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (printWriter != null) {
            printWriter.println(scanner.nextLine());
            printWriter.flush();
        }
    }
}