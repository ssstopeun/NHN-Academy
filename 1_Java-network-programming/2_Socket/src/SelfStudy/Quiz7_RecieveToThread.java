package SelfStudy;

import java.io.*;
import java.net.Socket;

public class Quiz7_RecieveToThread {
    static class Receiver extends Thread{
        BufferedReader input;
        public Receiver(BufferedReader input){
            this.input = input;
        }

        @Override
        public void run(){
            char[] buffer = new char[2048];

            try{
                while(!Thread.currentThread().isInterrupted()){
                    int inputLength = input.read(buffer);
                    System.out.println(new String(buffer,0,inputLength));
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;

        if(args.length>0){
            host = args[0];
        }

        try {
            if (args.length > 1) {
                port = Integer.parseInt(args[1]);
            }
        }catch(NumberFormatException ignore){
            System.err.println("Wrong Port Number");
            System.exit(1);
        }

        try(Socket socket = new Socket(host,port)){
            System.out.println("Complete connection");

            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));

            String line;

            Receiver receiver = new Receiver(socketIn);

            receiver.start();

              while((line=terminalIn.readLine())!=null){
                if(line.trim().equals("exit")){
                    break;
                }
                socketOut.write(line+"\n");
                socketOut.flush();

            }

        }catch(IOException e){
            System.err.println("Error : "+e);
        }
    }
}
