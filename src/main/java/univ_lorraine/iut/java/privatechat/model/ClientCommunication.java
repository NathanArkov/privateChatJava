package univ_lorraine.iut.java.privatechat.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;

public class ClientCommunication implements Runnable {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ClientCommunication(Socket socket) {
        super();
        this.socket = socket;

    }

    @Override
    public void run() {
        try {

            User client = new User("nathan", InetAddress.getLocalHost().toString(), 12345);
            this.ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();
            System.out.println(message.toString());
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(new Message(client, MessageType.INIT, "Pong", LocalDateTime.now(), null));
            oos.flush();

            while (true) {
                Message messageRecu = (Message) ois.readObject();
                if(messageRecu != null) {
                    messageRecu.setReceptionDate(LocalDateTime.now());
                    System.out.println(messageRecu);
                    FileWriter writer = new FileWriter("data/" + client.getUsername() + "/" + messageRecu.getSender().getUsername() + ".conv");
                    writer.write(messageRecu.toString());
                    writer.close();
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            // close resources
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
