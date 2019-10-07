package GEL;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

class Area extends Actor {

    Area(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getResource("\\res\\spawng.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}