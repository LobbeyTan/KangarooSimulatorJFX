package kangaroo.simulation.animal;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import kangaroo.simulation.utils.Animation;
import kangaroo.simulation.utils.Handler;
import kangaroo.simulation.world.World;
import kangaroo.simulation.world.Path;
import kangaroo.simulation.world.Point;

public class Kangaroo extends Creature{
    private World world;
    private Integer pointID;
    private Double pounchStorage;
    private Double pounchFood;
    private Character gender;
    private boolean movable;
    
    private Animation animDown, animUp, animLeft, animRight;
    private int width;
    private int height;
    private GraphicsContext g;

    private static final Image up1 = new Image("/resources/simulator/back_1.png");
    private static final Image up2 = new Image("/resources/simulator/back_2.png");
    private static final Image up3 = new Image("/resources/simulator/back_3.png");
    private static final Image up4 = new Image("/resources/simulator/back_4.png");
    private static final Image up5 = new Image("/resources/simulator/back_5.png");
    private static final Image[] up = {up1, up2, up3, up4, up5};
    private static final Image down1 = new Image("/resources/simulator/front_1.png");
    private static final Image down2 = new Image("/resources/simulator/front_2.png");
    private static final Image down3 = new Image("/resources/simulator/front_3.png");
    private static final Image down4 = new Image("/resources/simulator/front_4.png");
    private static final Image down5 = new Image("/resources/simulator/front_5.png");
    private static final Image[] down = {down1, down2, down3, down4, down5};
    private static final Image left1 = new Image("/resources/simulator/left_1.png");
    private static final Image left2 = new Image("/resources/simulator/left_2.png");
    private static final Image left3 = new Image("/resources/simulator/left_3.png");
    private static final Image left4 = new Image("/resources/simulator/left_4.png");
    private static final Image left5 = new Image("/resources/simulator/left_5.png");
    private static final Image left6 = new Image("/resources/simulator/left_6.png");
    private static final Image left7 = new Image("/resources/simulator/left_7.png");
    private static final Image[] left = {left1, left2, left3, left4, left5, left6, left7};
    private static final Image right1 = new Image("/resources/simulator/right_1.png");
    private static final Image right2 = new Image("/resources/simulator/right_2.png");
    private static final Image right3 = new Image("/resources/simulator/right_3.png");
    private static final Image right4 = new Image("/resources/simulator/right_4.png");
    private static final Image right5 = new Image("/resources/simulator/right_5.png");
    private static final Image right6 = new Image("/resources/simulator/right_6.png");
    private static final Image right7 = new Image("/resources/simulator/right_7.png");
    private static final Image[] right = {right1, right2, right3, right4, right5, right6, right7};
    //female kangaroo
    private static final Image fup1 = new Image("/resources/simulator/fUp_1.png");
    private static final Image fup2 = new Image("/resources/simulator/fUp_2.png");
    private static final Image fup3 = new Image("/resources/simulator/fUp_3.png");
    private static final Image fup4 = new Image("/resources/simulator/fUp_4.png");
    private static final Image fup5 = new Image("/resources/simulator/fUp_5.png");
    private static final Image[] fup = {fup1, fup2, fup3, fup4, fup5};
    private static final Image fdown1 = new Image("/resources/simulator/fDown_1.png");
    private static final Image fdown2 = new Image("/resources/simulator/fDown_2.png");
    private static final Image fdown3 = new Image("/resources/simulator/fDown_3.png");
    private static final Image fdown4 = new Image("/resources/simulator/fDown_4.png");
    private static final Image fdown5 = new Image("/resources/simulator/fDown_5.png");
    private static final Image[] fdown = {fdown1, fdown2, fdown3, fdown4, fdown5};
    private static final Image fleft1 = new Image("/resources/simulator/fLeft_1.png");
    private static final Image fleft2 = new Image("/resources/simulator/fLeft_2.png");
    private static final Image fleft3 = new Image("/resources/simulator/fLeft_3.png");
    private static final Image fleft4 = new Image("/resources/simulator/fLeft_4.png");
    private static final Image fleft5 = new Image("/resources/simulator/fLeft_5.png");
    private static final Image fleft6 = new Image("/resources/simulator/fLeft_6.png");
    private static final Image fleft7 = new Image("/resources/simulator/fLeft_7.png");
    private static final Image[] fleft = {fleft1, fleft2, fleft3, fleft4, fleft5, fleft6, fleft7};
    private static final Image fright1 = new Image("/resources/simulator/fRight_1.png");
    private static final Image fright2 = new Image("/resources/simulator/fRight_2.png");
    private static final Image fright3 = new Image("/resources/simulator/fRight_3.png");
    private static final Image fright4 = new Image("/resources/simulator/fRight_4.png");
    private static final Image fright5 = new Image("/resources/simulator/fRight_5.png");
    private static final Image fright6 = new Image("/resources/simulator/fRight_6.png");
    private static final Image fright7 = new Image("/resources/simulator/fRight_7.png");
    private static final Image[] fright = {fright1, fright2, fright3, fright4, fright5, fright6, fright7};
    //MoveKangaroo
    private static final Image mup1 = new Image("/resources/simulator/mUp_1.png");
    private static final Image mup2 = new Image("/resources/simulator/mUp_2.png");
    private static final Image mup3 = new Image("/resources/simulator/mUp_3.png");
    private static final Image mup4 = new Image("/resources/simulator/mUp_4.png");
    private static final Image mup5 = new Image("/resources/simulator/mUp_5.png");
    private static final Image[] mup = {mup1, mup2, mup3, mup4, mup5};
    private static final Image mdown1 = new Image("/resources/simulator/mDown_1.png");
    private static final Image mdown2 = new Image("/resources/simulator/mDown_2.png");
    private static final Image mdown3 = new Image("/resources/simulator/mDown_3.png");
    private static final Image mdown4 = new Image("/resources/simulator/mDown_4.png");
    private static final Image mdown5 = new Image("/resources/simulator/mDown_5.png");
    private static final Image[] mdown = {mdown1, mdown2, mdown3, mdown4, mdown5};
    private static final Image mleft1 = new Image("/resources/simulator/mLeft_1.png");
    private static final Image mleft2 = new Image("/resources/simulator/mLeft_2.png");
    private static final Image mleft3 = new Image("/resources/simulator/mLeft_3.png");
    private static final Image mleft4 = new Image("/resources/simulator/mLeft_4.png");
    private static final Image mleft5 = new Image("/resources/simulator/mLeft_5.png");
    private static final Image mleft6 = new Image("/resources/simulator/mLeft_6.png");
    private static final Image mleft7 = new Image("/resources/simulator/mLeft_7.png");
    private static final Image[] mleft = {mleft1, mleft2, mleft3, mleft4, mleft5, mleft6, mleft7};
    private static final Image mright1 = new Image("/resources/simulator/mRight_1.png");
    private static final Image mright2 = new Image("/resources/simulator/mRight_2.png");
    private static final Image mright3 = new Image("/resources/simulator/mRight_3.png");
    private static final Image mright4 = new Image("/resources/simulator/mRight_4.png");
    private static final Image mright5 = new Image("/resources/simulator/mRight_5.png");
    private static final Image mright6 = new Image("/resources/simulator/mRight_6.png");
    private static final Image mright7 = new Image("/resources/simulator/mRight_7.png");
    private static final Image[] mright = {mright1, mright2, mright3, mright4, mright5, mright6, mright7};
    
