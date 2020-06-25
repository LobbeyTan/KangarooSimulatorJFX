
package kangaroo.simulation.utils;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class SpriteSheet {
	private Image sheet;

	public SpriteSheet(Image sheet) {
		this.sheet = sheet;
	}

	public Image crop(int x, int y, int width, int height) {
		return new WritableImage(sheet.getPixelReader(), x, y, width, height);
	}
}
