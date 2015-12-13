package GEL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable{ 

    private final int OFFSET = 30;
    private static final int SPACE = 20;
    private static final int LEFT_COLLISION = 1;
    private static final int RIGHT_COLLISION = 2;
    private static final int TOP_COLLISION = 3;
    private static final int BOTTOM_COLLISION = 4;

    private static ArrayList walls = new ArrayList();
    private static ArrayList baggs = new ArrayList();
    private static ArrayList areas = new ArrayList();
    private static ArrayList thiefs = new ArrayList();
    private static ArrayList cops = new ArrayList();
    public static final int MAXPLAYER = 9;
    private static ArrayList id_thiefs = new ArrayList(MAXPLAYER);
    private static ArrayList id_cops = new ArrayList(MAXPLAYER);
    protected static int PlayerID = 0;
    private static Thief soko;
    private int w = 0;
    private int h = 0;
    private static boolean completed = false;
    
    private String level =
              "##################################\n"
            + "#@    #############    #######   #\n"
            + "#     #############              #\n"
            + "#        ###                     #\n"
            + "#                                #\n"
            + "#     ###   ###  #########  ######\n"
            + "#     #  %    #  #########  #  ..#\n"
            + "#     #  %    #  #########  #  ..#\n"
            + "#     #  %    #          #  #  ..#\n"
            + "#     #       #  ######  #  #    #\n"
            + "#     ###   ###  ######  #  #    #\n"
            + "#                           #  ###\n"
            + "#               ###  ##          #\n"
            + "#     #######   ###  ###         #\n"
            + "#     #######        #########   #\n"
            + "##################################\n";

    public Board() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
        for(int i = 0; i < MAXPLAYER; i++){
            id_thiefs.add(i, true);
            id_cops.add(i, true);
        }
    }

    public int getBoardWidth() {
        return this.w;
    }
    public void checkMovement(){
        for(int i = 0; i < thiefs.size(); i++){
            Thief th = (Thief) thiefs.get(i);
            
        }
    }
    public int getBoardHeight() {
        return this.h;
    }
    public static boolean getThiefCount(){
        return id_thiefs.contains(true);
    }
    public static boolean getCopCount(){
        return id_cops.contains(true);
    }
    public final void initWorld() {
        
        int x = OFFSET;
        int y = OFFSET;
        
        Wall wall;
        Baggage b;
        Area a;
        Cop c;


        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            } else if (item == '#') {
                wall = new Wall(x, y);
                walls.add(wall);
                x += SPACE;
            } else if (item == '$') {
                b = new Baggage(x, y);
                baggs.add(b);
                x += SPACE;
            } else if (item == '.') {
                a = new Area(x, y);
                areas.add(a);
                x += SPACE;
            } else if (item == '@') {
                soko = new Thief(x, y);
                x += SPACE;
            }else if  (item == '%'){
                c = new Cop(x, y);
                cops.add(c);
                x += SPACE;
            }else if (item == ' ') {
                x += SPACE;
            }

            h = y;
        }
    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(walls);
        world.addAll(areas);
        world.addAll(baggs);
        world.addAll(cops);
        world.add(soko);

        for (int i = 0; i < world.size(); i++) {

            Actor item = (Actor) world.get(i);

            if ((item instanceof Thief)
                    || (item instanceof Baggage)) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (completed) {
                return;
            }

            
            int key = e.getKeyCode();


            if (key == KeyEvent.VK_LEFT) {
                if (checkWallCollision(soko, LEFT_COLLISION)) {
                    return;
                }
                if(checkPlayerCollision(LEFT_COLLISION)){
                    return;
                }
                if (checkBagCollision(LEFT_COLLISION)) {
                    return;
                }

                soko.move(-SPACE, 0, "l");

            } else if (key == KeyEvent.VK_RIGHT) {

                if (checkWallCollision(soko, RIGHT_COLLISION)) {
                    return;
                }

                if (checkBagCollision(RIGHT_COLLISION)) {
                    return;
                }

                soko.move(SPACE, 0, "r");

            } else if (key == KeyEvent.VK_UP) {

                if (checkWallCollision(soko, TOP_COLLISION)) {
                    return;
                }

                if (checkBagCollision(TOP_COLLISION)) {
                    return;
                }

                soko.move(0, -SPACE, "u");

            } else if (key == KeyEvent.VK_DOWN) {

                if (checkWallCollision(soko, BOTTOM_COLLISION)) {
                    return;
                }

                if (checkBagCollision(BOTTOM_COLLISION)) {
                    return;
                }

                soko.move(0, SPACE, "d");

            } else if (key == KeyEvent.VK_R) {
                restartLevel();
            }

            repaint();
        }
    }
    public static void movePlayer(int PID, String direction){
        if(direction.equalsIgnoreCase("W")){
            Thief thieves = (Thief) thiefs.get(PID);
            if(checkWallCollision(thieves, TOP_COLLISION)){
                return;
            }
            if(checkBagCollision(TOP_COLLISION)){
                return;
            }
            if(checkPlayerCollision(TOP_COLLISION)){
                return;
            }
            thieves.move(0, -SPACE, "u");
        }
    }
    private static boolean checkPlayerCollision(int type){
        if(type == LEFT_COLLISION){
            for(int i = 0; i < cops.size(); i++){
                Cop cop = (Cop)  cops.get(i);
                if(soko.isLeftCollision(cop)){
                    System.err.println("Collided with cop");
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    private static boolean checkWallCollision(Actor actor, int type) {

        if (type == LEFT_COLLISION) {

            for (int i = 0; i < walls.size(); i++) {
                Wall wall = (Wall) walls.get(i);
                if (actor.isLeftCollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == RIGHT_COLLISION) {

            for (int i = 0; i < walls.size(); i++) {
                Wall wall = (Wall) walls.get(i);
                if (actor.isRightCollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == TOP_COLLISION) {

            for (int i = 0; i < walls.size(); i++) {
                Wall wall = (Wall) walls.get(i);
                if (actor.isTopCollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == BOTTOM_COLLISION) {

            for (int i = 0; i < walls.size(); i++) {
                Wall wall = (Wall) walls.get(i);
                if (actor.isBottomCollision(wall)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private static boolean checkBagCollision(int type) {

        if (type == LEFT_COLLISION) {

            for (int i = 0; i < baggs.size(); i++) {

                Baggage bag = (Baggage) baggs.get(i);
                if (soko.isLeftCollision(bag)) {

                    for (int j=0; j < baggs.size(); j++) {
                        Baggage item = (Baggage) baggs.get(j);
                        if (!bag.equals(item)) {
                            if (bag.isLeftCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag, LEFT_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(-SPACE, 0);
                }
            }
            return false;

        } else if (type == RIGHT_COLLISION) {

            for (int i = 0; i < baggs.size(); i++) {

                Baggage bag = (Baggage) baggs.get(i);
                if (soko.isRightCollision(bag)) {
                    for (int j=0; j < baggs.size(); j++) {

                        Baggage item = (Baggage) baggs.get(j);
                        if (!bag.equals(item)) {
                            if (bag.isRightCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag, RIGHT_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(SPACE, 0);
                }
            }
            return false;

        } else if (type == TOP_COLLISION) {

            for (int i = 0; i < baggs.size(); i++) {

                Baggage bag = (Baggage) baggs.get(i);
                if (soko.isTopCollision(bag)) {
                    for (int j = 0; j < baggs.size(); j++) {

                        Baggage item = (Baggage) baggs.get(j);
                        if (!bag.equals(item)) {
                            if (bag.isTopCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag, TOP_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(0, -SPACE);
                }
            }

            return false;

        } else if (type == BOTTOM_COLLISION) {
        
            for (int i = 0; i < baggs.size(); i++) {

                Baggage bag = (Baggage) baggs.get(i);
                if (soko.isBottomCollision(bag)) {
                    for (int j = 0; j < baggs.size(); j++) {

                        Baggage item = (Baggage) baggs.get(j);
                        if (!bag.equals(item)) {
                            if (bag.isBottomCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag, BOTTOM_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(0, SPACE);
                }
            }
        }

        return false;
    }
    public void run(){
        synchronized(cops){
            checkMovement();
            repaint();
        }
        synchronized(thiefs){
            checkMovement();
            repaint();
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void restartLevel() {

        areas.clear();
        baggs.clear();
        walls.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }
    
    public static int getEmptyID(String team){
        //synchronized(this){
            int ID = 0;
            if(team.equalsIgnoreCase("ladri")){
                while(ID < MAXPLAYER + 1){
                    if(id_thiefs.get(ID).equals(true)){
                        id_thiefs.set(ID, false);
                        break;
                    }
                    ID++;
                }
            }
            else if(team.equalsIgnoreCase("guardie")){
                while(ID < 10){
                    if(id_cops.get(ID).equals(true)){
                        id_thiefs.set(ID, false);
                        break;
                    }
                    ID++;
                }
            }
            return ID; //Se torna "9" allora non ci sono posti disponibili
        //}
    }
}