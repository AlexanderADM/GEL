package sokoban;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Wall extends Actor {

    private Image image;

    public Wall(int x, int y) {
        super(x, y);

        //URL loc = this.getClass().getResource("wall.png");
        //System.err.println(this.getClass().getResource("/res/wall.png"));
        ImageIcon iia = new ImageIcon("C:\\Users\\Adrian\\Desktop\\Java Projects\\Sokoban\\src\\sokoban\\res\\wall.png");
        image = iia.getImage();
        this.setImage(image);

    }
}