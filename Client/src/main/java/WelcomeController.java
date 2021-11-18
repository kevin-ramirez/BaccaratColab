import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    static String ipAddress;
    static int portNumber;
    static Client clientConnection;
    static BaccaratInfo info;

    @FXML
    TextField ipText;

    @FXML
    TextField portText;

    @FXML
    Button connectToServerBtn;

    @FXML
    private void connectionBtnHandler(ActionEvent event) {
        ipAddress = ipText.getText();
        portNumber = Integer.parseInt(portText.getText());

        System.out.println(ipAddress);
        System.out.println(portNumber);
        /*
        We need the welcome scene window open because it is the means of how to send data back and forth because
        the initial client connection was made here, if we close it the client thread will also close.
         */
        connectToServerBtn.setDisable(true);

        GameScene scene = new GameScene();
        Stage stage = new Stage();

        try {
            scene.start(stage);
        } catch(Exception e) {
            System.out.println(e);
        }

        clientConnection = new Client(data -> {
            Platform.runLater(() -> {setInfo((BaccaratInfo)data);
            });
        });
        clientConnection.start();
    }

    private void setInfo(BaccaratInfo data) {
        info = data;
    }

    public String returnBetAmount() {
        return info.betAmount;
    }

    public String returnServerMessage() {
        return info.serverMessage;
    }

}
