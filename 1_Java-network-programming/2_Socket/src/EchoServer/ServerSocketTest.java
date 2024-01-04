package EchoServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ServerSocketTest {
    static int port = 1234;
    public static void main(String[] args) throws IOException, InterruptedException {
        List<EchoServer> echoServerList= new LinkedList<>();
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server socket created");


            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("client connected : " +
                        socket.getInetAddress() + ":" +
                        socket.getPort());
                EchoServer echoServer = new EchoServer(socket.getInputStream(), socket.getOutputStream(),echoServerList);
                echoServer.start();

                echoServerList.add(echoServer);
            }

            for(EchoServer echoServer : echoServerList){
                echoServer.stop2();
            }
        }catch(IOException e){
            System.err.println(e);
        }
    }
}