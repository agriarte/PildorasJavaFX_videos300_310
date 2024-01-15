package pildoras.javafx_animaciones_videos_300_310;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX Video302_KeyFrames
 */
public class Video302_Keyframes extends Application {

    private Circle pelota;
    private double despl_X = 2;
    private double despl_Y = 3;

    final private double ANCHO = 600;
    final private double ALTO = 400;
    final private double TAMANO_BOLA = 40;

    @Override
    public void start(Stage stage) {

        // Crear un gradiente radial que se utilizará como relleno para la pelota
        RadialGradient radialGradient = new RadialGradient(
                0, 0, // Centro del gradiente
                0.32, 0.32, // Radio del foco del gradiente
                0.5, // Radio del gradiente
                true, // Proporción proporcional al radio (proporcional al radio en lugar de al área)
                CycleMethod.NO_CYCLE, // Método de ciclo del gradiente
                new Stop(0, Color.WHITE), // Parada de color inicial en blanco
                new Stop(1, Color.BLUE) // Parada de color final en azul
        );

        // Crear un círculo (pelota) con el gradiente radial como relleno
        pelota = new Circle(TAMANO_BOLA, radialGradient);

        // Establecer las coordenadas del centro de la pelota
        pelota.setCenterX(TAMANO_BOLA);
        pelota.setCenterY(TAMANO_BOLA);

        // Crear un grupo para contener la pelota, ya que no se puede agregar directamente un círculo a la escena
        Group grupo1 = new Group(pelota);

        // Definir un KeyFrame que se ejecutará cada 5 milisegundos
        KeyFrame miKey1 = new KeyFrame(Duration.millis(5), e -> {
            // Actualizar las coordenadas de la pelota según el desplazamiento en X e Y
            pelota.setCenterX(pelota.getCenterX() + despl_X);
            pelota.setCenterY(pelota.getCenterY() + despl_Y);

            // Revertir el desplazamiento en X si la pelota alcanza los límites horizontales
            if (pelota.getCenterX() < TAMANO_BOLA || pelota.getCenterX() >= ANCHO - TAMANO_BOLA) {
                despl_X = -despl_X;
            }

            // Revertir el desplazamiento en Y si la pelota alcanza los límites verticales
            if (pelota.getCenterY() <= TAMANO_BOLA || pelota.getCenterY() >= ALTO - TAMANO_BOLA) {
                despl_Y = -despl_Y;
            }
        });

        // Crear una línea de tiempo (Timeline) con el KeyFrame y configurarla para repetirse indefinidamente
        Timeline timeline = new Timeline(miKey1);
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Iniciar la animación
        timeline.play();

        Scene scene = new Scene(grupo1, ANCHO, ALTO);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
