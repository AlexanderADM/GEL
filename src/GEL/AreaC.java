package GEL;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AreaC extends Actor {

    public AreaC(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getResource("\\res\\area.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}