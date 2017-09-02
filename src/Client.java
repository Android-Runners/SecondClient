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
            System.out.println("Enter a number:");
            Integer integer = scanner.nextInt();
            Message messageToSend = new Message(integer, -1);
            output.writeObject(messageToSend);
            output.close();
            output.flush();
            Message messageToGet = (Message)input.readObject();
            System.out.println(messageToGet.toString());
           /* System.out.println("Please, enter your name: ");
            String s = scanner.nextLine();
            out.println("NAME" + s);
            String initName = in.readLine();
            System.out.println(initName);
            out = new PrintWriter(socket.getOutputStream(), true);
            Resender resender = new Resender(s, out, scanner);
            resender.start();
            while (true) {
                String line = in.readLine();
                System.out.println(line);
            }*/
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
