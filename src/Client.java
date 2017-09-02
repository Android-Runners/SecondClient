import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client extends Thread{
    private final int PORT = 12073;
    private Scanner scanner;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    Client() {
        scanner = new Scanner(System.in);
    }
    @Override
    public void run(){
        scanner = new Scanner(System.in);
        String address = "0.tcp.ngrok.io"; // ""
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, PORT);
            System.out.println("You have connected. Port: " + PORT);
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
            new Thread(new Resender(output, scanner)).start();
            while(true){
                Object messageToGet = input.readObject();
                System.out.println(messageToGet.toString());
            }

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
