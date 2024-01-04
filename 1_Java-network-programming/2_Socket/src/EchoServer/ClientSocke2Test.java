package EchoServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class ClientSocke2Test {
    public static void main(String[] args) {
        try( Socket socket = new Socket("localhost",1234)){
            BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());
            System.out.println("Connected");

            output.write("#xtra2\n".getBytes());
            output.flush();

            while(socket.isConnected()){
                String message = "Hello, CNU[" + socket.getLocalPort() + "]\n";
                output.write(message.getBytes());
                output.flush();

                byte[] buffer = new byte[256];
                int length = input.read(buffer,0,buffer.length);
                if(length>0){
                    String echoMessage = new String(Arrays.copyOf(buffer,length));
                    System.out.println("Echo receive : " +echoMessage);
                }
                Thread.sleep(1000);
            }

        }catch(IOException e){
           e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
