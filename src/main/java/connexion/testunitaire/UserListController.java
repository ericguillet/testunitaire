package connexion.testunitaire;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class UserListController {
	private Stage stage = new Stage();
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
    private void goBack(ActionEvent event) throws IOException {
    	try {
        	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        	Parent root  = loader.load();
        	Scene scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    	}
    	finally {
    		
    	}
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
