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
            System.err.println("Connection from: " + s);
            System.err.println("Connection established - initialized I/O methods");
            System.err.println("Waiting for client to select squad.");
            System.err.println("Ladri squad has " + Board.thiefs.size() + " players");
            System.err.println("Cops squad has  " + Board.cops.size()   + " players");
            System.out.println("" + System.currentTimeMillis());
            squad = br.readLine();            
            
            if(squad.equalsIgnoreCase("ladri") && Board.getThiefCount() == true){
                PID = Board.getEmptyID(squad);
                if(PID == 9){
                    System.err.println("Client Thread from: " + s);
                    System.err.println("Maximum players reached, stopping thread and closing connection");
                    pw.println("error");
                    pw.flush();
                    System.err.println("Sent error code to client, interrupting thread");
                    Thread.sleep(50);
                    Thread.currentThread().interrupt();
                    Thread.sleep(2000);
                }
                else{
                    System.err.println("Client Thread from + " + s);
                    System.err.println("Maximum player NOT reached, Thread accepted");
                    pw.println("ok");
                    pw.flush();
                }
            }else if(squad.equalsIgnoreCase("guardie") && Board.getCopCount() == true){
                PID = Board.getEmptyID(squad);

                if(PID == 9){
                    System.err.println("Client Thread from + " + s);
                    System.err.println("Maximum players reached, stopping thread and closing connection");
                    pw.println("error");
                    pw.flush();
                    System.err.println("Sent error code to client, interrupting thread");
                    Thread.sleep(50);
                    Thread.currentThread().interrupt();
                    Thread.sleep(2000);
                }
                else{
                    System.err.println("Client Thread from + " + s);
                    System.err.println("Maximum player NOT reached, Thread accepted");
                    pw.println("ok");
                    pw.flush();
                }
            }else{
                System.err.println("General Error");
                pw.println("error");
                pw.flush();
                Thread.sleep(50);
                Thread.currentThread().interrupt();
                Thread.sleep(2000);
            }

            System.err.println("Client selected squad : " + squad);
            System.err.println("Waiting for client username: ");
            
            name = br.readLine();
            Board.randomSpawn(PID, squad);
            long time = System.currentTimeMillis();
            while(true){
                cmd = br.readLine();
                
                if(cmd == null || cmd.equalsIgnoreCase("exit")){
                    Board.releaseID(PID,squad);
                    pw.close();
                    br.close();
                    s.close();
                    Thread.currentThread().interrupt();
                    Thread.sleep(2000);
                }               
                while(System.currentTimeMillis() - 10 > time){
                    if(!Board.movePlayer(PID, squad, cmd)){
                        pw.println("disconnect");
                        pw.flush();
                        pw.close();
                        br.close();
                        s.close();
                        break;
                    }
                    time = System.currentTimeMillis();
                }
            }
        }catch(IOException e){
            System.err.println(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
