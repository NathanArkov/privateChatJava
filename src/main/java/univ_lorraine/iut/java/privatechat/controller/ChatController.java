package univ_lorraine.iut.java.privatechat.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import univ_lorraine.iut.java.privatechat.App;
import univ_lorraine.iut.java.privatechat.model.*;

public class ChatController {

    @FXML private ListView<Conversation> conversationListView;
    private ObservableList<Conversation> conversationList = FXCollections.observableArrayList();
    private String userLogin;
    @FXML private Button btnAddContact;
    @FXML private TextField inputField;
    @FXML private Button sendButton;
    private Thread threadClient;
    private Thread threadServeur;
    private BlockingQueue<Message> listeMessages;

    public void initialize() throws IOException, InterruptedException, ClassNotFoundException {
        userLogin = App.getUser();
        listeMessages = new LinkedBlockingQueue<Message>();
        String[] args = new String[0];
        // Lancement du SERVEUR
        threadServeur = new Thread(new Serveur());
        threadServeur.start();

        // Lancement du CLIENT
        threadClient = new Thread(new Client(listeMessages));
        threadClient.sleep(10000);
        threadClient.start();

        // App.setWindowTitle("SaferChat (Utilisateur : " + userLogin + ")");
        // conversationListView.setItems(conversationList);
        // conversationListView.setCellFactory(param -> new ConversationListCell());

    }

    public void addConversation(Conversation conversation) {
        conversationList.add(conversation);
    }

    @FXML
    private void sendMessage() throws IOException {
        String message = inputField.getText();
        inputField.clear();

        Message messageToSend = new Message(new User(userLogin, "1.1.1.1",12345), MessageType.MESSAGE, message, LocalDateTime.now(), null);

        // Envoi du message au serveur
        listeMessages.add(messageToSend);
        }

    @FXML
    private void logout() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void addContact() throws IOException {
        App.setRoot("addContact");
    }
}