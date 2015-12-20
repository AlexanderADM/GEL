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
        }else if(type.equalsIgnoreCase("cb1")){
            URL loc = this.getClass().getResource("\\res\\cb1.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("cb2")){
            URL loc = this.getClass().getResource("\\res\\cb2.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("cb3")){
            URL loc = this.getClass().getResource("\\res\\cb3.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("cb4")){
            URL loc = this.getClass().getResource("\\res\\cb4.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("cb5")){
            URL loc = this.getClass().getResource("\\res\\cb5.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("cb6")){
            URL loc = this.getClass().getResource("\\res\\cb6.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("cb7")){
            URL loc = this.getClass().getResource("\\res\\cb7.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("cb8")){
            URL loc = this.getClass().getResource("\\res\\cb8.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("cb9")){
            URL loc = this.getClass().getResource("\\res\\cb9.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("angnw")){
            URL loc = this.getClass().getResource("\\res\\angnw.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("angne")){
            URL loc = this.getClass().getResource("\\res\\angne.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("angsw")){
            URL loc = this.getClass().getResource("\\res\\angsw.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("angse")){
            URL loc = this.getClass().getResource("\\res\\angse.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("ws")){
            URL loc = this.getClass().getResource("\\res\\ws.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("wt")){
            URL loc = this.getClass().getResource("\\res\\wt.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("wd")){
            URL loc = this.getClass().getResource("\\res\\wd.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("wst")){
            URL loc = this.getClass().getResource("\\res\\wst.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("porta")){
            URL loc = this.getClass().getResource("\\res\\porta.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tenda1")){
            URL loc = this.getClass().getResource("\\res\\tenda1.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("tenda3")){
            URL loc = this.getClass().getResource("\\res\\tenda3.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala1")){
            URL loc = this.getClass().getResource("\\res\\pala1.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala2")){
            URL loc = this.getClass().getResource("\\res\\pala2.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala3")){
            URL loc = this.getClass().getResource("\\res\\pala3.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala4")){
            URL loc = this.getClass().getResource("\\res\\pala4.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala5")){
            URL loc = this.getClass().getResource("\\res\\pala5.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala6")){
            URL loc = this.getClass().getResource("\\res\\pala6.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala7")){
            URL loc = this.getClass().getResource("\\res\\pala7.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala8")){
            URL loc = this.getClass().getResource("\\res\\pala8.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala9")){
            URL loc = this.getClass().getResource("\\res\\pala9.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala10")){
            URL loc = this.getClass().getResource("\\res\\pala10.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala11")){
            URL loc = this.getClass().getResource("\\res\\pala11.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala12")){
            URL loc = this.getClass().getResource("\\res\\pala12.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala13")){
            URL loc = this.getClass().getResource("\\res\\pala13.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala14")){
            URL loc = this.getClass().getResource("\\res\\pala14.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala15")){
            URL loc = this.getClass().getResource("\\res\\pala15.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala16")){
            URL loc = this.getClass().getResource("\\res\\pala16.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala17")){
            URL loc = this.getClass().getResource("\\res\\pala17.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala18")){
            URL loc = this.getClass().getResource("\\res\\pala18.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala19")){
            URL loc = this.getClass().getResource("\\res\\pala19.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala20")){
            URL loc = this.getClass().getResource("\\res\\pala20.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala21")){
            URL loc = this.getClass().getResource("\\res\\pala21.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala22")){
            URL loc = this.getClass().getResource("\\res\\pala22.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala23")){
            URL loc = this.getClass().getResource("\\res\\pala23.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala24")){
            URL loc = this.getClass().getResource("\\res\\pala24.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }else if(type.equalsIgnoreCase("pala25")){
            URL loc = this.getClass().getResource("\\res\\pala25.png");
            ImageIcon iia = new ImageIcon(loc);
            image = iia.getImage();
            this.setImage(image);
        }
    }
}