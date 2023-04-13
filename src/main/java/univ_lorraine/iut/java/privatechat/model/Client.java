package univ_lorraine.iut.java.privatechat.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Client implements Runnable{

    private ObjectOutputStream oos = null;
    private BlockingQueue<Message> messagesToSend;

    public void sendMessage(Message message) throws IOException {
        Scanner scanner = new Scanner(System.in);
        oos.writeObject(message);
        oos.flush();
    }

    public Client (BlockingQueue<Message> messagesToSend) throws IOException {
        this.messagesToSend = messagesToSend;


    }

    @Override
    public void run() {
        try {
            User client = new User("nathan", InetAddress.getLocalHost().toString(), 12345);

            // get the localhost IP address, if server is running on some other IP, you need
            // to use that
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = null;
            oos = null;
            ObjectInputStream ois = null;

            // establish socket connection to server
            // socket = new Socket(host.getHostName(), 12345);
            socket = new Socket("100.64.49.92", 12345);
            // write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Sending request to Socket Server");
            oos.writeObject(new Message(client, MessageType.INIT, "Ping", LocalDateTime.now(), null));
            // read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            Message response = (Message) ois.readObject();
            System.out.println("Message: " + response.toString());

        /*oos.writeObject();
        oos.flush();*/

            Message msg;
            while((msg = messagesToSend.poll()) != null) {
                sendMessage(msg);
            }


            // close resources
            ois.close();
            oos.close();
            Thread.sleep(2000);
        }
        catch (ClassNotFoundException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
