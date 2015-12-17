package GEL;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SiepeVerticale extends Actor {

    private Image image;

    public SiepeVerticale(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getResource("\\res\\siepev.png");
        ImageIcon iia = new ImageIcon(loc);
        image = iia.getImage();
        this.setImage(image);

    }
}