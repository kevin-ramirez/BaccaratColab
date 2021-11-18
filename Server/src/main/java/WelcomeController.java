import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    TextField portText;

    @FXML
    Button launchServerBtn;

    static int port;

    public void launchBtnHandler(ActionEvent event) {
        launchServerBtn.setDisable(true);
        port = Integer.parseInt(portText.getText());

        GameServer scene = new GameServer();
        Stage stage = new Stage();

        try {
            scene.start(stage);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
