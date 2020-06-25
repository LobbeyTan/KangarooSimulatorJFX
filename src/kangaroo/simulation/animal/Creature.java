
package kangaroo.simulation.animal;

import kangaroo.simulation.Entity.Entity;
import kangaroo.simulation.Tile.Tile;
import kangaroo.simulation.utils.Handler;



public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

    protected float speed;
    protected float xMove, yMove;
    protected boolean moveLeft = false;
    protected boolean moveRight = false;
    protected boolean moveUp = false;
    protected boolean moveDown = false;
    protected boolean attack = false;
    
    public Creature() {
        // EMPTY CONSTRUCTOR
    }

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    // Getter and Setter
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
