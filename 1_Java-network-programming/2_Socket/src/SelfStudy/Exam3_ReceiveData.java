package SelfStudy;

import java.io.IOException;
import java.net.Socket;

public class Exam3_ReceiveData {
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

            int ch;
            while((ch=socket.getInputStream().read())>=0){
                System.out.println(ch);
            }

        }catch(IOException e){
            System.err.println("Error : "+e);
        }
    }
}
