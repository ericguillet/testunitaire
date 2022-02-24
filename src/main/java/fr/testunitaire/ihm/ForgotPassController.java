package fr.testunitaire.ihm;

import java.io.IOException;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import fr.testunitaire.dao.JSONAccess;
import fr.testunitaire.mdp.GénérateurMdp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

public class ForgotPassController {
  private Stage stage = new Stage();
  @FXML private TextField login;
  @FXML private Label newPass;
	@FXML private ImageView goBack;
  
	@FXML
    private void goBack() throws IOException {
    	App.setRoot("login");
    }
	
	@FXML
    private void mouseEntered() {
		goBack.setStyle("-fx-cursor: hand;");
    }
	
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
    String randString = GénérateurMdp.générerMdp();
    newPass.setText(randString);
    user.put("mdp", randString);
    user.put("isPassConf", true);
    userArray.set(index, user);
    JSONAccess.writeToJSON(userArray);
    
    Clipboard clipboard = Clipboard.getSystemClipboard();
    ClipboardContent content = new ClipboardContent();
    content.putString(randString);
    clipboard.setContent(content);
  }
}