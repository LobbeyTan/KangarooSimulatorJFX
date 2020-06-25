
package kangaroo.simulation.Tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Tile {

	// CLASS
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	protected Image texture;
	protected final int id;

	public Tile(Image texture, int id) {
            this.texture = texture;
            this.id = id;

//		tiles[id] = this;
	}

	public void tick() {

	}

	public void render(GraphicsContext g, int x, int y) {
            g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT);
	}

	public boolean isSolid() {
            return false;
	}

	public int getId() {
            return id;
	}
}
