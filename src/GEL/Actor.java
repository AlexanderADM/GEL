package GEL;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.Image;

class Actor {

    private final int SPACE = 20;

    private int x;
    private int y;
    private Image image;

    @Contract(pure = true)
    Actor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Image getImage() {
        return this.image;
    }

    void setImage(Image img) {
        image = img;
    }

    int x() {
        return this.x;
    }

    int y() {
        return this.y;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
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