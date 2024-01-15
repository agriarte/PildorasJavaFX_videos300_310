package pildoras.javafx_animaciones_videos_300_310;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * JavaFX Video310 Animaciones táctiles
 * Para probarlo es necesario ejecutar el código desde una pantalla táctil o 
 * instalar el plugin de Gluon para ejecutar JavaFX en dispositivos móviles
 */
public class Video310_AnimacionesTactiles extends Application {

    private final double ANCHO_ESCENA = 600;
    private final double ALTO_ESCENA = 400;
    private final double ALTO_CUADRADO = 200;
    private final double ANCHO_CUADRADO = 200;

    @Override
    public void start(Stage stage) {

        // Crear un grupo para contener el rectángulo, ya que no se puede agregar directamente a la escena
        Group miGrupo = new Group();

        Rectangle r = new Rectangle((ANCHO_ESCENA - ANCHO_CUADRADO) / 2, (ALTO_ESCENA - ALTO_CUADRADO) / 2, ANCHO_CUADRADO, ALTO_CUADRADO);
        r.setFill(Color.BLUE);
        r.setStroke(Color.RED);
        r.setStrokeWidth(2);

        // EVENTOS DE ZOOM
        r.setOnZoom(e -> {
            //actualizar escala según zoom. Escala actual * factor de escala del evento zoom (dedos en pellizco)
            r.setScaleX(r.getScaleX() * e.getZoomFactor());
            r.setScaleY(r.getScaleY() * e.getZoomFactor());

            e.consume();//para que no propague el evento a los objetos padre.
        });
        // EVENTOS DE ROTACION
        r.setOnRotate(e -> {
            //actualizar ángulo de rotación sumando ángulo actual más el ángulo rotado con los dedos.
            r.setRotate(r.getRotate() + e.getAngle());
            e.consume();
        });
        // EVENTOS DE DESPLAZAMIENTO
        // derecha, izquierda, arriba y abajo
        r.setOnSwipeLeft(e -> {
            r.setX(0);
            e.consume();
        });

        r.setOnSwipeRight(e -> {
            r.setX(ANCHO_ESCENA - r.getWidth());
            e.consume();
        });

        r.setOnSwipeDown(e -> {
            r.setY(ALTO_ESCENA - r.getHeight());
            e.consume();
        });

        r.setOnSwipeUp(e -> {
            r.setY(0);
            e.consume();
        });

        miGrupo.getChildren().add(r);

        Scene scene = new Scene(miGrupo, ANCHO_ESCENA, ALTO_ESCENA);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
