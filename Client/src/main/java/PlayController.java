import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public void testButtonHandle() {
        String message = testField.getText();
        testField.clear();
        WelcomeController.clientConnection.send(message);
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
    }

}
