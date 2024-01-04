package Homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Arrays;


public class Quiz5 {
    static String address = "180.210.81.192";
    static int port = 12345;
    public static void main(String[] args) throws IOException {

        if(args.length>0){
            address = args[0];
        }
        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            Socket socket = new Socket(address,port);
            System.out.println("connection complete");

            while(true){
                byte[] buffer = new byte[256];
                int length = socket.getInputStream().read(buffer);
                System.out.println("Received : "+length);
                String data = new String(Arrays.copyOf(buffer,length));

                if(data.equals("exit")){
                    return;
                }

                System.out.println(data);

                socket.close();
            }

        } catch(ConnectException e){
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
