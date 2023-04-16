package univ_lorraine.iut.java.privatechat.model;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client implements Runnable{

    private ObjectOutputStream oos = null;
    private BlockingQueue<Message> messagesToSend;
    private User user;
    private String client;

    public void sendMessage(Message message) throws IOException {
        try {

            FileWriter writer = new FileWriter("data/" + client + "/" + user.getUsername() + ".txt");
            writer.write(message.toString());
            writer.close();
            oos.writeObject(message);
            oos.flush();
            System.out.println("Message envoyé et sauvegardé");
        }
        catch (IOException e) {
            System.out.println("Erreur lors de l'envoi du message");
            e.printStackTrace();
        }

    }

    public Client (BlockingQueue<Message> messagesToSend, String user, String client) throws IOException {
        this.messagesToSend = messagesToSend;
        this.client = client;

        final Pattern pattern = Pattern.compile("[^ (:)]+");
        final Matcher matcher = pattern.matcher(user);
        String[] userInfos = new String[3];
        int i = 0;
        while (matcher.find()) {
            userInfos[i] = matcher.group().replaceAll("\"", "");
            i++;

        }
        String username = userInfos[0];
        String ipaddress = userInfos[1];
        int port = Integer.parseInt(userInfos[2]);
        this.user = new User(username, ipaddress, port);

    }

    @Override
    public void run() {
        try {
            User client = new User(this.client, InetAddress.getLocalHost().toString(), 12345);

            // get the localhost IP address, if server is running on some other IP, you need
            // to use that
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = null;
            oos = null;
            ObjectInputStream ois = null;

            // establish socket connection to server
            // socket = new Socket(host.getHostName(), 12345);
            socket = new Socket(user.getIpaddress(), user.getPort());
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
            boolean running = true;
            Message msg;
            while((msg = messagesToSend.poll()) != null || running) {
                sendMessage(msg);
                Thread.sleep(100);
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
