
package kangaroo.simulation.utils;

import kangaroo.simulation.FX.SimulatorController;
import kangaroo.simulation.world.World;

public class Handler {

	private SimulatorController simulator;
	private World world;

	public Handler(SimulatorController simulator) {
		this.simulator = simulator;
	}

	public Camera getGameCamera() {
		return simulator.getCamera();
	}

	public int getWidth() {
		return simulator.getWidth();
	}

	public int getHeight() {
		return simulator.getHeight();
	}

	public SimulatorController getGame() {
		return simulator;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
