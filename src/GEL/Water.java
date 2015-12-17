package GEL;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Water extends Actor {

    private Image image;

    public Water(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getResource("\\res\\water.png");
        ImageIcon iia = new ImageIcon(loc);
        image = iia.getImage();
        this.setImage(image);

    }
}