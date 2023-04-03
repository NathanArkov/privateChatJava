package univ_lorraine.iut.java.privatechat.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import univ_lorraine.iut.java.privatechat.App;
import univ_lorraine.iut.java.privatechat.model.Conversation;

public class AddContactController {

    @FXML private ListView<Conversation> conversationListView;
    private ObservableList<Conversation> conversationList = FXCollections.observableArrayList();
    private String userLogin;


    public void initialize() {
        userLogin = App.getUser();
        App.setWindowTitle("Ajouter un contact - SaferChat");
        conversationListView.setItems(conversationList);
    }

    public void addConversation(Conversation conversation) {
        conversationList.add(conversation);
    }

    @FXML
    private void submit() throws IOException {
        App.setRoot("chat");
    }
}