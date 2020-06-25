package kangaroo.simulation.world;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import kangaroo.simulation.Entity.EntityManager;
import kangaroo.simulation.FX.SimulatorController;
import kangaroo.simulation.Tile.Tile;
import kangaroo.simulation.animal.Kangaroo;
import kangaroo.simulation.stateManager.StateManager;
import kangaroo.simulation.utils.Handler;

public class World extends StateManager{
    private Handler handler;
    private int width, height;
    private EntityManager entityManager;
    
    private Point head;
    private Integer threshold;
    
    private static final Image background = new Image("/resources/simulator/sea.png"); 
    
    public World() {
        head = null;
    }
    
    public World(Handler handler) {
        this.handler = handler;
        entityManager = new EntityManager(handler);
        init();
    }

    public void addPoint(Integer ID) {
        Point newPoint = new Point(ID, null);
        if(isEmpty()) {head = newPoint; return;}

        Point currentPoint = head;
        while(currentPoint.getNextPoint() != null) {
                currentPoint = currentPoint.getNextPoint();
        }
        currentPoint.setNextPoint(newPoint);
    }

    public void addAllPoints(Integer[] IDs) {
        for(Integer e: IDs) addPoint(e);
    }

    public boolean addPath(Integer srcID, Integer desID, Integer height) {
        if(getPoint(srcID) == null || getPoint(desID) == null) return false;

        Point currentPoint = head;
        while(currentPoint != null) {
            if(currentPoint.getID().equals(srcID)) {
                Point desPoint = getPoint(desID);
                Path newPath = new Path(desPoint, null, height);
                Path currentPath = currentPoint.getPath();
                if(currentPath == null) currentPoint.setPath(newPath);
                else {
                        while(currentPath.getNextPath() != null) currentPath = currentPath.getNextPath();
                        currentPath.setNextPath(newPath);
                }
                return true;
            }
            currentPoint = currentPoint.getNextPoint();
        }

        return false;
    }

    public boolean deletePath(Integer srcID, Integer desID) {
        if(!isValidPath(srcID, desID)) return false;

        Point currentPoint = head;
        while(currentPoint != null) {
            if(currentPoint.getID().equals(srcID)) {
                Point desPoint = getPoint(desID);
                Path currentPath = currentPoint.getPath();
                Path previousPath = null;
                while(currentPath != null) {
                        if(currentPath.getNextPoint().equals(desPoint)) {
                                if(previousPath == null) currentPoint.setPath(currentPath.getNextPath());
                                else previousPath.setNextPath(currentPath.getNextPath());
                                return true;
                        }
                        previousPath = currentPath;
                        currentPath = currentPath.getNextPath();
                }
            }
            currentPoint = currentPoint.getNextPoint();
        }
        return false;
    }

    public boolean isValidPath(Integer srcID, Integer desID) {
        if(getPoint(srcID) == null || getPoint(desID) == null) return false;

        Point currentPoint = head;
        while(currentPoint != null) {
            if(currentPoint.getID().equals(srcID)) {
                Point desPoint = getPoint(desID);
                Path currentPath = currentPoint.getPath();
                while(currentPath != null) {
                        if(currentPath.getNextPoint().equals(desPoint)) return true;
                        currentPath = currentPath.getNextPath();
                }
            }
            currentPoint = currentPoint.getNextPoint();
        }
        return false;
    }

    public Integer getHeight(Integer srcID, Integer desID) {
        if(!isValidPath(srcID, desID)) return null;

        Point currentPoint = head;
        while(currentPoint != null) {
            if(currentPoint.getID().equals(srcID)) {
                Point desPoint = getPoint(desID);
                Path currentPath = currentPoint.getPath();
                while(currentPath != null) {
                        if(currentPath.getNextPoint().equals(desPoint)) return currentPath.getHeight();
                        currentPath = currentPath.getNextPath();
                }
            }
            currentPoint = currentPoint.getNextPoint();
        }
        return null;
    }

