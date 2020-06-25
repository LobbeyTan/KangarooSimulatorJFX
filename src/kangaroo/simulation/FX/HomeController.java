package kangaroo.simulation.FX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;
import static kangaroo.simulation.FX.SimulatorController.start;
import kangaroo.simulation.Main;
import kangaroo.simulation.audio.AudioPlayer;


public class HomeController implements Initializable {

    @FXML
    private VBox sideBar;
    @FXML
    private Button home;
    @FXML
    private Button simulator;
    @FXML
    private Button dashboard;
    @FXML
    private Button help;
    @FXML
    private Button setting;
    @FXML
    private Pane homeScene;
    @FXML
    private Canvas displayHome;
    @FXML
    private HBox topBar;
    @FXML
    private ImageView close;
    @FXML
    private ImageView minimize;
    @FXML
    private ImageView mute;
    @FXML
    private ImageView fastForward;
    @FXML
    private ImageView play;
    @FXML
    private ImageView stop;
    @FXML
    private ImageView slowForward;
    @FXML
    private ImageView sideTransition;
    @FXML
    private BorderPane container;
    @FXML
    private Group endAlert;
    @FXML
    private Button button_Yes;
    @FXML
    private Button button_No;
    @FXML
    private Button button_Restart;
    
    private Image bgImage;
    private static final String HOMEFXML = "/kangaroo/simulation/FX/HOME.fxml";
    private static final String POINTCONFIGFXML = "/kangaroo/simulation/FX/PointGenerator.fxml";
    private static final String KANGAROOCONFIGRFXML = "/kangaroo/simulation/FX/KangarooGenerator.fxml";
    private static final String SIMULATORFXML = "/kangaroo/simulation/FX/Simulator.fxml";
    private static final String DASHBOARDFXML = "/kangaroo/simulation/FX/Dashboard.fxml";
    private static final String HELPFXML = "/kangaroo/simulation/FX/Help.fxml";
    private static final String SETTINGFXML = "/kangaroo/simulation/FX/Setting.fxml";
    
    private AudioClip mouseClick = new AudioClip(getClass().getResource("/resources/audio/mouseClick.mp3").toString());
    private AudioClip mouseHover = new AudioClip(getClass().getResource("/resources/audio/mouseHover.mp3").toString());
    
    public static final Integer HOME = 1;
    public static final Integer SIMULATOR = 2;
    public static final Integer DASHBOARD = 3;
    public static final Integer HELP = 4;
    public static final Integer SETTING = 5;
    public static final Integer DISABLESCENESWITCH = 6;
    
    public static final Integer POINTGENERATOR = 1;
    public static final Integer KANGAROOGENRATOR = 2;
    public static final Integer SIMULATION = 3;
    public static final Integer SETTINGGENERATOR = 4;
    public static final Integer HELPGENERATOR=5;
    
    public static final Integer SCENEWIDTH = 1050;
    public static final Integer SCENEHEIGHT = 800;
    
    public static Parent homeRoot;
    public static Parent pointConfigRoot;
    public static Parent kangarooConfigRoot;
    public static Parent simulatorRoot;
    public static Parent dashboardRoot;
    public static Parent helpRoot;
    public static Parent settingRoot;
    public static boolean isRunning;
    
    public static AudioPlayer audioPlayer;
    
    public static BorderPane currentContainer;
    public static Node currentRoot;
    
    public static int currentScene = 1;
    public static int tempScene = 0;
    public static int currentSimulatorScene = 1;
    
    public static boolean isExpand = false;
    public static boolean isTranslating = false;
    public static boolean isMute = false;
    
