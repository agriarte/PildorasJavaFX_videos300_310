package pildoras.javafx_animaciones_videos_300_310;

import java.util.ArrayList;
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
 * JavaFX Video304 a 308. Ejercicio Pelotas rebotando
 */
public class Video304_308_EjercicioPelotasRebotando extends Application {

    final private double ANCHO = 600;
    final private double ALTO = 400;

    private ArrayList<Pelota> pelotas = new ArrayList<>();

    @Override
    public void start(Stage stage) {

        // 
        Group miGrupo = new Group();

        //bucle que crea X pelotas
        for (int i = 0; i < 10; i++) {
            pelotas.add(new Pelota(20, ANCHO, ALTO, pelotas));
        }

        miGrupo.getChildren().addAll(pelotas);

        Scene scene = new Scene(miGrupo, ANCHO, ALTO);
        stage.setScene(scene);
        stage.setTitle("Ejercicio Pelotas");

        stage.show();

        //ANIMACION
        KeyFrame k = new KeyFrame(Duration.millis(10), e -> {
            for (Pelota pelota : pelotas) {
                pelota.mover();
            }
        });
        Timeline miTimeLine = new Timeline(k);
        miTimeLine.setCycleCount(Timeline.INDEFINITE);
        miTimeLine.play();
    }

    public static void main(String[] args) {
        launch();
    }

    //********** clase interna de PelotasRebotan
    //lo hace así para que el arraylist de pelotas sea visible en la clase principal y en pelotas
    class Pelota extends Circle {

        public double radio;
        private final double CAMPOANCHO;
        private final double CAMPOALTO;

        public double x_velocidad;
        public double y_velocidad;

        public Pelota(double radio, double campoAncho, double campoAlto, ArrayList<Pelota> pelotas) {
            super();
            this.radio = radio;
            this.CAMPOANCHO = campoAncho;
            this.CAMPOALTO = campoAlto;

            super.setRadius(radio);
            //num aleatorio. Rango el ancho del Pane - radio de la pelota. En el curso comenta sumar 1 para evitar resultado 0.
            //No se entiende ese razonamiento ya que si el random menor del radio aunque sumes 1 puedes tener valores negativos
            //Sí se consigue que sea como mínimo visible 1 pixel
            super.setCenterX(Math.random() * (campoAncho - this.radio) + 1);
            super.setCenterY(Math.random() * (campoAlto - this.radio) + 1);

            // Degradado de la pelota
            RadialGradient g = new RadialGradient(0, 0, 0.40, 0.40, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(1, Color.CORAL));
            super.setFill(g);
            
            //Velocidades aleatorias
            this.x_velocidad = Math.random() * 3 + 1;
            this.y_velocidad = Math.random() * 3 + 1;
        }

        //Método para mover las pelotas
        public void mover() {
            super.setCenterX(super.getCenterX() + this.x_velocidad);
            super.setCenterY(super.getCenterY() + this.y_velocidad); // Corregir esta línea

            //detectar colisión con borde izquierdo
            //(si coordenada es menor que el radio de la pelota está tocando
            if (super.getCenterX() <= this.radio) {
                super.setCenterX(radio);
                //invertir velocidad X para que vaya en otra dirección
                this.x_velocidad = -this.x_velocidad;
            }
            //detectar colisión con borde derecho
            //(si coordenada es mayor que el ancho de la escena - radio
            if (super.getCenterX() >= this.CAMPOANCHO - this.radio) {
                super.setCenterX(this.CAMPOANCHO - this.radio);
                //invertir velocidad X para que vaya en otra dirección
                this.x_velocidad = -this.x_velocidad;
            }

            //detectar colisión con borde superior
            if (super.getCenterY() <= this.radio) {
                super.setCenterY(this.radio);
                //invertir velocidad Y para que vaya en otra dirección
                this.y_velocidad = -this.y_velocidad;
            }
            //detectar colisión con borde inferior
            if (super.getCenterY() >= this.CAMPOALTO - this.radio) {
                super.setCenterY(this.CAMPOALTO - this.radio);
                //invertir velocidad Y para que vaya en otra dirección
                this.y_velocidad = -this.y_velocidad;
            }

            // Detectar colisiones con otras pelotas
            for (Pelota b : pelotas) {
                //intersect devuelve true si el objeto chocha contra objeto.
                //en este ejemplo no se tiene en cuenta contra que choca.
                //Cualquier choque provocará el mismo comportamiento que es cambiar las direcciones X e Y.
                if (b != this && b.intersects(super.getLayoutBounds())) {
                    // se guarda una variable temporal con los valores XY de la pelota del objeto que llama al método intersects
                    // se invierte la velocidad  de 2 bolas en caso de colisión
                    double tempX = this.x_velocidad;
                    double tempY = this.y_velocidad;
                    this.x_velocidad = b.x_velocidad;
                    b.x_velocidad = tempX;
                    this.y_velocidad = b.y_velocidad;
                    b.y_velocidad = tempY;
                    break;// Si choca romper bucle para que no siga evaluando.
                }
            }
        }
    }

}
