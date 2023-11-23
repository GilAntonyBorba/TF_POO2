package Controllers;

import Classes.Principal;
import Classes.Heroi;
import Classes.Vilao;
import Classes.Personagem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ControllerVisualizar implements Initializable {

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Text juncao;

    @FXML
    private ChoiceBox<String> choicePersonagem;

    @FXML
    private ImageView imgPersonagem;

    @FXML
    private ImageView imgPoder;

    @FXML
    private ToggleGroup mutante;

    @FXML
    private RadioButton radioHeroi;

    @FXML
    private RadioButton radioVilao;

    @FXML
    private Text textPower;

    @FXML
    void changeArray(ActionEvent event) {
        textPower.setText(null);
        juncao.setText(null);
        choicePersonagem.setValue(null);
        for (Personagem p : Principal.personagensHerois)
            choicePersonagem.getItems().remove(p.getNome());
        for (Personagem p : Principal.personagensViloes)
            choicePersonagem.getItems().remove(p.getNome());
        if (radioHeroi.isSelected()) {
            for (Personagem p : Principal.personagensHerois)
                choicePersonagem.getItems().add(p.getNome());
        }
        if (radioVilao.isSelected()) {
            for (Personagem p : Principal.personagensViloes)
                choicePersonagem.getItems().add(p.getNome());
        }
        juncao.setText(null);
        imgPersonagem.setVisible(false);
        imgPoder.setVisible(false);
        choicePersonagem.setOnAction(this::getPoder);
    }

    void getPoder(ActionEvent event) {
        String escolhaPersonagem = choicePersonagem.getValue();
        juncao.setText("+");
        imgPersonagem.setVisible(true);
        imgPoder.setVisible(true);

        for (String s : Principal.herois.keySet()) {
            if (s.equals(escolhaPersonagem)) {
                imgPersonagem.setImage(new Image(getClass().getResourceAsStream(Principal.herois.get(s))));
                for (Heroi h : Principal.personagensHerois) {
                    if (h.getNome().equals(s)) {
                        imgPoder.setImage(new Image(
                                getClass().getResourceAsStream(Principal.poderes.get(h.getPoder()))));
                    }
                }
            }
        }
        for (String s : Principal.viloes.keySet()) {
            if (s.equals(escolhaPersonagem)) {
                imgPersonagem.setImage(new Image(getClass().getResourceAsStream(Principal.viloes.get(s))));
                for (Vilao h : Principal.personagensViloes) {
                    if (h.getNome().equals(s)) {
                        imgPoder.setImage(new Image(
                                getClass().getResourceAsStream(Principal.poderes.get(h.getPoder()))));
                    }
                }
            }
        }

        for (Heroi p : Principal.personagensHerois) {
            if (p.getNome().equals(escolhaPersonagem)) {
                textPower.setText(p.getPoder() + p.getVoar());

            }
        }
        for (Vilao p : Principal.personagensViloes) {
            if (p.getNome().equals(escolhaPersonagem)) {
                textPower.setText(p.getPoder() + p.getVoar());
            }
        }
    }

    // PODE SER PRECISO SER ALTERADO!!!!
    @FXML
    void excluirCadastro(ActionEvent event) {
        Principal.personagensHerois.clear();
        Principal.personagensViloes.clear();

        choicePersonagem.getItems().clear();
        choicePersonagem.setValue(null);

        changeArray(event);
    }

    @FXML
    void scenePrincipal(ActionEvent event) {
        Principal.changeScreen("Principal");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Principal.addOnChangeScreenListener(new Principal.onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object data) {
                if (newScreen.equals("Visualizar")) {
                    textPower.setText(null);
                    System.out.println("Voltou pra Visualizar");
                    radioHeroi.setSelected(false);
                    radioVilao.setSelected(false);
                    choicePersonagem.setValue(null);
                    imgPersonagem.setVisible(false);
                    imgPoder.setVisible(false);
                    juncao.setText(null);
                }
            }
        });
    }
}