package EchoServer;

import java.io.*;
import java.util.List;

public class EchoServer extends Thread{
    //단순하게 input으로 들어온걸 다시 보내는 것
    String id="";
    InputStream input;
    OutputStream output;
    boolean running = false;

    BufferedReader bufferedInput;
    BufferedOutputStream bufferedOutput;
    List<EchoServer> echoServerList;
    public EchoServer(InputStream input, OutputStream output,List<EchoServer> echoServerList){
        this.bufferedOutput = new BufferedOutputStream(output);
        this.bufferedInput= new BufferedReader(new InputStreamReader(input));
        this.echoServerList=echoServerList;
    }

    public void stop2(){
        running = false;
    }

    public void write(String message){
        try{
            bufferedOutput.write(message.getBytes());
            bufferedOutput.flush();
        }catch(IOException e){
            stop2();
        }
    }

    public String getClientId(){
        return id;
    }

    @Override
    public void run(){


        byte[] buffer = new byte[256];
        running = true;
        while(running){
            try{
                String message = bufferedInput.readLine();
                System.out.println("Receive : "+message);


                    if(message.charAt(0)=='#'&&message.length()>1){
                        String id = message.substring(1,message.length());
                        setClientId(id);
                    }else if(message.charAt(0)=='@'&&message.length()>1){
                        String[] fields = message.split(" ",2);
                        String targetId = fields[0].substring(1,fields[0].length());

                        for(EchoServer echoServer : echoServerList){
                            echoServer.write(fields[1]);
                        }
                    }
                    else if(id.length()>0) {
                        message += "from" + id;
                        System.out.println("Echo : " + message);
                        for(EchoServer echoServer : echoServerList){
                            echoServer.write(message);
                        }
                    }


            }catch(IOException e){
                running = false;
            }

        }
    }

    private void setClientId(String id) {
        if((id==null)&&(id.length()==0)){
            throw new IllegalArgumentException();
        }
        this.id=id;
        System.out.println("Set ID : "+id);
    }
}
