package Classes;

import java.util.ArrayList;
import java.util.HashMap;

import Controllers.ControllerIniciar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Principal extends Application {

    public static Stage stage;
    public static Scene ScenePrincipal;
    public static Scene SceneCadastrar;
    public static Scene SceneVisualizar;
    public static Scene SceneJogar;
    public static Scene SceneIniciar;

    public static ControllerIniciar controllerIniciar;

    private static ArrayList<onChangeScreen> listeners = new ArrayList<>();
    public static ArrayList<Heroi> personagensHerois = new ArrayList<>();
    public static ArrayList<Vilao> personagensViloes = new ArrayList<>();

    public static HashMap<String, String> herois = new HashMap<String, String>();
    public static HashMap<String, String> viloes = new HashMap<String, String>();
    public static HashMap<String, String> poderes = new HashMap<String, String>();

    @Override
    public void start(Stage stage) throws Exception {

        Principal.stage = stage;

        stage.setTitle("Inicio");

        Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("../FXML/FXML-Principal.fxml"));
        ScenePrincipal = new Scene(fxmlPrincipal);

        Parent fxmlCadastrar = FXMLLoader.load(getClass().getResource("../FXML/FXML-Cadastrar.fxml"));
        SceneCadastrar = new Scene(fxmlCadastrar);

        Parent fxmlVisualizar = FXMLLoader.load(getClass().getResource("../FXML/FXML-Visualizar.fxml"));
        SceneVisualizar = new Scene(fxmlVisualizar);

        Parent fxmlJogar = FXMLLoader.load(getClass().getResource("../FXML/FXML-Jogar.fxml"));
        SceneJogar = new Scene(fxmlJogar);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/FXML-Iniciar.fxml"));
        Parent fxmlIniciar = loader.load();
        controllerIniciar = loader.getController();
        SceneIniciar = new Scene(fxmlIniciar);

        /*Parent fxmlIniciar = FXMLLoader.load(getClass().getResource("../FXML/FXML-Iniciar.fxml"));
        SceneIniciar = new Scene(fxmlIniciar);*/

        ScenePrincipal.getStylesheets().add(getClass().getResource("../CSS/style.css").toExternalForm());
        SceneCadastrar.getStylesheets().add(getClass().getResource("../CSS/style.css").toExternalForm());
        SceneVisualizar.getStylesheets().add(getClass().getResource("../CSS/style.css").toExternalForm());
        SceneJogar.getStylesheets().add(getClass().getResource("../CSS/style.css").toExternalForm());

        stage.setScene(ScenePrincipal);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("..\\img\\simbolo.png")));
        stage.setTitle("Principal");
        stage.show();
    }

    public static void changeScreen(String src) {
        changeScreen(src, null);
    }

    public static void changeScreen(String src, Object data) {
        switch (src) {
            case "Principal":
                stage.setScene(ScenePrincipal);
                stage.setTitle(src);
                notifyAllListeners("Principal", data);
                break;
            case "Cadastrar":
                stage.setScene(SceneCadastrar);
                stage.setTitle(src);
                notifyAllListeners("Cadastrar", data);
                break;
            case "Visualizar":
                stage.setScene(SceneVisualizar);
                stage.setTitle(src);
                notifyAllListeners("Visualizar", data);
                break;
            case "Jogar":
                stage.setScene(SceneJogar);
                stage.setTitle(src);
                notifyAllListeners("Jogar", data);
                break;
            case "Iniciar":
                stage.setScene(SceneIniciar);
                stage.setTitle(src);
                notifyAllListeners("Iniciar", data);
                break;
            default:
                stage.setScene(ScenePrincipal);
                stage.setTitle("Principal");
                break;
        }
    }

    public static interface onChangeScreen {
        void onScreenChanged(String newScreen, Object data);
    }

    public static void addOnChangeScreenListener(onChangeScreen newListener) {
        listeners.add(newListener);
    }

    private static void notifyAllListeners(String newScreen, Object data) {
        for (onChangeScreen l : listeners) {
            l.onScreenChanged(newScreen, data);
        }
    }

    public static void main(String[] args) {
        herois.put("Thor", "..\\img\\Herois\\Thor.png");
        herois.put("Homem Aranha", "..\\img\\Herois\\SpiderMan.png");
        herois.put("Capitão América", "..\\img\\Herois\\CapitainAmerica.png");
        herois.put("Doutor Estranho", "..\\img\\Herois\\DoctorStrange.png");
        herois.put("Homem de Ferro", "..\\img\\Herois\\IronMan.png");
        herois.put("Feiticeira Scarlet", "..\\img\\Herois\\ScarletWitch.png");

        viloes.put("Thanos", "..\\img\\Viloes\\Thanos.png");
        viloes.put("Loki", "..\\img\\Viloes\\Loki.png");
        viloes.put("Goblin Verde", "..\\img\\Viloes\\GreenGoblin.png");

        poderes.put("Bola de Plasma", "..\\img\\Poderes\\blueball.png");
        poderes.put("Bomba", "..\\img\\Poderes\\bomb.png");
        poderes.put("Escudo", "..\\img\\Poderes\\Escudo.png");
        poderes.put("Manopla", "..\\img\\Poderes\\manopla.png");
        poderes.put("Mjolnir", "..\\img\\Poderes\\Mjolnir.png");
        poderes.put("Feitiço", "..\\img\\Poderes\\spell.png");
        poderes.put("Teia", "..\\img\\Poderes\\web.png");
        // Example.populate();
        for (String h : herois.keySet()) {
            Heroi ph = helper.HibernateController.procurarHeroi(h);
            if (ph != null)
                Controllers.ControllerCadastrar.cadastrarBanco(ph);
            // personagensHerois.add(ph);
        }

        for (String v : viloes.keySet()) {
            Vilao pv = helper.HibernateController.procurarVilao(v);
            if (pv != null)
                Controllers.ControllerCadastrar.cadastrarBanco(pv);
            // personagensViloes.add(pv);
        }

        launch(args);
    }

}