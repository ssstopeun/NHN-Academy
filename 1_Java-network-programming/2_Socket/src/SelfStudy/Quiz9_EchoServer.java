package SelfStudy;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Quiz9_EchoServer {
    public static void main(String[] args) {
        int port = 1234;

        if(args.length>0){
            try{
                port = Integer.parseInt(args[0]);
            }catch(NumberFormatException ignore){
                System.err.println("Wrong Port");
                System.exit(1);
            }
        }

        try(ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));){

            System.out.println("Client connected");
            String line;
            while((line = reader.readLine())!=null){
                System.out.println(line);
                writer.write(line);
                writer.flush();
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
