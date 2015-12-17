package GEL;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SiepeASW extends Actor {

    private Image image;

    public SiepeASW(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getResource("\\res\\siepeasw.png");
        ImageIcon iia = new ImageIcon(loc);
        image = iia.getImage();
        this.setImage(image);

    }
}