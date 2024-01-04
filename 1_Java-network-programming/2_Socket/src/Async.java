import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedList;

public class Async {
    static class Receiver extends Thread{
        BufferedInputStream input;
        boolean running = false;

        public Receiver(BufferedInputStream input){
            this.input=input;
        }

        public void stop2(){
            this.running = false;
        }

        @Override
        public void run(){
            byte[] buffer = new byte[256];
            running = true;


            while(running){
                try{
                    System.out.println("wait message");
                    int length = input.read(buffer,0,buffer.length);
                    System.out.println(new String(Arrays.copyOf(buffer,length)));
                }catch(IOException e){
                    System.err.println("Error : "+e.getMessage());
                    stop2();
                }
            }
        }
    }
    static LinkedList<String> messageList = new LinkedList<>();
    public static void main(String[] args) throws InterruptedException {
        try{
            Socket socket = new Socket("180.210.81.192",12345);
            BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());

//            Receiver receiver = new Receiver(input);
//            receiver.start();
            Thread reciever = new Thread(() -> {
                byte[] buffer = new byte[256];
                boolean running = true;

                while(running){
                    try{
                        int length = input.read(buffer,0,buffer.length);
                        String message = new String(Arrays.copyOf(buffer,length));
                        messageList.add(message);
                        System.out.println(message);

                    }catch(IOException e){
                        System.err.println("Error : " + e.getMessage());
                        running = false;
                    }

                }
            });
            reciever.start();

            Thread sender = new Thread(()->{
                for(int i=0;i<5;i++){
                    String message = "Hello ["+i+"]";
                    System.out.println("Send : "+message);
                    try{
                        output.write(message.getBytes());
                        output.flush();
                        Thread.sleep(1000);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

           sender.start();

        }catch(UnknownHostException e){
            System.err.println("Unknown Host impormation");
        }catch(IOException e){
            System.err.println("I/O error");
        }
    }
}
