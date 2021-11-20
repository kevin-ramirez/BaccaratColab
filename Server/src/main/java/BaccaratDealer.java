import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BaccaratDealer {
    ArrayList<Card> deck;

    public void generateDeck() {
        String[] suites = Card.suites();
        int[] numbers = Card.numbers();

        deck = new ArrayList<>();

        for (String suite : suites) {
            for (int number : numbers) {
                deck.add(new Card(suite, number));
            }
        }
    }

    public int deckSize() {
        return deck.size();
    }

    public Card drawOne() {
        Random rand = new Random();
        int card = rand.nextInt(deckSize());
        return deck.remove(card);
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public ArrayList<Card> dealHand() {
        Card first = drawOne();
        Card second = drawOne();
        ArrayList<Card> arr = new ArrayList<>();
        arr.add(first);
        arr.add(second);
        return arr;
    }

}
