
package kangaroo.simulation.Entity;

import java.awt.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import kangaroo.simulation.utils.Handler;

public abstract class Entity {
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected boolean active = true;
	protected Rectangle bounds;
        
        public Entity() {
            // EMPTY CONSTRUCTOR
        }

	public Entity(Handler handler, float x, float y, int width, int height) {
            this.handler = handler;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;

            bounds = new Rectangle(0, 0, width, height);
	}

	public boolean isActive() {
            return active;
	}

	public void setActive(boolean active) {
            this.active = active;
	}

	public float getX() {
            return x;
	}

	public void setX(float x) {
            this.x = x;
	}

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

	public float getY() {
            return y;
	}

	public void setY(float y) {
            this.y = y;
        }

	public abstract void update();

	public abstract void draw(GraphicsContext g);

	public boolean checkEntityCollisions(float xOffset, float yOffset) {
            for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
                if (e.equals(this))
                        continue;
                if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                        return true;
            }
            return false;
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
            return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
                           
	}
}
