import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Server {

    int clientCount = 1;
    static int totalClients = 0;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    TheServer server;
    private Consumer<Serializable> callback;

    Server(Consumer<Serializable> call) {
        callback = call;
        server = new TheServer();
        server.start();
    }

    public class TheServer extends Thread {

        public void run() {
            try(ServerSocket mySocket = new ServerSocket(WelcomeController.port)) {
                while(true) {
                    ClientThread c = new ClientThread(mySocket.accept(), clientCount);
                    callback.accept("Client #: " + clientCount + " connected to the server.");
                    clients.add(c);
                    clientCount++;
                    totalClients++;
                    c.start();

                }
            } catch(Exception e) {
                callback.accept("Server socket did not launch");
            }
        }
    }

    class ClientThread extends Thread {

        Socket connection;
        int count;
        ObjectInputStream in;
        ObjectOutputStream out;
        BaccaratInfo info = new BaccaratInfo();

        ClientThread(Socket s, int count) {
            this.connection = s;
            this.count = count;
        }

        public void run() {
            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);

//                info.serverMessage = "Hi From Server!";
//                System.out.println("Was I called");
//
//                out.reset();
//                out.flush();
//                out.writeObject(info);

            } catch(Exception e) {
                System.out.println("Streams not open");
            }

            while(true) {
                try {
                    BaccaratInfo data = (BaccaratInfo)in.readObject();

                    String message = data.message;

                    callback.accept("Client #: " + count + " sent " + data.message);

                    info.serverMessage = "Hi From Server! " + message;

                    out.reset();
                    out.flush();
                    out.writeObject(info);

                } catch(Exception e) {
                    callback.accept("Client #: " + clientCount + " disconnected from the server.");
                    clients.remove(this);
                    totalClients--;
                    break;
                }
            }
        }
    }

}
