package univ_lorraine.iut.java.privatechat.controller;

import java.io.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import univ_lorraine.iut.java.privatechat.App;

public class LoginController {

    private final File directory = new File("data");
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Button btnLogin;
    @FXML private Label errorLabelPassword;


    private boolean checkUser(String login) {
        File file = new File(directory, login + ".pwd");
        return file.exists() && file.isFile();
    }

    private boolean checkPassword(String login, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/" + login + ".pwd"))){
            String readLine = reader.readLine();
            String requiredLine = "password=" + password;
            if(requiredLine.equals(readLine)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private boolean createAccount(String login, String password) throws IOException {
        File file = new File(directory, login + ".pwd");
        boolean success = false;
        try {
            success = file.createNewFile();

            try (FileWriter writer = new FileWriter(file)) {
                writer.write("password=" + password);
                writer.write(System.lineSeparator());
            }
            catch(IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return success;
    }

    @FXML private void initialize() {
        // App.setWindowTitle("SaferChat - Connexion");
        if(!directory.exists()) {
            System.out.println("Création du dossier data");
            directory.mkdir();
        }
    }

    @FXML
    private void login() throws IOException {
        String login = loginField.getText();
        String password = passwordField.getText();

        if(!checkUser(login)) {
            System.out.println("Fichier utilisateur introuvable, création du compte");
            boolean accountCreationStatus = createAccount(login, password);
            if(!accountCreationStatus) {
                System.out.println("Erreur lors de la création du compte");
                return;
            }
        }

        if(!checkPassword(login, password)) {
            System.out.println("Mot de passe incorrect");
            errorLabelPassword.setStyle("-fx-text-fill: red;");
            return;
        }

        System.out.println("Connexion réussie");
        App.setUser(login);
        App.setRoot("chat");
    }
}
