package SelfStudy;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class Quiz5_ReceiveDataAndExit {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;

        if(args.length>0){
            host = args[0];
        }

        try {
            if (args.length > 1) {
                port = Integer.parseInt(args[1]);
            }
        }catch(NumberFormatException ignore){
            System.err.println("Wrong Port Number");
            System.exit(1);
        }

        try(Socket socket = new Socket(host,port)){
            System.out.println("Complete connection");

            BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
            int readLength;
            byte[] buffer = new byte[2048];

            while((readLength = input.read(buffer))>0){

                String message = new String(Arrays.copyOf(buffer,readLength));

                if(message.trim().equals("exit")){
                    break;
                }

                System.out.println(message);
            }
        }catch(IOException e){
            System.err.println("Error : "+e);
        }
    }
}
