package univ_lorraine.iut.java.privatechat.model;

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
            this.ois = new ObjectInputStream(socket.getInputStream());
            User client = new User("nathan", InetAddress.getLocalHost().toString(), 12345);

            Message message = (Message) ois.readObject();
            System.out.println(message.toString());
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(new Message(client, MessageType.INIT, "Pong", LocalDateTime.now(), null));
            oos.flush();
            while (true) {
                Message messageRecu = (Message) ois.readObject();
                messageRecu.setReceptionDate(LocalDateTime.now());
                System.out.println(messageRecu);
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
