package GEL;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

class Player extends Actor {
    long time;
    /**
     * role denotes the player type
     * 0 is a Cop
     * 1 is a Thief
     */
    private int role;
    // Path for the required player texture
    private StringBuilder path;
    // Path in URL format
    private URL pathURL;
    // ImageIcon from the given URL path
    private ImageIcon iia;

    Player(int x, int y, int PID, int role) {
        super(x, y);
        path = new StringBuilder();

        if(role == 0){
            path.append("\\res\\cop").append(PID);
        }else{
            path.append("\\res\\player").append(PID);
        }

        pathURL = this.getClass().getResource(path.toString() +".png");
        iia = new ImageIcon(pathURL);
        this.setImage(iia.getImage());

        long time = (role == 0 ? System.currentTimeMillis() : 0);
    }

    void move(int x, int y, String direction){
        pathURL = this.getClass().getResource(path.toString() + "_" + direction + ".png");
        iia = new ImageIcon(pathURL);
        this.setImage(iia.getImage());
        this.setX(this.x()+x);
        this.setY(this.y()+y);
    }
}
