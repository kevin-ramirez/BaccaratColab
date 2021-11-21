import javafx.scene.image.Image;
import java.util.HashMap;

public class GenerateCardImages {
    HashMap<String, Image> cards = new HashMap<>();

    public HashMap<String, Image> createCardDeck() {
        String[] suite = {"H", "D", "S", "C"};
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        for (String face : suite) {
            for (int number : numbers) {
                String path = ("/cards/" + number + face + ".jpg");
                Image card = new Image(path, 90, 154, false, true);
                String key = number + face;
                cards.put(key, card);
            }
        }
        return cards;
    }
}
