package GEL;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SiepeANE extends Actor {

    private Image image;

    public SiepeANE(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getResource("\\res\\siepeane.png");
        ImageIcon iia = new ImageIcon(loc);
        image = iia.getImage();
        this.setImage(image);

    }
}