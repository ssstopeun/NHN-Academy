package SelfStudy.Quiz11;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class DataReceiver implements Runnable{
    Thread thread;
    Socket socket;
    static ArrayList<DataReceiver> clients = new ArrayList<>();
    public DataReceiver(Socket socket) {
        this.thread = new Thread(this);
        this.socket=socket;

        clients.add(this);
    }

    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            System.out.println(socket.getInetAddress().getHostAddress()+" : "+socket.getPort()+" client connected");
            String line;

            while(socket.isConnected()){
                while((line=reader.readLine())!=null){
                    for(DataReceiver client : clients){
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.socket.getOutputStream()));
                        writer.write(line);
                        writer.flush();
                    }
                }

            }


        } catch (IOException ignore) {
            System.out.println("client가 접속을 종료했습니다.");
        }
    }
    public void start() {
        thread.start();
    }
}