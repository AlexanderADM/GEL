package GEL;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Piantina extends Actor {

    private Image image;

    public Piantina(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getResource("\\res\\piantina.png");
        ImageIcon iia = new ImageIcon(loc);
        image = iia.getImage();
        this.setImage(image);

    }
}