import java.util.ArrayList;


public class BaccaratGameLogic {

    public static String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {

        int playerScore = handTotal(hand1);
        int bankerScore = handTotal(hand2);

        int playerDiff = Math.abs(playerScore - 9);
        int bankerDiff = Math.abs(bankerScore - 9);

        if (playerDiff < bankerDiff)
        {
            return "Player";
        }

        else if (bankerDiff < playerDiff){
            return "Banker";
        }

        else {
            return "Draw";
        }

    }

    public static int handTotal(ArrayList<Card> hand) {
        //System.out.println("Checkpoint 4");

        int total = 0;
        for (int i = 0; i < hand.size(); i++){
            total += cardValue(hand.get(i));
        }

        if (total >= 10){
            return (total - 10);
        }

        else {
            //System.out.println("Does it return 0?");
            return total;
        }
    }

    public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
        //System.out.println("Checkpoint 3");
        if (handTotal(hand) <= 2){
            //System.out.println("Checkpoint 5");
            return true;
        }

        else if (handTotal(hand) >= 7){
            return false;
        }

        else if ((handTotal(hand)) <= 5 && (playerCard == null)){
            return true;
        }

        else if ((handTotal(hand)) <= 3 && (cardValue(playerCard) == 0)) {
            return true;
        }

        else if ((handTotal(hand)) <= 3 && (cardValue(playerCard) == 1)) {
            return true;
        }

        else if ((handTotal(hand)) <= 4 && (cardValue(playerCard) == 2)) {
            return true;
        }

        else if ((handTotal(hand)) <= 4 && (cardValue(playerCard) == 3)) {
            return true;
        }

        else if ((handTotal(hand)) <= 5 && (cardValue(playerCard) == 4)) {
            return true;
        }

        else if ((handTotal(hand)) <= 5 && (cardValue(playerCard) == 5)) {
            return true;
        }

        else if ((handTotal(hand)) <= 6 && (cardValue(playerCard) == 6)) {
            return true;
        }

        else if ((handTotal(hand)) <= 6 && (cardValue(playerCard) == 7)) {
            return true;
        }

        else if ((handTotal(hand)) <= 2 && (cardValue(playerCard) == 8)) {
            return true;
        }

        else if ((handTotal(hand)) <= 3 && (cardValue(playerCard) == 9)) {
            return true;
        }










//        if (handTotal(hand) == 3 && cardValue(playerCard) == 8){
//            return false;
//        }
//
//        if (handTotal(hand) == 3 && cardValue(playerCard) != 8){
//            return true;
//        }
//
//        if (handTotal(hand) == 4 && (cardValue(playerCard) == 0 ||
//                cardValue(playerCard) == 1 || cardValue(playerCard) == 8 || cardValue(playerCard) == 9)){
//            return false;
//        }
//
//        if (handTotal(hand) == 4 && (playerCard == null ||
//                cardValue(playerCard) == 2 || cardValue(playerCard) == 3 || cardValue(playerCard) == 4 ||
//                cardValue(playerCard) == 5 || cardValue(playerCard) == 6 || cardValue(playerCard) == 7)){
//            return true;
//        }
//
//        if (handTotal(hand) == 5 && (playerCard == null || cardValue(playerCard) == 4 || cardValue(playerCard) == 5
//                || cardValue(playerCard) == 6 || cardValue(playerCard) == 7)){
//            return true;
//        }
//
//        if (handTotal(hand) == 5 && (cardValue(playerCard) == 0 || cardValue(playerCard) == 1
//                || cardValue(playerCard) == 2 || cardValue(playerCard) == 3 || cardValue(playerCard) == 8
//                || cardValue(playerCard) == 9)){
//            return false;
//        }
//
//        if (handTotal(hand) == 6 && (cardValue(playerCard) == 6 || cardValue(playerCard) == 7)){
//            return true;
//        }
//
//        if (handTotal(hand) == 6 && (playerCard == null || cardValue(playerCard) == 0 || cardValue(playerCard) == 1)
//                || cardValue(playerCard) == 2 || cardValue(playerCard) == 3 || cardValue(playerCard) == 4 || cardValue(playerCard) == 5
//                || cardValue(playerCard) == 8 || cardValue(playerCard) == 9){
//            return false;
//        }

        return false;
    }

    public static boolean evaluatePlayerDraw(ArrayList<Card> hand) {
        if (handTotal(hand) <= 5){
            return true;
        }
        return false;
    }

    public static int cardValue(Card myCard){
        //System.out.println("Checkpoint 5");
        if (myCard.getValue() >= 10) {
            //System.out.println("Enters if statement >= 10");
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
