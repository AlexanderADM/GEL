package GEL;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Thief extends Actor {

    public Thief(int x, int y, int PID) {
        super(x, y);

        URL loc = this.getClass().getResource("\\res\\player" + PID + ".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    void move(int x, int y, String cmd, int PID) {
        switch (cmd) {
            case "l": {
                URL loc = this.getClass().getResource("\\res\\player" + PID + "_l.png");
                ImageIcon iia = new ImageIcon(loc);
                Image image = iia.getImage();
                this.setImage(image);
                break;
            }
            case "r": {
                URL loc = this.getClass().getResource("\\res\\player" + PID + "_r.png");
                ImageIcon iia = new ImageIcon(loc);
                Image image = iia.getImage();
                this.setImage(image);
                break;
            }
            case "u": {
                URL loc = this.getClass().getResource("\\res\\player" + PID + "_u.png");
                ImageIcon iia = new ImageIcon(loc);
                Image image = iia.getImage();
                this.setImage(image);
                break;
            }
            case "d": {
                URL loc = this.getClass().getResource("\\res\\player" + PID + "_d.png");
                ImageIcon iia = new ImageIcon(loc);
                Image image = iia.getImage();
                this.setImage(image);
                break;
            }
        }
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }
}