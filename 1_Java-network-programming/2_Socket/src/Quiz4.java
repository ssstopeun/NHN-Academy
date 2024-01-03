import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;


public class Quiz4 {
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
                String input = br.readLine();
                if(input.equals("exit")){
                    return;
                }

                socket.getOutputStream().write(input.getBytes());
                socket.getOutputStream().write("\n".getBytes());
                socket.getOutputStream().flush();

                socket.close();
            }

        } catch(ConnectException e){
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
