package SelfStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Quiz4_SendDataAndExit {
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
            System.out.println("Complete Server Connection");
            OutputStream output = socket.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                String line = br.readLine();
                if(line.equals("exit")){
                    break;
                }

                output.write(line.getBytes());
                output.write("\n".getBytes());
                output.flush();
            }

        } catch (UnknownHostException e) {
            System.err.println(host +" : "+port+" connection fail");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
