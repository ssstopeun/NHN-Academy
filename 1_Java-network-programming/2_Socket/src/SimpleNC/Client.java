package SimpleNC;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("=========================================\n" +
                "Client\n" +
                "Usage: snc 127.0.0.1 [port]\n" +
                "=========================================");

        boolean correctInput = false;

        while(!correctInput){
            System.out.print("~$ ");
            Scanner scanner = new Scanner(System.in);
            String input [] = scanner.nextLine().split(" ");

            String command = input[0];
            String ip = input[1];
            int port = Integer.parseInt(input[2]);

            if (command.equals("snc") && ip.equals("127.0.0.1")) {
                correctInput=true;
                try {
                    String severIp = "127.0.0.1";
                    Socket socket = new Socket(severIp, port);

                    ClientSender sender = new ClientSender(socket);
                    ClientReceiver receiver = new ClientReceiver(socket);

                    sender.start();
                    receiver.start();
                } catch (ConnectException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("Invalid input value. Please enter again");
            }
        }

    }
}