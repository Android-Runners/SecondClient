import java.io.*;
import java.net.*;
import java.util.*;

public class Resender extends Thread{
    private Scanner scanner;
    private ObjectOutputStream output;
    private Socket socket;
    Resender(ObjectOutputStream output, Scanner scanner){
        this.scanner = scanner;
        this.socket = socket;
        this.output = output;
    }
    Resender(){}
    @Override
    public void run(){
        try {
            while (true) {
                String s;
                Integer i;
                s = scanner.nextLine();
                String[] arrayString = s.split(" ");
                i = Integer.valueOf(arrayString[arrayString.length-1]);
                String b = "";
                s = "";
                for(String c : arrayString){
                    b = c;
                    s += c + " ";
                }
                s = s.substring(0, s.length() - b.length()-1);
                Message messageToSend = new Message(s, i);
                output.writeObject(messageToSend);
                output.flush();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
