package SimpleNC;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        System.out.println("=========================================\n" +
                "Server\n" +
                "Usage: snc [option] [port]\n"+
                "Option: \n" +
                "-l <port>\n" +
                "=========================================");

        boolean correctInput = false;

        while (!correctInput){
            System.out.print("~$ ");
            Scanner scanner = new Scanner(System.in);
            String input[] = scanner.nextLine().split(" ");

            if(input.length!=3){
                System.out.println("Invalid input value. Please enter again");
                continue;
            }

            String command = input[0];
            String option = input[1];
            int port = Integer.parseInt(input[2]);

            if (command.equals("snc") && option.equals("-l")) {
                correctInput=true;
                ServerSocket serverSocket = null;
                Socket socket = null;

                try {
                    serverSocket = new ServerSocket(port);

                    socket = serverSocket.accept();

                    ServerSender sender = new ServerSender(socket);
                    ServerReceiver receiver = new ServerReceiver(socket);

                    sender.start();
                    receiver.start();

                } catch (IOException e) {
                    System.err.println("Error :" + e);
                }
            }else{
                System.out.println("Invalid input value. Please enter again");
            }
        }
    }
}
