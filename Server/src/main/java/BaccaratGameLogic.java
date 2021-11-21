import java.util.ArrayList;


public class BaccaratGameLogic {

    public static String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {

        int playerScore = handTotal(hand1);
        int bankerScore = handTotal(hand2);

        int playerDiff = Math.abs(playerScore - 9);
        int bankerDiff = Math.abs(bankerScore - 9);

        if (playerDiff < bankerDiff)
        {
            return "Player Won";
        }

        else if (bankerDiff < playerDiff){
            return "Banker Won";
        }

        else {
            return "Draw";
        }

    }

    public static int handTotal(ArrayList<Card> hand) {


        int card1 = cardValue(hand.get(0));
        int card2 = cardValue(hand.get(1));

        int total = card1 + card2;

        if (total >= 10){
            return (total - 10);
        }

        else {
            return total;
        }
    }

    public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {


        return true;
    }

    public static boolean evaluatePlayerDraw(ArrayList<Card> hand) {
        if (handTotal(hand) <= 5){
            return true;
        }
        return false;
    }

    public static int cardValue(Card myCard){
        if (myCard.getValue() >= 10) {
            return 0;
        }
        else {
            return myCard.getValue();
        }
    }

    public static int isNatural(ArrayList<Card> hand1, ArrayList<Card> hand2){
        if (handTotal(hand1) == 9 && handTotal(hand2) != 9){
            return 0;
        }

        if (handTotal(hand1) == 8 && (handTotal(hand2) != 9 && handTotal(hand2) != 8 )){
            return 0;
        }

        if (handTotal(hand2) == 9 && handTotal(hand1) != 9){
            return 1;
        }

        if (handTotal(hand2) == 8 && (handTotal(hand1) != 9 && handTotal(hand1) != 8 )){
            return 1;
        }
        return 2;
    }
}
