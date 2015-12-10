package sokoban;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Thief extends Actor {

    public Thief(int x, int y) {
        super(x, y);

        //URL loc = this.getClass().getResource("/sokoban.png");
        ImageIcon iia = new ImageIcon("D:\\Java\\2015-2016\\NetBeans\\Sokoban\\src\\sokoban\\res\\player.png");
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void move(int x, int y, String cmd) {
        if(cmd.equals("l")){
            ImageIcon iia = new ImageIcon("D:\\Java\\2015-2016\\NetBeans\\Sokoban\\src\\sokoban\\res\\player_l.png");
            Image image = iia.getImage();
            this.setImage(image);
        }
        else if(cmd.equals("r")){
            ImageIcon iia = new ImageIcon("D:\\Java\\2015-2016\\NetBeans\\Sokoban\\src\\sokoban\\res\\player_r.png");
            Image image = iia.getImage();
            this.setImage(image);
        }
        else if(cmd.equals("u")){
            ImageIcon iia = new ImageIcon("D:\\Java\\2015-2016\\NetBeans\\Sokoban\\src\\sokoban\\res\\player_u.png");
            Image image = iia.getImage();
            this.setImage(image);
        }
        else if(cmd.equals("d")){
            ImageIcon iia = new ImageIcon("D:\\Java\\2015-2016\\NetBeans\\Sokoban\\src\\sokoban\\res\\player_d.png");
            Image image = iia.getImage();
            this.setImage(image);
        }
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }
}