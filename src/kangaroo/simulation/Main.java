package kangaroo.simulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static Stage window = null;
    
    @Override
    public void start(Stage stage) throws Exception {        
        stage.initStyle(StageStyle.UNDECORATED);
//        Parent root = FXMLLoader.load(getClass().getResource("/kangaroo/simulation/FX/Simulator.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/kangaroo/simulation/FX/Home.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        window = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
