package kangaroo.simulation.world;




import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import kangaroo.simulation.Entity.Entity;
import kangaroo.simulation.animal.Kangaroo;
import kangaroo.simulation.utils.Handler;


public class Point extends Entity {
    private static final Image pointImg = new Image("/resources/simulator/point.png");
    private static final Image foodImg = new Image("/resources/simulator/apple.png");
    private static final Image colonyImg = new Image("/resources/simulator/colony.png");
    
    private Integer ID;
    private Double food;
    private Integer maxKangaroo;
    private Integer pathNumber;
    private Path pointPath;
    private Point nextPoint;
    private ArrayList<Kangaroo> kangaroos;
    private boolean hasColony;
    
    private int x, y, width, height;
    
    private double[] appleX;
    private double[] appleY;
    private int appleSize;
    
    
    
    public Point() {
        ID = null;
        food = null;
        maxKangaroo = null;
        pathNumber = null;
        kangaroos = new ArrayList<>();
    }

    public Point(Integer ID, Point nextPoint) {
        this.ID = ID;
        this.nextPoint = nextPoint;
        kangaroos = new ArrayList<>();
    }

    public Point(Integer ID, Point nextPoint, Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        this.ID = ID;
        this.nextPoint = nextPoint;
        kangaroos = new ArrayList<>();
    }
	
    public void setInfo(Integer food, Integer maxKangaroo, Integer pathNumber) {
        this.food = food.doubleValue();
        this.maxKangaroo = maxKangaroo;
        this.pathNumber = pathNumber;
    }
    
    public void setPosition(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void addKangaroo(Kangaroo kangaroo) {
        if(kangaroos.size() == maxKangaroo) {System.out.println("Point " + ID + " has reached maximum number of kangaroos"); return;}
        kangaroos.add(kangaroo);
    }

    public void removeKangaroo(Kangaroo kangaroo) {
        for(int i = 0; i < kangaroos.size(); i++) {
                if(kangaroos.get(i).equals(kangaroo)) {kangaroos.remove(i); break;}
        }
    }

    public Integer getFemaleKangaroo() {
        int female = 0;
        for(int i = 0; i < kangaroos.size(); i++) {
                if(kangaroos.get(i).getGender().equals('F')) female ++;
        }
        return female;
    }

    public Integer getID() {return this.ID;}
    public Double getFoodAmount() {return this.food;}
    public Integer maxKangaroo() {return this.maxKangaroo;}
    public Integer pathNumber() {return this.pathNumber;}
    public Integer getKangarooNumber() {return this.kangaroos.size();}
    public Path getPath() {return this.pointPath;}
    public Point getNextPoint() {return this.nextPoint;}
    public ArrayList<Kangaroo> getKangaroos() {return this.kangaroos;}
    public boolean getColony() {return this.hasColony;}
    public boolean hasKangaroo() {return (getKangarooNumber() != 0);}

    @Override
    public int getWidth() {return this.width;}
    @Override
    public int getHeight() {return this.height;}
    @Override
    public float getX(){return this.x;}
    @Override
    public float getY(){return this.y;}


    public void setFoodAmount(Double food) {this.food = (food >= 0)?food : 0;}
    public void setNextPoint(Point nextPoint) {this.nextPoint = nextPoint;}
    public void setPath(Path pointPath) {this.pointPath = pointPath;}
    public void setColony(boolean hasColony) {this.hasColony = hasColony;}
    public void setWidth(int width) {this.width = width;}
    public void setHeight(int height) {this.height = height;}
          
    
    
    public void show() {
        System.out.printf("ID: %d Remainder food: %d Kangaroos: %d hasColony: %s\n", ID, getFoodAmount().intValue(), getKangarooNumber(), getColony());
    }

    @Override
    public String toString() {
        return ID + " --> ";
    }

    @Override
    public void update() {
        
    }

    
    @Override
    public void draw(GraphicsContext g) {
        if(getColony()){
            g.drawImage(colonyImg, x, y, width, height);
        }
        else{
            g.drawImage(pointImg, x, y, width, height);
        }

        for(int i = 0; i < appleX.length; i++) {
            g.drawImage(foodImg, appleX[i], appleY[i], appleSize, appleSize);
        }
    }
    
    public void drawEntity(GraphicsContext g) {
        appleSize = width/15;
        appleX = new double[getFoodAmount().intValue()];
        appleY = new double[getFoodAmount().intValue()];
        for (int i = 0; i < getFoodAmount().intValue(); i++) {
            appleX[i] = x + width/10 + Math.random()*(width*0.7);
            appleY[i] = y + height/10 + Math.random()*(height*0.7);  
        }
    }
}
