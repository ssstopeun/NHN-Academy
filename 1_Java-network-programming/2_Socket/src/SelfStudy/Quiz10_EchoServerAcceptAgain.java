package SelfStudy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Quiz10_EchoServerAcceptAgain {
    static void connectService(ServerSocket serverSocket){
        try(Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {

            System.out.println(socket.getInetAddress().getHostAddress()+" : "+socket.getPort()+" client connected");
            String line;
            while((line=reader.readLine())!=null){
                writer.write(line);
                writer.flush();
            }

        } catch (IOException ignore) {
            System.out.println("client가 접속을 종료했습니다.");
        }
    }
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

        try(ServerSocket serverSocket = new ServerSocket(port)){
            while(!Thread.currentThread().isInterrupted()){
                Quiz10_EchoServerAcceptAgain.connectService(serverSocket);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
