package kangaroo.simulation.FX;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.*;
import javafx.scene.chart.XYChart.Data;
import kangaroo.simulation.world.Point;


public class DashboardController extends SimulatorController implements Initializable {

    @FXML
    private TabPane dash_tabPane;

    @FXML
    private Tab dash_tab1;

    @FXML
    private AnchorPane dashboard_pane;

    @FXML
    private CategoryAxis x_axis;

    @FXML
    private NumberAxis y_axis;

    @FXML
    private Text numRemFemaleKang_txt1;

    @FXML
    private Label numRemFemaleKang_ans;

    @FXML
    private Text numRemMaleKang_txt1;

    @FXML
    private Label numRemMaleKang_ans;

    @FXML
    private Text numFKang_txt2;

    @FXML
    private Text numMKang_txt2;

    @FXML
    private Text numFKang_txt1;

    @FXML
    private Text numMKang_txt1;

    @FXML
    private Label numFKang_ans;

    @FXML
    private Label numMKang_ans;

    @FXML
    private Text numRemFemaleKang_txt2;

    @FXML
    private Text numRemMaleKang_txt2;

    @FXML
    private Tab dash_tab2;

    @FXML
    private AnchorPane dashboard2_pane;

    @FXML
    private ScrollPane scroll_pane;

    @FXML
    private Label foodCT_p1;

    @FXML
    private Label maleCT_p1;

    @FXML
    private Label femaleCT_p1;

    @FXML
    private Label foodCT_p2;

    @FXML
    private Label maleCT_p2;

    @FXML
    private Label femaleCT_p2;

    @FXML
    private Label foodCT_p3;

    @FXML
    private Label maleCT_p3;

    @FXML
    private Label femaleCT_p3;

    @FXML
    private Label foodCT_p4;

    @FXML
    private Label maleCT_p4;

    @FXML
    private Label femaleCT_p4;

    @FXML
    private Label foodCT_p5;

    @FXML
    private Label maleCT_p5;

    @FXML
    private Label femaleCT_p5;

    @FXML
    private Label foodCT_p6;

    @FXML
    private Label maleCT_p6;

    @FXML
    private Label femaleCT_p6;

    @FXML
    private Label foodCT_p7;

    @FXML
    private Label maleCT_p7;

    @FXML
    private Label femaleCT_p7;

    @FXML
    private Label foodCT_p8;

    @FXML
    private Label maleCT_p8;

    @FXML
    private Label femaleCT_p8;

    @FXML
    private Label foodCT_p9;

    @FXML
    private Label maleCT_p9;

    @FXML
    private Label femaleCT_p9;

    @FXML
    private Label foodCT_p10;

    @FXML
    private Label maleCT_p10;

    @FXML
    private Label femaleCT_p10;

    @FXML
    private Label foodCT_p11;

    @FXML
    private Label maleCT_p11;

    @FXML
    private Label femaleCT_p11;

    @FXML
    private Label foodCT_p12;

    @FXML
    private Label maleCT_p12;

    @FXML
    private Label femaleCT_p12;

    @FXML
    private Label foodCT_p13;

    @FXML
    private Label maleCT_p13;

    @FXML
    private Label femaleCT_p13;

    @FXML
    private Label foodCT_p14;

    @FXML
    private Label maleCT_p14;

    @FXML
    private Label femaleCT_p14;

    @FXML
    private Label foodCT_p15;

    @FXML
    private Label maleCT_p15;

    @FXML
    private Label femaleCT_p15;

    @FXML
    private Label foodCT_p16;

    @FXML
    private Label maleCT_p16;

    @FXML
    private Label femaleCT_p16;

    @FXML
    private Label foodCT_p17;

    @FXML
    private Label maleCT_p17;

    @FXML
    private Label femaleCT_p17;

    @FXML
    private Label foodCT_p18;

    @FXML
    private Label maleCT_p18;

    @FXML
    private Label femaleCT_p18;

    @FXML
    private Label foodCT_p19;

    @FXML
    private Label maleCT_p19;

