import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PlayController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    GenerateCardImages generate = new GenerateCardImages();
    WelcomeController view = new WelcomeController();
    HashMap<String, Image> cardMap = generate.createCardDeck();
    String betting;

    @FXML
    ListView<String> testView = new ListView<>();

    @FXML
    TextField testField;

    @FXML
    Button testButton;

    @FXML
    Button displayBtn;

    @FXML
    ImageView playerCardOne;

    @FXML
    ImageView playerCardTwo;

    @FXML
    ImageView playerCardThree;

    @FXML
    ImageView bankerCardOne;

    @FXML
    ImageView bankerCardTwo;

    @FXML
    ImageView bankerCardThree;

    @FXML
    Button playerBtn;

    @FXML
    Button drawBtn;

    @FXML
    Button bankerBtn;

    @FXML
    HBox btnHbox;

    @FXML
    Button playAgainBtn;

    @FXML
    Text winTotal;

    @FXML
    Text whoWon;

    @FXML
    public void playAgainBtnHandler() {
        WelcomeController.clientConnection.sendPlayAgain();
        playerCardOne.setImage(null);
        playerCardTwo.setImage(null);
        whoWon.setText("");
    }

    @FXML
    public void playerBtnHandler() {
        betting = "Player";
    }

    @FXML
    public void drawBtnHandler() {
        betting = "Draw";
    }

    @FXML
    public void bankerBtnHandler() {
        betting = "Banker";
    }

    @FXML
    public void testButtonHandle() {
        String bet = testField.getText();
        testField.clear();
        WelcomeController.clientConnection.send(bet, betting);
    }

    @FXML
    public void displayBtnHandler() {
        testView.getItems().add(view.returnServerMessage());
        System.out.println(view.returnServerMessage());
        testView.getItems().add(view.returnPlayerCards().get(0).getSuite() + " " + view.returnPlayerCards().get(0).getValue());
        testView.getItems().add(view.returnPlayerCards().get(1).getSuite() + " " + view.returnPlayerCards().get(1).getValue());
        //Image card = new Image("/cards/1C.jpg", 90, 154, false, true);
        String value = String.valueOf(view.returnPlayerCards().get(0).getValue());
        String key = value + view.returnPlayerCards().get(0).getSuite().charAt(0);
        playerCardOne.setImage(cardMap.get(key));
        value = String.valueOf(view.returnPlayerCards().get(1).getValue());
        key = value + view.returnPlayerCards().get(1).getSuite().charAt(0);
        playerCardTwo.setImage(cardMap.get(key));
        whoWon.setText(view.returnWhoWon());
        Double amount = view.returnWinnings();
        winTotal.setText("Winnings: " + String.valueOf(amount));
    }

}
