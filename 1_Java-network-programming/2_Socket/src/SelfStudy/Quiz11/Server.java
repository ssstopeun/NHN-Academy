package SelfStudy.Quiz11;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
    int port;

    public Server(int port){
        this.port = port;
    }

    @Override
    public void run(){
        try(ServerSocket serverSocket = new ServerSocket(port)){

            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();

                DataReceiver connectServer = new DataReceiver(socket);
                connectServer.start();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Server(1234));

        thread.start();
        thread.join();
    }
}