    @FXML
    private Label femaleCT_p19;

    @FXML
    private Label foodCT_p20;

    @FXML
    private Label maleCT_p20;

    @FXML
    private Label femaleCT_p20;


    @FXML
    private Label number_colony_ans;

    @FXML
    private Label threshold_colony_ans;
    
    @FXML
    private StackedBarChart<String,Number> dashboard_chart;

    private static XYChart.Series dataSeriesMale;

    private static XYChart.Series dataSeriesFemale;

    @FXML
    private Label TR_pointA_line0;

    @FXML
    private Label TR_pointB_line0;

    @FXML
    private Label TR_pointA_line1;

    @FXML
    private Label TR_pointB_line1;

    @FXML
    private Label TR_pointA_line2;

    @FXML
    private Label TR_pointB_line2;

    @FXML
    private Label TR_pointA_line3;

    @FXML
    private Label TR_pointB_line3;

    @FXML
    private Label TR_pointA_line4;

    @FXML
    private Label TR_pointB_line4;
    
    @FXML
    private Label id_1;
    @FXML
    private Label id_2;
    @FXML
    private Label id_3;
    @FXML
    private Label id_4;
    @FXML
    private Label id_5;
    @FXML
    private Label id_6;
    @FXML
    private Label id_7;
    @FXML
    private Label id_8;
    @FXML
    private Label id_9;
    @FXML
    private Label id_10;
    @FXML
    private Label id_11;
    @FXML
    private Label id_12;
    @FXML
    private Label id_13;
    @FXML
    private Label id_14;
    @FXML
    private Label id_15;
    @FXML
    private Label id_16;
    @FXML
    private Label id_17;
    @FXML
    private Label id_18;
    @FXML
    private Label id_19;
    @FXML
    private Label id_20;
    
    private String lineA0 = "";
    private String lineA1 = "";
    private String lineA2 = "";
    private String lineA3 = "";
    private String lineA4 = "";

    private String lineB0 = "";
    private String lineB1 = "";
    private String lineB2 = "";
    private String lineB3 = "";
    private String lineB4 = "";
    
    private static Label[] IDs;
    private static Label[] maleCT;
    private static Label[] femaleCT;
    private static Label[] foodCT;
    private boolean initialized;
    public static ScheduledExecutorService scheduledExecutorService;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IDs = new Label[] {id_1, id_2, id_3, id_4, id_5, id_6, id_7, id_8, id_9, id_10, id_11, id_11, id_12, id_13, id_14, id_15, id_16, id_17, id_18, id_19, id_20};
        maleCT = new Label[] {maleCT_p1, maleCT_p2, maleCT_p3, maleCT_p4, maleCT_p5, maleCT_p6, maleCT_p7, maleCT_p8, maleCT_p9, maleCT_p10, 
                              maleCT_p11, maleCT_p12, maleCT_p13, maleCT_p14, maleCT_p15,maleCT_p16, maleCT_p17,maleCT_p18, maleCT_p19, maleCT_p20};
        femaleCT = new Label[] {femaleCT_p1, femaleCT_p2, femaleCT_p3, femaleCT_p4, femaleCT_p5, femaleCT_p6, femaleCT_p7, femaleCT_p8, femaleCT_p9, femaleCT_p10,
                                femaleCT_p11, femaleCT_p12, femaleCT_p13, femaleCT_p14, femaleCT_p15, femaleCT_p16, femaleCT_p17, femaleCT_p18, femaleCT_p19, femaleCT_p20};
        foodCT = new Label[] {foodCT_p1, foodCT_p2, foodCT_p3, foodCT_p4, foodCT_p5, foodCT_p6, foodCT_p7, foodCT_p8, foodCT_p9, foodCT_p10,
                              foodCT_p11, foodCT_p12, foodCT_p13, foodCT_p14, foodCT_p15, foodCT_p16, foodCT_p17, foodCT_p18, foodCT_p19, foodCT_p20};
        
