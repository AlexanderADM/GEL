package GEL;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SiepeANW extends Actor {

    private Image image;

    public SiepeANW(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getResource("\\res\\siepeanw.png");
        ImageIcon iia = new ImageIcon(loc);
        image = iia.getImage();
        this.setImage(image);

    }
}