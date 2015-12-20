package GEL;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Tenda extends Actor {

    private Image image;

    public Tenda(int x, int y, String type) {
        super(x, y);
        if(type.equalsIgnoreCase("tenda4")){
            URL loc = this.getClass().getResource("\\res\\tenda4.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tenda5")){
            URL loc = this.getClass().getResource("\\res\\tenda5.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tenda6")){
            URL loc = this.getClass().getResource("\\res\\tenda6.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tenda7")){
            URL loc = this.getClass().getResource("\\res\\tenda7.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tenda8")){
            URL loc = this.getClass().getResource("\\res\\tenda8.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tenda9")){
            URL loc = this.getClass().getResource("\\res\\tenda9.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tenda0")){
            URL loc = this.getClass().getResource("\\res\\tenda0.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tenda11")){
            URL loc = this.getClass().getResource("\\res\\tenda11.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tenda12")){
            URL loc = this.getClass().getResource("\\res\\tenda12.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tenda13")){
            URL loc = this.getClass().getResource("\\res\\tenda13.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tenda14")){
            URL loc = this.getClass().getResource("\\res\\tenda14.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tenda15")){
            URL loc = this.getClass().getResource("\\res\\tenda15.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tendina1")){
            URL loc = this.getClass().getResource("\\res\\tendina1.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tendina2")){
            URL loc = this.getClass().getResource("\\res\\tendina2.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tendina3")){
            URL loc = this.getClass().getResource("\\res\\tendina3.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tendina4")){
            URL loc = this.getClass().getResource("\\res\\tendina4.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tendina5")){
            URL loc = this.getClass().getResource("\\res\\tendina5.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tendina6")){
            URL loc = this.getClass().getResource("\\res\\tendina6.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }
    }
}