        dataSeriesMale = new XYChart.Series();
        dataSeriesFemale = new XYChart.Series();
        initialized = false;
        
        x_axis.getCategories().add("Male");
        x_axis.getCategories().add("Female");
        dataSeriesMale.setName("Male");
        dashboard_chart.getData().add(dataSeriesMale);
        
        dataSeriesFemale.setName("Female");
        dashboard_chart.getData().add(dataSeriesFemale);

        dashboard_chart.getStylesheets().add(getClass().getResource("/kangaroo/simulation/FX/style.css").toString());

        dashboard_chart.setAnimated(false);

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        
        
        
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                Date now = new Date();
                
                if(SimulatorController.hasInitialized) {
                if(!initialized) {init(); initialized = false;}
                    update();
                } 
            });
        },0,1, TimeUnit.SECONDS);
    }
    
    public void init() {
        threshold_colony_ans.setText(world.getThreshold().toString());

        int[] totalKangaroos = totalKangaroo(); // Male // Female
        
        numMKang_ans.setText(String.valueOf(totalKangaroos[0]));
        numFKang_ans.setText(String.valueOf(totalKangaroos[1]));
        
        for(int i = 0; i < pSize; i++) {
            IDs[i].setText(String.valueOf(points.get(i).getID()));
        }
    }

    public void update() {  
        int[] updates = super.worldUpdates(); // Male // Female // Number of Colony
        numRemMaleKang_ans.setText(String.valueOf(updates[0]));
        numRemFemaleKang_ans.setText(String.valueOf(updates[1]));
        number_colony_ans.setText(String.valueOf(updates[2]));

        dashboard_chart.getData().clear();

        dataSeriesFemale = new XYChart.Series<>();
        dataSeriesMale = new XYChart.Series<>();

        dashboard_chart.getData().add(dataSeriesMale);
        dashboard_chart.getData().add(dataSeriesFemale);


        //you juz need to change lineA0 value, do not change other value , this code is for the travelling kangaroos code
        //lineA0 is the source and lineB0 is the destination
        if(migration[0] != null) {
            lineA4 = lineA3;
            lineA3 = lineA2;
            lineA2 = lineA1;
            lineA1 = lineA0;
            lineA0 = (String.valueOf(migration[0])); // src
            TR_pointA_line0.setText("Point "+lineA0);
            TR_pointA_line1.setText("Point "+lineA1);
            TR_pointA_line2.setText("Point "+lineA2);
            TR_pointA_line3.setText("Point "+lineA3);
            TR_pointA_line4.setText("Point "+lineA4);
            migration[0] = null;
        }

        if(migration[1] != null) {
            lineB4 = lineB3;
            lineB3 = lineB2;
            lineB2 = lineB1;
            lineB1 = lineB0;
            lineB0 = (String.valueOf(migration[1])); // des
            TR_pointB_line0.setText("Point "+lineB0); 
            TR_pointB_line1.setText("Point "+lineB1);
            TR_pointB_line2.setText("Point "+lineB2);
            TR_pointB_line3.setText("Point "+lineB3);
            TR_pointB_line4.setText("Point "+lineB4);
            migration[1] = null;
        }

        //the below code is to update female, male and food count
        for(int i = 0; i< pSize; i++){
            Point currentP = points.get(i);
            updateData(i, currentP.getID(), currentP.getFoodAmount().intValue(), currentP.getFemaleKangaroo(), currentP.getKangarooNumber());
        }
    }

    public void updateData(int index, int pID, int foodAmount, int femaleKangaroo, int totalK){
        int maleKangaroo = totalK - femaleKangaroo;
        
        dataSeriesMale.getData().add(new XYChart.Data("Point " + pID, maleKangaroo));
        dataSeriesFemale.getData().add(new XYChart.Data("Point " + pID, femaleKangaroo));
        
        maleCT[index].setText(String.valueOf(maleKangaroo));
        femaleCT[index].setText(String.valueOf(femaleKangaroo));
        foodCT[index].setText(String.valueOf(foodAmount));
    }
}