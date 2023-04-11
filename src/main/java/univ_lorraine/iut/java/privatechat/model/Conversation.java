package univ_lorraine.iut.java.privatechat.model;

import javafx.collections.ObservableList;

public class Conversation {

    private Integer conversationId;
    private ObservableList<Message> messages;
    private String contactName;

    public Conversation(Integer conversationId, ObservableList<Message> messages, String contactName) {
        this.conversationId = conversationId;
        this.messages = messages;
        this.contactName = contactName;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public ObservableList<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

}
