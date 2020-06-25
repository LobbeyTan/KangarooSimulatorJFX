
package kangaroo.simulation.Entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;


import javafx.scene.canvas.GraphicsContext;
import kangaroo.simulation.utils.Handler;
import kangaroo.simulation.world.World;


public class EntityManager {

    private Handler handler;

    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {

        @Override
        public int compare(Entity a, Entity b) {
            if (a.getY() + a.getHeight() < b.getY() + b.getHeight())
                    return -1;
            return 1;
        }
    };

    public EntityManager(Handler handler) {
        this.handler = handler;

        entities = new ArrayList<Entity>();

    }
    
    boolean start = false;
    public void update() {
        for(Entity e : entities) {
            e.update();
        }
    }
    

    public void draw(GraphicsContext g) {
        for (Entity e : entities) {
            if(!start){System.out.println(e);}
                e.draw(g);
        }
        start = true;
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    // Getter and Setter

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
