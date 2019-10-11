package GEL;

import javax.swing.*;
import java.net.URL;

class Texture extends Actor{

    Texture(int x, int y,String type){
        super(x, y);
        URL pathURL = this.getClass().getResource("\\res\\" + type + ".png");
        ImageIcon imageIcon = new ImageIcon(pathURL);
        this.setImage(imageIcon.getImage());
    }
}
