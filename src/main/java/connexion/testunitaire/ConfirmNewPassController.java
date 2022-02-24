package connexion.testunitaire;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ConfirmNewPassController {
  private Stage stage = new Stage();
  @FXML private JSONObject user;
  @FXML private PasswordField newMdp;
  @FXML private PasswordField confNewMdp;
  @FXML private Label errorLabel;
  
  @FXML public void setUser(JSONObject userObject) {
    user = userObject;
  }
	
  @SuppressWarnings("unchecked")
  @FXML
  private void confirmNewPass(ActionEvent event) throws IOException {
    if (verifyMdp() && newMdp.getText() != "") {
      JSONArray userArray = JSONAccess.getJSON();
      for (int i = 0; i < userArray.size(); i++) {
        JSONObject jsonuser = (JSONObject) userArray.get(i);
        if (user.get("login").equals(jsonuser.get("login"))) {
          user.put("mdp", newMdp.getText());
          user.put("isPassConf", false);
          userArray.set(i, user);
          JSONAccess.writeToJSON(userArray);
        }
      }
      LoginController controller = new LoginController();
      if (user.get("role").equals("Utilisateur")) {
        controller.connectUser(user);
      }
      else if (user.get("role").equals("Administrateur")) {
        controller.connectAdmin();
      }
    }
    else {
      errorLabel.setText("Les mots de passe ne correspondent pas !");
    }
  }
	
  private boolean verifyMdp(){
    String regex = "^^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!\\\"#$%&'()*+,-./:;<=>?[\\\\]^_`{|}~])(?=\\S+$).{4,}$";
    if (newMdp.getText().matches(regex)) {
      if (confNewMdp.getText().equals(newMdp.getText())) {
        return true;
      }
    }
    return false;
  }
}
