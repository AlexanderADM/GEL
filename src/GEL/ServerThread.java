package GEL;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable{
    private ServerSocket sSock;

    ServerThread(){
        new Thread(this).start();
    }
    
    public void run(){
        try {
            int port = 12345;
            sSock = new ServerSocket(port);
        }catch(IOException e){
            e.printStackTrace();
        }
        while(true) {
            try {
                Socket con = sSock.accept();
                new ClientThread(con);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }  
}
