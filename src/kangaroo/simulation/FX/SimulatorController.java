package kangaroo.simulation.FX;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import kangaroo.simulation.animal.Kangaroo;
import kangaroo.simulation.utils.Camera;
import kangaroo.simulation.utils.Handler;
import kangaroo.simulation.world.Path;
import kangaroo.simulation.world.Point;
import kangaroo.simulation.world.World;

public class SimulatorController implements Initializable {
    private static final double MAX_SCALE = 10.0d;
    private static final double MIN_SCALE = 1.0d;
    private static final double MAX_SPEED = 60;
    private static final double MIN_SPEED = 60 / Math.pow(1.5, 5);
    
    @FXML
    private Canvas canvas;
    @FXML
    private CheckBox pathBox;
    @FXML
    private AnchorPane root;
    @FXML
    private ScrollPane scrollPane;
    
    public static int canvasWidth;
    public static int canvasHeight;
    public static World world;
    public static boolean start;
    public static boolean hasInitialized;
    public static boolean worldCreated;
    public static AnimationTimer runtimer;
    public static Integer[] migration = new Integer[2];
    
    public static int kSize;
    public static int pSize;
    public static double speed;
    public static double nanoTime;
    public static ArrayList<Kangaroo> kangaroos;
    public static ArrayList<Point> points;
    public static ArrayList<Point> movingPath;
    
    private Camera camera;
    private Handler handler;
    private GraphicsContext graphic;
    
    private static boolean drawAllPath;
    private static boolean drawDetail;
    private static boolean isPoint;
    private static int currentPoint;
    private static int migrateCnt;
    protected static int terminateCnt;
    private static int appleCnt;
    private static int currentKangaroo;
    private static int lastKangaroo;
    
