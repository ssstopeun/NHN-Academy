package SelfStudy;

import java.io.IOException;
import java.net.Socket;

public class Quiz2_FindConnectablePort {
    public static void main(String[] args) {
        for(int port=0;port<10000;port++){
            try(Socket socket = new Socket("localhost",port)){
                System.out.println("Port ["+port+"] 열려 있습니다.");
            }catch(IOException e){
                System.err.println("Error : "+e);
            }
        }
    }
}