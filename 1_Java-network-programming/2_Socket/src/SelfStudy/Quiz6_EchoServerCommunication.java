package SelfStudy;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Quiz6_EchoServerCommunication {
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
            BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));

            String line;

            while((line=terminalIn.readLine())!=null){
                socketOut.write(line+"\n");
                socketOut.flush();

                line = socketIn.readLine();
                terminalOut.write(line+"\n");
                terminalOut.flush();
            }

        }catch(IOException e){
            System.err.println("Error : "+e);
        }
    }
}
