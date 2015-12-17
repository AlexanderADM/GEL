package GEL;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Siepe extends Actor {

    private Image image;

    public Siepe(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getResource("\\res\\siepe.png");
        ImageIcon iia = new ImageIcon(loc);
        image = iia.getImage();
        this.setImage(image);

    }
}