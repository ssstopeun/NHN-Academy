package SelfStudy;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Quiz3_HostInformation {
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
            System.err.println("wrong port data");
            System.exit(1);
        }

        try(Socket socket = new Socket(host,port)){
            System.out.println("complete server connection");
            System.out.println("Local address : "+socket.getLocalAddress().getHostAddress());
            System.out.println("Local port :    "+socket.getLocalPort());
            System.out.println("Remote address :"+socket.getInetAddress().getHostAddress());
            System.out.println("Remote port :   "+socket.getPort());
            System.out.println(socket.toString());
        } catch (ConnectException e) {
            System.err.println(host+" : "+port+" fail connection");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
