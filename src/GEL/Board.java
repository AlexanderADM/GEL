package GEL;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable{

    private static final int SPACE = 20;
    private static final int LEFT_COLLISION;
    private static final int RIGHT_COLLISION;
    private static final int TOP_COLLISION;
    private static final int BOTTOM_COLLISION;
    private static final int maxPlayers;

    static {
        LEFT_COLLISION = 1;
        RIGHT_COLLISION = 2;
        TOP_COLLISION = 3;
        BOTTOM_COLLISION = 4;
        maxPlayers = 9;
    }

    private static final Random ran = new Random();
    private static final ArrayList<Wall> walls = new ArrayList<Wall>();
    private static final ArrayList<Grass> grs = new ArrayList<Grass>();
    private static final ArrayList<Tenda> tent = new ArrayList<Tenda>();
    private static final ArrayList<Baggage> bags = new ArrayList<Baggage>();
    private static final ArrayList<Area> areas = new ArrayList<Area>();
    private static final ArrayList<AreaC> areac = new ArrayList<AreaC>();
    static final ArrayList<Player> thieves = new ArrayList<Player>();
    static final ArrayList<Player> cops = new ArrayList<Player>();
    private static final ArrayList<Boolean> id_thieves = new ArrayList<>();
    private static final ArrayList<Boolean> id_cops;

    static {
        id_cops = new ArrayList<>();
    }

    private static String lastSafe = "";
    private static String oldestCop = "";
    //private static Thief soko;
    private int w = 0;
    private int h = 0;
    private static boolean completed = false;

    Board() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
        new Thread(this).start();
        for(int i = 0; i < maxPlayers; i++){
            id_thieves.add(i, true);
            id_cops.add(i, true);
        }
    }

    int getBoardWidth() {
        return this.w;
    }
    int getBoardHeight() {
        return this.h;
    }
    @Contract(pure = true)
    synchronized static boolean getThiefCount(){
        return id_thieves.contains(true);
    }
    @Contract(pure = true)
    synchronized static boolean getCopCount(){
        return id_cops.contains(true);
    }
    private void initWorld() {

        int OFFSET = 30;
        int x = OFFSET;
        int y = OFFSET;
        
        Wall wall;
        Grass grass;
        Tenda tenda;
        Baggage b;
        Area a;
        AreaC e;
        Player c;
        Player d;

        String level = "<=====================================================>\n"
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
                bags.add(b);
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
                d = new Player(x, y, 0,1);
                thieves.add(d);
                x += SPACE;
            }else if  (item == '%'){
                c = new Player(x, y, 0,0);
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
                tent.add(tenda);
                x += SPACE;
            }else if (item == '5') {
                tenda = new Tenda(x, y,"tenda5");
                tent.add(tenda);
                x += SPACE;
            }else if (item == '6') {
                tenda = new Tenda(x, y,"tenda6");
                tent.add(tenda);
                x += SPACE;
            }else if (item == '7') {
                tenda = new Tenda(x, y,"tenda7");
                tent.add(tenda);
                x += SPACE;
            }else if (item == '8') {
                tenda = new Tenda(x, y,"tenda8");
                tent.add(tenda);
                x += SPACE;
            }else if (item == '9') {
                tenda = new Tenda(x, y,"tenda9");
                tent.add(tenda);
                x += SPACE;
            }else if (item == '0') {
                tenda = new Tenda(x, y,"tenda0");
                tent.add(tenda);
                x += SPACE;
            }else if (item == '&') {
                tenda = new Tenda(x, y,"tenda11");
                tent.add(tenda);
                x += SPACE;
            }else if (item == '¬') {
                tenda = new Tenda(x, y,"tenda12");
                tent.add(tenda);
                x += SPACE;
            }else if (item == 'è') {
                tenda = new Tenda(x, y,"tenda13");
                tent.add(tenda);
                x += SPACE;
            }else if (item == 'ò') {
                tenda = new Tenda(x, y,"tenda14");
                tent.add(tenda);
                x += SPACE;
            }else if (item == 'à') {
                tenda = new Tenda(x, y,"tenda15");
                tent.add(tenda);
                x += SPACE;
            }else if (item == 'Ñ') {
                tenda = new Tenda(x, y,"tendina1");
                tent.add(tenda);
                x += SPACE;
            }else if (item == 'Á') {
                tenda = new Tenda(x, y,"tendina2");
                tent.add(tenda);
                x += SPACE;
            }else if (item == 'ÿ') {
                tenda = new Tenda(x, y,"tendina3");
                tent.add(tenda);
                x += SPACE;
            }else if (item == 'Õ') {
                tenda = new Tenda(x, y,"tendina4");
                tent.add(tenda);
                x += SPACE;
            }else if (item == 'Ì') {
                tenda = new Tenda(x, y,"tendina5");
                tent.add(tenda);
                x += SPACE;
            }else if (item == 'Ö') {
                tenda = new Tenda(x, y,"tendina6");
                tent.add(tenda);
                x += SPACE;
            }else if (item == '^') {
                grass = new Grass(x, y, "fiorellino");
                grs.add(grass);
                x += SPACE;
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

    private void buildWorld(@NotNull Graphics g) {
        g.setColor(new Color(77, 109, 46));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList<Actor> world = new ArrayList<Actor>();
        world.addAll(areas);
        world.addAll(areac);
        world.addAll(grs);
        world.addAll(cops);
        world.addAll(thieves);
        //world.add(soko);
        world.addAll(bags);
        world.addAll(tent);
        world.addAll(walls);

        for (Actor o : world) {

            if ((o instanceof Player) || (o instanceof Baggage)) {
                g.drawImage(o.getImage(), o.x(), o.y(), this);
            } else {
                g.drawImage(o.getImage(), o.x(), o.y(), this);
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
            switch (lastSafe) {
                case "0":
                    g.setColor(new Color(255, 0, 0));
                    break;
                case "1":
                    g.setColor(new Color(255, 125, 10));
                    break;
                case "2":
                    g.setColor(new Color(248, 255, 0));
                    break;
                case "3":
                    g.setColor(new Color(16, 255, 0));
                    break;
                case "4":
                    g.setColor(new Color(0, 255, 240));
                    break;
                case "5":
                    g.setColor(new Color(37, 148, 255));
                    break;
                case "6":
                    g.setColor(new Color(0, 4, 255));
                    break;
                case "7":
                    g.setColor(new Color(89, 0, 174));
                    break;
                case "8":
                    g.setColor(new Color(255, 17, 202));
                    break;
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
    
    synchronized static boolean movePlayer(int PID, @NotNull String team, String direction){
        try{
            if(team.equalsIgnoreCase("ladri")) {
                Player thief = thieves.get(PID);
                if (direction.equalsIgnoreCase("W")) {
                    if (checkWallCollision(thief, TOP_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, TOP_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, TOP_COLLISION)) {
                        return false;
                    }
                    thief.move(0, -SPACE, "u");
                } else if (direction.equalsIgnoreCase("S")) {
                    if (checkWallCollision(thief, BOTTOM_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, BOTTOM_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, BOTTOM_COLLISION)) {
                        return false;
                    }
                    thief.move(0, SPACE, "d");
                } else if (direction.equalsIgnoreCase("A")) {
                    if (checkWallCollision(thief, LEFT_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, LEFT_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, LEFT_COLLISION)) {
                        return false;
                    }
                    thief.move(-SPACE, 0, "l");
                } else if (direction.equalsIgnoreCase("D")) {
                    if (checkWallCollision(thief, RIGHT_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, RIGHT_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, RIGHT_COLLISION)) {
                        return false;
                    }
                    thief.move(SPACE, 0, "r");
                }
                isSafe(PID);
            }else if(team.equalsIgnoreCase("guardie")){
                Player cp = cops.get(PID);
                if (direction.equalsIgnoreCase("W")) {
                    if (checkWallCollision(cp, TOP_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, TOP_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, TOP_COLLISION)) {
                        return true;
                    }
                    cp.move(0, -SPACE, "u");
                } else if (direction.equalsIgnoreCase("S")) {
                    if (checkWallCollision(cp, BOTTOM_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, BOTTOM_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, BOTTOM_COLLISION)) {
                        return true;
                    }
                    cp.move(0, SPACE, "d");
                } else if (direction.equalsIgnoreCase("A")) {
                    if (checkWallCollision(cp, LEFT_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, LEFT_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, LEFT_COLLISION)) {
                        return true;
                    }
                    cp.move(-SPACE, 0, "l");
                } else if (direction.equalsIgnoreCase("D")) {
                    if (checkWallCollision(cp, RIGHT_COLLISION)) {
                        return true;
                    }
                    if (checkBagCollision(PID, team, RIGHT_COLLISION)) {
                        return true;
                    }
                    if (checkPlayerCollision(PID, team, RIGHT_COLLISION)) {
                        return true;
                    }
                    cp.move(SPACE, 0, "r");
                }
            }
        }catch(ClassCastException e){
            return false;
        }
        return true;
    }
    private static void isSafe(int PID){
        Player th = thieves.get(PID);
        Area zone;
        for (Area area : areas) {
            zone = area;
            if (zone.x() == th.x() && zone.y() == th.y()) {
                System.err.println("Thief ID: " + PID + " is in the safe zone.");
                gotAway(PID);
                releaseID(PID, "ladri");
                randomSpawn(PID, "ladri");
                kickCop();
            }
        }
    }
    private synchronized static void gotAway(int PID){
            System.err.println("Thief " + PID + "  " + " got away.");
            lastSafe = "" + PID;
    }
    private static void kickCop(){

        long timeNew;
        long timeOld = System.currentTimeMillis();
        int PID = 0;
        Player p;
        boolean check = false;        
        if(cops.size() != 0) {
            for (int i = 0; i < cops.size(); i++) {
                try{
                    p = cops.get(i);
                    timeNew = p.time;
                    if(timeNew < timeOld){
                        timeOld = timeNew;
                        PID = i;
                        check = true;
                    }
                }catch(ClassCastException ignored){
                }
            }
        }
        if(check) {
            oldestCop = "" + PID;
            releaseID(PID, "guardie");
        }
    }
    private static boolean checkPlayerCollision(int PID, @NotNull String team, int type){
        if(team.equalsIgnoreCase("ladri")) {
            Player th = thieves.get(PID);
            if (type == LEFT_COLLISION) {
                for (Player o : cops) {
                    try {
                        if (th.isLeftCollision(o)) {
                            System.err.println("ID: " + PID + " collided with cop");
                            releaseID(PID, team);
                            return true;
                        }
                    } catch (ClassCastException ignored) {
                    }
                }
                return false;
            } else if (type == RIGHT_COLLISION) {
                for (Player o : cops) {
                    try {
                        if (th.isRightCollision(o)) {
                            System.err.println("ID: " + PID + " collided with cop");
                            releaseID(PID, team);
                            return true;
                        }
                    } catch (ClassCastException ignored) {
                    }
                }
            } else if (type == TOP_COLLISION) {
                for (Player o : cops) {
                    try {
                        if (th.isTopCollision(o)) {
                            System.err.println("ID: " + PID + " collided with cop");
                            releaseID(PID, team);
                            return true;
                        }
                    } catch (ClassCastException ignored) {
                    }
                }
            } else if (type == BOTTOM_COLLISION) {
                for (Player o : cops) {
                    try {
                        if (th.isBottomCollision(o)) {
                            System.err.println("ID: " + PID + " collided with cop");
                            releaseID(PID, team);
                            return true;
                        }
                    } catch (ClassCastException ignored) {
                    }
                }
            }
        }else if(team.equalsIgnoreCase("guardie")){
            Player cp = cops.get(PID);
            if(type == BOTTOM_COLLISION){
                for (int i = 0; i < thieves.size(); i++) {
                    try{
                        Player th = thieves.get(i);
                        if (cp.isBottomCollision(th)) {
                            System.err.println("ID: " + PID +" collided with thief.");
                            releaseID(i, "ladri");
                            return true;
                        }
                    }catch(ClassCastException ignored){
                    }
                }
            }else if(type == TOP_COLLISION){
                for (int i = 0; i < thieves.size(); i++) {
                    try{
                        Player th = thieves.get(i);
                        if (cp.isTopCollision(th)) {
                            System.err.println("ID: " + PID +" collided with thief.");
                            releaseID(i, "ladri");
                            return true;
                        }
                    }catch(ClassCastException ignored){
                    }
                }
            }else if(type == RIGHT_COLLISION){
                for (int i = 0; i < thieves.size(); i++) {
                    try{
                        Player th = thieves.get(i);
                        if (cp.isRightCollision(th)) {
                            System.err.println("ID: " + PID +" collided with thief.");
                            releaseID(i, "ladri");
                            return true;
                        }
                    }catch(ClassCastException ignored){
                    }
                }
            }else if(type == LEFT_COLLISION){
                for (int i = 0; i < thieves.size(); i++) {
                    try{
                        Player th = thieves.get(i);
                        if (cp.isLeftCollision(th)) {
                            System.err.println("ID: " + PID +" collided with thief.");
                            releaseID(i, "ladri");
                            return true;
                        }
                    }catch(ClassCastException ignored){
                    }
                }
            }
        }
        return false;
    }
    private static boolean checkWallCollision(Actor actor, int type) {
        if (type == LEFT_COLLISION) {
            for (Wall o : walls) {
                if (actor.isLeftCollision(o)) {
                    return true;
                }
            }
            return false;
        } else if (type == RIGHT_COLLISION) {
            for (Wall o : walls) {
                if (actor.isRightCollision(o)) {
                    return true;
                }
            }
            return false;
        } else if (type == TOP_COLLISION) {
            for (Wall o : walls) {
                if (actor.isTopCollision(o)) {
                    return true;
                }
            }
            return false;
        } else if (type == BOTTOM_COLLISION) {
            for (Wall o : walls) {
                if (actor.isBottomCollision(o)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private static boolean checkBagCollision(int PID,String team, int type) {
        Player th = thieves.get(PID);
        Player cp = cops.get(PID);
        if (type == LEFT_COLLISION) {
            if(team.equalsIgnoreCase("ladri")) {
                for(Baggage bag : bags){
                    if(th.isLeftCollision(bag)){
                        if(checkWallCollision(bag, LEFT_COLLISION)) {
                            return true;
                        }
                        bag.move(-SPACE,0);
                    }
                }
            }else{
                for(Baggage bag : bags){
                    if(cp.isLeftCollision(bag)){
                        if(checkWallCollision(bag, LEFT_COLLISION)) {
                            return true;
                        }
                        bag.move(-SPACE,0);
                    }
                }
            }
            return false;
        }else if (type == RIGHT_COLLISION) {
            if(team.equalsIgnoreCase("ladri")) {
                for(Baggage bag : bags){
                    if(th.isLeftCollision(bag)){
                        if(checkWallCollision(bag, RIGHT_COLLISION)) {
                            return true;
                        }
                        bag.move(-SPACE,0);
                    }
                }
            }else{
                for(Baggage bag : bags){
                    if(cp.isLeftCollision(bag)){
                        if(checkWallCollision(bag, RIGHT_COLLISION)) {
                            return true;
                        }
                        bag.move(-SPACE,0);
                    }
                }
            }
            return false;
        } else if (type == TOP_COLLISION) {
            if(team.equalsIgnoreCase("ladri")) {
                for(Baggage bag : bags){
                    if(th.isLeftCollision(bag)){
                        if(checkWallCollision(bag, TOP_COLLISION)) {
                            return true;
                        }
                        bag.move(-SPACE,0);
                    }
                }
            }else{
                for(Baggage bag : bags){
                    if(cp.isLeftCollision(bag)){
                        if(checkWallCollision(bag, RIGHT_COLLISION)) {
                            return true;
                        }
                        bag.move(-SPACE,0);
                    }
                }
            }
            return false;
        } else if (type == BOTTOM_COLLISION) {
            if(team.equalsIgnoreCase("ladri")) {
                for(Baggage bag : bags){
                    if(th.isLeftCollision(bag)){
                        if(checkWallCollision(bag, BOTTOM_COLLISION)) {
                            return true;
                        }
                        bag.move(-SPACE,0);
                    }
                }
            }else{
                for(Baggage bag : bags){
                    if(cp.isLeftCollision(bag)){
                        if(checkWallCollision(bag, BOTTOM_COLLISION)) {
                            return true;
                        }
                        bag.move(-SPACE,0);
                    }
                }
            }
            return false;
        }
        return false;
    }
    public void run(){
        while(true) {
            synchronized (cops){ repaint(); }
            synchronized (thieves){ repaint(); }
            try{ Thread.sleep(40); } catch (InterruptedException ignored){}
        }
    }
    private void restartLevel() {
        areas.clear();
        bags.clear();
        walls.clear();
        areac.clear();
        grs.clear();
        tent.clear();
        initWorld();
        for(int i = 0; i < thieves.size(); i++){
            try{
                releaseID(i, "ladri");
            } catch(IndexOutOfBoundsException ignored){
            }
        }
        for(int i = 0; i < cops.size(); i++){
            try{
                releaseID(i, "guardie");
            } catch(IndexOutOfBoundsException ignored){
            }
        }
        if (completed) {
            completed = false;
        }
    }
    
    
    synchronized static int getEmptyID(@NotNull String team){
        int ID = 0;
        if (team.equalsIgnoreCase("ladri")) {
            while (ID < maxPlayers + 1) {
                if (id_thieves.get(ID).equals(true)) {
                    System.err.println("Position found at: " + ID + " Player ID set to " + ID);
                    id_thieves.set(ID, false);
                    break;
                }
                ID++;
            }
        } else if (team.equalsIgnoreCase("guardie")) {
            while (ID < maxPlayers + 1) {
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
    
    synchronized static void randomSpawn(int PID, @NotNull String team){
        boolean found = false;
        int k = 0;
        if(team.equalsIgnoreCase("ladri")){
            while(true) {
                int randomspawn = ran.nextInt(areac.size());
                AreaC c = areac.get(randomspawn);
                System.err.println("Thief ArrayList size: " + thieves.size());
                Player chk;
                while(true){
                    try{
                        try{
                            chk = thieves.get(k);
                            if(chk.x() == c.x() && chk.y() == c.y()){
                                System.out.println("Coordinates already used; X: " + c.x() + " Y: " + c.y());
                                found = true;
                            }
                        }
                        catch(IndexOutOfBoundsException e){
                            break;
                        }
                    }
                    catch(ClassCastException ignored){
                    }
                    k++;
                }
                if (!found) {
                    Player a = new Player(c.x(), c.y(), PID,1);
                    System.err.println("Created new Thief");
                    System.err.println("X: " + c.x() + " Y: " + c.y());
                    try{
                        thieves.set(PID, a);
                    }catch(IndexOutOfBoundsException e){
                        thieves.add(PID, a);
                    }
                    break;
                }
            }
        }else if(team.equalsIgnoreCase("guardie")){
            while(true){
                int randomspawn = ran.nextInt(areas.size());
                Area a = areas.get(randomspawn);
                System.err.println("Cop ArrayList size: " + cops.size());
                Player chk;
                while(true){
                    try{
                        try{
                            chk = cops.get(k);
                            if(chk.x() == a.x() && chk.y() == a.y()){
                                System.out.println("Coordinates already used; X: " + a.x() + " Y: " + a.y());
                                found = true;
                            }
                        }
                        catch(IndexOutOfBoundsException e){
                            break;
                        }
                    }
                    catch(ClassCastException ignored){
                    }
                    k++;
                }
                if (!found) {
                    Player b = new Player(a.x(), a.y(), PID,0);
                    System.err.println("Created new Cop");
                    System.err.println("X: " + a.x() + " Y: " + a.y());
                    try{
                        cops.set(PID, b);
                    }catch(IndexOutOfBoundsException e){
                        cops.add(PID, b);
                    }
                    break;
                }
            }
        }
    }
    
    synchronized static void releaseID(int PID, @NotNull String team){
        if (team.equalsIgnoreCase("ladri")) {
            try{
                id_thieves.set(PID, true);
                thieves.remove(PID);
            }catch(IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }else if (team.equalsIgnoreCase("guardie")) {
            try{
                id_cops.set(PID, true);
                cops.remove(PID);
            }catch(IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
    }
}