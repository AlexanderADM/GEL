package GEL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
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
    private static Random ran = new Random();
    private static ArrayList walls = new ArrayList();
    private static ArrayList baggs = new ArrayList();
    private static ArrayList areas = new ArrayList();
    private static ArrayList areac = new ArrayList();
    protected static ArrayList thiefs = new ArrayList();
    protected static ArrayList cops = new ArrayList();
    public static final int MAXPLAYER = 9;
    private static ArrayList id_thiefs = new ArrayList();
    private static ArrayList id_cops = new ArrayList();
    private static Thief soko;
    private int w = 0;
    private int h = 0;
    private static boolean completed = false;
    
    private static String level =
              "##################################\n"
            + "#£££££#############    #######   #\n"
            + "#£££££#############              #\n"
            + "#£££££   ###                     #\n"
            + "#£££££                $          #\n"
            + "#£££££####$####  #### ####  ######\n"
            + "#£££££#  %    #  #  @    #  #  ..#\n"
            + "#£££££#  %    $  #########  #  ..#\n"
            + "#£££££#  %    #          #  #  ..#\n"
            + "#£££££$       #  ######  #  #    #\n"
            + "#£££££####$####  ######  #  #    #\n"
            + "#£££££                      #  ###\n"
            + "#£££££          ###  ##          #\n"
            + "#£££££#######   ###  ###         #\n"
            + "#£££££#######       #########    #\n"
            + "##################################\n";

    public Board() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
        new Thread(this).start();
        for(int i = 0; i < MAXPLAYER; i++){
            id_thiefs.add(i, true);
            id_cops.add(i, true);
        }
    }

    public int getBoardWidth() {
        return this.w;
    }
    public int getBoardHeight() {
        return this.h;
    }
    synchronized static boolean getThiefCount(){
        return id_thiefs.contains(true);
    }
    synchronized static boolean getCopCount(){
        return id_cops.contains(true);
    }
    public final void initWorld() {
        
        int x = OFFSET;
        int y = OFFSET;
        
        Wall wall;
        Baggage b;
        Area a;
        AreaC e;
        Cop c;
        Thief d;


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
            } else if(item == '£'){
                e = new AreaC(x, y);
                areac.add(e);
                x += SPACE;
            } else if (item == '@') {
                soko = new Thief(x, y);
                d = new Thief(x, y);
                thiefs.add(d);
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
        boolean chk;

        ArrayList world = new ArrayList();
        world.addAll(areas);
        world.addAll(areac);
        world.addAll(baggs);
        
        int c = 0;
        while(true){
            try{
                cops.get(c);
                try{
                    chk = (boolean)cops.get(c);
                }
                catch(ClassCastException e){
                    chk = true;
                }
                if(chk == false){
                } else{
                    world.add((Cop)cops.get(c));
                }
            }
            catch(IndexOutOfBoundsException e){
                break;
            }
            c++;
        }
        
        int t = 0;
        while(true){
            try{
                thiefs.get(t);
                try{
                    chk = (boolean)thiefs.get(t);
                }
                catch(ClassCastException e){
                    chk = true;
                }
                if(chk == false){
                } else{
                    world.add((Thief)thiefs.get(t));
                }
            }
            catch(IndexOutOfBoundsException e){
                break;
            }
            t++;
        }
        
        world.add(soko);
        world.addAll(walls);

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
    synchronized static void movePlayer(int PID,String team, String direction){
        if(team.equalsIgnoreCase("ladri")) {
            if (direction.equalsIgnoreCase("W")) {
                Thief thieves = (Thief) thiefs.get(PID);
                if (checkWallCollision(thieves, TOP_COLLISION)) {
                    return;
                }
                if (checkBagCollision(TOP_COLLISION)) {
                    return;
                }
                if (checkPlayerCollision(PID, team, TOP_COLLISION)) {
                    return;
                }
                thieves.move(0, -SPACE, "u");
            } else if (direction.equalsIgnoreCase("S")) {
                Thief thieves = (Thief) thiefs.get(PID);
                if (checkWallCollision(thieves, BOTTOM_COLLISION)) {
                    return;
                }
                if (checkBagCollision(BOTTOM_COLLISION)) {
                    return;
                }
                if (checkPlayerCollision(PID, team, BOTTOM_COLLISION)) {
                    return;
                }
                thieves.move(0, SPACE, "d");
            } else if (direction.equalsIgnoreCase("A")) {
                Thief thieves = (Thief) thiefs.get(PID);
                if (checkWallCollision(thieves, LEFT_COLLISION)) {
                    return;
                }
                if (checkBagCollision(LEFT_COLLISION)) {
                    return;
                }
                if (checkPlayerCollision(PID, team, LEFT_COLLISION)) {
                    return;
                }
                thieves.move(-SPACE, 0, "l");
            } else if (direction.equalsIgnoreCase("D")) {
                Thief thieves = (Thief) thiefs.get(PID);
                if (checkWallCollision(thieves, RIGHT_COLLISION)) {
                    return;
                }
                if (checkBagCollision(RIGHT_COLLISION)) {
                    return;
                }
                if (checkPlayerCollision(PID, team, RIGHT_COLLISION)) {
                    return;
                }
                thieves.move(SPACE, 0, "r");
            }
            isSafe(PID);
        }else if(team.equalsIgnoreCase("guardie")){
            if (direction.equalsIgnoreCase("W")) {
                Cop cp = (Cop) cops.get(PID);
                if (checkWallCollision(cp, TOP_COLLISION)) {
                    return;
                }
                if (checkBagCollision(TOP_COLLISION)) {
                    return;
                }
                if (checkPlayerCollision(PID, team, TOP_COLLISION)) {
                    return;
                }
                cp.move(0, -SPACE, "u");
            } else if (direction.equalsIgnoreCase("S")) {
                Cop cp = (Cop) cops.get(PID);
                if (checkWallCollision(cp, BOTTOM_COLLISION)) {
                    return;
                }
                if (checkBagCollision(BOTTOM_COLLISION)) {
                    return;
                }
                if (checkPlayerCollision(PID, team, BOTTOM_COLLISION)) {
                    return;
                }
                cp.move(0, SPACE, "d");
            } else if (direction.equalsIgnoreCase("A")) {
                Cop cp = (Cop) cops.get(PID);
                if (checkWallCollision(cp, LEFT_COLLISION)) {
                    return;
                }
                if (checkBagCollision(LEFT_COLLISION)) {
                    return;
                }
                if (checkPlayerCollision(PID, team, LEFT_COLLISION)) {
                    return;
                }
                cp.move(-SPACE, 0, "l");
            } else if (direction.equalsIgnoreCase("D")) {
                Cop cp = (Cop) cops.get(PID);
                if (checkWallCollision(cp, RIGHT_COLLISION)) {
                    return;
                }
                if (checkBagCollision(RIGHT_COLLISION)) {
                    return;
                }
                if (checkPlayerCollision(PID, team, RIGHT_COLLISION)) {
                    return;
                }
                cp.move(SPACE, 0, "r");
            }
        }
    }
    private static boolean isSafe(int PID){
        Thief th = (Thief) thiefs.get(PID);
        Area zone;
        for (int i = 0; i < areas.size(); i++) {
            zone = (Area) areas.get(i);
            if(zone.x() == th.x() && zone.y() == th.y()){
                System.err.println("Thief ID: " + PID + " is in the safe zone.");
                releaseID(PID, "ladri");
                return true;
            }
        }
        return false;
    }
    private static boolean checkPlayerCollision(int PID, String team, int type){
        if(team.equalsIgnoreCase("ladri")) {
            Thief th = (Thief) thiefs.get(PID);
            if (type == LEFT_COLLISION) {
                for (int i = 0; i < cops.size(); i++) {
                    Cop cop = (Cop) cops.get(i);
                    if (th.isLeftCollision(cop)) {
                        System.err.println("ID: " + PID + " collided with cop");
                        releaseID(PID, team);
                        return true;
                    }
                }
                return false;
            } else if (type == RIGHT_COLLISION) {
                for (int i = 0; i < cops.size(); i++) {
                    Cop cop = (Cop) cops.get(i);
                    if (th.isRightCollision(cop)) {
                        System.err.println("ID: " + PID + " collided with cop");
                        releaseID(PID, team);
                        return true;
                    }
                }
            } else if (type == TOP_COLLISION) {
                for (int i = 0; i < cops.size(); i++) {
                    Cop cop = (Cop) cops.get(i);
                    if (th.isTopCollision(cop)) {
                        System.err.println("ID: " + PID + " collided with cop");
                        releaseID(PID, team);
                        return true;
                    }
                }
            } else if (type == BOTTOM_COLLISION) {
                for (int i = 0; i < cops.size(); i++) {
                    Cop cop = (Cop) cops.get(i);
                    if (th.isBottomCollision(cop)) {
                        System.err.println("ID: " + PID + " collided with cop");
                        releaseID(PID, team);
                        return true;
                    }
                }
            }
        }else if(team.equalsIgnoreCase("guardie")){
            Cop cp = (Cop) cops.get(PID);
            if(type == BOTTOM_COLLISION){
                for (int i = 0; i < thiefs.size(); i++) {
                    Thief th = (Thief) thiefs.get(i);
                    if (cp.isBottomCollision(th)) {
                        System.err.println("ID: " + PID +" collided with thief.");
                        releaseID(i, "ladri");
                        return true;
                    }
                }
            }else if(type == TOP_COLLISION){
                for (int i = 0; i < thiefs.size(); i++) {
                    Thief th = (Thief) thiefs.get(i);
                    if (cp.isTopCollision(th)) {
                        System.err.println("ID: " + PID +" collided with thief.");
                        releaseID(i, "ladri");
                        return true;
                    }
                }
            }else if(type == RIGHT_COLLISION){
                for (int i = 0; i < thiefs.size(); i++) {
                    Thief th = (Thief) thiefs.get(i);
                    if (cp.isRightCollision(th)) {
                        System.err.println("ID: " + PID +" collided with thief.");
                        releaseID(i, "ladri");
                        return true;
                    }
                }
            }else if(type == LEFT_COLLISION){
                for (int i = 0; i < thiefs.size(); i++) {
                    Thief th = (Thief) thiefs.get(i);
                    if (cp.isLeftCollision(th)) {
                        System.err.println("ID: " + PID +" collided with thief.");
                        releaseID(i, "ladri");
                        return true;
                    }
                }
            }
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
        while(true) {
            synchronized (cops) {
                repaint();
            }
            synchronized (thiefs) {
                repaint();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void restartLevel() {

        areas.clear();
        baggs.clear();
        walls.clear();
        thiefs.clear();
        cops.clear();
        areac.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }
    

    synchronized static int getEmptyID(String team){
        int ID = 0;
        if (team.equalsIgnoreCase("ladri")) {
            while (ID < MAXPLAYER + 1) {
                if (id_thiefs.get(ID).equals(true)) {
                    System.err.println("Position found at: " + ID + " Player ID set to " + ID);
                    id_thiefs.set(ID, false);
                    break;
                }
                ID++;
            }
        } else if (team.equalsIgnoreCase("guardie")) {
            while (ID < MAXPLAYER + 1) {
                if (id_cops.get(ID).equals(true)) {
                    System.err.println("Position found at: " + ID + " Player ID set to " + ID);
                    id_thiefs.set(ID, false);
                    break;
                }
                ID++;
            }
        }
        return ID; //Se torna "9" allora non ci sono posti disponibili
    }
    
    synchronized static void randomSpawn(int PID, String team){
        if(team.equalsIgnoreCase("ladri")){
            while(true) {
                int randomspawn = ran.nextInt(areac.size());
                AreaC c = (AreaC) areac.get(randomspawn);
                System.err.println("Thief ArrayList size: " + thiefs.size());
                Thief chk;
                boolean found = false;
                int k = 0;
                while(true){
                    try{
                        try{
                            chk = (Thief) thiefs.get(k);
                            if(chk.x() == c.x() && chk.y() == c.y()){
                                System.out.println("Coordinates already used; X: " + c.x() + " Y: " + c.y());
                                found = true;
                            }
                        }
                        catch(IndexOutOfBoundsException e){
                            break;
                        }
                    }
                    catch(ClassCastException e){
                    }
                    k++;
                }
                if (found == false) {
                    Thief a = new Thief(c.x(), c.y());
                    System.err.println("Created new Thief");
                    System.err.println("X: " + c.x() + " Y: " + c.y());
                    try{
                        thiefs.get(PID);
                        thiefs.set(PID, a);
                    }catch(IndexOutOfBoundsException e){
                        thiefs.add(PID, a);
                    }
                    break;
                }
            }
        }else if(team.equalsIgnoreCase("guardie")){
            while(true){
                int randomspawn = ran.nextInt(areas.size());
                Area a = (Area) areas.get(randomspawn);
                System.err.println("Cop ArrayList size: " + cops.size());
                Cop chk;
                boolean found = false;
                int k = 0;
                while(true){
                    try{
                        try{
                            chk = (Cop) cops.get(k);
                            if(chk.x() == a.x() && chk.y() == a.y()){
                                System.out.println("Coordinates already used; X: " + a.x() + " Y: " + a.y());
                                found = true;
                            }
                        }
                        catch(IndexOutOfBoundsException e){
                            break;
                        }
                    }
                    catch(ClassCastException e){
                    }
                    k++;
                }
                if (found == false) {
                    Cop b = new Cop(a.x(), a.y());
                    System.err.println("Created new Cop");
                    System.err.println("X: " + a.x() + " Y: " + a.y());
                    try{
                        cops.get(PID);
                        cops.set(PID, b);
                    }catch(IndexOutOfBoundsException e){
                        cops.add(PID, b);
                    }
                    break;
                }
            }
        }
    }
    
    synchronized static void releaseID(int PID, String team){
        if (team.equalsIgnoreCase("ladri")) {
            try{
                id_thiefs.set(PID, true);
                thiefs.set(PID, false);
            }catch(IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }else if (team.equalsIgnoreCase("guardie")) {
            try{
                id_cops.set(PID, true);
                cops.set(PID, false);
            }catch(IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
    }
}