// package application;

// import javafx.application.Application;
// import javafx.event.EventHandler;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.image.ImageView;
// import javafx.scene.input.KeyEvent;
// import javafx.scene.paint.Color;
// import javafx.stage.Stage;

// public class Main extends Application {

//     @Override
//     public void start(Stage primaryStage) {
//         try {
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/View.fxml"));
//             Parent root = loader.load();
//             ControllerIniciar controller = loader.getController();
//             Scene scene = new Scene(root, Color.BLACK);
            
            
//             //Instancia os Personagens
//             Scarlet scarlet = new Scarlet();
//             DoctorStrange doctor = new DoctorStrange();
            
            
//             //Manda para Controler os personagens
//             controller.getPictures(scarlet, doctor);
            
            
//             //Adiciona os eventos de Teclado
//             scene.setOnKeyPressed(event -> {
//             	controller.ReceberEvento(event);
//             });
//             scene.setOnKeyReleased(event -> {
//             	controller.RetirarEvento(event);
//             });
            

// 			//Mostra a tela
// 			primaryStage.setScene(scene);
// 			primaryStage.show();

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }
