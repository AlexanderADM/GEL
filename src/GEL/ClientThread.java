package GEL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable{
    //Declaring variable for the connection.
    BufferedReader br;
    PrintWriter pw;
    Socket s;
    String squad,name,cmd;
    int PID; // Unique ID per player
    // Starting Client Thread
    public ClientThread(Socket conn){
        s = conn;
        new Thread(this).start();
    }
    // Starting the thread and executing startup sequences
    // Initializing input/output streams
    // Getting client squad
    // Getting client ID
    // Getting client Username
    public void run(){
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw = new PrintWriter(s.getOutputStream());
            System.err.println("Connection from : " + s);
            System.err.println("Connection established - initialized I/O methods");
            System.err.println("Waiting for client to select squad.");

            squad = br.readLine();
            if(squad.equalsIgnoreCase("ladri") && Board.getThiefCount() == true){
                PID = Board.getEmptyID(squad);
                if(PID == 9){
                    pw.println("error");
                    pw.flush();
                    Thread.currentThread().interrupt();
                }
                else{
                    pw.println("ok");
                    pw.flush();
                }
            }else if(squad.equalsIgnoreCase("guardie") && Board.getCopCount() == true){
                PID = Board.getEmptyID(squad);
                if(PID == 9){
                    pw.println("error");
                    pw.flush();
                    Thread.currentThread().interrupt();
                }
                else{
                    pw.println("ok");
                    pw.flush();
                }
                pw.println("error");
                pw.flush();
            }

            System.err.println("Client selected squad : " + squad);
            System.err.println("Waiting for client username: ");
            do{
                name = br.readLine();
            }while(name != null || name.length() < 2);

            //TODO Client Spawn Randomly

            while(true){
                System.err.println("Waiting client move");
                cmd = br.readLine();
                if(cmd == null){
                    //TODO Disconnect Method
                    break;
                }
                Board.movePlayer(PID, cmd);
                System.err.println("Client entered: " + cmd);
            }


        }catch(IOException e){
            System.err.println(e);
        }
    }
}
