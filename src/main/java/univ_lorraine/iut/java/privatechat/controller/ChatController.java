package univ_lorraine.iut.java.privatechat.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import univ_lorraine.iut.java.privatechat.App;
import univ_lorraine.iut.java.privatechat.model.Conversation;
import univ_lorraine.iut.java.privatechat.model.ConversationListCell;

public class ChatController {

    @FXML private ListView<Conversation> conversationListView;
    private ObservableList<Conversation> conversationList = FXCollections.observableArrayList();
    private String userLogin;
    @FXML private Button btnAddContact;

    public void initialize() {
        userLogin = App.getUser();
        // App.setWindowTitle("SaferChat (Utilisateur : " + userLogin + ")");
        // conversationListView.setItems(conversationList);
        // conversationListView.setCellFactory(param -> new ConversationListCell());

    }

    public void addConversation(Conversation conversation) {
        conversationList.add(conversation);
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