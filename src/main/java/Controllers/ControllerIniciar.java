package Controllers;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import Classes.Heroi;
import Classes.Personagem;
import Classes.Principal;
import Classes.Vilao;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class ControllerIniciar implements Initializable {

    private final Set<KeyCode> pressedKeys = new HashSet<>();

    @FXML
    private ImageView myImageView;

    @FXML
    private ImageView mySecondImageView;

    @FXML
    private ImageView powerImageView;

    @FXML
    private ImageView powerSecondImageView;

    @FXML
    private ProgressBar myProgressBar;

    @FXML
    private ProgressBar myProgressBar1;

    @FXML
    private Label myLabelLife;

    @FXML
    private Label myLabelLife1;

    double progress;
    double progress1;

    private int lifeMyImageView = 100;
    private int lifeMySecondImageView = 100;

    private double myDano = 5;

    private boolean powerActivated = false;
    private boolean powerActivated1 = false;

    private boolean winner = false;

    private double quantTransitionX = 1500.0;
    private double quantDurationTranslate = 1200;

    private TranslateTransition translate; // Variável para armazenar a animação myImageView
    private TranslateTransition translate1; // Variável para armazenar a animação mySecondImageView
    private TranslateTransition powerAnimation;
    private TranslateTransition powerSecondAnimation;

    private Personagem personagem1;
    private Personagem personagem2;

    Image firstPicture;
    Image secondPicture;
    Image firstPower;
    Image secondPower;

    String nameCharacter;
    String nameCharacter1;

    boolean canFly;
    boolean canFly1;

    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-Recebendo as Imagens dos
    // personagens e dos poderes=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    public void getPictures(Personagem p1, Personagem p2) {
        personagem1 = p1;
        personagem2 = p2;
        if (p1 instanceof Heroi) {
            firstPicture = new Image(getClass().getResourceAsStream(Principal.herois.get(p1.getNome())));
        }
        if (p1 instanceof Vilao) {
            firstPicture = new Image(getClass().getResourceAsStream(Principal.viloes.get(p1.getNome())));
        }
        if (p2 instanceof Heroi) {
            secondPicture = new Image(getClass().getResourceAsStream(Principal.herois.get(p2.getNome())));
        }
        if (p2 instanceof Vilao) {
            secondPicture = new Image(getClass().getResourceAsStream(Principal.viloes.get(p2.getNome())));
        }
        firstPower = new Image(getClass().getResourceAsStream(Principal.poderes.get(p1.getPoder())));
        secondPower = new Image(getClass().getResourceAsStream(Principal.poderes.get(p2.getPoder())));

        System.out.println(firstPicture);
        System.out.println(secondPicture);

        nameCharacter = p1.getNome();
        nameCharacter1 = p2.getNome();

        canFly = p1.getVoar() == "" ? false : true;
        canFly1 = p2.getVoar() == "" ? false : true;

        System.out.println(canFly);

        if (!canFly) {
            translate.stop();
        }
        if (!canFly1) {
            translate1.stop();
        }

        myImageView.setImage(firstPicture);
        mySecondImageView.setImage(secondPicture);

        powerImageView.setImage(firstPower);
        powerSecondImageView.setImage(secondPower);

    }

    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-Eventos de
    // Teclado=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

    // Recebe os eventos de teclado quando o usuário pressionar a tecla
    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    public void ReceberEvento(KeyEvent event) {
        pressedKeys.add(event.getCode());
        moveImage();
        System.out.println(event.getCode());
    }

    // Retira o evento de teclado quando o usuário soltar a
    // tecla=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public void RetirarEvento(KeyEvent event) {
        pressedKeys.remove(event.getCode());
    }

    // Método que será responsável por mover as
    // imagens=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    private void moveImage() {
        double ImageMoveX = 0;
        double ImageMoveY = 0;
        double Image1MoveX = 0;
        double Image1MoveY = 0;

        // Obtendo as dimensões da tela/área de jogo
        double screenWidth = 1530.0;
        double screenHeight = 829.0;

        // Defina as dimensões do personagem 1
        double characterWidth = myImageView.getBoundsInParent().getWidth();
        double characterHeight = myImageView.getBoundsInParent().getHeight();

        // Defina as dimensões do personagem 2
        double SecondcharacterWidth = myImageView.getBoundsInParent().getWidth();
        double SecondcharacterHeight = myImageView.getBoundsInParent().getHeight();

        if (pressedKeys.contains(KeyCode.W)) {
            if (myImageView.getLayoutY() - 35 >= 0) {
                if (canFly) {
                    ImageMoveY -= 20;
                }
            }
        }
        if (pressedKeys.contains(KeyCode.S)) {
            if (myImageView.getLayoutY() + myImageView.getBoundsInParent().getHeight() + 10 <= screenHeight) {
                if (canFly) {
                    ImageMoveY += 20;
                }
            }
        }
        if (pressedKeys.contains(KeyCode.A)) {
            if (myImageView.getLayoutX() - 10 >= 0) {
                ImageMoveX -= 20;
            }
        }
        if (pressedKeys.contains(KeyCode.D)) {
            if (myImageView.getLayoutX() + myImageView.getBoundsInParent().getWidth() + 10 <= screenWidth) {
                ImageMoveX += 20;
            }
        }
        // ---------------
        if (pressedKeys.contains(KeyCode.UP)) {
            if (mySecondImageView.getLayoutY() - 35 >= 0) {
                if (canFly1) {
                    Image1MoveY -= 20;
                }
            }
        }
        if (pressedKeys.contains(KeyCode.DOWN)) {
            if (mySecondImageView.getLayoutY() + mySecondImageView.getBoundsInParent().getHeight()+ 10 <= screenHeight) {
                if (canFly1) {
                    Image1MoveY += 20;
                }
            }
        }
        if (pressedKeys.contains(KeyCode.LEFT)) {
            if (mySecondImageView.getLayoutX() - 10 >= 0) {
                Image1MoveX -= 20;
            }
        }
        if (pressedKeys.contains(KeyCode.RIGHT)) {
            if (mySecondImageView.getLayoutX() + mySecondImageView.getBoundsInParent().getWidth() + 10 <= screenWidth) {
                Image1MoveX += 20;
            }
        }
        // ---------------
        // About animations
        if (pressedKeys.contains(KeyCode.Q)) {
            if (!powerActivated) {
                handlePowerActivation();
            }
        }
        if (pressedKeys.contains(KeyCode.SEMICOLON)) {
            if (!powerActivated1) {
                handlePowerActivation1();
            }
        }

        double ImgCurrentX = myImageView.getLayoutX();
        double ImgCurrentY = myImageView.getLayoutY();
        double Img1CurrentX = mySecondImageView.getLayoutX();
        double Img1CurrentY = mySecondImageView.getLayoutY();

        myImageView.setLayoutX(ImgCurrentX + ImageMoveX);
        myImageView.setLayoutY(ImgCurrentY + ImageMoveY);
        mySecondImageView.setLayoutX(Img1CurrentX + Image1MoveX);
        mySecondImageView.setLayoutY(Img1CurrentY + Image1MoveY);
    }

    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-Animations=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

    // Cria as animações dos personagens e
    // Start=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public void initialize(URL arg0, ResourceBundle arg1) {
        translate = createTranslateAnimation(myImageView);
        translate.play();
        translate1 = createTranslateAnimation(mySecondImageView);
        translate1.play();

        powerImageView.setVisible(false);

        myProgressBar.setStyle("-fx-accent: red;");
        myLabelLife.setText(Double.toString(lifeMyImageView));

        myProgressBar1.setStyle("-fx-accent: red;");
        myLabelLife1.setText(Double.toString(lifeMySecondImageView));
    }

    private TranslateTransition createTranslateAnimation(ImageView imageView) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(imageView);
        translate.setDuration(Duration.millis(1500));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setInterpolator(Interpolator.EASE_BOTH);
        translate.setByY(30);
        translate.setByY(-30);
        translate.setAutoReverse(true);
        return translate;
    }

    // Métodos que serão responsáveis por mover os
    // poderes=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    // Ativação do Poder
    // 1=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    public void handlePowerActivation() {
        final Timeline[] collisionCheckTimeline = { null }; // Usando um array para armazenar a Timeline
        if (!powerActivated) {
            powerActivated = true;

            // Obtenha a posição atual da Scarlet Witch
            double scarletX = myImageView.getLayoutX();
            double scarletY = myImageView.getLayoutY();

            // Configure a posição do poderzinho
            powerImageView.setLayoutX(scarletX + 100);
            powerImageView.setLayoutY(scarletY - 20);

            // Ative a animação do poderzinho
            powerAnimation = createPowerAnimation(powerImageView);

            // Criando a Timeline
            collisionCheckTimeline[0] = new Timeline(
                    new KeyFrame(Duration.millis(100), event -> {
                        System.out.println("Timeline");
                        if (checkCollision(powerImageView, mySecondImageView)) {
                            System.out.println("Poder atingiu " + nameCharacter1 + " !");
                            decreaseProgress1();
                            powerImageView.setVisible(false);
                            verifyWinner();
                        }
                    }));
            collisionCheckTimeline[0].setCycleCount(Timeline.INDEFINITE); // Define a execução contínua da linha do
                                                                          // tempo
            collisionCheckTimeline[0].play();

            // Start Animation
            powerAnimation.play();

            // Mostra o poderzinho
            powerImageView.setVisible(true);

            // Animação de retorno do poderzinho
            powerAnimation.setOnFinished(event -> {
                TranslateTransition returnAnimation = createReturnAnimation(powerImageView);
                returnAnimation.play();
                returnAnimation.setOnFinished(event2 -> {
                    powerImageView.setVisible(false);
                    powerActivated = false;
                    collisionCheckTimeline[0].stop();
                });
            });

        }
    }

    // Ativação do Poder
    // 2=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    public void handlePowerActivation1() {
        final Timeline[] collisionCheckTimeline1 = { null }; // Usando um array para armazenar a Timeline
        if (!powerActivated1) {
            powerActivated1 = true;

            // Obtenha a posição atual da Scarlet Witch
            double secondScarletX = mySecondImageView.getLayoutX();
            double secondScarletY = mySecondImageView.getLayoutY();

            // Configure a posição do poderzinho
            powerSecondImageView.setLayoutX(secondScarletX + 100);
            powerSecondImageView.setLayoutY(secondScarletY - 20);

            // Ative a animação do poderzinho
            powerSecondAnimation = createPowerAnimation1(powerSecondImageView);

            // Criando a Timeline
            collisionCheckTimeline1[0] = new Timeline(
                    new KeyFrame(Duration.millis(100), event -> {
                        System.out.println("Timeline1");
                        if (checkCollision(powerSecondImageView, myImageView)) {
                            System.out.println("Poder atingiu " + nameCharacter + " !");
                            decreaseProgress();
                            powerSecondImageView.setVisible(false);
                            verifyWinner();
                        }
                    }));
            collisionCheckTimeline1[0].setCycleCount(Timeline.INDEFINITE); // Define a execução contínua da linha do
                                                                           // tempo
            collisionCheckTimeline1[0].play();

            // Start Animation
            powerSecondAnimation.play();

            // Mostra o poderzinho
            powerSecondImageView.setVisible(true);

            // Animação de retorno do poderzinho
            powerSecondAnimation.setOnFinished(event -> {
                TranslateTransition returnAnimation1 = createReturnAnimation1(powerSecondImageView);
                returnAnimation1.play();
                returnAnimation1.setOnFinished(event1 -> {
                    powerSecondImageView.setVisible(false);
                    powerActivated1 = false;
                    collisionCheckTimeline1[0].stop();
                });
            });

        }
    }

    // Cria as animações dos
    // poderes=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-z

    private TranslateTransition createPowerAnimation(ImageView imageView) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(imageView);
        translate.setDuration(Duration.millis(quantDurationTranslate));
        translate.setInterpolator(Interpolator.EASE_BOTH);
        translate.setByX(quantTransitionX);

        return translate;
    }

    private TranslateTransition createPowerAnimation1(ImageView imageView) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(imageView);
        translate.setDuration(Duration.millis(quantDurationTranslate));
        translate.setInterpolator(Interpolator.EASE_BOTH);
        translate.setByX(-quantTransitionX);

        return translate;
    }

    private TranslateTransition createReturnAnimation(ImageView imageView) {
        TranslateTransition returnAnimation = new TranslateTransition();
        returnAnimation.setNode(imageView);
        returnAnimation.setDuration(Duration.millis(1)); // Define a duração como 1 segundo
        returnAnimation.setInterpolator(Interpolator.EASE_BOTH);
        returnAnimation.setByX(-quantTransitionX); // Define o movimento de volta
        return returnAnimation;
    }

    private TranslateTransition createReturnAnimation1(ImageView imageView) {
        TranslateTransition returnAnimation = new TranslateTransition();
        returnAnimation.setNode(imageView);
        returnAnimation.setDuration(Duration.millis(1)); // Define a duração como 1 segundo
        returnAnimation.setInterpolator(Interpolator.EASE_BOTH);
        returnAnimation.setByX(+quantTransitionX); // Define o movimento de volta
        return returnAnimation;
    }

    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-IncreaseProgress=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    public void decreaseProgress() {
        if (lifeMyImageView != 0) {
            progress -= myDano / 100;
            myProgressBar.setProgress(progress);
            lifeMyImageView -= myDano;
            myLabelLife.setText(Double.toString(lifeMyImageView));
        }

    }

    public void decreaseProgress1() {
        if (lifeMySecondImageView != 0) {
            progress1 -= myDano / 100;
            ;
            myProgressBar1.setProgress(progress1);
            lifeMySecondImageView -= myDano;
            myLabelLife1.setText(Double.toString(lifeMySecondImageView));
        }
    }

    public void verifyWinner() {
        System.out.println(nameCharacter1);
        if (!winner) {
            if (lifeMyImageView == 0) {
                System.out.println("Player " + nameCharacter1 + " Wins!");
                if(personagem1 instanceof Heroi){
                    Principal.personagensHerois.remove(personagem1);
                }else{
                    Principal.personagensViloes.remove(personagem1);
                }
                winner = true;
                Principal.changeScreen("Principal");
            }
            if (lifeMySecondImageView == 0) {
                System.out.println("Player " + nameCharacter + " Wins!");
                if(personagem2 instanceof Heroi){
                    Principal.personagensHerois.remove(personagem2);
                }else{
                    Principal.personagensViloes.remove(personagem2);
                }
                winner = true;
                Principal.changeScreen("Principal");
            }
        }

    }

    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-Getters=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

    public ImageView getMyImageView() {
        return myImageView;
    }

    public ImageView getMySecondImageView() {
        return mySecondImageView;
    }

    private boolean checkCollision(ImageView power, ImageView character) {
        return power.getBoundsInParent().intersects(character.getBoundsInParent());
    }

}
