package univ_lorraine.iut.java.privatechat.controller;

import java.io.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import univ_lorraine.iut.java.privatechat.App;
import univ_lorraine.iut.java.privatechat.model.Conversation;

public class AddContactController {


    @FXML private TextField contactfield;
    @FXML private TextField ipv4field;
    @FXML private TextField portfield;
    @FXML private Label errorLabel;
    @FXML private Button retourButton;
    private String userLogin;
    private File directory;


    public void initialize() {
        userLogin = App.getUser();
        directory = new File("data/" + userLogin);
        if(!directory.exists()) {
            System.out.println("Création du dossier contacts de l'utilisateur");
            directory.mkdir();
        }
        // App.setWindowTitle("Ajouter un contact - SaferChat");
    }

    private boolean checkContactExists(String contact) {
        File file = new File(directory, contact + ".conv");
        return file.exists() && file.isFile();
    }


    private boolean createContact(String nom_contact, String ipv4, String port) throws IOException {
        if(checkContactExists(nom_contact)) {
            System.out.println("Le contact existe déjà + ratio");
            errorLabel.setStyle("-fx-text-fill: red;");
            return false;
        }
        File file = new File(directory, nom_contact + ".conv");
        boolean success = false;
        try {
            success = file.createNewFile();

            try (FileWriter writer = new FileWriter(file)) {
                writer.write("nom contact=" + nom_contact);
                writer.write(System.lineSeparator());
                writer.write("ipv4=" + ipv4);
                writer.write(System.lineSeparator());
                writer.write("port=" + port);
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

    @FXML
    private void goBack() throws IOException {
        App.setRoot("chat");
    }

    @FXML
    private void submitContact() throws IOException {
        String nom_contact = contactfield.getText();
        String port = portfield.getText();
        String ipv4 = ipv4field.getText();
        System.out.println("Création du contact");
        boolean accountCreationStatus = createContact(nom_contact , ipv4 , port);
        if(!accountCreationStatus) {
            System.out.println("Erreur lors de la création du contact");
            return;
        }

        System.out.println("Création du contact réussie");
        App.setRoot("chat");
    }
}