    public ArrayList<Path> getNearbyPaths(Integer ID) {
        Point targetPoint = getPoint(ID);
        ArrayList<Path> arr = new ArrayList<>();
        if(targetPoint == null) return null;

        Path targetPath = targetPoint.getPath();
        while(targetPath != null) {
            arr.add(targetPath);
            targetPath = targetPath.getNextPath();
        }
        return arr;
    }

    public ArrayList<Point> getNearbyPoints(Integer ID) {
        Point targetPoint = getPoint(ID);
        ArrayList<Point> arr = new ArrayList<>();
        if(targetPoint == null) return null;

        Path targetPath = targetPoint.getPath();
        while(targetPath != null) {
            arr.add(targetPath.getNextPoint());
            targetPath = targetPath.getNextPath();
        }
        return arr;
    }
    
    public ArrayList<Integer> getNearbyID (Integer ID) {
        Point targetPoint = getPoint(ID);
        ArrayList<Integer> arr = new ArrayList<>();
        if(targetPoint == null) return null;

        Path targetPath = targetPoint.getPath();
        while(targetPath != null) {
            arr.add(targetPath.getNextPoint().getID());
            targetPath = targetPath.getNextPath();
        }
        return arr;
    }

    public Point getPoint(Integer ID) {
        if(isEmpty()) return null;

        Point currentPoint = head;
        while(currentPoint != null) {
            if(currentPoint.getID().equals(ID)) return currentPoint;
            currentPoint = currentPoint.getNextPoint();
        }
        return null;
    }

    public void updateColony() {
        Point currentPoint = head;
        while(currentPoint != null) {
            if(currentPoint.getKangarooNumber() >= threshold && !currentPoint.getColony()) {
                currentPoint.setColony(true);
                ArrayList<Kangaroo> kangaroos = currentPoint.getKangaroos();
                Double sharedFood = 0.0;
                for(Kangaroo e: kangaroos){
                    sharedFood += e.getPounchFood();
                    e.setMovable(false);                
                }
                currentPoint.setFoodAmount(currentPoint.getFoodAmount() + sharedFood);
            }
            currentPoint = currentPoint.getNextPoint();
        }
    }

    public void migrate(Kangaroo kangaroo, Integer srcID, Integer desID) {
        Point source = getPoint(srcID);
        Point destination = getPoint(desID);

        source.removeKangaroo(kangaroo);
        destination.addKangaroo(kangaroo);
        kangaroo.setPointID(desID);
    }

    public void display() {
        Point currentPoint = head;
        ArrayList<Point> colony	= new ArrayList<>();
        ArrayList<Kangaroo> kangaroos = new ArrayList<>(); 

        while(currentPoint != null) {
            if(currentPoint.getColony()) colony.add(currentPoint);
            else kangaroos.addAll(currentPoint.getKangaroos());

            currentPoint = currentPoint.getNextPoint();
        }

        System.out.println("Number of colonies: " + colony.size());
        for(Point e: colony) {
            System.out.println("ID: " + e.getID() + " KangaroosNumber: " + e.getKangarooNumber());
            for(Kangaroo k: e.getKangaroos()) System.out.println(k.toString());
        }

        System.out.println("Number of remaining kangaroos: " + kangaroos.size());
        for(Kangaroo e: kangaroos) System.out.println(e.toString());
    }

    public Integer totalPoints() {
        Point currentPoint = head;
        Integer number = 0;

        while(currentPoint != null) {
            number ++;
            currentPoint = currentPoint.getNextPoint();
        }
        return number;
    }

