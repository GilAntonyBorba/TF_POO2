package Controllers;

import Classes.FabricaHeroi;
import Classes.FabricaPersonagem;
import Classes.FabricaVilao;
import Classes.Principal;
import Classes.Heroi;
import Classes.Vilao;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ControllerCadastrar implements Initializable {

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;
    
    @FXML
    private Label cadastrado;
    
    @FXML
    private CheckBox checkVoar;

    @FXML
    private ChoiceBox<String> choicePersonagem;

    @FXML
    private ChoiceBox<String> choicePoder;

    @FXML
    private ToggleGroup mutante;

    @FXML
    private RadioButton radioHeroi;

    @FXML
    private RadioButton radioVilao;

    @FXML
    void changeArray(ActionEvent event) {

        cadastrado.setText(null);
        choicePoder.getSelectionModel().clearSelection();
        checkVoar.setSelected(false);
        choicePersonagem.getItems().clear();
        if (radioHeroi.isSelected()) {
            for (String i : Principal.herois.keySet())
                choicePersonagem.getItems().add(i);
        }
        if (radioVilao.isSelected()) {
            for (String i : Principal.viloes.keySet())
                choicePersonagem.getItems().add(i);
        }
    }

    @FXML
    void salvarCadastro(ActionEvent event) {

        if((!radioHeroi.isSelected() && !radioVilao.isSelected()) || choicePersonagem.getValue() == null || choicePoder.getValue() == null){
            System.out.println("Cadastro Incompleto...");
            return;
        }

        FabricaPersonagem fabricaPersonagem = radioHeroi.isSelected() ? new FabricaHeroi()
                : radioVilao.isSelected() ? new FabricaVilao() : null;

        fabricaPersonagem.criarPersonagem(choicePersonagem.getValue(), choicePoder.getValue(), checkVoar.isSelected());
        cadastrado.setText(choicePersonagem.getValue() + " cadastrado(a)!");
        radioHeroi.setSelected(false);
        radioVilao.setSelected(false);
        choicePoder.getSelectionModel().clearSelection();
        checkVoar.setSelected(false);
        choicePersonagem.getItems().clear();
    }

    public static void cadastrarBanco(Heroi h) {
        FabricaPersonagem f = new FabricaHeroi();
        f.criarPersonagem(h);
    }

    public static void cadastrarBanco(Vilao v) {
        FabricaPersonagem f = new FabricaVilao();
        f.criarPersonagem(v);
    }

    @FXML
    void scenePrincipal(ActionEvent event) {
        Principal.changeScreen("Principal");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Principal.addOnChangeScreenListener(new Principal.onChangeScreen() {
            public void onScreenChanged(String newScreen, Object data) {
                if (newScreen.equals("Cadastrar")) {
                    System.out.println("Voltou pra Cadastrar");
                    radioHeroi.setSelected(false);
                    radioVilao.setSelected(false);
                    checkVoar.setSelected(false);
                    choicePoder.getSelectionModel().clearSelection();
                    choicePersonagem.getItems().clear();
                    cadastrado.setText(null);
                }
            }
        });

        for (String i : Principal.poderes.keySet())
            choicePoder.getItems().add(i);

    }

}