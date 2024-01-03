import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class ConnectSocket {
    public static void main(String[] args) {
        try{

            Socket socket = new Socket("180.210.81.192",12345);
            System.out.println("complete server connect.");


            socket.getOutputStream().write("hello".getBytes());

            byte[] buffer = new byte[256];
            int length = socket.getInputStream().read(buffer);
            System.out.println("Receivd : "+length);
            System.out.println("Data : "+ new String(Arrays.copyOf(buffer,5)));


            socket.close();
        } catch(UnknownHostException e){
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}