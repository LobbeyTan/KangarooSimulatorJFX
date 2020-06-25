package kangaroo.simulation.FX;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Control;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ToggleSwitch extends Control{
    private BooleanProperty switchedOn = new SimpleBooleanProperty(false);
    
    private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
    private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));
    
    private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);
    
    public ToggleSwitch() {
        Rectangle background = new Rectangle(80, 40);
        background.setArcWidth(40);
        background.setArcHeight(40);
        background.setFill(Color.WHITE);
        background.setStroke(Color.LIGHTGRAY);
        
        Circle trigger = new Circle(20);
        trigger.setCenterX(20);
        trigger.setCenterY(20);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(Color.LIGHTGRAY);
        
        DropShadow shadow = new DropShadow();
        shadow.setRadius(2);
        trigger.setEffect(shadow);
        
        translateAnimation.setNode(trigger);
        fillAnimation.setShape(background);
        getChildren().addAll(background, trigger);
        
        switchedOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState.booleanValue();
            translateAnimation.setToX(isOn ? 80 - 40 : 0);
            fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
            fillAnimation.setToValue(isOn ? Color.LIGHTGREEN : Color.WHITE);
            
            animation.play();
        });
        
        setOnMouseClicked((event) -> {
            switchedOn.set(!switchedOn.get());
        });
    }
    
    public BooleanProperty switchOnProperty() {
        return switchedOn;
    }
    
    public void setSwitchOnProperty(boolean isOn) {
        switchedOn.set(isOn);
    }
}
