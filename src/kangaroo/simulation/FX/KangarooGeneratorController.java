package kangaroo.simulation.FX;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class KangarooGeneratorController extends HomeController implements Initializable {
    @FXML
    private Pane kangarooGeneratorScene;
    @FXML
    private TableView<KangarooData> table;
    @FXML
    private TableColumn<KangarooData, Integer> col_Num;
    @FXML
    private TableColumn<KangarooData, String> col_Gender;
    @FXML
    private TableColumn<KangarooData, Integer> col_Storage;
    @FXML
    private TableColumn<KangarooData, Integer> col_Position;
    @FXML
    private TextField numInput;
    @FXML
    private Slider storageInput;
    @FXML
    private Group formGroup;
    @FXML
    private TextField genderIn;
    @FXML
    private TextField storageIn;
    @FXML
    private MenuButton positionInput;
    @FXML
    private TextField threshold;
    
    public static int currentThreshold = 1;
    
    private static final String ERROR = "error2";
    private boolean hasInitialized = false;
    private int kangarooNumber = 1;
    private int selectedPoint = 0;
    private int currentStorage = 1;
    
    private ToggleSwitch toggle;
    private ArrayList<Integer> points = new ArrayList<>();
    private ObservableList<KangarooData> listKangaroo = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configureSwitch();
        updateKangarooNumber();

    }    
    
    @FXML
    private void updateData(ActionEvent event) {
        int index = table.getSelectionModel().getSelectedIndex();
        if(index < 0 || hasError()) return;
        listKangaroo.add(index, new KangarooData(Integer.parseInt(numInput.getText()), genderIn.getText(), currentStorage, selectedPoint));
        listKangaroo.remove(index+1);
        System.out.println(listKangaroo);
        updateTable();
        clearData();
    }

    @FXML
    private void deleteData(ActionEvent event) {
        int index = table.getSelectionModel().getSelectedIndex();
        if(index < 0 || hasError()) return;
        listKangaroo.remove(index);
        if(kangarooNumber != 1) {kangarooNumber--; updateKangarooNumber();}
        rearrange();
        updateTable();
        clearData();
    }
    
     @FXML
    private void addData(ActionEvent event) {
        if(hasError() || hasError()) return;
        listKangaroo.add(new KangarooData(kangarooNumber, genderIn.getText(), currentStorage, selectedPoint));
        kangarooNumber++;
        updateKangarooNumber();
        updateTable();
        clearData();
    }
    
    @FXML
    private void getSelected(MouseEvent event) {
        int index = table.getSelectionModel().getSelectedIndex();
        if(index < 0) return;
        KangarooData data = table.getSelectionModel().getSelectedItem();
        numInput.setText(data.getNum() + "");
        toggle.setSwitchOnProperty((data.getGender().equals("MALE")));
        storageIn.setText(data.getStorage() + ""); 
        storageInput.setValue(data.getStorage()); currentStorage = data.getStorage();
        positionInput.setText("Point " + data.getPosition()); selectedPoint = data.getPosition();
    }
    
    @FXML
    private void selectPoint(MouseEvent event) {
        initSelection();
        if(!positionInput.getContextMenu().isShowing()) positionInput.getContextMenu().show(positionInput, Side.BOTTOM, 0, 0);
    }
    
    @FXML
    private void updateStorage(MouseEvent event) {
        storageIn.setText((int)storageInput.getValue() + "");
        currentStorage = Integer.parseInt(storageIn.getText());
    }
    
    @FXML
    private void setStorage(KeyEvent event) {
        String input = storageIn.getText();
        if(input.isEmpty()) {storageIn.setText(currentStorage+""); storageIn.selectAll(); storageInput.setValue(currentStorage); return;}
        if(!isInteger(input)) {
            inputReset();
            return;
        }
        Integer in = Integer.parseInt(input);
        if(in < 0 || in > 20) inputReset();
        currentStorage = in;
        storageInput.setValue(currentStorage);
    }
    
    private void inputReset() {
        storageIn.setText("1"); 
        storageInput.setValue(1);
        currentStorage = 1;
        storageIn.positionCaret(storageIn.getText().length());
    }
    
    @FXML
    private void getThreshold(KeyEvent event) {
        String input = threshold.getText();
        if(input.isEmpty()) {threshold.setText(currentThreshold + ""); threshold.selectAll(); return;}
        if(!isInteger(input)) {
            threshold.setText("1");
            currentThreshold = 1;
            threshold.positionCaret(threshold.getText().length());
            return;
        }
        currentThreshold = Integer.parseInt(input);
    }
    
    @FXML
    private void startSimulation(ActionEvent event) {
        try {
            PrintWriter print = new PrintWriter(new FileOutputStream("kangarooConfig.txt"));
            if(hasEntryError()) return;
            for(int i = 0; i < listKangaroo.size(); i++) print.println(listKangaroo.get(i).export());
            print.close();
            showSimulator();
            SimulatorController.worldCreated = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KangarooGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void toBack(MouseEvent event) {
        hasInitialized = false;
        showPointGenerator();
    }
    
    private void configureSwitch() {
        toggle = new ToggleSwitch();
        toggle.setTranslateX(255);
        toggle.setTranslateY(140);
        toggle.setSwitchOnProperty(true);
        genderIn.textProperty().bind(Bindings.when(toggle.switchOnProperty()).then("MALE").otherwise("FEMALE"));
        formGroup.getChildren().add(toggle);
    }
    
    private void updateTable() {
        col_Num.setCellValueFactory(new PropertyValueFactory<KangarooData, Integer>("num"));
        col_Gender.setCellValueFactory(new PropertyValueFactory<KangarooData, String>("gender"));
        col_Storage.setCellValueFactory(new PropertyValueFactory<KangarooData, Integer>("storage"));
        col_Position.setCellValueFactory(new PropertyValueFactory<KangarooData, Integer>("position"));
        
        table.setItems(listKangaroo);
    }
    
    private void clearData() {
        table.getSelectionModel().clearSelection();
        toggle.setSwitchOnProperty(true);
        storageIn.setText("1"); storageInput.setValue(1);
        positionInput.setText("Select Point");
        numInput.setText(kangarooNumber + "");
        selectedPoint = 0;
        currentStorage = 1;
    }
    
    public boolean hasError() {
        if(selectedPoint == 0) {positionInput.setId(ERROR); return true;}
        positionInput.setId("pointMenu"); return false;
    }
    
    public boolean hasEntryError() {
        if(listKangaroo.isEmpty()) {table.getStyleClass().add("error"); return true;}
        else table.getStyleClass().remove("error"); return false;
    }
    
    private boolean isInteger(String input) {
        try {
            int num = Integer.parseInt(input);
            if(num <= 0 || num > 100) return false;
        }catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    private void updateKangarooNumber() {
        numInput.setText(kangarooNumber + "");
    }
    
    private void rearrange() {
        for(int i = 0; i < listKangaroo.size(); i++) {
            listKangaroo.get(i).setNum(i+1);
        }
    }

    private void initSelection() {
        if(hasInitialized) return;
        points.clear();
        for(int i = 0; i < PointGeneratorController.IDList.length; i++) {
            if(PointGeneratorController.IDList[i] != 0) points.add(PointGeneratorController.IDList[i]);
        }
        
        ContextMenu menu = new ContextMenu();
        menu.setStyle("-fx-padding: 0px 0px 0px 0px;" + "-fx-margin: 0px 0px 0px 0px;");
        for(int e: points) {
            MenuItem item = new MenuItem("Point " + e);
            item.setId("Point " + e);
            item.setStyle("-fx-pref-width: 164px;" 
                            + "-fx-padding: 0px -1px 0px 4px;" 
                            + "-fx-margin: 0px 0px 0px 0px;" 
                            + "-fx-border-width: 0px 0px 0px 0px;"
                            + "-fx-font-family: \"Garamond\", Times, serif;"
                            + "-fx-font-weight: bold;");
            item.getStyleClass().add("menu-item2");
            
            item.setOnAction((event) -> {
                selectedPoint = Integer.parseInt(event.getSource().toString().split("[^0-9]+")[1]);
                positionInput.setText("Point " + selectedPoint);
            });
            menu.getItems().add(item);
        }
        
        positionInput.setContextMenu(menu);
        hasInitialized = true;
    }

    
}