    public Kangaroo(Integer pointID, Integer pounchStorage, Character gender, World world) {
        this.pointID = pointID;
        this.pounchStorage = pounchStorage.doubleValue();
        this.pounchFood = 0.0;
        this.gender = gender;
        this.movable = movable();
        this.world = world;
        Point currentPoint = world.getPoint(pointID);
        currentPoint.addKangaroo(this);
        currentPoint.setFoodAmount(this.fillPounchFood(currentPoint.getFoodAmount()));
        
        initAnimation();
    }

    public Kangaroo(Integer pointID, Integer pounchStorage, Character gender, World world, Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        this.pointID = pointID;
        this.pounchStorage = pounchStorage.doubleValue();
        this.pounchFood = 0.0;
        this.gender = gender;
        this.movable = movable();
        this.world = world;
        Point currentPoint = world.getPoint(pointID);
        currentPoint.addKangaroo(this);
        currentPoint.setFoodAmount(this.fillPounchFood(currentPoint.getFoodAmount()));
        
        initAnimation();
    }
    
    private void initAnimation() {
        if(this.gender.equals('M')){
            animDown = new Animation(75, down);
            animUp = new Animation(75, up);
            animLeft = new Animation(75, left);
            animRight = new Animation(75, right);
        }
        else{
            animDown = new Animation(75, fdown);
            animUp = new Animation(75, fup);
            animLeft = new Animation(75, fleft);
            animRight = new Animation(75, fright);
        }
    }

    private boolean movable() {
        if(gender.equals('M')) return true;
        return false;
    }

