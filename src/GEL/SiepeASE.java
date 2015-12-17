package GEL;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SiepeASE extends Actor {

    private Image image;

    public SiepeASE(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getResource("\\res\\siepease.png");
        ImageIcon iia = new ImageIcon(loc);
        image = iia.getImage();
        this.setImage(image);

    }
}