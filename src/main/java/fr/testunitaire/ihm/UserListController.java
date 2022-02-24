package fr.testunitaire.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.testunitaire.dao.JSONAccess;
import fr.testunitaire.entité.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class UserListController {
	@FXML private ImageView goBack;
	@FXML private TableView<User> userTable;
	@FXML private TableColumn<User,String> fnameCol;
	@FXML private TableColumn<User,String> lnameCol;
	@FXML private TableColumn<User,String> roleCol;
	@FXML private TableColumn<User,String> telCol;
	@FXML private TableColumn<User,String> mailCol;
	@FXML private TableColumn<User,String> adrCol;
	@FXML private TableColumn<User,String> ddnCol;
	
	private ObservableList<User> users = FXCollections.observableArrayList(getUserList(JSONAccess.getJSON()));
	
	@FXML
	public void initialize () {
		fnameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		lnameCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
		telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
		mailCol.setCellValueFactory(new PropertyValueFactory<>("mail"));
		adrCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		ddnCol.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
		userTable.setItems(users);
	}
	
	@FXML
    private void goBack() throws IOException {
    	App.setRoot("login");
    }
	
	@FXML
    private void mouseEntered() {
		goBack.setStyle("-fx-cursor: hand;");
    }
	
	@FXML
	private void suppUser(ActionEvent event) {
		User user = userTable.getSelectionModel().getSelectedItem();
		JSONArray userArray = (JSONArray) JSONAccess.getJSON();
		for(int i=0;i<userArray.size();i++){
			JSONObject userObject = (JSONObject) userArray.get(i);
			if(user.getLogin().equals(userObject.get("login"))) {
				userArray.remove(i);
            	users = FXCollections.observableArrayList(getUserList(userArray));
            	userTable.setItems(users);
            	JSONAccess.writeToJSON(userArray);
            }
		}
	}
    
	private List<User> getUserList(JSONArray userArray){
        List<User> list = new ArrayList<User>();
   	 try {
         for(int i=0;i<userArray.size();i++) {
    		 ObjectMapper objectmapper = new ObjectMapper();
    		 objectmapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    		 User user = objectmapper.readValue(userArray.get(i).toString(), User.class);
    		 System.out.println(user.getLogin());
    		 list.add(user);
    	 }
         return list;
   	 }
   	 catch (IOException e) {
        e.printStackTrace();
    }
   	 return new ArrayList<User>();
  }
}
