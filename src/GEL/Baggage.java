package GEL;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

class Baggage extends Actor {

    Baggage(int x, int y) {
        super(x, y);
        URL loc = this.getClass().getResource("\\res\\baggage.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    void move(int x, int y) {
        this.setX(this.x()+x);
        this.setY(this.y()+y);
    }
}