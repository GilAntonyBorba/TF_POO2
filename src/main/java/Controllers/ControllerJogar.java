package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Classes.Heroi;
import Classes.Personagem;
import Classes.Principal;
import Classes.Vilao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ControllerJogar implements Initializable {

    @FXML
    private ToggleGroup mutante1;

    @FXML
    private ToggleGroup mutante2;

    @FXML
    private RadioButton HeroiPlayer1;

    @FXML
    private RadioButton HeroiPlayer2;

    @FXML
    private RadioButton VilaoPlayer1;

    @FXML
    private RadioButton VilaoPlayer2;

    @FXML
    private Button btnIniciar;

    @FXML
    private Button btnVoltar;

    @FXML
    private ComboBox<String> choicePersonagem1;

    @FXML
    private ComboBox<String> choicePersonagem2;

    @FXML
    void changeArray1(ActionEvent event) {
        choicePersonagem1.setValue(null);
        for (Personagem p : Principal.personagensHerois)
            choicePersonagem1.getItems().remove(p.getNome());
        for (Personagem p : Principal.personagensViloes)
            choicePersonagem1.getItems().remove(p.getNome());
        if (HeroiPlayer1.isSelected()) {
            for (Personagem p : Principal.personagensHerois)
                choicePersonagem1.getItems().add(p.getNome());
        }
        if (VilaoPlayer1.isSelected()) {
            for (Personagem p : Principal.personagensViloes)
                choicePersonagem1.getItems().add(p.getNome());
        }
    }

    @FXML
    void changeArray2(ActionEvent event) {
        choicePersonagem2.setValue(null);
        for (Personagem p : Principal.personagensHerois)
            choicePersonagem2.getItems().remove(p.getNome());
        for (Personagem p : Principal.personagensViloes)
            choicePersonagem2.getItems().remove(p.getNome());
        if (HeroiPlayer2.isSelected()) {
            for (Personagem p : Principal.personagensHerois)
                choicePersonagem2.getItems().add(p.getNome());
        }
        if (VilaoPlayer2.isSelected()) {
            for (Personagem p : Principal.personagensViloes)
                choicePersonagem2.getItems().add(p.getNome());
        }

    }

    @FXML
    void sceneIniciar(ActionEvent event) {

        if((!HeroiPlayer1.isSelected() && !VilaoPlayer1.isSelected()) || (!HeroiPlayer2.isSelected() && !VilaoPlayer2.isSelected()) || choicePersonagem1.getValue() == null || choicePersonagem2.getValue() == null){
            System.out.println("Impossivel iniciar o jogo...");
            return;
        }

        Personagem p1 = null;
        Personagem p2 = null;

        if (HeroiPlayer1.isSelected()) {
            for (Heroi h : Principal.personagensHerois) {
                if (h.getNome().equals(choicePersonagem1.getValue()))
                    p1 = h;
            }
        }else{
            for (Vilao v : Principal.personagensViloes) {
                if (v.getNome().equals(choicePersonagem1.getValue()))
                    p1 = v;
            }
        }
        if (HeroiPlayer2.isSelected()) {
            for (Heroi h : Principal.personagensHerois) {
                if (h.getNome().equals(choicePersonagem2.getValue()))
                    p2 = h;
            }
        }else{
            for (Vilao v : Principal.personagensViloes) {
                if (v.getNome().equals(choicePersonagem2.getValue()))
                    p2 = v;
            }
        }

        Principal.SceneIniciar.setOnKeyPressed(e -> {
            Principal.controllerIniciar.ReceberEvento(e);
        });
        Principal.SceneIniciar.setOnKeyReleased(e -> {
            Principal.controllerIniciar.RetirarEvento(e);
        });
        Principal.controllerIniciar.getPictures(p1, p2);
        Principal.changeScreen("Iniciar");
    }

    @FXML
    void scenePrincipal(ActionEvent event) {
        Principal.changeScreen("Principal");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Principal.addOnChangeScreenListener(new Principal.onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object data) {
                if (newScreen.equals("Jogar")) {
                    System.out.println("Voltou pra Jogar");
                    HeroiPlayer1.setSelected(false);
                    HeroiPlayer2.setSelected(false);
                    VilaoPlayer1.setSelected(false);
                    VilaoPlayer2.setSelected(false);
                    choicePersonagem1.setValue(null);
                    choicePersonagem2.setValue(null);
                }
            }
        });
    }

}