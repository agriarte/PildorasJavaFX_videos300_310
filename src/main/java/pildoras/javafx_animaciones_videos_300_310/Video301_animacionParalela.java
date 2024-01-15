package pildoras.javafx_animaciones_videos_300_310;

import javafx.animation.ParallelTransition;
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
 * JavaFX Video301 animación paralela
 */
public class Video301_animacionParalela extends Application {

    @Override
    public void start(Stage stage) {

        Circle circulo1 = new Circle(0, 100, 70, Color.BLUE);
        Circle circulo2 = new Circle(0, 100, 70, Color.GREEN);
        
        //creo un grupo porque no se puede agregar directamente un circulo a la escena
        Group grupo1 = new Group(circulo1,circulo2);

        Scene scene = new Scene(grupo1, 640, 480);
        stage.setScene(scene);

        //ANIMACION PELOTA 1
        TranslateTransition t = new TranslateTransition(Duration.millis(700), circulo1);

        t.setFromX(circulo1.getRadius());
        t.setToX(scene.getWidth() - circulo1.getRadius());

        t.setFromY(scene.getHeight() / 2);
        t.setToY(scene.getHeight() / 2);

        t.setCycleCount(Transition.INDEFINITE);
        t.setAutoReverse(true);
        
        //ANIMACION PELOTA 2
        TranslateTransition t2 = new TranslateTransition(Duration.millis(700), circulo2);

        t2.setFromX(scene.getWidth() - circulo2.getRadius());
        t2.setToX(circulo2.getRadius());

        t2.setFromY(scene.getHeight() / 4);
        t2.setToY(scene.getHeight() / 4);

        t2.setCycleCount(Transition.INDEFINITE);
        t2.setAutoReverse(true);

        // Arrancar la animación en Paralelo
        ParallelTransition pt = new ParallelTransition(t,t2);
        pt.play();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
