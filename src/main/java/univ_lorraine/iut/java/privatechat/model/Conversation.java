package univ_lorraine.iut.java.privatechat.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Conversation {

    private Integer conversationId;
    private List<Message> messages;
    private String contactName;

    public Conversation(Integer conversationId, List<Message> messages, String contactName) {
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

    public String getLastMessage() {
        if (messages.isEmpty()) {
            return "";
        } else {
            Message lastMessage = messages.get(messages.size() - 1);
            return lastMessage.getContent();
        }
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

    /*public void saveToFile(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(conversationId + "/" + contactName);
            for (Message message : messages) {
                if(message.getSender().equals(contactName) && message.getType() == MessageType.MESSAGE) {
                    writer.println(message.getSendedDate() + "/" + message.getReceptionDate() + "/" + message.getContent());
                }
            }
        }
    }

    public static User getUserFromFile(String name, String login) throws IOException {
        try (Scanner scanner = new Scanner(new File(login + ".contacts"))) {
            ArrayList<User> contacts = new ArrayList<>();
            // read the rest of the file

            String line = scanner.nextLine();
            String[] parts = line.split("/");
            User user = new User(parts[0], parts[1], Integer.parseInt(parts[2]));
            contacts.add(user);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                parts = line.split("/");
                user = new User(parts[0], parts[1], Integer.parseInt(parts[2]));
                contacts.add(user);
            }
            for (User contact : contacts) {
                if (contact.getUsername().equals(name)) {
                    return contact;
                }
            }

        }
    }

    public static Conversation loadFromFile(String filename, String login) throws IOException {
        try (Scanner scanner = new Scanner(new File(filename))) {
            String firstLine = scanner.nextLine();
            String[] parts = firstLine.split("/");
            List<Message> messages = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                String[] messageParts = message.split("/");
                String contactName = filename.split(".")[0];
                Message message1 = new Message(getUserFromFile(contactName, login), MessageType.MESSAGE, messageParts[2], LocalDateTime.parse(messageParts[0]), LocalDateTime.parse(messageParts[1]));
                messages.add(message1);
            }
            Conversation conversation = new Conversation(Integer.parseInt(parts[0]), messages, parts[1]);
            return conversation;
        }
    }*/

}
