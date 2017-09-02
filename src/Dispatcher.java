public class Dispatcher {
    public static void main(String... args){
        new Thread(new Client()).start();
    }
}
