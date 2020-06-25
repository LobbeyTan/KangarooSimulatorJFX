
package kangaroo.simulation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.text.Font;

public class FontLoader {

    public static Font loadFont(String path, float size) {
        try {
            return Font.loadFont(new FileInputStream(path), size);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
