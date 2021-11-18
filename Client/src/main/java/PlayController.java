import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    WelcomeController view = new WelcomeController();

    @FXML
    ListView<String> testView = new ListView<>();

    @FXML
    TextField testField;

    @FXML
    Button testButton;

    @FXML
    Button displayBtn;

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
    }

}