    public boolean migrate() {
        ArrayList<Point> nearbyPoints = world.getNearbyPoints(pointID);
        ArrayList<Path> nearbyPaths = world.getNearbyPaths(pointID);
        boolean move = false;
        Point desPoint = world.getPoint(pointID);
        Double currentFood = world.getPoint(pointID).getFoodAmount().doubleValue();

        for(int i = 0; i < nearbyPoints.size(); i++) {
            Point currentPoint = nearbyPoints.get(i);
            Path currentPath = nearbyPaths.get(i);
            Double foodNeeded = foodNeeded(currentPath.getHeight());
            Double foodRemainAfterMove = currentPoint.getFoodAmount() - foodNeeded + pounchFood;
            if(currentPoint.getColony()) {
                    foodNeeded += currentPoint.getKangarooNumber();
                    foodRemainAfterMove = pounchFood - foodNeeded;
                    if(foodRemainAfterMove < 0) continue;
            }

            if(foodRemainAfterMove >= currentFood) {
                    if(foodRemainAfterMove == currentFood && currentPoint.getFemaleKangaroo() <= desPoint.getFemaleKangaroo()) continue;
                    move = true;
                    currentFood = foodRemainAfterMove;
                    desPoint = currentPoint;
            }
        }

        if(!move) return false;
        world.migrate(this, pointID, desPoint.getID());
        desPoint.setFoodAmount(fillPounchFood(currentFood));
        return move;
    }

    public Double foodNeeded(Integer height) {
        return (height + (0.5 * pounchFood));
    }

    public Double fillPounchFood(Double food) {
        this.pounchFood = food.doubleValue();
        if(pounchFood > pounchStorage) {
                Double remainder = pounchFood - pounchStorage;
                pounchFood = pounchStorage.doubleValue();
                return remainder;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return String.format("%d %d %s", pointID, pounchFood.intValue(), gender);
    }

    public Integer getCurrentPointID() {return this.pointID;}
    public Double getPounchStorage() {return this.pounchStorage;}
    public Double getPounchFood() {return this.pounchFood;}
    public Character getGender() {return this.gender;}
    public boolean getMovable() {return this.movable;}
    public Point getPoint() {return world.getPoint(this.getCurrentPointID());}
    @Override
    public int getWidth() {return width/2;}
    @Override
    public int getHeight() {return height/2;}
    
    public void setPointID(Integer pointID) {this.pointID = pointID;}
    public void setPounchFood(Double pounchFood) {this.pounchFood = pounchFood;};
    public void setMovable(boolean movable) {this.movable = movable;}
    
    public int xMap() {return (int)(this.x + getPoint().getX());}
    public int yMap() {return (int)(this.y + getPoint().getY());}
    
    private int cnt = 0;
    @Override
    public void update() {
        animDown.update();
        animUp.update();
        animLeft.update();
        animRight.update();
        if(cnt == 0) {
            move();
            cnt = (int)(Math.random() * 50) + 50;
        }
        cnt--;
    }
    
    public void setDimension() {
        height = getPoint().getHeight();
        width = getPoint().getWidth();
    }

    @Override
    public void draw(GraphicsContext g) {
        g.drawImage(getCurrentAnimationFrame(), x + getPoint().getX(), y + getPoint().getY(), width/2, height/2);
    }
    
    private Image getCurrentAnimationFrame(){
        if(xMove == -1 && yMove == 0){
            return animLeft.getCurrentFrame();
        }
        else if(xMove == 1 && yMove == 0){
            return animRight.getCurrentFrame();
        }
        else if(yMove == -1 && xMove == 0){
            return animUp.getCurrentFrame();
        }
        else if(yMove == 1 && xMove == 0){
            return animDown.getCurrentFrame();
        }
        else{

            return animDown.getFrame()[0];
        }
    }
    
    public void move() {
        int num = (int)(Math.random()*7);
        int pointX = (int)getPoint().getX();
        int pointY = (int)getPoint().getY();
        xMove = 0;
        yMove = 0;
        if (num == 0) {// Moving Left
            if (x + pointX >= pointX ){
                    x -= getPoint().getWidth()/7;
                    xMove = -1;
                    yMove = 0;
            }
        } else if (num == 1) {// Moving Right
            if (x + pointX <= pointX + width - width/1.9){
                    x += getPoint().getWidth()/7;
                    xMove = +1;
                    yMove = 0;
            }
        } else if (num == 2) {// Moving Down
            if (y + pointY < pointY + height - height/1.5){
                    y += getPoint().getWidth()/7;
                    yMove = 1;
                    xMove = 0;
            }
        } else if (num == 3) {// Moving Up
            if (y + pointY > pointY - height/5){
                    y -= getPoint().getWidth()/7;
                    yMove = -1;
                    xMove = 0;
            }
        } else{
            xMove = 0;
            yMove = 0;
        }
    }
    
    //kangaroo animation when moved
    public void moveKangaroo(){   
        animDown.setFrame(mdown);
        animUp.setFrame(mup);
        animLeft.setFrame(mleft);
        animRight.setFrame(mright);
        update();
    }
    
    // kangaroo animation reset to default
    public void resetDefault() {
    	if(this.gender.equals('M')){
            animDown.setFrame(down);
            animUp.setFrame(up);
            animLeft.setFrame(left);
            animRight.setFrame(right);
        }
        else{
            animDown.setFrame(fdown);
            animUp.setFrame(fup);
            animLeft.setFrame(fleft);
            animRight.setFrame(fright);
        }
        update();
    }
}
