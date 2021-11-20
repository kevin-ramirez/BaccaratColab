public class Card {
    String suite;
    int value;

    Card(String theSuite, int theValue) {
        suite = theSuite;
        value = theValue;
    }

    public static String[] suites() {
        return new String[]{"Hearts", "Diamonds", "Spades", "Clubs"};
    }

    public static int[] numbers() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    }

    public String getSuite() {
        return suite;
    }

    public int getValue() {
        return value;
    }

}
