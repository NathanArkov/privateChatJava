package univ_lorraine.iut.java.privatechat.controller;

import java.io.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import univ_lorraine.iut.java.privatechat.App;
import univ_lorraine.iut.java.privatechat.model.Conversation;

public class AddContactController {


    @FXML private TextField contactfield;
    @FXML private TextField ipv4field;
    @FXML private TextField portfield;



    public void initialize() {
        userLogin = App.getUser();
        // App.setWindowTitle("Ajouter un contact - SaferChat");
    }

    @FXML
    private void submit() throws IOException {
        App.setRoot("chat");
    }

    private final File directory = new File("data");
    private boolean createContact(String login, String nom_contact, String ipv4, String port) throws IOException {
        File file = new File(directory, login + nom_contact + ".pwd");
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
    private void login() throws IOException {
        String nom_contact = contactfield.getText();
        String port = portfield.getText();
        String ipv4 = ipv4field.getText();
        String login = App.getUser();
        System.out.println("Création du contact");
        boolean accountCreationStatus = createContact(login, nom_contact , ipv4 , port);
        if(!accountCreationStatus) {
            System.out.println("Erreur lors de la création du contact");
            return;
        }



        System.out.println("Création du contact réussie");
        App.setUser(login);
        App.setRoot("chat");
    }
}