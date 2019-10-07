package GEL;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.Image;

public class Actor {

    private final int SPACE = 20;

    private int x;
    private int y;
    private Image image;

    @Contract(pure = true)
    public Actor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

//    boolean checkCollision(Actor actor, int type){
//        return false;
//    }

    boolean isLeftCollision(@NotNull Actor actor) {
        return ((this.x() - SPACE) == actor.x()) && (this.y() == actor.y());
    }

    boolean isRightCollision(@NotNull Actor actor) {
        return ((this.x() + SPACE) == actor.x()) && (this.y() == actor.y());
    }

    boolean isTopCollision(@NotNull Actor actor) {
        return ((this.y() - SPACE) == actor.y()) && (this.x() == actor.x());
    }

    boolean isBottomCollision(@NotNull Actor actor) {
        return ((this.y() + SPACE) == actor.y()) && (this.x() == actor.x());
    }
}