import java.net.Socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class CheckOpenSocket {
    public static void main(String[] args) {
        for(int i=20;i<=100;i++){
            try{
                Socket socket = new Socket("180.210.81.192",i);
                System.out.println(i+" port open now");
                socket.close();

            }catch (Exception ignore) {
            }
        }

    }
}

