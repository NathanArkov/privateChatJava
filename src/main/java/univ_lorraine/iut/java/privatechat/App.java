package univ_lorraine.iut.java.privatechat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import univ_lorraine.iut.java.privatechat.model.User;

import java.io.IOException;
import java.nio.file.FileSystemLoopException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static String user;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("SaferChat");
        stage.show();
    }

    public static void setUser(String newUser) {
        user = newUser;
    }

    public static String getUser() {
        return user;
    }
    public static void setWindowSize(int width, int height) {
        scene.getWindow().setWidth(width);
        scene.getWindow().setHeight(height);
    }

    public static void setWindowTitle(String title) {
        Stage stage = (Stage) scene.getWindow();
        stage.setTitle(title);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}