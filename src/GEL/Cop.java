package GEL;

import java.awt.Image;
import java.net.URL;
import java.util.Date;
import javax.swing.ImageIcon;

public class Cop extends Actor {
    long time = 0;
    public Cop(int x, int y, int PID) {
        super(x, y);
        URL loc = this.getClass().getResource("\\res\\cop" + PID + ".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
        time = Long.valueOf(System.currentTimeMillis());
    }

    public void move(int x, int y, String cmd, int PID) {
        if(cmd.equals("l")){
            URL loc = this.getClass().getResource("\\res\\cop" + PID + "_l.png");
            ImageIcon iia = new ImageIcon(loc);
            Image image = iia.getImage();
            this.setImage(image);
        }
        else if(cmd.equals("r")){
            URL loc = this.getClass().getResource("\\res\\cop" + PID + "_r.png");
            ImageIcon iia = new ImageIcon(loc);
            Image image = iia.getImage();
            this.setImage(image);
        }
        else if(cmd.equals("u")){
            URL loc = this.getClass().getResource("\\res\\cop" + PID + "_u.png");
            ImageIcon iia = new ImageIcon(loc);
            Image image = iia.getImage();
            this.setImage(image);
        }
        else if(cmd.equals("d")){
            URL loc = this.getClass().getResource("\\res\\cop" + PID + "_d.png");
            ImageIcon iia = new ImageIcon(loc);
            Image image = iia.getImage();
            this.setImage(image);
        }
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }
}