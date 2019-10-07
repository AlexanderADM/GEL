package GEL;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

class AreaC extends Actor {

    AreaC(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getResource("\\res\\spawnl.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}