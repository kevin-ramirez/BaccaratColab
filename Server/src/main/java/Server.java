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
        BaccaratGame game = new BaccaratGame();
        BaccaratGameLogic gameLogic = new BaccaratGameLogic();
        BaccaratDealer dealer =  new BaccaratDealer();

        ClientThread(Socket s, int count) {
            this.connection = s;
            this.count = count;
        }

        public void run() {
            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);

                info.serverMessage = "Hi From Server!";

                out.reset();
                out.flush();
                out.writeObject(info);

            } catch(Exception e) {
                System.out.println("Streams not open");
            }

            while(true) {
                try {
                    BaccaratInfo data = (BaccaratInfo)in.readObject();

                    if (data.playAgain == false) {


                        double myBet = Double.parseDouble(data.betAmount);
                        String betChoice = data.bettingOn;

                        game.currentBet = myBet;

                        //System.out.println(myBet);

                        dealer.generateDeck();

                        //System.out.println(dealer.deckSize());

                        ArrayList<Card> playerHand = dealer.dealHand();
                        info.playerCards = playerHand;


                        //System.out.println(dealer.deckSize());


                        ArrayList<Card> bankerHand = dealer.dealHand();
                        info.dealerCards = bankerHand;

                        //System.out.println(dealer.deckSize());

                        System.out.println(playerHand.get(0).getSuite());
                        System.out.println(playerHand.get(0).getValue());
                        System.out.println(playerHand.get(1).getSuite());
                        System.out.println(playerHand.get(1).getValue());


                        System.out.println(bankerHand.get(0).getSuite());
                        System.out.println(bankerHand.get(0).getValue());
                        System.out.println(bankerHand.get(1).getSuite());
                        System.out.println(bankerHand.get(1).getValue());

                        if (BaccaratGameLogic.isNatural(playerHand, bankerHand) == 0) {
                            System.out.println("Player has natural");
                            info.whoWon = "Player";
                            System.out.println(BaccaratGameLogic.handTotal(playerHand));
                            System.out.println(BaccaratGameLogic.handTotal(bankerHand));
                            System.out.println(BaccaratGameLogic.whoWon(playerHand, bankerHand));

                        } else if (BaccaratGameLogic.isNatural(playerHand, bankerHand) == 1) {
                            System.out.println("Banker has natural");
                            info.whoWon = "Banker";
                            System.out.println(BaccaratGameLogic.handTotal(playerHand));
                            System.out.println(BaccaratGameLogic.handTotal(bankerHand));
                            System.out.println(BaccaratGameLogic.whoWon(playerHand, bankerHand));
                        } else {
                            //System.out.println("Checkpoint 1");
                            if (BaccaratGameLogic.evaluatePlayerDraw(playerHand)) {
                                Card playerAdditional = dealer.drawOne();
                                info.playerThirdCard = playerAdditional;
                                playerHand.add(playerAdditional);
                                System.out.println(playerHand.get(2).getSuite());
                                System.out.println(playerHand.get(2).getValue());
                                if (BaccaratGameLogic.evaluateBankerDraw(bankerHand, playerHand.get(2))) {
                                    //System.out.println("Checkpoint 7");
                                    Card bankerAdditional = dealer.drawOne();
                                    info.bankerThirdCard = bankerAdditional;
                                    bankerHand.add(bankerAdditional);
                                    System.out.println(bankerHand.get(2).getSuite());
                                    System.out.println(bankerHand.get(2).getValue());
                                    //System.out.println("Checkpoint 8");
                                }
                            }

                            if (!BaccaratGameLogic.evaluatePlayerDraw(playerHand)) {
                                if (BaccaratGameLogic.evaluateBankerDraw(bankerHand, null)) {
                                    //System.out.println("Checkpoint 7");
                                    Card bankerAdditional = dealer.drawOne();
                                    info.bankerThirdCard = bankerAdditional;
                                    bankerHand.add(bankerAdditional);
                                    System.out.println(bankerHand.get(2).getSuite());
                                    System.out.println(bankerHand.get(2).getValue());
                                    //System.out.println("Checkpoint 8");
                                }
                            }

                            //System.out.println("Checkpoint 9");
                            System.out.println(BaccaratGameLogic.handTotal(playerHand));
                            System.out.println(BaccaratGameLogic.handTotal(bankerHand));
                            System.out.println(BaccaratGameLogic.whoWon(playerHand, bankerHand));
                            info.playerHandValue = String.valueOf(BaccaratGameLogic.handTotal(playerHand));
                            info.bankerHandValue = String.valueOf(BaccaratGameLogic.handTotal(bankerHand));
                            info.whoWon = BaccaratGameLogic.whoWon(playerHand, bankerHand);


                            //System.out.println("Checkpoint 10");
                        }

                        //game.outCome(betChoice, info.whoWon, info.playerHandValue, info.bankerHandValue);

                        game.outCome(betChoice, info.whoWon, BaccaratGameLogic.handTotal(playerHand), BaccaratGameLogic.handTotal(bankerHand));

                        game.totalWinnings += game.evaluateWinnings();

                        info.winnings = game.totalWinnings;

                        System.out.println(game.outCome(betChoice, info.whoWon, BaccaratGameLogic.handTotal(playerHand), BaccaratGameLogic.handTotal(bankerHand)));

                        System.out.println(game.totalWinnings);


                        //bankerHand.get(0).getSuite();


                        //Card myCard = dealer.drawOne();

                        //System.out.println(playerHand.get(0) + " " + myCard.getValue());


                        //BaccaratGameLogic logic = new BaccaratGameLogic();

                        //System.out.println(bankerHand.get(0));

                        //System.out.println(dealer.deckSize());


                        callback.accept("Client #: " + count + " sent $:" + data.betAmount);
                        callback.accept("Client #: " + count + " " + info.whoWon + " won. This was a " + game.betOutcome + " for the client.");
                        callback.accept("Client #: " + count + " Total winnings " + game.totalWinnings);

                        info.serverMessage = "Hi From Server! " + data.betAmount;

                        out.reset();
                        out.flush();
                        out.writeObject(info);
                    }

                    if (data.playAgain == true) {
                        callback.accept("Client #: " + count + " is playing again");
                        info.serverMessage = "Hi From Server!, you are playing again";
                        out.reset();
                        out.flush();
                        out.writeObject(info);
                    }

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
