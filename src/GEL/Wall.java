package GEL;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Wall extends Actor {

    private Image image;

    public Wall(int x, int y, String type) {
        super(x, y);
        if(type.equalsIgnoreCase("wall")) {
            URL loc = this.getClass().getResource("\\res\\wall.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("water")){
            URL loc = this.getClass().getResource("\\res\\water.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("siepeasw")){
            URL loc = this.getClass().getResource("\\res\\siepeasw.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("siepease")){
            URL loc = this.getClass().getResource("\\res\\siepease.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("siepeane")){
            URL loc = this.getClass().getResource("\\res\\siepeane.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("siepeanw")){
            URL loc = this.getClass().getResource("\\res\\siepeanw.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("siepev")){
            URL loc = this.getClass().getResource("\\res\\siepev.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("piantina")){
            URL loc = this.getClass().getResource("\\res\\piantina.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("siepe")){
            URL loc = this.getClass().getResource("\\res\\siepe.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("casa1")){
            URL loc = this.getClass().getResource("\\res\\casa1.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("casa2")){
            URL loc = this.getClass().getResource("\\res\\casa2.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("casa3")){
            URL loc = this.getClass().getResource("\\res\\casa3.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("casa4")){
            URL loc = this.getClass().getResource("\\res\\casa4.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("casa5")){
            URL loc = this.getClass().getResource("\\res\\casa5.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("casa6")){
            URL loc = this.getClass().getResource("\\res\\casa6.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("casa7")){
            URL loc = this.getClass().getResource("\\res\\casa7.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tetto1")){
            URL loc = this.getClass().getResource("\\res\\tetto1.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tetto2")){
            URL loc = this.getClass().getResource("\\res\\tetto2.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tetto3")){
            URL loc = this.getClass().getResource("\\res\\tetto3.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tetto4")){
            URL loc = this.getClass().getResource("\\res\\tetto4.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tetto5")){
            URL loc = this.getClass().getResource("\\res\\tetto5.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tetto6")){
            URL loc = this.getClass().getResource("\\res\\tetto6.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tetto7")){
            URL loc = this.getClass().getResource("\\res\\tetto7.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tetto8")){
            URL loc = this.getClass().getResource("\\res\\tetto8.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tetto9")){
            URL loc = this.getClass().getResource("\\res\\tetto9.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }
    }
}