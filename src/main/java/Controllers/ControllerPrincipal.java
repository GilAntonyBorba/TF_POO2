package Controllers;

import Classes.Principal;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.application.Platform;
import helper.HibernateController;

public class ControllerPrincipal implements Initializable {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnJogar;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVisualizar;

    @FXML
    void sceneCadastrar(ActionEvent event) {
        Principal.changeScreen("Cadastrar");
    }

    @FXML
    void sceneJogar(ActionEvent event) {
        Principal.changeScreen("Jogar");
    }

    @FXML
    void sceneSair(ActionEvent event) {

        HibernateController.limparTabelaHeroi();
        HibernateController.limparTabelaVilao();

        for (Classes.Heroi h : Principal.personagensHerois) {
            helper.HibernateController.registrarHeroi(h);
        }

        for (Classes.Vilao v : Principal.personagensViloes) {
            helper.HibernateController.registrarVilao(v);
        }

        System.out.println("Finalizando programa");
        Platform.exit();
    }

    @FXML
    void sceneVisualizar(ActionEvent event) {
        Principal.changeScreen("Visualizar");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Principal.addOnChangeScreenListener(new Principal.onChangeScreen() {
            public void onScreenChanged(String newScreen, Object data) {
                if (newScreen.equals("Principal")) {
                    System.out.println("Voltou pra Principal");
                }
            }
        });

    }

}
