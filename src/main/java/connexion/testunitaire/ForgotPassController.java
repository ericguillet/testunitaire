package connexion.testunitaire;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgotPassController {
  private Stage stage = new Stage();
  @FXML private TextField login;
  @FXML private Label newPass;
  
	
  @FXML
  private void sendNewPass() {
    JSONArray users = JSONAccess.getJSON();
    for (int i = 0; i < users.size(); i++) {
      JSONObject user = (JSONObject) users.get(i);
      if (user.get("login").equals(login.getText())) {
        changePass(user, users, i);
      }
    }
  }
	
  @SuppressWarnings("unchecked")
  private void changePass(JSONObject user, JSONArray userArray, int index) {
    String randString = generateRandomString();
    newPass.setText(randString);
    user.put("mdp", randString);
    user.put("isPassConf", true);
    userArray.set(index, user);
    JSONAccess.writeToJSON(userArray);
  }
	
  private String generateRandomString() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();
    String generatedString = random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    return generatedString;
  }
	
	
  @FXML
  private void goBack(ActionEvent event) throws IOException {
    try {
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
      Parent root  = loader.load();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    finally {
    	
    }
  }
}