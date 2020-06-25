
package kangaroo.simulation.utils;

import javafx.scene.image.Image;

public class Animation {

    private int speed, index;
    private long lastTime, timer;
    private Image[] frames;

    public Animation(int speed, Image[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void update() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
                index++;
                timer = 0;
                if (index >= frames.length)
                        index = 0;
        }
    }
    
    public void setFrame(Image[] frames) {
        this.frames = frames;
    }

    public Image getCurrentFrame() {
        return frames[index];
    }
    
    public Image[] getFrame () {return this.frames;}
}
