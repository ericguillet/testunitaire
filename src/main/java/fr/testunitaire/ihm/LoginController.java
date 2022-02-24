package fr.testunitaire.ihm;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import fr.testunitaire.dao.JSONAccess;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class LoginController{	
  static private Stage stage = new Stage();
  
  @FXML private TextField loginInput;
  @FXML private PasswordField mdpInput;
  
  @FXML
  private void toConfirmPass(JSONObject user) throws IOException {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/confNewPass.fxml"));
      Parent root  = loader.load();
      ConfirmNewPassController controller = loader.getController();
      controller.setUser(user);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    finally {
    	
    }
  }
	
  @FXML  
  public void connectAdmin() throws IOException {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/userList.fxml"));
      Parent root  = loader.load();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    finally {
    	
     }
  }
  
  @FXML  
  public void connectUser(JSONObject user) throws IOException {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/userInfos.fxml"));
      Parent root  = loader.load();
      UserInfosController controller = loader.getController();
      controller.setUser(user);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    finally {
    	
    }
  }
    
  @FXML
  private void toForgotMdp(ActionEvent event) throws IOException {
    try {
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/forgotPass.fxml"));
      Parent root  = loader.load();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    finally {
    	
    }
  }
    
  @FXML
  private void toUserCreation(ActionEvent event) throws IOException {
    try {
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/signup.fxml"));
      Parent root  = loader.load();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    finally {
    	
    }
  }
    
  @FXML
  private void login(ActionEvent event) throws IOException {  	
    JSONArray userArray = JSONAccess.getJSON(); 	
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    for (int i = 0; i < userArray.size(); i++) {
      JSONObject userObject = (JSONObject) userArray.get(i);
      if (userObject.get("mdp").equals(mdpInput.getText())
          && userObject.get("login").equals(loginInput.getText())){
        if ((boolean) userObject.get("isPassConf")) {
          toConfirmPass(userObject);
    	}
        else if (userObject.get("role").equals("Utilisateur")) {
          connectUser(userObject);
    	}
        else if (userObject.get("role").equals("Administrateur")) {
          connectAdmin();
        }
      }
   }
 }
}
