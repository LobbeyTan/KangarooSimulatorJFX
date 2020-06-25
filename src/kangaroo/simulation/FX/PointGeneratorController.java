package kangaroo.simulation.FX;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PointGeneratorController extends HomeController implements Initializable {
    @FXML
    private Pane pointGeneratorScene;
    @FXML
    private Text pointConfigured;
    @FXML
    private Pane point_1;
    @FXML
    private TextField foodAvailable_1;
    @FXML
    private MenuButton connectedPoint_1;
    @FXML
    private TextField pointID_1;
    @FXML
    private TextField maxKangaroo_1;
    @FXML
    private TextField height_1;
    @FXML
    private CheckBox checkBox_1;
    @FXML
    private Pane point_2;
    @FXML
    private TextField foodAvailable_2;
    @FXML
    private MenuButton connectedPoint_2;
    @FXML
    private TextField pointID_2;
    @FXML
    private TextField maxKangaroo_2;
    @FXML
    private TextField height_2;
    @FXML
    private CheckBox checkBox_2;
    @FXML
    private Pane point_3;
    @FXML
    private TextField foodAvailable_3;
    @FXML
    private MenuButton connectedPoint_3;
    @FXML
    private TextField pointID_3;
    @FXML
    private TextField maxKangaroo_3;
    @FXML
    private TextField height_3;
    @FXML
    private CheckBox checkBox_3;
    @FXML
    private Pane point_4;
    @FXML
    private TextField foodAvailable_4;
    @FXML
    private MenuButton connectedPoint_4;
    @FXML
    private TextField pointID_4;
    @FXML
    private TextField maxKangaroo_4;
    @FXML
    private TextField height_4;
    @FXML
    private CheckBox checkBox_4;
    @FXML
    private Pane point_5;
    @FXML
    private TextField foodAvailable_5;
    @FXML
    private MenuButton connectedPoint_5;
    @FXML
    private TextField pointID_5;
    @FXML
    private TextField maxKangaroo_5;
    @FXML
    private TextField height_5;
    @FXML
    private CheckBox checkBox_5;
    @FXML
    private Pane point_6;
    @FXML
    private TextField foodAvailable_6;
    @FXML
    private MenuButton connectedPoint_6;
    @FXML
    private TextField pointID_6;
    @FXML
    private TextField maxKangaroo_6;
    @FXML
    private TextField height_6;
    @FXML
    private CheckBox checkBox_6;
    @FXML
    private Pane point_8;
    @FXML
    private TextField foodAvailable_8;
    @FXML
    private MenuButton connectedPoint_8;
    @FXML
    private TextField pointID_8;
    @FXML
    private TextField maxKangaroo_8;
    @FXML
    private TextField height_8;
    @FXML
    private CheckBox checkBox_8;
    @FXML
    private Pane point_7;
    @FXML
    private TextField foodAvailable_7;
    @FXML
    private MenuButton connectedPoint_7;
    @FXML
    private TextField pointID_7;
    @FXML
    private TextField maxKangaroo_7;
    @FXML
    private TextField height_7;
    @FXML
    private CheckBox checkBox_7;
    @FXML
    private Pane point_9;
    @FXML
    private TextField foodAvailable_9;
    @FXML
    private MenuButton connectedPoint_9;
    @FXML
    private TextField pointID_9;
    @FXML
    private TextField maxKangaroo_9;
    @FXML
    private TextField height_9;
    @FXML
    private CheckBox checkBox_9;
    @FXML
    private Pane point_10;
    @FXML
    private TextField foodAvailable_10;
    @FXML
    private MenuButton connectedPoint_10;
    @FXML
    private TextField pointID_10;
    @FXML
    private TextField maxKangaroo_10;
    @FXML
    private TextField height_10;
    @FXML
    private CheckBox checkBox_10;
    @FXML
    private Pane point_12;
    @FXML
    private TextField foodAvailable_12;
    @FXML
    private MenuButton connectedPoint_12;
    @FXML
    private TextField pointID_12;
    @FXML
    private TextField maxKangaroo_12;
    @FXML
    private TextField height_12;
    @FXML
    private CheckBox checkBox_12;
    @FXML
    private Pane point_11;
    @FXML
    private TextField foodAvailable_11;
    @FXML
    private MenuButton connectedPoint_11;
    @FXML
    private TextField pointID_11;
    @FXML
    private TextField maxKangaroo_11;
    @FXML
    private TextField height_11;
    @FXML
    private CheckBox checkBox_11;
    @FXML
    private Pane point_13;
    @FXML
    private TextField foodAvailable_13;
    @FXML
    private MenuButton connectedPoint_13;
    @FXML
    private TextField pointID_13;
    @FXML
    private TextField maxKangaroo_13;
    @FXML
    private TextField height_13;
    @FXML
    private CheckBox checkBox_13;
    @FXML
    private Pane point_14;
    @FXML
    private TextField foodAvailable_14;
    @FXML
    private MenuButton connectedPoint_14;
    @FXML
    private TextField pointID_14;
    @FXML
    private TextField maxKangaroo_14;
    @FXML
    private TextField height_14;
    @FXML
    private CheckBox checkBox_14;
    @FXML
    private Pane point_16;
    @FXML
    private TextField foodAvailable_16;
    @FXML
    private MenuButton connectedPoint_16;
    @FXML
    private TextField pointID_16;
    @FXML
    private TextField maxKangaroo_16;
    @FXML
    private TextField height_16;
    @FXML
    private CheckBox checkBox_16;
    @FXML
    private Pane point_15;
    @FXML
    private TextField foodAvailable_15;
    @FXML
    private MenuButton connectedPoint_15;
    @FXML
    private TextField pointID_15;
    @FXML
    private TextField maxKangaroo_15;
    @FXML
    private TextField height_15;
    @FXML
    private CheckBox checkBox_15;
    @FXML
    private Pane point_17;
    @FXML
    private TextField foodAvailable_17;
    @FXML
    private MenuButton connectedPoint_17;
    @FXML
    private TextField pointID_17;
    @FXML
    private TextField maxKangaroo_17;
    @FXML
    private TextField height_17;
    @FXML
    private CheckBox checkBox_17;
    @FXML
    private Pane point_18;
    @FXML
    private TextField foodAvailable_18;
    @FXML
    private MenuButton connectedPoint_18;
    @FXML
    private TextField pointID_18;
    @FXML
    private TextField maxKangaroo_18;
    @FXML
    private TextField height_18;
    @FXML
    private CheckBox checkBox_18;
    @FXML
    private Pane point_20;
    @FXML
    private TextField foodAvailable_20;
    @FXML
    private MenuButton connectedPoint_20;
    @FXML
    private TextField pointID_20;
    @FXML
    private TextField maxKangaroo_20;
    @FXML
    private TextField height_20;
    @FXML
    private CheckBox checkBox_20;
    @FXML
    private Pane point_19;
    @FXML
    private TextField foodAvailable_19;
    @FXML
    private MenuButton connectedPoint_19;
    @FXML
    private TextField pointID_19;
    @FXML
    private TextField maxKangaroo_19;
    @FXML
    private TextField height_19;
    @FXML
    private CheckBox checkBox_19;
    @FXML
    private Button completed;
    @FXML
    private MenuButton sampleTest;
    
    private static final String ERROR = "error";
    private int[] currentSelectedPoint;
    private int configuredPoint;
    private CheckBox[] checkBoxs;
    private TextField[] heights;
    private TextField[] pointIDs;
    private TextField[] maxKangaroos;
    private TextField[] foodAvailables;
    private MenuButton[] connectedPoints;
    
    public static boolean usingSample;
    public static int sample;
    
    public static int[] IDList;
    private int[][] heightList;
    private int[] kangarooList;
    private int[] foodList;
    private boolean[] isConfigured;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkBoxs = new CheckBox[] {checkBox_1,  checkBox_2, checkBox_3, checkBox_4, checkBox_5, checkBox_6, checkBox_7, checkBox_8, checkBox_9, checkBox_10, 
                                    checkBox_11, checkBox_12, checkBox_13, checkBox_14, checkBox_15, checkBox_16, checkBox_17, checkBox_18, checkBox_19, checkBox_20};
        
        heights = new TextField[] {height_1, height_2, height_3, height_4, height_5, height_6, height_7, height_8, height_9 ,height_10, 
                                   height_11, height_12, height_13, height_14, height_15, height_16, height_17, height_18, height_19 ,height_20};
        
        pointIDs = new TextField[] {pointID_1, pointID_2, pointID_3, pointID_4, pointID_5, pointID_6, pointID_7, pointID_8, pointID_9, pointID_10,
                                    pointID_11, pointID_12, pointID_13, pointID_14, pointID_15, pointID_16, pointID_17, pointID_18, pointID_19, pointID_20,};
        
        maxKangaroos = new TextField[] {maxKangaroo_1, maxKangaroo_2, maxKangaroo_3, maxKangaroo_4, maxKangaroo_5, maxKangaroo_6, maxKangaroo_7, maxKangaroo_8, maxKangaroo_9, maxKangaroo_10,
                                        maxKangaroo_11, maxKangaroo_12, maxKangaroo_13, maxKangaroo_14, maxKangaroo_15, maxKangaroo_16, maxKangaroo_17, maxKangaroo_18, maxKangaroo_19, maxKangaroo_20,};
        
        foodAvailables = new TextField[] {foodAvailable_1, foodAvailable_2, foodAvailable_3, foodAvailable_4, foodAvailable_5, foodAvailable_6, foodAvailable_7, foodAvailable_8, foodAvailable_9, foodAvailable_10,
                                          foodAvailable_11, foodAvailable_12, foodAvailable_13, foodAvailable_14, foodAvailable_15, foodAvailable_16, foodAvailable_17, foodAvailable_18, foodAvailable_19, foodAvailable_20,};
        
        connectedPoints = new MenuButton[] {connectedPoint_1, connectedPoint_2, connectedPoint_3, connectedPoint_4, connectedPoint_5, connectedPoint_6, connectedPoint_7, connectedPoint_8, connectedPoint_9, connectedPoint_10,
                                            connectedPoint_11, connectedPoint_12, connectedPoint_13, connectedPoint_14, connectedPoint_15, connectedPoint_16, connectedPoint_17, connectedPoint_18, connectedPoint_19, connectedPoint_20,};
        
        IDList = new int[20];
        heightList = new int[20][20];
        kangarooList = new int[20];
        foodList = new int[20];
        isConfigured = new boolean[20];
        configuredPoint = 0;
        addConfiguredPoint(configuredPoint);
        
        currentSelectedPoint = new int[2];
        usingSample = false;
        sample = 0;
    }    

    @FXML
    private void configurePoint(ActionEvent event) {
        if(usingSample) return;
        int i = getCurrentPointIndex(event);
        if(!isConfigured[i]) {
            addConfiguredPoint(++configuredPoint);
            isConfigured[i] = true;
            activateInput(i);
        }else {
            addConfiguredPoint(--configuredPoint);
            isConfigured[i] = false;
            clearInput(i);
            deactivateInput(i);
        }
    }
    
    @FXML
    private void getID(KeyEvent event) {
        int i = getCurrentPointIndex(event);
        String input = pointIDs[i].getText();
        
        if(input.isEmpty() && IDList[i] != 0) {pointIDs[i].setText(IDList[i] + ""); pointIDs[i].selectAll(); return;}
        if(!isInteger(input)) {
            pointIDs[i].setText(input.substring(0, getCharacterIndex(input))); 
            pointIDs[i].positionCaret(pointIDs[i].getText().length());
            return;
        }
        
        IDList[i] = Integer.parseInt(input);
    }

    @FXML
    private void getFood(KeyEvent event) {
        int i = getCurrentPointIndex(event);
        if(hasIDInputError(i)) {foodAvailables[i].setText(""); return;}
        String input = foodAvailables[i].getText();
        if(input.isEmpty() && foodList[i] != 0) {foodAvailables[i].setText(foodList[i] + ""); foodAvailables[i].selectAll(); return;}
        if(!isInteger(input)) {
            foodAvailables[i].setText(input.substring(0, getCharacterIndex(input))); 
            foodAvailables[i].positionCaret(foodAvailables[i].getText().length());
            return;
        }
        foodList[i] = Integer.parseInt(input);
    }

    @FXML
    private void getMaxKangaroo(KeyEvent event) {
        int i = getCurrentPointIndex(event);
        if(hasIDInputError(i)) {maxKangaroos[i].setText(null); return;}
        String input = maxKangaroos[i].getText();
        if(input.isEmpty() && kangarooList[i] != 0) {maxKangaroos[i].setText(kangarooList[i]+""); maxKangaroos[i].selectAll(); return;}
        if(!isInteger(input)) {
            maxKangaroos[i].setText(input.substring(0, getCharacterIndex(input))); 
            maxKangaroos[i].positionCaret(maxKangaroos[i].getText().length());
            return;
        }
        kangarooList[i] = Integer.parseInt(input);
    }

    @FXML
    private void getHeight(KeyEvent event) {
        int i = getCurrentPointIndex(event);
        if(i == currentSelectedPoint[0]) {
            String input = heights[i].getText();
            if(!isInteger(input)) {
                heights[i].setText(input.substring(0, getCharacterIndex(input))); 
                heights[i].positionCaret(heights[i].getText().length());
                return;
            }
            heightList[i][currentSelectedPoint[1]] = Integer.parseInt(heights[i].getText());
            System.out.println(i + " " + currentSelectedPoint[1] + " " + heightList[i][currentSelectedPoint[1]]);
        }
    }

    @FXML
    private void getPoints(MouseEvent event) {
        int i = getCurrentPointIndex(event);
        for(int j = 0; j < 20; j++) {if(connectedPoints[j].getContextMenu() != null && i != j)connectedPoints[j].setContextMenu(null); heights[j].setDisable(true);}

        if(connectedPoints[i].getContextMenu() == null) connectedPoints[i].setContextMenu(getMenu(i));
        if(!connectedPoints[i].getContextMenu().isShowing()) connectedPoints[i].getContextMenu().show(connectedPoints[i], Side.BOTTOM, 0, 0);
    }
    
    @FXML
    private void selectSample(ActionEvent event) {
        usingSample = true;
        if(event.getSource() instanceof MenuItem) {
            MenuItem currentItem = (MenuItem) event.getSource();
            sampleTest.setText(currentItem.getText());
            sample = Integer.parseInt(currentItem.getText().split("[^0-9]+")[1]);
            completed.setText("START");
        }
        System.out.println(sample);
    }

    
    @FXML
    private void toNextScene(ActionEvent event) {
        if(usingSample) {showSimulator(); SimulatorController.worldCreated = true;}
        
        try {
            PrintWriter print = new PrintWriter(new FileOutputStream("pointConfig.txt"));
            if(hasError()) return;
            for(int i = 0; i < isConfigured.length; i++) {
                if(!isConfigured[i]) continue;
                print.printf("%d %d %d %d\n", IDList[i], foodList[i], kangarooList[i], getSize(heightList[i]));
            }
            for(int i = 0; i < heightList.length; i++) {
                for(int j = 0; j < heightList[i].length; j++) {
                    if(heightList[i][j] != 0) print.println(IDList[i] + " " + IDList[j] + " " + heightList[i][j]);
                }
            }
            print.close();
            showKangarooGenerator();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PointGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addConfiguredPoint(int n) {
        pointConfigured.setText("Points Configured : " + n);
    }
    
    private int getCurrentPointIndex(Event event) {
        String src = event.getSource().toString();
        return Integer.parseInt(src.split("[^0-9]+")[1]) - 1;
    }
    
    private boolean hasIDInputError(int index) {
        if(IDList[index] == 0 || isRepeated(pointIDs[index].getText(), index)) {pointIDs[index].selectAll(); pointIDs[index].requestFocus(); return true;}
        return false;
    }
    
    private void activateInput(int index) {
        pointIDs[index].setDisable(false);
        maxKangaroos[index].setDisable(false);
        connectedPoints[index].setDisable(false);
        foodAvailables[index].setDisable(false);
    }
    
    private void deactivateInput(int index) {
        heights[index].setDisable(true);
        pointIDs[index].setDisable(true);
        maxKangaroos[index].setDisable(true);
        connectedPoints[index].setDisable(true);
        foodAvailables[index].setDisable(true);
    }
    
    private void clearInput(int index) {
        heights[index].setText("");
        pointIDs[index].setText("");
        maxKangaroos[index].setText("");
        foodAvailables[index].setText("");
        connectedPoints[index].setContextMenu(null);
        
        IDList[index] = 0;
        heightList[index] = new int[20];
        kangarooList[index] = 0;
        foodList[index] = 0;
    }
    
    public ContextMenu getMenu(int index) {
        ArrayList<MenuItem> items = new ArrayList<>();
        MenuButton menuButton = connectedPoints[index];

        for(int i = 0; i < IDList.length; i++) {
            if(i != index && IDList[i] != 0) {
                MenuItem item = new MenuItem("Point " + IDList[i]);
                item.setId("Point " + IDList[i]);
                item.setStyle("-fx-pref-width: 172px;" 
                            + "-fx-padding: 0px -1px 0px -1px;" 
                            + "-fx-margin: 0px 0px 0px 0px;" 
                            + "-fx-border-width: 0px 0px 0px 0px;"
                            + "-fx-font-family: \"Garamond\", Times, serif;"
                            + "-fx-font-weight: bold;");
                item.setOnAction((event) -> {
                    int j = index; int k = indexOf(getCurrentPointIndex(event) + 1);
                    currentSelectedPoint[0] = j;
                    currentSelectedPoint[1] = k;
                    
                    menuButton.setText(item.getId());
                    if(!connectedPoints[j].getText().equals("Select Point")) heights[j].setDisable(false);
                    if(heightList[j][k] != 0) heights[j].setText(heightList[j][k] + "");
                    else heights[j].setText("");
                });
                items.add(item);
            }
        }
        
        ContextMenu menu = new ContextMenu();
        menu.setStyle("-fx-padding: 0px 0px 0px 0px;" + "-fx-margin: 0px 0px 0px 0px;");
        menu.getItems().addAll(items);
        return menu;
    }
    
    private int getCharacterIndex(String str) {
        for(int i = 0; i < str.length(); i++) {
            if(!Character.isDigit(str.charAt(i))) return i;
        }
        return str.length();
    }
    
    private boolean hasError() {
        recheck();
        boolean hasError = checkRepeated();
        boolean getRequest = false;
        
        if(configuredPoint == 0) {hasError = true; checkBox_1.getStyleClass().add(ERROR);}
        for(int i = 0; i < isConfigured.length; i++) {
            if(isConfigured[i] == false) continue;
            if(IDList[i] == 0) {hasError = true; pointIDs[i].getStyleClass().add(ERROR); 
            if(!getRequest) {pointIDs[i].requestFocus(); getRequest = true;}}
            
            if(foodList[i] == 0) {hasError = true; foodAvailables[i].getStyleClass().add(ERROR); 
            if(!getRequest) {foodAvailables[i].requestFocus(); getRequest = true;}}
            
            if(kangarooList[i] == 0) {hasError = true; maxKangaroos[i].getStyleClass().add(ERROR); 
            if(!getRequest) {maxKangaroos[i].requestFocus(); getRequest = true;}}
        }
        return hasError;
    }
    
    private void recheck() {
        checkBox_1.getStyleClass().remove(ERROR);
        for(TextField e: pointIDs) if(e.getStyleClass().remove(ERROR));
        for(TextField e: foodAvailables) if(e.getStyleClass().remove(ERROR));
        for(TextField e: maxKangaroos) if(e.getStyleClass().remove(ERROR));
    }
    
    private boolean isInteger(String input) {
        try {
            int num = Integer.parseInt(input);
            if(num <= 0) return false;
        }catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    private boolean isRepeated(String input, int index) {
        if(input.isEmpty()) return false;
        int in = Integer.parseInt(input.trim());
        for(int i = 0; i < IDList.length; i++) if(IDList[i] == in && i != index) return true; 
        return false;
    }
    
    private boolean checkRepeated() {
        int length = IDList.length;
        for(int i = 0; i < length-1; i++) {
            if(IDList[i] == 0) continue;
            for(int j = i+1; j < length; j++) {
                if(IDList[j] == 0) continue;
                if(IDList[i] == IDList[j]) {pointIDs[j].getStyleClass().add(ERROR); return true;}
            }
        }
        return false;
    }
    
    private int getSize(int[] arr) {
        int cnt = 0;
        for(int i = 0; i < arr.length; i++) if(arr[i] != 0) cnt++;
        return cnt;
    }
    
    private void showHieghts() {
        for(int[] x : heightList) {
            for(int y : x) System.out.print(y + " ");
            System.out.println("");
        }
    }
    
    private int indexOf(int ID) {
        for(int i = 0; i < IDList.length; i++) {
            if(IDList[i] == ID) return i;
        }
        return -1;
    }
}
