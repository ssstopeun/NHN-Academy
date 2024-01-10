package SelfStudy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Exam2_SendData {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;

        if(args.length>0){
            host = args[0];
        }

        try{
            if(args.length>1){
                port = Integer.parseInt(args[1]);
            }
        }catch(NumberFormatException ignore){
            System.err.println("wrong port");
            System.exit(1);
        }

        try(Socket socket = new Socket(host,port)){
            socket.getOutputStream().write("Hello World!".getBytes());

        } catch (UnknownHostException e) {
            System.err.println(host +" : "+port+" connection fail");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
