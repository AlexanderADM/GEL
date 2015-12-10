package sokoban;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Cop extends Actor {

    public Cop(int x, int y) {
        super(x, y);
        URL loc = this.getClass().getResource("\\res\\cop.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void move(int x, int y, String cmd) {
        if(cmd.equals("l")){
            URL loc = this.getClass().getResource("\\res\\cop_l.png");
            ImageIcon iia = new ImageIcon(loc);
            Image image = iia.getImage();
            this.setImage(image);
        }
        else if(cmd.equals("r")){
            URL loc = this.getClass().getResource("\\res\\cop_r.png");
            ImageIcon iia = new ImageIcon(loc);
            Image image = iia.getImage();
            this.setImage(image);
        }
        else if(cmd.equals("u")){
            URL loc = this.getClass().getResource("\\res\\cop_u.png");
            ImageIcon iia = new ImageIcon(loc);
            Image image = iia.getImage();
            this.setImage(image);
        }
        else if(cmd.equals("d")){
            URL loc = this.getClass().getResource("\\res\\cop_d.png");
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