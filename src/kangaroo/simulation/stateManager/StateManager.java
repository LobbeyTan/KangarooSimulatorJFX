package kangaroo.simulation.stateManager;

import javafx.scene.canvas.GraphicsContext;

public abstract class StateManager {
	public static int currentLevel;

	public abstract void init();

	public abstract void update();

	public abstract void draw(GraphicsContext graphic);
}
