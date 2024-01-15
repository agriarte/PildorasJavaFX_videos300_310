package pildoras.javafx_animaciones_videos_300_310;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX Video300_animacionPelotaH
 */
public class Video300_animacionPelotaH extends Application {

    @Override
    public void start(Stage stage) {
        
        Circle circulo1 = new Circle(0, 100, 70, Color.BLUE);
        //creo un grupo porque no se puede agregar directamente un circulo a la escena
        Group grupo1 = new Group(circulo1);
         
        Scene scene = new Scene(grupo1, 640, 480);
        stage.setScene(scene);
        
        
        //ANIMACION
        TranslateTransition t = new TranslateTransition(Duration.millis(700), grupo1);
        
        t.setFromX(circulo1.getRadius()); 
        t.setToX(scene.getWidth()-circulo1.getRadius());
        
        t.setFromY(scene.getHeight()/2);
        t.setToY(scene.getHeight()/2);
        
        t.setCycleCount(Transition.INDEFINITE);
        t.setAutoReverse(true);
        
        t.play();
        
        
        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}