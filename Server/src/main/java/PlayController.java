import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayController implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    Server sererConnection;

    @FXML
    ListView<String> serverList = new ListView<>();

    @FXML
    Button launchAgainBtn;

    @FXML
    Text numClients;

    @FXML
    public void launchAgainHandle(ActionEvent event) {
        launchAgainBtn.setDisable(true);
        sererConnection = new Server(data -> {
            Platform.runLater(() -> {
                serverList.getItems().add(data.toString());
                numClients.setText("Clients connected: " + Server.totalClients);
            });
        });
    }
}
