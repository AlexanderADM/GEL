package GEL;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

class Grass extends Actor {

    Grass(int x, int y, String type) {
        super(x, y);
        Image image;
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
        }else if(type.equalsIgnoreCase("tenda2")){
            URL loc = this.getClass().getResource("\\res\\tenda2.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }
    }
}