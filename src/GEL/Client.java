package GEL;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends javax.swing.JFrame {
    Socket con;
    BufferedReader br;
    PrintWriter pw;

    CheckServer r = new CheckServer();
    SendCmd command = new SendCmd();
    Thread check, cmd;
    
    Scanner sc = new Scanner(System.in);
    
    private static boolean completed = false;
    
    
    public Client() {
        initComponents();
        setResizable(false);
        Play.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Guardia = new javax.swing.JRadioButton();
        Ladro = new javax.swing.JRadioButton();
        name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Title = new javax.swing.JLabel();
        Play = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGroup1.add(Guardia);
        Guardia.setText("Guardia");
        Guardia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardiaActionPerformed(evt);
            }
        });

        buttonGroup1.add(Ladro);
        Ladro.setText("Ladro");
        Ladro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LadroActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome");

        Title.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Guardie e Ladri");
        Title.setToolTipText("");

        Play.setText("Gioca");
        Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Guardia)
                        .addGap(10, 10, 10)
                        .addComponent(Ladro))
                    .addComponent(Play, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Guardia)
                    .addComponent(Ladro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Play)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayActionPerformed
        try {
            if (name.getText().length() > 1) {
                if (Play.getText().equals("Esci")) {
                    //check.interrupt();
                    cmd.interrupt();
                    pw.println("exit");
                    pw.flush();
                    con.close();
                    pw.close();
                    br.close();
                    Play.setText("Gioca");
                    Guardia.setEnabled(true);
                    Ladro.setEnabled(true);
                    name.setEnabled(true);

                } else {
                    con = new Socket("127.0.0.1", 12345);
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    pw = new PrintWriter(con.getOutputStream());
                    if (Guardia.isSelected()) {
                        pw.println("guardie");
                    } else if (Ladro.isSelected()) {
                        pw.println("ladri");
                    }
                    pw.flush();
                    String mess = br.readLine();
                    if(mess.equalsIgnoreCase("ok")) {
                        System.err.println("Test");
                        pw.println(name.getText());
                        pw.flush();
                        Play.setText("Esci");
                        Guardia.setEnabled(false);
                        Ladro.setEnabled(false);
                        name.setEnabled(false);
                        //TODO Movement for computer client
                        //addKeyListener(new T2Adapter());
                        //setFocusable(true);
                        //check = new Thread(r);
                        //check.start();
                        cmd = new Thread(command);
                        cmd.start();
                    }
                    else if(mess.equalsIgnoreCase("error")){
                        System.err.println("Error");
                        con.close();
                        pw.close();
                        br.close();
                        Play.setText("Gioca");
                    }
                }
            }
        }
        catch(IOException e){
            System.out.println("" + e + "\n");
        }
    }//GEN-LAST:event_PlayActionPerformed

    private void GuardiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardiaActionPerformed
        Play.setEnabled(true);
    }//GEN-LAST:event_GuardiaActionPerformed

    private void LadroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LadroActionPerformed
        Play.setEnabled(true);
    }//GEN-LAST:event_LadroActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }
    
    public class SendCmd implements Runnable{
        public void run(){
            while(true){
                String s;
                s = sc.nextLine();
                pw.println(s);
                pw.flush();
            }
        }
    }

    public class CheckServer implements Runnable{
        public void run(){            
            int test;
            while(true){
                try{
                    
                    //test = con.getInputStream().read();
                    try {
                        //Thread.sleep(1000);
                        //System.out.println("" + test);
                        if(con.getInputStream().read() == -1){
                            con.close();
                            pw.close();
                            br.close();
                            Play.setText("Gioca");
                            Guardia.setEnabled(true);
                            Ladro.setEnabled(true);
                            name.setEnabled(true);
                            Thread.sleep(100);
                            check.interrupt();
                            Thread.sleep(2000);
                        }
                    } catch (InterruptedException e) {
                    }
                } catch(IOException e){
                }
            }
        }
    }

    public class T2Adapter extends KeyAdapter {

        
        public T2Adapter(){
            //TODO Finish this
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("OK");
            int key = e.getKeyCode();
            
            System.out.println(key);
            if (key == KeyEvent.VK_LEFT) {
                System.out.println("LEFT");
            }
            else if (key == KeyEvent.VK_RIGHT) {
                System.out.println("RIGHT");
            }
            else if (key == KeyEvent.VK_UP) {    
                System.out.println("UP");
            }
            else if (key == KeyEvent.VK_DOWN) {     
                System.out.println("DOWN");
            }
        } 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Guardia;
    private javax.swing.JRadioButton Ladro;
    private javax.swing.JButton Play;
    private javax.swing.JLabel Title;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables
}
