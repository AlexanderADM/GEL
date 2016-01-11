package GEL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
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
    private static ArrayList grs = new ArrayList();
    private static ArrayList tende = new ArrayList();
    private static ArrayList baggs = new ArrayList();
    private static ArrayList areas = new ArrayList();
    private static ArrayList areac = new ArrayList();
    protected static ArrayList thiefs = new ArrayList();
    protected static ArrayList cops = new ArrayList();
    public static final int MAXPLAYER = 9;
    private static ArrayList id_thiefs = new ArrayList();
    private static ArrayList id_cops = new ArrayList();
    private static String lastSafe = "";
    private static String oldestCop = "";
    //private static Thief soko;
    private int w = 0;
    private int h = 0;
    private static boolean completed = false;
    private static String level =
                      "<=====================================================>\n"
                    + "[£££££££[?????????????????????????????????????????????[\n"
                    + "[£££££££[??<====>--<====>???QWWWWE??***--***??tyyyu???[\n"
                    + "[£££££££[??[-^^-*--*-^^-[???ASSSSD??*--^-^-*??ghhhj???[\n"
                    + "[£££££££[??{*****--*****}???ZXXXXC??***^-***??bnnnm???[\n"
                    + "[£££££££[??----------^---???assssd????????????????????[\n"
                    + "[$$$$===}??--^------^^---???a`ss`d??tyyyu??QWWWWWWE???[\n"
                    + "[????****??<*****--*****>???zxxxxc??ghhhj??ZXXXXXXC???[\n"
                    + "[????*^^*??[-^^-*--*-^^-[???????????bnnnm??bnnnnnnm???[\n"
                    + "[????****??{====}--{====}??QWE??QWE???????????????????[\n"
                    + "[??????????????????????????ZXC??ZXC???????????????????[\n"
                    + "[??????????????????????????a`d??a`d???????????????????[\n"
                    + "[????70&&&&¬9??70&&&&¬9????zxc??zxc??????????????<=$$=[\n"
                    + "[????4èòòòòà6??4èòòòòà6??????????????????????????[....[\n"
                    + "[????12222223??12222223????IOOOOOOP??????????????[....[\n"
                    + "[??????????????????????????J++++++L??↕‼¶§§§¶▬↑???$....[\n"
                    + "[??????????????????????????J++++++L??♪♫☼☼☼☼☼►◄???$....[\n"
                    + "[????ÕÌÌÌÌÌÌÖ??ÕÌÌÌÌÌÌÖ????J++++++L??○◙▲Å©®▲♂♀???$....[\n"
                    + "[????ÑÁÁÁÁÁÁÿ??ÑÁÁÁÁÁÁÿ????BNNNNNNM??_••¡¢¤••◘???[....[\n"
                    + "[????12222223??12222223??????????????????????????[....[\n"
                    + "[??????????????????????????QWE??QWE??????????????{=$$=[\n"
                    + "[??????????????????????????ZXC??ZXC???????????????????[\n"
                    + "[????****??<============>??a`d??a`d??789ÕÌÖ??ÕÌÖ789???[\n"
                    + "[????*^^*??[--^^--^--^--[??zxc??zxc??456ÑÁÿ??ÑÁÿ456???[\n"
                    + "[????****??{============}????????????123123??123123???[\n"
                    + "[$$$$===>???????????????????<====>????????????????????[\n"
                    + "[£££££££[???????????????????[-^^-[????????????????????[\n"
                    + "[£££££££[??<============>???[--^-[???789ÕÌÖ??ÕÌÖ789???[\n"
                    + "[£££££££[??[--^---^^----[???[-^--[???456ÑÁÿ??ÑÁÿ456???[\n"
                    + "[£££££££[??{============}???{====}???123123??123123???[\n"
                    + "[£££££££[?????????????????????????????????????????????[\n"
                    + "{=====================================================}\n";

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
        Grass grass;
        Tenda tenda;
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
            }else if (item == '#') {
                wall = new Wall(x, y, "wall");
                walls.add(wall);
                x += SPACE;
            }else if (item == '-') {
                grass = new Grass(x, y, "grass");
                grs.add(grass);
                x += SPACE;
            }else if (item == '[') {
                wall = new Wall(x, y, "siepev");
                walls.add(wall);
                x += SPACE;
            }else if (item == '=') {
                wall = new Wall(x, y,"siepe");
                walls.add(wall);
                x += SPACE;
            }else if (item == '{') {
                wall = new Wall(x, y,"siepeasw");
                walls.add(wall);
                x += SPACE;
            }else if (item == '}') {
                wall = new Wall(x, y,"siepease");
                walls.add(wall);
                x += SPACE;
            }else if (item == '<') {
                wall = new Wall(x, y,"siepeanw");
                walls.add(wall);
                x += SPACE;
            }else if (item == '>') {
                wall = new Wall(x, y,"siepeane");
                walls.add(wall);
                x += SPACE;
            }else if(item == '+'){
                wall = new Wall(x, y,"water");
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
                //soko = new Thief(x, y, 0);
                d = new Thief(x, y, 0);
                thiefs.add(d);
                x += SPACE;
            }else if  (item == '%'){
                c = new Cop(x, y, 0);
                cops.add(c);
                x += SPACE;
            }else if (item == ' ') {
                x += SPACE;
            }else if (item == 'z') {
                wall = new Wall(x, y,"casa1");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'x') {
                wall = new Wall(x, y,"casa2");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'c') {
                wall = new Wall(x, y,"casa3");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'a') {
                wall = new Wall(x, y,"casa4");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'd') {
                wall = new Wall(x, y,"casa5");
                walls.add(wall);
                x += SPACE;
            }else if (item == 's') {
                wall = new Wall(x, y,"casa6");
                walls.add(wall);
                x += SPACE;
            }else if (item == '`') {
                wall = new Wall(x, y,"casa7");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'Z') {
                wall = new Wall(x, y,"tetto1");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'X') {
                wall = new Wall(x, y,"tetto2");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'C') {
                wall = new Wall(x, y,"tetto3");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'A') {
                wall = new Wall(x, y,"tetto4");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'S') {
                wall = new Wall(x, y,"tetto5");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'D') {
                wall = new Wall(x, y,"tetto6");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'Q') {
                wall = new Wall(x, y,"tetto7");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'W') {
                wall = new Wall(x, y,"tetto8");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'E') {
                wall = new Wall(x, y,"tetto9");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'b') {
                wall = new Wall(x, y,"cb1");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'n') {
                wall = new Wall(x, y,"cb2");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'm') {
                wall = new Wall(x, y,"cb3");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'g') {
                wall = new Wall(x, y,"cb4");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'h') {
                wall = new Wall(x, y,"cb5");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'j') {
                wall = new Wall(x, y,"cb6");
                walls.add(wall);
                x += SPACE;
            }else if (item == 't') {
                wall = new Wall(x, y,"cb7");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'y') {
                wall = new Wall(x, y,"cb8");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'u') {
                wall = new Wall(x, y,"cb9");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'M') {
                wall = new Wall(x, y,"angse");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'B') {
                wall = new Wall(x, y,"angsw");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'I') {
                wall = new Wall(x, y,"angnw");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'P') {
                wall = new Wall(x, y,"angne");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'J') {
                wall = new Wall(x, y,"ws");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'O') {
                wall = new Wall(x, y,"wt");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'L') {
                wall = new Wall(x, y,"wd");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'N') {
                wall = new Wall(x, y,"wst");
                walls.add(wall);
                x += SPACE;
            }else if (item == '?') {
                grass = new Grass(x, y, "pietra");
                grs.add(grass);
                x += SPACE;
            }else if (item == '~') {
                grass = new Grass(x, y, "porta");
                grs.add(grass);
                x += SPACE;
            }else if (item == '*') {
                wall = new Wall(x, y,"piantina");
                walls.add(wall);
                x += SPACE;
            }else if (item == '1') {
                wall = new Wall(x, y,"tenda1");
                walls.add(wall);
                x += SPACE;
            }else if (item == '2') {
                grass = new Grass(x, y,"tenda2");
                grs.add(grass);
                x += SPACE;
            }else if (item == '3') {
                wall = new Wall(x, y,"tenda3");
                walls.add(wall);
                x += SPACE;
            }else if (item == '4') {
                tenda = new Tenda(x, y,"tenda4");
                tende.add(tenda);
                x += SPACE;
            }else if (item == '5') {
                tenda = new Tenda(x, y,"tenda5");
                tende.add(tenda);
                x += SPACE;
            }else if (item == '6') {
                tenda = new Tenda(x, y,"tenda6");
                tende.add(tenda);
                x += SPACE;
            }else if (item == '7') {
                tenda = new Tenda(x, y,"tenda7");
                tende.add(tenda);
                x += SPACE;
            }else if (item == '8') {
                tenda = new Tenda(x, y,"tenda8");
                tende.add(tenda);
                x += SPACE;
            }else if (item == '9') {
                tenda = new Tenda(x, y,"tenda9");
                tende.add(tenda);
                x += SPACE;
            }else if (item == '0') {
                tenda = new Tenda(x, y,"tenda0");
                tende.add(tenda);
                x += SPACE;
            }else if (item == '&') {
                tenda = new Tenda(x, y,"tenda11");
                tende.add(tenda);
                x += SPACE;
            }else if (item == '¬') {
                tenda = new Tenda(x, y,"tenda12");
                tende.add(tenda);
                x += SPACE;
            }else if (item == 'è') {
                tenda = new Tenda(x, y,"tenda13");
                tende.add(tenda);
                x += SPACE;
            }else if (item == 'ò') {
                tenda = new Tenda(x, y,"tenda14");
                tende.add(tenda);
                x += SPACE;
            }else if (item == 'à') {
                tenda = new Tenda(x, y,"tenda15");
                tende.add(tenda);
                x += SPACE;
            }else if (item == 'Ñ') {
                tenda = new Tenda(x, y,"tendina1");
                tende.add(tenda);
                x += SPACE;
            }else if (item == 'Á') {
                tenda = new Tenda(x, y,"tendina2");
                tende.add(tenda);
                x += SPACE;
            }else if (item == 'ÿ') {
                tenda = new Tenda(x, y,"tendina3");
                tende.add(tenda);
                x += SPACE;
            }else if (item == 'Õ') {
                tenda = new Tenda(x, y,"tendina4");
                tende.add(tenda);
                x += SPACE;
            }else if (item == 'Ì') {
                tenda = new Tenda(x, y,"tendina5");
                tende.add(tenda);
                x += SPACE;
            }else if (item == 'Ö') {
                tenda = new Tenda(x, y,"tendina6");
                tende.add(tenda);
                x += SPACE;
            }else if (item == '^') {
                grass = new Grass(x, y, "fiorellino");
                grs.add(grass);
                x += SPACE;;
            }else if (item == '_') {
                wall = new Wall(x, y, "pala1");
                walls.add(wall);
                x += SPACE;
            }else if (item == '•') {
                wall = new Wall(x, y, "pala2");
                walls.add(wall);
                x += SPACE;
            }else if (item == '◘') {
                wall = new Wall(x, y, "pala3");
                walls.add(wall);
                x += SPACE;
            }else if (item == '○') {
                wall = new Wall(x, y, "pala4");
                walls.add(wall);
                x += SPACE;
            }else if (item == '◙') {
                wall = new Wall(x, y, "pala5");
                walls.add(wall);
                x += SPACE;
            }else if (item == '♂') {
                wall = new Wall(x, y, "pala6");
                walls.add(wall);
                x += SPACE;
            }else if (item == '♀') {
                wall = new Wall(x, y, "pala7");
                walls.add(wall);
                x += SPACE;
            }else if (item == '♪') {
                wall = new Wall(x, y, "pala8");
                walls.add(wall);
                x += SPACE;
            }else if (item == '♫') {
                wall = new Wall(x, y, "pala9");
                walls.add(wall);
                x += SPACE;
            }else if (item == '☼') {
                wall = new Wall(x, y, "pala10");
                walls.add(wall);
                x += SPACE;
            }else if (item == '►') {
                wall = new Wall(x, y, "pala11");
                walls.add(wall);
                x += SPACE;
            }else if (item == '◄') {
                wall = new Wall(x, y, "pala12");
                walls.add(wall);
                x += SPACE;
            }else if (item == '↕') {
                wall = new Wall(x, y, "pala13");
                walls.add(wall);
                x += SPACE;
            }else if (item == '‼') {
                wall = new Wall(x, y, "pala14");
                walls.add(wall);
                x += SPACE;
            }else if (item == '¶') {
                wall = new Wall(x, y, "pala15");
                walls.add(wall);
                x += SPACE;
            }else if (item == '§') {
                wall = new Wall(x, y, "pala16");
                walls.add(wall);
                x += SPACE;
            }else if (item == '▬') {
                wall = new Wall(x, y, "pala17");
                walls.add(wall);
                x += SPACE;
            }else if (item == '↑') {
                wall = new Wall(x, y, "pala18");
                walls.add(wall);
                x += SPACE;
            }else if (item == '▲') {
                wall = new Wall(x, y, "pala19");
                walls.add(wall);
                x += SPACE;
            }else if (item == '¡') {
                wall = new Wall(x, y, "pala20");
                walls.add(wall);
                x += SPACE;
            }else if (item == '¢') {
                wall = new Wall(x, y, "pala21");
                walls.add(wall);
                x += SPACE;
            }else if (item == '¤') {
                wall = new Wall(x, y, "pala22");
                walls.add(wall);
                x += SPACE;
            }else if (item == 'Å') {
                wall = new Wall(x, y, "pala23");
                walls.add(wall);
                x += SPACE;
            }else if (item == '©') {
                wall = new Wall(x, y, "pala24");
                walls.add(wall);
                x += SPACE;
            }else if (item == '®') {
                wall = new Wall(x, y, "pala25");
                walls.add(wall);
                x += SPACE;
            }


            h = y;
        }
    }

    public void buildWorld(Graphics g) {
        g.setColor(new Color(77, 109, 46));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        boolean chk;

        ArrayList world = new ArrayList();
        world.addAll(areas);
        world.addAll(areac);
        world.addAll(grs);        
        
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
        
        //world.add(soko);
        
        world.addAll(baggs);
        world.addAll(tende);
        world.addAll(walls);

        for (int i = 0; i < world.size(); i++) {

            Actor item = (Actor) world.get(i);

            if ((item instanceof Thief)
                    || (item instanceof Baggage)) {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
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
        if(!lastSafe.isEmpty() && !oldestCop.isEmpty()) {
            if(lastSafe.equals("0")){
                g.setColor(new Color(255, 0, 0));
            }else if(lastSafe.equals("1")){
                g.setColor(new Color(255, 125, 10));
            }else if(lastSafe.equals("2")){
                g.setColor(new Color(248, 255, 0));
            }else if(lastSafe.equals("3")){
                g.setColor(new Color(16, 255, 0));
            }else if(lastSafe.equals("4")){
                g.setColor(new Color(0, 255, 240));
            }else if(lastSafe.equals("5")){
                g.setColor(new Color(37, 148, 255));
            }else if(lastSafe.equals("6")){
                g.setColor(new Color(0, 4, 255));
            }else if(lastSafe.equals("7")){
                g.setColor(new Color(89, 0, 174));
            }else if(lastSafe.equals("8")){
                g.setColor(new Color(255, 17, 202));
            }

            g.drawString("Thief " + lastSafe + " got away, Cop " + oldestCop + " was kicked out.", 25, 20);
        }
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (completed) {
                return;
            }

            
            int key = e.getKeyCode();


            /*if (key == KeyEvent.VK_LEFT) {

                if (checkWallCollision(soko, BOTTOM_COLLISION)) {
                    return;
                }

                soko.move(0, SPACE, "d", 0);

            }*/ if (key == KeyEvent.VK_R) {
                restartLevel();
            }

            //repaint();
        }
    }
    
    synchronized static boolean movePlayer(int PID,String team, String direction){
        try{
            if(team.equalsIgnoreCase("ladri")) {
                if (direction.equalsIgnoreCase("W")) {
                    Thief thieves = (Thief) thiefs.get(PID);
                    if (checkWallCollision(thieves, TOP_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, TOP_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, TOP_COLLISION)) {
                        return false;
                    }
                    thieves.move(0, -SPACE, "u", PID);
                } else if (direction.equalsIgnoreCase("S")) {
                    Thief thieves = (Thief) thiefs.get(PID);
                    if (checkWallCollision(thieves, BOTTOM_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, BOTTOM_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, BOTTOM_COLLISION)) {
                        return false;
                    }
                    thieves.move(0, SPACE, "d", PID);
                } else if (direction.equalsIgnoreCase("A")) {
                    Thief thieves = (Thief) thiefs.get(PID);
                    if (checkWallCollision(thieves, LEFT_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, LEFT_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, LEFT_COLLISION)) {
                        return false;
                    }
                    thieves.move(-SPACE, 0, "l", PID);
                } else if (direction.equalsIgnoreCase("D")) {
                    Thief thieves = (Thief) thiefs.get(PID);
                    if (checkWallCollision(thieves, RIGHT_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, RIGHT_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, RIGHT_COLLISION)) {
                        return false;
                    }
                    thieves.move(SPACE, 0, "r", PID);
                }
                isSafe(PID);
            }else if(team.equalsIgnoreCase("guardie")){
                if (direction.equalsIgnoreCase("W")) {
                    Cop cp = (Cop) cops.get(PID);
                    if (checkWallCollision(cp, TOP_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, TOP_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, TOP_COLLISION)) {
                        return true;
                    }
                    cp.move(0, -SPACE, "u", PID);
                } else if (direction.equalsIgnoreCase("S")) {
                    Cop cp = (Cop) cops.get(PID);
                    if (checkWallCollision(cp, BOTTOM_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, BOTTOM_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, BOTTOM_COLLISION)) {
                        return true;
                    }
                    cp.move(0, SPACE, "d", PID);
                } else if (direction.equalsIgnoreCase("A")) {
                    Cop cp = (Cop) cops.get(PID);
                    if (checkWallCollision(cp, LEFT_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, LEFT_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, LEFT_COLLISION)) {
                        return true;
                    }
                    cp.move(-SPACE, 0, "l", PID);
                } else if (direction.equalsIgnoreCase("D")) {
                    Cop cp = (Cop) cops.get(PID);
                    if (checkWallCollision(cp, RIGHT_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, RIGHT_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, RIGHT_COLLISION)) {
                        return true;
                    }
                    cp.move(SPACE, 0, "r", PID);
                }
            }
        }catch(ClassCastException e){
            return false;
        }
        return true;
    }
    private static void isSafe(int PID){
        Thief th = (Thief) thiefs.get(PID);
        Area zone;
        for (int i = 0; i < areas.size(); i++) {
            zone = (Area) areas.get(i);
            if(zone.x() == th.x() && zone.y() == th.y()){
                System.err.println("Thief ID: " + PID + " is in the safe zone.");
                gotAway(PID, "ladri");
                releaseID(PID, "ladri");
                randomSpawn(PID, "ladri");
                kickCop();
            }
        }
    }
    synchronized static void gotAway(int PID, String team){
        if(team.equalsIgnoreCase("ladri")){
            System.err.println("Thief " + PID + "  " + " got away.");
            lastSafe = "" + PID;
        }
    }
    public static void kickCop(){

        long timeNew;
        long timeOld = System.currentTimeMillis();
        int PID = 0;
        Cop p;
        boolean check = false;        
        if(cops.size() != 0) {
            for (int i = 0; i < cops.size(); i++) {
                try{
                    p =  (Cop) cops.get(i);
                    timeNew = p.time;
                    if(timeNew < timeOld){
                        timeOld = timeNew;
                        PID = i;
                        check = true;
                    }
                }catch(ClassCastException e ){
                }
            }
        }
        if(check == true) {
            oldestCop = "" + PID;
            releaseID(PID, "guardie");
        }
    }
    private static boolean checkPlayerCollision(int PID, String team, int type){
        if(team.equalsIgnoreCase("ladri")) {
            Thief th = (Thief) thiefs.get(PID);
            if (type == LEFT_COLLISION) {
                for (int i = 0; i < cops.size(); i++) {
                    try{
                        Cop cop = (Cop) cops.get(i);
                        if (th.isLeftCollision(cop)) {
                            System.err.println("ID: " + PID + " collided with cop");
                            releaseID(PID, team);
                            return true;
                        }
                    }catch(ClassCastException e){
                    }
                }
                return false;
            } else if (type == RIGHT_COLLISION) {
                for (int i = 0; i < cops.size(); i++) {
                    try{
                        Cop cop = (Cop) cops.get(i);
                        if (th.isRightCollision(cop)) {
                            System.err.println("ID: " + PID + " collided with cop");
                            releaseID(PID, team);
                            return true;
                        }
                    }catch(ClassCastException e){
                    }
                }
            } else if (type == TOP_COLLISION) {
                for (int i = 0; i < cops.size(); i++) {
                    try{
                        Cop cop = (Cop) cops.get(i);
                        if (th.isTopCollision(cop)) {
                            System.err.println("ID: " + PID + " collided with cop");
                            releaseID(PID, team);
                            return true;
                        }
                    }catch(ClassCastException e){
                    }
                }
            } else if (type == BOTTOM_COLLISION) {
                for (int i = 0; i < cops.size(); i++) {
                    try{
                        Cop cop = (Cop) cops.get(i);
                        if (th.isBottomCollision(cop)) {
                            System.err.println("ID: " + PID + " collided with cop");
                            releaseID(PID, team);
                            return true;
                        }
                    }catch(ClassCastException e){
                    }
                }
            }
        }else if(team.equalsIgnoreCase("guardie")){
            Cop cp = (Cop) cops.get(PID);
            if(type == BOTTOM_COLLISION){
                for (int i = 0; i < thiefs.size(); i++) {
                    try{
                        Thief th = (Thief) thiefs.get(i);
                        if (cp.isBottomCollision(th)) {
                            System.err.println("ID: " + PID +" collided with thief.");
                            releaseID(i, "ladri");
                            return true;
                        }
                    }catch(ClassCastException e){
                    }
                }
            }else if(type == TOP_COLLISION){
                for (int i = 0; i < thiefs.size(); i++) {
                    try{
                        Thief th = (Thief) thiefs.get(i);
                        if (cp.isTopCollision(th)) {
                            System.err.println("ID: " + PID +" collided with thief.");
                            releaseID(i, "ladri");
                            return true;
                        }
                    }catch(ClassCastException e){
                    }
                }
            }else if(type == RIGHT_COLLISION){
                for (int i = 0; i < thiefs.size(); i++) {
                    try{
                        Thief th = (Thief) thiefs.get(i);
                        if (cp.isRightCollision(th)) {
                            System.err.println("ID: " + PID +" collided with thief.");
                            releaseID(i, "ladri");
                            return true;
                        }
                    }catch(ClassCastException e){
                    }
                }
            }else if(type == LEFT_COLLISION){
                for (int i = 0; i < thiefs.size(); i++) {
                    try{
                        Thief th = (Thief) thiefs.get(i);
                        if (cp.isLeftCollision(th)) {
                            System.err.println("ID: " + PID +" collided with thief.");
                            releaseID(i, "ladri");
                            return true;
                        }
                    }catch(ClassCastException e){
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

    private static boolean checkBagCollision(int PID,String team, int type) {
        Thief th;
        Cop cp;
        if (type == LEFT_COLLISION) {
            if(team.equalsIgnoreCase("ladri")) {
                try {
                    th = (Thief) thiefs.get(PID);
                    for (int i = 0; i < baggs.size(); i++) {

                        Baggage bag = (Baggage) baggs.get(i);

                        if (th.isLeftCollision(bag)) {

                            for (int y = 0; y < baggs.size(); y++) {
                                Baggage item = (Baggage) baggs.get(y);
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
                } catch (IndexOutOfBoundsException e) {
                    //e.printStackTrace();
                } catch (ClassCastException e) {
                    //e.printStackTrace();
                }
            }else if(team.equalsIgnoreCase("guardie")) {
                try {
                    cp = (Cop) cops.get(PID);
                    for (int j = 0; j < baggs.size(); j++) {

                        Baggage bag = (Baggage) baggs.get(j);

                        if (cp.isLeftCollision(bag)) {

                            for (int y = 0; y < baggs.size(); y++) {
                                Baggage item = (Baggage) baggs.get(y);
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
                } catch (IndexOutOfBoundsException e) {
                    //e.printStackTrace();
                } catch (ClassCastException e) {
                    //e.printStackTrace();
                }
            }
            return false;
        } else if (type == RIGHT_COLLISION) {
            if(team.equalsIgnoreCase("ladri")) {
                try {
                    th = (Thief) thiefs.get(PID);
                    for (int i = 0; i < baggs.size(); i++) {

                        Baggage bag = (Baggage) baggs.get(i);

                        if (th.isRightCollision(bag)) {

                            for (int y = 0; y < baggs.size(); y++) {
                                Baggage item = (Baggage) baggs.get(y);
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
                } catch (IndexOutOfBoundsException e) {
                    //e.printStackTrace();
                } catch (ClassCastException e) {
                    //e.printStackTrace();
                }
            }else if(team.equalsIgnoreCase("guardie")) {
                try {
                    cp = (Cop) cops.get(PID);
                    for (int j = 0; j < baggs.size(); j++) {

                        Baggage bag = (Baggage) baggs.get(j);

                        if (cp.isRightCollision(bag)) {

                            for (int y = 0; y < baggs.size(); y++) {
                                Baggage item = (Baggage) baggs.get(y);
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
                } catch (IndexOutOfBoundsException e) {
                    //e.printStackTrace();
                } catch (ClassCastException e) {
                    //e.printStackTrace();
                }
            }
            return false;

        } else if (type == TOP_COLLISION) {
            if(team.equalsIgnoreCase("ladri")) {
                try {
                    th = (Thief) thiefs.get(PID);
                    for (int i = 0; i < baggs.size(); i++) {

                        Baggage bag = (Baggage) baggs.get(i);

                        if (th.isTopCollision(bag)) {

                            for (int y = 0; y < baggs.size(); y++) {
                                Baggage item = (Baggage) baggs.get(y);
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
                } catch (IndexOutOfBoundsException e) {
                    //e.printStackTrace();
                } catch (ClassCastException e) {
                    // e.printStackTrace();
                }
            } else if(team.equalsIgnoreCase("guardie")) {
                try {
                    cp = (Cop) cops.get(PID);
                    for (int j = 0; j < baggs.size(); j++) {

                        Baggage bag = (Baggage) baggs.get(j);

                        if (cp.isTopCollision(bag)) {

                            for (int y = 0; y < baggs.size(); y++) {
                                Baggage item = (Baggage) baggs.get(y);
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
                } catch (IndexOutOfBoundsException e) {
                    //e.printStackTrace();
                } catch (ClassCastException e) {
                    //e.printStackTrace();
                }
            }
            return false;

        } else if (type == BOTTOM_COLLISION) {
            if(team.equalsIgnoreCase("ladri")) {
                try {
                    th = (Thief) thiefs.get(PID);
                    for (int i = 0; i < baggs.size(); i++) {

                        Baggage bag = (Baggage) baggs.get(i);

                        if (th.isBottomCollision(bag)) {

                            for (int y = 0; y < baggs.size(); y++) {
                                Baggage item = (Baggage) baggs.get(y);
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
                } catch (IndexOutOfBoundsException e) {
                    //e.printStackTrace();
                } catch (ClassCastException e) {
                    //e.printStackTrace();
                }
            }else if(team.equalsIgnoreCase("guardie")) {
                try {
                    cp = (Cop) cops.get(PID);
                    for (int j = 0; j < baggs.size(); j++) {

                        Baggage bag = (Baggage) baggs.get(j);

                        if (cp.isBottomCollision(bag)) {

                            for (int y = 0; y < baggs.size(); y++) {
                                Baggage item = (Baggage) baggs.get(y);
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
                } catch (IndexOutOfBoundsException e) {
                    //e.printStackTrace();
                } catch (ClassCastException e) {
                    //e.printStackTrace();
                }
            }
            return false;
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
                Thread.sleep(40);
            } catch (InterruptedException ex) {
            }
        }
    }
    public void restartLevel() {
        areas.clear();
        baggs.clear();
        walls.clear();
        areac.clear();
        grs.clear();
        tende.clear();
        initWorld();
        for(int i = 0; i < thiefs.size(); i++){
            try{
                releaseID(i, "ladri");
            } catch(IndexOutOfBoundsException e){
            }
        }
        for(int i = 0; i < cops.size(); i++){
            try{
                releaseID(i, "guardie");
            } catch(IndexOutOfBoundsException e){
            }
        }
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
                    id_cops.set(ID, false);
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
                    Thief a = new Thief(c.x(), c.y(), PID);
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
                    Cop b = new Cop(a.x(), a.y(), PID);
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