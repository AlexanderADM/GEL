package GEL;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Grass extends Actor {

    private Image image;

    public Grass(int x, int y, String type) {
        super(x, y);
        if(type.equalsIgnoreCase("grass")) {
            URL loc = this.getClass().getResource("\\res\\grass.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if (type.equalsIgnoreCase("pietra")){
            URL loc = this.getClass().getResource("\\res\\pietra.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if (type.equalsIgnoreCase("fiorellino")){
            URL loc = this.getClass().getResource("\\res\\fiorellino.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }
    }
}