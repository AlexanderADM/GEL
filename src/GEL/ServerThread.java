package GEL;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable{
    private ServerSocket sSock;
    private Socket con;
    private int port = 8080;
    
    public ServerThread(){
        new Thread(this).start();
    }
    
    public void run(){
        while(true) {
            try {
                sSock = new ServerSocket(port);
                con = sSock.accept();
                new ClientThread(con);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }  
}
