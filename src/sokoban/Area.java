package sokoban;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Area extends Actor {

    public Area(int x, int y) {
        super(x, y);

        //URL loc = this.getClass().getResource("/area.png");
        ImageIcon iia = new ImageIcon("D:\\Java\\2015-2016\\NetBeans\\Sokoban\\src\\sokoban\\res\\area.png");
        Image image = iia.getImage();
        this.setImage(image);
    }
}