    private double xOffset, yOffset;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isRunning = true;
        GraphicsContext graphic = displayHome.getGraphicsContext2D();
        bgImage = new Image("/resources/background/homeBackground.jpg");
        graphic.drawImage(bgImage, (bgImage.getWidth()-displayHome.getWidth())/2, -30, displayHome.getWidth(), displayHome.getHeight(), 0, 0, displayHome.getWidth(), displayHome.getHeight());
        initAudio();
        dragable();
        init();
    }
    
    private void dragable() {
        topBar.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
            event.consume();
        });
        
        topBar.addEventHandler(MouseDragEvent.MOUSE_DRAGGED, (event) -> {
            topBar.setCursor(Cursor.CLOSED_HAND);
            Main.window.setX(event.getScreenX() - xOffset);
            Main.window.setY(event.getScreenY() - yOffset);
            Main.window.setOpacity(0.8f);
            event.consume();
        });
        
        topBar.setOnDragDone((event) -> {
            topBar.setCursor(Cursor.DEFAULT);
            Main.window.setOpacity(1.0f);
            event.consume();
        });
        
        topBar.setOnMouseReleased((event) -> {
            topBar.setCursor(Cursor.DEFAULT);
            Main.window.setOpacity(1.0f);
            event.consume();
        });
    }
    
    private void initAudio() {
        audioPlayer = new AudioPlayer("Music 1", true);
        audioPlayer.play(true, AudioPlayer.INFINITY);
        audioPlayer.setVolume(0.1);
        mouseClick.setVolume(100);
        mouseHover.setVolume(100);
    }
    
    @FXML
    private void displayHome(ActionEvent event) {
        if(currentScene == HOME || currentScene == DISABLESCENESWITCH || isTranslating) return;
        parallelTransition(homeRoot);
        currentScene = HOME;
    }
    
    @FXML
    private void simulatorController(ActionEvent event) {
        if(currentScene == SIMULATOR || currentScene == DISABLESCENESWITCH || isTranslating) return;
        if(currentSimulatorScene == POINTGENERATOR) {showPointGenerator(); return;}
        if(currentSimulatorScene == KANGAROOGENRATOR) {showKangarooGenerator(); return;}
        if(currentSimulatorScene == SIMULATION) showSimulator();
    }
    
    public void showPointGenerator() {
        slideTansition(pointConfigRoot, SCENEWIDTH, 0, true);
        currentScene = SIMULATOR;
        currentSimulatorScene = POINTGENERATOR;
    }
    
    public void showKangarooGenerator() {
        slideTansition(kangarooConfigRoot, SCENEWIDTH, 0, true);
        currentScene = SIMULATOR;
        currentSimulatorScene = KANGAROOGENRATOR;
    }

    public void showSimulator() {
        slideTansition(simulatorRoot, SCENEWIDTH, 0, true);
        currentScene = SIMULATOR;
        currentSimulatorScene = SIMULATION;
    }

    @FXML
    private void showDashboard(ActionEvent event) {
        if(currentScene == DASHBOARD || currentScene == DISABLESCENESWITCH || isTranslating) return;
        parallelTransition(dashboardRoot);
        currentScene = DASHBOARD;
    }

    @FXML
    private void showHelp(ActionEvent event) {
        if(currentScene == HELP || currentScene == DISABLESCENESWITCH || isTranslating) return;
        parallelTransition(helpRoot);
        currentScene = HELP;
    }

    @FXML
    private void showSetting(ActionEvent event) {
        if(currentScene == SETTING || currentScene == DISABLESCENESWITCH || isTranslating) return;
        parallelTransition(settingRoot);
        currentScene = SETTING;
    }

    @FXML
    private void close(MouseEvent event) {
        stopAllTimer(); isRunning = false; System.exit(0);
    }

    @FXML
    private void minimize(MouseEvent event) {
        Stage stage = (Stage) container.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML
    private void sideTranslation(MouseEvent event) {
        ObservableList<Node> childrens = sideBar.getChildren();
        if(!isExpand) {
            for(int i = 0; i < childrens.size(); i++) childrens.get(i).setTranslateX(-186);
            sideBar.setPrefWidth(64);
            sideTransition.setImage(new Image("/resources/Icons/expand.png"));
            isExpand = true;
        }else {
            for(int i = 0; i < childrens.size(); i++) childrens.get(i).setTranslateX(0);
            sideBar.setPrefWidth(250);
            sideTransition.setImage(new Image("/resources/Icons/contract.png"));
            isExpand = false;
        }
    }
    
    @FXML
    private void updateTransition(MouseEvent event) {
        if(currentScene == HOME) {
            displayHome.setWidth(homeScene.getWidth());
            displayHome.setHeight(homeScene.getHeight());
            GraphicsContext graphic = displayHome.getGraphicsContext2D();
            graphic.drawImage(bgImage, (bgImage.getWidth()-displayHome.getWidth())/2, -30, displayHome.getWidth(), displayHome.getHeight(), 0, 0, displayHome.getWidth(), displayHome.getHeight()); 
            return;
        }
        
    }
    
    @FXML
    private void hover(MouseEvent event) {
        mouseHover.play();
    }

    @FXML
    private void click(MouseEvent event) {
        mouseClick.play();
    }
    
    private void init() {
        try {
            homeRoot = homeScene;
            currentContainer = container;
            pointConfigRoot = FXMLLoader.load(getClass().getResource(POINTCONFIGFXML));
            kangarooConfigRoot = FXMLLoader.load(getClass().getResource(KANGAROOCONFIGRFXML));
            simulatorRoot = FXMLLoader.load(getClass().getResource(SIMULATORFXML));
            dashboardRoot = FXMLLoader.load(getClass().getResource(DASHBOARDFXML));
            settingRoot = FXMLLoader.load(getClass().getResource(SETTINGFXML));
            helpRoot = FXMLLoader.load(getClass().getResource(HELPFXML));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //======================================TRANSITION=======================================//
    
    public void slideTansition (Parent root, int fromValue, int toValue, boolean translateX) {
        isTranslating = true;
        if(translateX) root.translateXProperty().set(fromValue);
        else root.translateYProperty().set(fromValue);
        
        Node previousRoot = currentContainer.getCenter();
        
        currentContainer.setCenter(root);
        currentRoot = currentContainer.getCenter();
        Timeline timeLine = new Timeline();
        KeyValue keyValue = new KeyValue(((translateX)? root.translateXProperty() : root.translateYProperty()), toValue, Interpolator.EASE_IN);
        KeyFrame keyframe = new KeyFrame(Duration.seconds(1), keyValue);
        timeLine.getKeyFrames().add(keyframe);
        timeLine.play();
        
        timeLine.setOnFinished((event1) -> {
            currentContainer.getChildren().remove(previousRoot);
            isTranslating = false;
        });
    }
    
    public void parallelTransition(Node in) {
        isTranslating = true;
        Node out = currentContainer.getCenter();
        
        in.translateXProperty().set(SCENEWIDTH);
        currentContainer.setCenter(in);
        currentRoot = currentContainer.getCenter();
        TranslateTransition timeline1 = new TranslateTransition(Duration.seconds(1), in);
        TranslateTransition timeline2 = new TranslateTransition(Duration.seconds(1), out);
        timeline1.setToX(0);
        timeline2.setToX(SCENEWIDTH);
        
        ParallelTransition parallelTransition = new ParallelTransition(timeline2, timeline1);
        parallelTransition.play();
        
        parallelTransition.setOnFinished((event) -> {
            currentContainer.getChildren().remove(out);
            isTranslating = false;
        });
    }
    
    public void fadeTransition(Node node, int fromValue, int toValue) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1));
        fade.setNode(node);
        fade.setFromValue(fromValue);
        fade.setToValue(toValue);
        fade.play();
    }
    
    public void loadScene(String FXML) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource(FXML));
            Node currentScene = currentContainer.getCenter();
            currentContainer.getChildren().add(root);
            currentContainer.getChildren().remove(currentScene);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////////////////////BUTTONS////////////////////////////////////
    
    @FXML
    private void mute(MouseEvent event) {
        isMute = !isMute;
        audioPlayer.setMute(isMute);
        if(isMute) {
            mute.setImage(new Image("/resources/Icons/mute.png"));
        }else mute.setImage(new Image("/resources/Icons/unmute.png"));
    }

    @FXML
    private void fastForward(MouseEvent event) {
        SimulatorController.setSpeed(true);
    }

    @FXML
    private void play(MouseEvent event) {
        SimulatorController.start = true;
    }

    @FXML
    private void stop(MouseEvent event) {
        SimulatorController.start = false;
    }

    @FXML
    private void slowForward(MouseEvent event) {
        SimulatorController.setSpeed(false);
    }

    @FXML
    private void exit(ActionEvent event) {
        stopAllTimer(); isRunning = false; System.exit(0);
    }

    @FXML
    private void resume(ActionEvent event) {
        alertToDefault();
        
        currentScene = tempScene;
        SimulatorController.terminateCnt -= 3; 
        start = true;
    }

    @FXML
    private void restart(ActionEvent event) {
        reinit();
        showPointGenerator();
        
        alertToDefault(); 
    }
    
    @FXML
    private void restartToHome(MouseEvent event) {
        reinit();
        
        if(currentScene != HOME) {
            parallelTransition(homeRoot);
            currentScene = HOME;
        }
        currentSimulatorScene = POINTGENERATOR;
        
        alertToDefault();
    }
    
    private void alertToDefault(){
        endAlert.setDisable(true);
        endAlert.setVisible(false);
        
        container.getChildren().remove(endAlert);
        container.setRight(endAlert);
    }
    
    private void reinit() {
        try {
            stopAllTimer();
            pointConfigRoot = null;
            kangarooConfigRoot = null;
            simulatorRoot = null;
            dashboardRoot = null;
            
            System.gc();
            
            pointConfigRoot = FXMLLoader.load(getClass().getResource(POINTCONFIGFXML));
            kangarooConfigRoot = FXMLLoader.load(getClass().getResource(KANGAROOCONFIGRFXML));
            simulatorRoot = FXMLLoader.load(getClass().getResource(SIMULATORFXML));
            dashboardRoot = FXMLLoader.load(getClass().getResource(DASHBOARDFXML));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void stopAllTimer() {
        SimulatorController.runtimer.stop();
        DashboardController.scheduledExecutorService.shutdown();
    }

    
}