    private static int cursorX;
    private static int cursorY;
    private static int translateX;
    private static int translateY;
    private static String detail;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateCanvasDimension();
        init();
        run();
    }
    
    @FXML
    private void displayPath(ActionEvent event) {
        drawAllPath = !drawAllPath;
    }
    
    private void init() {
        start = false;
        hasInitialized = false;
        worldCreated = false;
        migration = new Integer[2];
        
        kSize = 0;
        pSize = 0;
        speed = 60;
        nanoTime = 1000000000.0 / speed; // 16666666.7
        
        kangaroos = new ArrayList<>();
        points = new ArrayList<>();
        movingPath = new ArrayList<>();
        graphic = canvas.getGraphicsContext2D();
        
        drawAllPath = false;
        drawDetail = false;
        isPoint = false;
        
        migrateCnt = 300;
        terminateCnt = 0;
        appleCnt = 0;
        currentKangaroo = 0;
        lastKangaroo = 0;
        
        handler = new Handler(this);
        world = new World(handler);
        camera = new Camera(handler, 0 ,0);
    }
    
    private void mapInit() {
        try {
            Scanner pointInput = null;
            Scanner kangarooInput = null;
            if(PointGeneratorController.usingSample) {
                
                pointInput = new Scanner(getClass().getResourceAsStream("/sampleTest/test_" + PointGeneratorController.sample + "/pointConfig.txt"));
                kangarooInput = new Scanner(getClass().getResourceAsStream("/sampleTest/test_" + PointGeneratorController.sample + "/kangarooConfig.txt"));
                
            }else{
                pointInput = new Scanner(new FileInputStream("pointConfig.txt"));
                kangarooInput = new Scanner(new FileInputStream("kangarooConfig.txt"));
            }
            
            while(pointInput.hasNextLine()) {
                Integer[] pointsInfo = stringToInt(pointInput.nextLine().split(" "));
                if(pointsInfo.length == 4) {
                    int ID = pointsInfo[0];
                    world.addPoint(ID);
                    world.getPoint(ID).setInfo(pointsInfo[1], pointsInfo[2], pointsInfo[3]);
                    points.add(world.getPoint(ID));
                    
                }else {
                    world.addPath(pointsInfo[0], pointsInfo[1], pointsInfo[2]);
                }
                
            }
           
            while(kangarooInput.hasNextLine()) {
                String[] kangaroosInfo = kangarooInput.nextLine().split(" ");
                kangaroos.add(new Kangaroo(Integer.parseInt(kangaroosInfo[0]), Integer.parseInt(kangaroosInfo[1]), kangaroosInfo[2].charAt(0) , world));
            }

            world.thresholdValue((PointGeneratorController.usingSample) ? 3 : KangarooGeneratorController.currentThreshold);
            world.displayPointsInfo();
            world.setPointPosition();
            world.addtoEntity();
            kSize = kangaroos.size();
            pSize = points.size();
            hasInitialized = true;
            System.out.println("Has initialized"); pointInput.close(); kangarooInput.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SimulatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addListener();
    }
    
    private void addListener() {
        ////////////////////////////SELECT///////////////////////////////
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
            cursorX = (int)event.getX();
            cursorY = (int)event.getY();
            translateX = (int)canvas.getTranslateX();
            translateY = (int)canvas.getTranslateY();
            
            if(event.isMiddleButtonDown()) {canvas.setTranslateX(0); canvas.setTranslateY(0); return;}
            if(event.isSecondaryButtonDown()) {canvas.setCursor(Cursor.CLOSED_HAND);return;}
            
            for(int i = 0; i < kSize; i++) {
                 Kangaroo currentK = kangaroos.get(i);
                 if(cursorX > currentK.xMap() && cursorX < (currentK.xMap() + currentK.getWidth()) && cursorY > currentK.yMap() && cursorY < (currentK.yMap() + currentK.getHeight())) {
                     detail = String.format("Point ID: %s\nGender: %s\nPouchFood: %s\nPouchStorage: %s", 
                                             currentK.getPoint().getID(), currentK.getGender(), currentK.getPounchFood().intValue(), currentK.getPounchStorage().intValue()); 
                     drawDetail = true; isPoint = false; currentPoint = currentK.getPoint().getID();
                     return;
                 }
            }
            for(int i = 0; i < pSize; i++) {
                Point currentP = points.get(i);
                if(cursorX > currentP.getX() && cursorX < currentP.getX() + currentP.getWidth() && cursorY > currentP.getY() && cursorY < currentP.getY() + currentP.getHeight()) {
                    detail = String.format("Point ID: %s\nEdges: %s\n" + ((currentP.getColony())?"Shared Food:" : "Food:") + "%s\nKangaroo: %s", currentP.getID(), world.getNearbyID(currentP.getID()), currentP.getFoodAmount().intValue(), currentP.getKangarooNumber()); 
                    drawDetail = true; isPoint = true; currentPoint = currentP.getID();
                    return;
                }
            }
            event.consume();
        });
        
        ///////////////////////////PANNING/////////////////////////////
        
        canvas.addEventHandler(MouseDragEvent.MOUSE_DRAGGED, (event) -> {
            if(event.isPrimaryButtonDown()) return;
            
            double scale = canvas.getScaleY();
            
            canvas.setTranslateX(translateX + (( event.getX() - cursorX) / scale));
            canvas.setTranslateY(translateY + (( event.getY() - cursorY) / scale));
            checkBound();
            event.consume();
        });
        
        //////////////////////////RESET///////////////////////////////
        canvas.requestFocus();
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, (event) -> {
            drawDetail = false;
            canvas.setCursor(Cursor.DEFAULT);
            event.consume();
        });
        
        /////////////////////////ZOOM///////////////////////////////
        
        canvas.addEventHandler(ScrollEvent.SCROLL, (event) -> {
            double delta = 1.2;

            double scale = canvas.getScaleY();
            double oldScale = scale;
            
            if (event.getDeltaY() < 0) scale /= delta;
            else scale *= delta;
            
            if(Double.compare(scale, MAX_SCALE) > 0) scale = MAX_SCALE;
            if(Double.compare(scale, MIN_SCALE) < 0) scale = MIN_SCALE;

            double f = (scale / oldScale)-1;

            double dx = (event.getX() - (canvas.getBoundsInParent().getWidth()/2 + canvas.getBoundsInParent().getMinX())); 
            double dy = (event.getY() - (canvas.getBoundsInParent().getHeight()/2 + canvas.getBoundsInParent().getMinY()));

            canvas.setScaleX(scale);
            canvas.setScaleY(scale);

            canvas.setTranslateX(canvas.getTranslateX() - (f*dx));
            canvas.setTranslateY(canvas.getTranslateY() - (f*dy));
            checkBound();
            event.consume();
        });
    }
    
    private void checkBound() {
        double xBound = ((canvas.getScaleX()-1) * canvasWidth)/2;
        double yBound = ((canvas.getScaleY()-1) * canvasHeight)/2;
        
        double tx = canvas.getTranslateX();
        double ty = canvas.getTranslateY();
        
        if(tx < 0 && tx < -xBound) canvas.setTranslateX(-xBound);
        else if(tx > xBound) canvas.setTranslateX(xBound);
        if(ty < 0 && ty < -yBound) canvas.setTranslateY(-yBound);
        else if(ty > yBound) canvas.setTranslateY(yBound);
    }

    private void run() {
//        mapInit();
//        updateEntity();
//        start = true;
        runtimer = new AnimationTimer() {
            long lastTime = System.nanoTime();
            long timer = System.currentTimeMillis();
            double delta = 0.0;
            int updates = 0;
            int frames = 0;
            
            @Override
            public void handle(long now) {
                if(start) {
                    if(worldCreated && !hasInitialized) {mapInit(); updateEntity();}
                    
                    delta += (now - lastTime) / nanoTime;
                    lastTime = now;

                    if(delta >= 1.0) {
                        update();
                        updates ++;
                        delta--;
                    }

                    draw();
                    frames++;
                    if(System.currentTimeMillis() - timer > 1000) {
                        timer += 1000;
                        System.out.println("UPS: " + updates + "\t" + "FPS: " + frames);
                        updates = 0;
                        frames = 0;
                    }
                }
            }
        };
        runtimer.start();
    }
    
    private void update() {
        world.update();
        if(hasInitialized) {
            if(terminateCnt == kSize) {showEndAlert();}
            if(migrateCnt == 0) {
                kangarooAnimation(lastKangaroo, currentKangaroo);
                movingPath.clear();
                world.updateColony();      
                if(kangaroos.get(currentKangaroo).getMovable())
                    if(world.optimumPath(kangaroos.get(currentKangaroo))){world.displayPointsInfo(); terminateCnt = 0;}
                
                updateEntity();
                lastKangaroo = currentKangaroo;
                currentKangaroo = (currentKangaroo + 1) % kSize; terminateCnt++;
                migrateCnt = 300;
                System.out.println("terminate counter: " + terminateCnt);
            }
            migrateCnt--;
        }
    }
    
    private void showEndAlert() {
        if(HomeController.isTranslating) return;
        HomeController.tempScene = HomeController.currentScene;
        HomeController.currentScene = HomeController.DISABLESCENESWITCH;
        
        BorderPane container = (BorderPane) HomeController.currentRoot.getParent();
        Group alert = (Group) container.getRight();
        if(alert == null) return;
        
        start = false;
        alert.setDisable(false);
        alert.setVisible(true);
        alert.toFront();
    }
    
    private void kangarooAnimation(int reset, int move) {
        if(reset == 0 && move == 0) {kangaroos.get(move).moveKangaroo(); return;}
        kangaroos.get(reset).resetDefault();
        kangaroos.get(move).moveKangaroo();
    }
    
    private void draw() {
        graphic.clearRect(0, 0, canvasWidth, canvasHeight);
        world.draw(graphic);
        if(drawAllPath) drawAllPath();
        drawMovingPath();
        
        if(drawDetail) {
            drawSelectedPath();
            graphic.setFill(Color.BLUEVIOLET);
            if(cursorX + 150 > canvasWidth) {
                if(isPoint) graphic.setFill(Color.web("#296DFF"));
                graphic.fillRoundRect(cursorX - 150, cursorY - 100, 150, 100, 30, 30);
                graphic.setFill(Color.BLACK);
                graphic.fillText(detail, cursorX -140, cursorY-75, 130);
            }else if(cursorY - 100 < 0) {
                if(isPoint) graphic.setFill(Color.web("#296DFF"));
                graphic.fillRoundRect(cursorX, cursorY, 150, 100, 30, 30);
                graphic.setFill(Color.BLACK);
                graphic.fillText(detail, cursorX + 10, cursorY+25, 130);
            }else {
                if(isPoint) graphic.setFill(Color.web("#296DFF"));
                graphic.fillRoundRect(cursorX, cursorY - 100, 150, 100, 30, 30);
                graphic.setFill(Color.BLACK);
                graphic.fillText(detail, cursorX + 10, cursorY-75, 130);
            }
        }
    }
    
    private void drawSelectedPath() {
        Point currentP = world.getPoint(currentPoint);
        ArrayList<Path> neighbour = world.getNearbyPaths(currentPoint);
        int centerX = (int)(currentP.getX() + currentP.getWidth()/2);
        int centerY = (int)(currentP.getY() + currentP.getHeight()/2);
        for(int i = 0; i < neighbour.size(); i++) {
            currentP = neighbour.get(i).getNextPoint();
            int height = neighbour.get(i).getHeight();
            int cX = (int)(currentP.getX() + currentP.getWidth()/2);
            int cY = (int)(currentP.getY() + currentP.getHeight()/2);
            graphic.setStroke(Color.rgb(255 - (i * 30), i * 12, (int)(Math.random()*255)));
            graphic.setLineWidth(5);
            graphic.strokeLine(centerX, centerY, cX, cY);
            drawHeight(centerX, centerY, cX, cY, height, false);
        }
    }
    
    private void drawMovingPath() {
        if(movingPath.isEmpty()) return;
        for(int i = 0; i < movingPath.size()-1; i++) {
            Point currentP = movingPath.get(i);
            Point nextP = movingPath.get(i+1);
            
            int centerX = (int)(currentP.getX() + currentP.getWidth()/2);
            int centerY = (int)(currentP.getY() + currentP.getHeight()/2);
            int cX = (int)(nextP.getX() + nextP.getWidth()/2);
            int cY = (int)(nextP.getY() + nextP.getHeight()/2);
            
            graphic.setLineWidth(5);
            graphic.setStroke(Color.RED);
            graphic.strokeLine(centerX, centerY, cX, cY);
        }
    }
    
    private void drawAllPath() {
        int r = 0, g = 0, b = 0;
        for(int i = 0; i < pSize; i++) {
            Point currentP = points.get(i);
            int centerX = (int)(currentP.getX() + currentP.getWidth()/2);
            int centerY = (int)(currentP.getY() + currentP.getHeight()/2);
            ArrayList<Path> paths = world.getNearbyPaths(currentP.getID());
            for(int j = 0; j < paths.size(); j++) {
                currentP = paths.get(j).getNextPoint();
                int height = paths.get(j).getHeight();
                int cX = (int)(currentP.getX() + currentP.getWidth()/2);
                int cY = (int)(currentP.getY() + currentP.getHeight()/2);
                r = (int)(j * 57)%255; g = (int)(j * 37 + 20)%255; b = (int)(255 - j*37)%255;
                graphic.setStroke(Color.rgb(r,g,b));
                graphic.setLineWidth(5);
                graphic.strokeLine(centerX, centerY, cX, cY);
                drawHeight(centerX, centerY, cX, cY, height, true);
            }
        }
    }
    
    private void drawHeight(int cx, int cy, int px, int py, int height, boolean displayAll) {
        int[] midpoint = {(cx+px)/2, (cy+py)/2};
        midpoint[0] = (cx + midpoint[0])/2; midpoint[1] = (cy + midpoint[1])/2;
        
        if(displayAll) graphic.setFill(Color.BLACK);
        else graphic.setFill(Color.DARKORCHID);
        graphic.fillRect(midpoint[0], midpoint[1] - 40, 40, 40);
        graphic.setFill(Color.WHITE);
        graphic.fillText(String.valueOf(height), midpoint[0]+15, midpoint[1]-15, 20);
    }
    
    public void updateEntity() {
        if(appleCnt % 2 == 0) {
            Point currentP = points.get((int)(Math.random()*pSize));
            currentP.setFoodAmount(currentP.getFoodAmount() + 2);
        }
        for(int i = 0; i < pSize; i++) {
            points.get(i).drawEntity(graphic);
        }
        appleCnt++;
    }
    
    public void updateCanvasDimension() {
        canvasWidth = (int) canvas.getWidth();
        canvasHeight = (int) canvas.getHeight();
    }
    
    public static void setSpeed(boolean increase) {
        if(increase) speed *= 1.5;
        else speed /= 1.5;
        
        if(speed <= MIN_SPEED) speed = MIN_SPEED;
        if(speed >= MAX_SPEED) speed = MAX_SPEED;
        
        nanoTime = 1000000000.0 / speed;
    }
    
    private Integer[] stringToInt(String[] arr) {
        Integer[] num = new Integer[arr.length];
        for(int i = 0; i < arr.length; i++) {
            num[i] = Integer.parseInt(arr[i]);
        }
        return num;
    }
    
    public int[] totalKangaroo() {
        int[] kangaroo = new int[2];
        for(int i = 0; i < kSize; i++) {
            if(kangaroos.get(i).getGender() == 'M') kangaroo[0] += 1;
            else kangaroo[1] += 1;
        }
        return kangaroo;
    }
    
    public int[] worldUpdates() {
        int[] updates = new int[3];
        for(int i = 0; i < pSize; i++) {
            Point currentP = points.get(i);
            if(!currentP.getColony()) {
                ArrayList<Kangaroo> list = currentP.getKangaroos();
                for(int j = 0; j < list.size(); j++) {
                    if(list.get(j).getGender() == 'M') updates[0] += 1;
                    else updates[1] += 1;
                }
            }else updates[2] += 1;
        }
        return updates;
    }
    
    public static void getMigration(int src, int des, List<Integer> movePath) {
        migration[0] = src;
        migration[1] = des;

        for(int i = 0; i < movePath.size(); i++) {
            movingPath.add(world.getPoint(movePath.get(i)));
        }
    }
    
    public Camera getCamera() {
        return camera;
    }

    public static int getWidth() {
        return canvasWidth;
    }

    public static int getHeight() {
        return canvasHeight;
    }
}