    public void clearMap() {
        head = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void displayPointsInfo() {
        Point currentPoint = head;
        while(currentPoint != null) {
                currentPoint.show();
                currentPoint = currentPoint.getNextPoint();
        }
        System.out.println();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Point currentPoint = head;

        while(currentPoint != null) {
                str.append(currentPoint.toString() + "\n");
                Path currentPath = currentPoint.getPath();
                while(currentPath != null) {
                        str.append(currentPath.toString());
                        currentPath = currentPath.getNextPath();
                }
                str.append("\n");
                currentPoint = currentPoint.getNextPoint();
        }
        return str.toString();
    }

    public void thresholdValue(Integer n) {this.threshold = n;}

    public Integer getThreshold() {return this.threshold;}
    
    ////////////////////////////////////////////////////////////////////////////
    
    public void setPointPosition(){
        if(this.totalPoints() == 1){
            Point currentPoint = head;
            while(currentPoint != null) {
                currentPoint.setPosition(100, 100, 610, 610);
                currentPoint = currentPoint.getNextPoint();
            }
        }
        else if(this.totalPoints() == 2){
            int[]x = {0,400};
            int[]y = {0,330};
            int size = 410;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 3){
            int[]x = {0,430,220};
            int[]y = {0,0,330};
            int size = 360;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 4){
            int[]x = {0,470,0,470};
            int[]y = {0,0,370,370};
            int size = 320;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 5){
            int[]x = {130,400,0,530,270};
            int[]y = {0,0,260,260,430};
            int size = 260;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 6){
            int[]x = {130,400,0,560,130,400};
            int[]y = {0,0,234,234,466,466};
            int size = 233;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 7){
            int[]x = {300,100,500,0,600,175,425};
            int[]y = {0,100,100,350,350,500,500};
            int size = 200;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 8){
            int[]x = {180,420,0,620,0,620,180,420};
            int[]y = {0,0,180,180,380,380,500,500};
            int size = 200;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 9){
            int[]x = {300,100,500,0,630,50,550,200,400};
            int[]y = {0,50,50,250,250,420,420,570,570};
            int size = 180;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 10){
            int[]x = {220,400,60,570,0,630,60,570,220,400};
            int[]y = {0,0,80,80,260,260,430,430,550,550};
            int size = 180;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 11){
            int[]x = {220,400,60,570,0,630,60,570,220,400,310};
            int[]y = {0,0,80,80,260,260,430,430,550,550,260};
            int size = 180;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 12){
            int[]x = {220,400,60,570,0,630,60,570,220,400,210,420};
            int[]y = {0,0,80,80,260,260,430,430,550,550,260,260};
            int size = 180;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 13){
            int[]x = {220,400,60,570,0,630,60,570,220,400,220,410,310};
            int[]y = {0,0,80,80,260,260,430,430,550,550,220,220,380};
            int size = 180;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 14){
            int[]x = {220,400,60,570,0,630,60,570,220,400,225,405,225,405};
            int[]y = {0,0,80,80,260,260,430,430,550,550,200,200,360,360};
            int size = 180;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 15){
            int[]x = {0,0,50,50,625,625,575,575,225,400,225,400,205,420,313};
            int[]y = {200,375,25,550,200,375,25,550,0,0,575,575,200,200,375};
            int size = 175;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 16){
            int[]x = {0,0,50,50,625,625,575,575,225,400,225,400,205,420,205,420};
            int[]y = {200,375,25,550,200,375,25,550,0,0,575,575,200,200,375,375};
            int size = 175;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 17){
            int[]x = {0,0,50,50,640,640,590,590,220,420,220,420,240,400,160,480,320};
            int[]y = {215,375,55,535,215,375,55,535,0,0,590,590,160,160,315,315,430};
            int size = 160;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 18){
            int[]x = {0,0,50,50,640,640,590,590,220,420,220,420,160,480,160,480,320,320};
            int[]y = {215,375,55,535,215,375,55,535,0,0,590,590,210,210,380,380,430,160};
            int size = 160;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
         else if(this.totalPoints() == 19){
            int[]x = {0,0,50,50,640,640,590,590,220,420,220,420,240,400,240,400,160,320,480};
            int[]y = {215,375,55,535,215,375,55,535,0,0,590,590,160,160,445,445,300,300,300};
            int size = 160;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
        else if(this.totalPoints() == 20){
            int[]x = {0,0,50,25,640,640,590,635,220,420,180,480,240,400,240,400,160,320,480,330};
            int[]y = {215,375,55,535,215,375,55,535,0,0,590,590,160,160,445,445,300,300,300,590};
            int size = 160;
            Point currentPoint = head;
            int i = 0;
            while(currentPoint != null) {
                currentPoint.setPosition(x[i], y[i], size, size);
                currentPoint = currentPoint.getNextPoint();
                i++;
            }
        }
    }
    
    public void addtoEntity(){
        pointEntity();
        kangaroosEntity();
    }
    
    private void pointEntity() {
        Point currentPoint = head;
        while(currentPoint != null) {
            entityManager.addEntity(currentPoint);
            currentPoint = currentPoint.getNextPoint();
        }
    }
    
    private void kangaroosEntity() {
        Point currentPoint = head;
        while(currentPoint != null) {
            for (int i = 0; i < currentPoint.getKangaroos().size(); i++) {
                entityManager.addEntity(currentPoint.getKangaroos().get(i));
                currentPoint.getKangaroos().get(i).setDimension();
            }
            currentPoint = currentPoint.getNextPoint();
        }
    }

    @Override
    public void init() {
        
    }

    @Override
    public void update() {
        entityManager.update();
    }

    @Override
    public void draw(GraphicsContext graphic) {
        graphic.drawImage(background, 0, 0);
        
        entityManager.draw(graphic);
    }
    
    public Tile getTile(int x, int y) {
        return null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    private Queue<Integer> adjId;
    private int countf; //point n food
    private int countvm; //visited
    private int dest;
    private ArrayList<Double> Food;
    
    public boolean optimumPath(Kangaroo kangaroo){
        adjId=new LinkedList<>();
        countf=0;
        countvm=0;
        dest=0;
        Point currentPoint=getPoint(kangaroo.getCurrentPointID());
        double[] food =new double[totalPoints()];
        int[] pointId=new int [totalPoints()];
        int[] visitedMax=new int[totalPoints()]; 
        adjId.add(currentPoint.getID());
        while(!adjId.isEmpty()){
            currentPoint=getPoint(adjId.remove());
            maxFood(food,pointId,visitedMax,currentPoint,kangaroo);
        }
        descending(food,pointId);

        for (int i = 0; i < countf; i++) {
            Point ori=getPoint(kangaroo.getCurrentPointID());
            Point next=getPoint(pointId[i]);
            if(ori.getFoodAmount()>next.getFoodAmount()) 
                continue;
            else if(ori.getFoodAmount()==next.getFoodAmount()){
                if(ori.getFemaleKangaroo()>=next.getFemaleKangaroo())
                    continue;
            }

            List<List<Integer>> path=allPathsToTarget(kangaroo.getCurrentPointID(),pointId[i],visitedMax,kangaroo);
            Food=new ArrayList<>();
            for (int j = 0; j < path.size(); j++) {

                    double foood=getFood(path.get(j),kangaroo); 

                    if(foood<0){
                        path.remove(j);
                        j--;
                    }
                    else{
                        Food.add(foood);
                    }
            }

            if(path.isEmpty())
                continue; 

            double maxFood=getMaxFood(Food);

            int maxFPath=0;
            for (int j = 0; j < Food.size(); j++) {

                if(maxFood==Food.get(j))
                    maxFPath=j;
            }

            int dest=checkPathColony(path.get(maxFPath),kangaroo);

            List<Integer> pathh=path.get(maxFPath);
            if(dest!=pathh.get( pathh.size()-1) || getPoint(dest).getColony() ){
                goColony(path.get(maxFPath),dest,kangaroo);

            }else{
                int sourceId=kangaroo.getCurrentPointID();
                Point source=getPoint(sourceId);
                Point target =getPoint(pointId[i]);
                if(maxFood==source.getFoodAmount()){
                    if(target.getFemaleKangaroo()<=source.getFemaleKangaroo())
                        continue;
                }else if(maxFood<source.getFoodAmount())
                    continue;

                    goDest(path.get(maxFPath),kangaroo);
            }
            pathToDest(path.get(maxFPath),kangaroo,dest);
            SimulatorController.getMigration(kangaroo.getCurrentPointID(), dest, path.get(maxFPath));
            migrate(kangaroo, kangaroo.getCurrentPointID(),dest);
            kangaroo.setDimension();
            System.out.println("true");
            return true;
        }
        return false;
    }
    
     public void pathToDest(List<Integer> path,Kangaroo kangaroo, int dest){ 
        System.out.print("The path is : ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i)+" ");
            if(path.get(i)==dest)
                break;
        }
        System.out.println();
    }
     
    public void goDest(List<Integer> path,Kangaroo kangaroo){ 
        Double foodLeft=0.0;
        for (int i = 0; i < path.size()-1; i++) {
            Point current=getPoint(path.get(i));
            Point nextPoint=getPoint(path.get(i+1));
            double kPouchFood=kangaroo.getPounchFood();

            foodLeft=nextPoint.getFoodAmount()+kPouchFood-getHeight(current.getID(),nextPoint.getID())-0.5*kPouchFood;
            if(foodLeft>=kangaroo.getPounchStorage()){

                 kangaroo.setPounchFood(kangaroo.getPounchStorage());

                 nextPoint.setFoodAmount(foodLeft-kangaroo.getPounchStorage());
            }else{
                 kangaroo.setPounchFood(foodLeft);
                 nextPoint.setFoodAmount(0.0);
            }
        }

        System.out.println();
    }
    
    public void goColony(List<Integer> path, int destId,Kangaroo kangaroo){
        for (int i = 0; i < path.size()-1; i++) {
            Point current=getPoint(path.get(i));
            Point nextPoint=getPoint(path.get(i+1));

            if(nextPoint.getID()==destId){

                Double foodleft=nextPoint.getFoodAmount()+kangaroo.getPounchFood()-0.5*kangaroo.getPounchFood()-getHeight(current.getID(),nextPoint.getID());
                nextPoint.setFoodAmount(foodleft);

                kangaroo.setMovable(false);
                return;
            }

            Double foodLeft=nextPoint.getFoodAmount()+kangaroo.getPounchFood()-0.5*kangaroo.getPounchFood()-getHeight(current.getID(),nextPoint.getID());
            if(foodLeft>=kangaroo.getPounchStorage()){

                kangaroo.setPounchFood(kangaroo.getPounchStorage());

                nextPoint.setFoodAmount(foodLeft-kangaroo.getPounchStorage());
            }else{
                kangaroo.setPounchFood(foodLeft);
                nextPoint.setFoodAmount(0.0);
            }
        }
    }
        
    public boolean checkFemale(List<Integer> path,double maxFood){
        int sourceId=path.get(0);
        int destId=path.get(path.size()-1);
        Point source=getPoint(sourceId);
        Point desti=getPoint(destId);
        if(source.getFoodAmount()==maxFood){
            if(source.getFemaleKangaroo()>=desti.getFemaleKangaroo())
                return false;
        }
        return true;
    }
        
    public int checkPathColony(List<Integer> path,Kangaroo kangaroo){ 
        for (int i = 0; i < path.size()-1; i++) {
            Point current=getPoint(path.get(i));
            Point nextPoint=getPoint(path.get(i+1));
            if(nextPoint.getColony()){
              return path.get(i+1);
            }
        }
        return path.get(path.size()-1);
    }
    
   public double getMaxFood(ArrayList<Double> food){
        double max=0;
        for (int i = 0; i < food.size(); i++) {
            if(food.get(i)>max)
                max=food.get(i);
        }
        return max;
    }
   
     public double getFood(List<Integer> path,Kangaroo kangaroo){ 
        double OriKFood=kangaroo.getPounchFood();
        Double foodLeft=-1.0;
        for (int i = 0; i < path.size()-1; i++) { 

            Point current=getPoint(path.get(i));
            Point nextPoint=getPoint(path.get(i+1));
            Double kPouchFood=kangaroo.getPounchFood();

            if(nextPoint.getColony()){

                Double foodleft = kPouchFood-0.5*kPouchFood-getHeight(current.getID(),nextPoint.getID());
                kangaroo.setPounchFood(OriKFood);
                if(foodleft<nextPoint.getKangarooNumber()){

                    return -1;
                }else{
                    return foodleft;
                }
            }
            foodLeft=nextPoint.getFoodAmount()+kPouchFood-getHeight(current.getID(),nextPoint.getID())-0.5*kPouchFood;
            if(foodLeft<0){
                kangaroo.setPounchFood(OriKFood);
                return -1 ;
            }
            else if(foodLeft>=kangaroo.getPounchStorage()){ 
                kangaroo.setPounchFood(kangaroo.getPounchStorage());
                foodLeft-=kangaroo.getPounchFood();
            }else{
                kangaroo.setPounchFood(foodLeft);
                foodLeft=0.0;
            }
        }
        kangaroo.setPounchFood(OriKFood);
        return foodLeft;
    }
   
    public List<List<Integer>> allPathsToTarget(int sourceId, int destId, int[] visitedMax, Kangaroo kangaroo){ 
        List<List<Integer>> result=new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> sourceid=new ArrayList<>();
        sourceid.add(sourceId);
        queue.add(sourceid);

        while(!queue.isEmpty()){
            List<Integer> visited=new ArrayList<>();
            List<Integer> path=queue.poll();
            int lastNode=path.get(path.size()-1);
            if(checkVisited(lastNode, visited))
                continue; 
            else 
                visited.add(lastNode);
            if(lastNode==destId){
                result.add(new ArrayList<>(path));
            }else{
                Point current=getPoint(lastNode);
                Path currentPath=current.getPath();
                while(currentPath!=null){
                    int nextPointId=currentPath.getNextPoint().getID();
                    boolean move=true;
                    for (int i = 0; i < path.size(); i++) {
                        if(path.get(i)==nextPointId){
                            move=false;
                            break;
                        }
                    }
                    if(!move){
                        currentPath=currentPath.getNextPath();
                        continue;
                    }
                    if(checkVisited(nextPointId,visited)){ 
                        currentPath=currentPath.getNextPath();
                        continue;
                    }
                    for (int i = 0; i < countvm; i++) {
                        if(visitedMax[i]==nextPointId){
                            currentPath=currentPath.getNextPath();
                            continue;
                        }
                    }

                    List<Integer> list=new ArrayList<>(path);
                    list.add(nextPointId);
                    queue.add(list);
                    currentPath=currentPath.getNextPath();
                }
            }
        }

        return result;
    }
   
    public boolean checkVisited(int a, List<Integer> visited){
        for (int i = 0; i < visited.size(); i++) {
            if(visited.get(i)==a)
                return true;
        }
        return false;
    }
   
    public void maxFood(double[] food,int[] pointId, int[] visitedMax,Point currentPoint,Kangaroo kangaroo){
        if(currentPoint!=null){
            if(currentPoint.getKangarooNumber()==currentPoint.maxKangaroo()){
                visitedMax[countvm]=currentPoint.getID();
                countvm++;
                return;
            }
            Path currentPath=currentPoint.getPath();
            while(currentPath!=null ){
                if(currentPath.getNextPoint().getKangarooNumber()==currentPath.getNextPoint().maxKangaroo()){
                    visitedMax[countvm]=currentPoint.getID();
                    countvm++;
                    currentPath=currentPath.getNextPath();
                    continue;
                }
                boolean add=true;
                for (int i = 0; i < countf; i++) {
                    if(currentPath.getNextPoint().getID()==pointId[i]){
                        add=false;
                        break;
                    }
                }
                if(!add){
                    currentPath=currentPath.getNextPath();
                    continue;
                }
                double foodLeft =currentPath.getNextPoint().getFoodAmount()-currentPath.getHeight()-kangaroo.getPounchStorage()*0.5;
                pointId[countf]=currentPath.getNextPoint().getID();
                food[countf]=foodLeft;
                countf++;
                adjId.add(currentPath.getNextPoint().getID());
                currentPath=currentPath.getNextPath();
            }
        }
    }
    
    public void descending(double[] food,int[] id){
        for (int i = 0; i <countf; i++) {
            for (int j = 0; j < countf-1; j++) {
                if(food[j]<food[j+1]){
                    double tempf=food[j];
                    food[j]=food[j+1];
                    food[j+1]=tempf;
                    int tempid=id[j];
                    id[j]=id[j+1];
                    id[j+1]=tempid;
                }
            }
        }
    }
}
