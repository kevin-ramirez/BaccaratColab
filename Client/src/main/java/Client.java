import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

public class Client extends Thread {
    Socket socketClient;
    BaccaratInfo info = new BaccaratInfo();
    ObjectOutputStream out;
    ObjectInputStream in;

    private Consumer<Serializable> callback;

    Client(Consumer<Serializable> call) {
        callback = call;
    }

    public void run() {
        try {
            socketClient = new Socket(WelcomeController.ipAddress, WelcomeController.portNumber);
            out = new ObjectOutputStream(socketClient.getOutputStream());
            in = new ObjectInputStream(socketClient.getInputStream());
            socketClient.setTcpNoDelay(true);
        } catch(Exception e) {
            System.out.println(e);
        }

        while(true) {
            try {
                BaccaratInfo info = (BaccaratInfo)in.readObject();
                callback.accept(info);
            } catch(Exception e) {
                System.out.println(e);
                System.out.println("Test");
            }
        }
    }

    public void send(String bet) {
        info.betAmount = bet;
        try {
            out.reset();
            out.flush();
            out.writeObject(info);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
