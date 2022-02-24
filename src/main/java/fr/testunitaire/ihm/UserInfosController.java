package fr.testunitaire.ihm;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import fr.testunitaire.dao.JSONAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class UserInfosController {
	private Stage stage = new Stage();
	private JSONObject user = new JSONObject();
	
	@FXML private ImageView goBack;
	@FXML private TextField nomValue;
	@FXML private TextField prenomValue;
	@FXML private ComboBox<String> roleValue;
	@FXML private TextField telValue;
	@FXML private TextField mailValue;
	@FXML private TextField adrValue;
	@FXML private DatePicker ddnValue;
	@FXML private ImageView photo;
	
	private File selectedFile;
	private ObservableList<String> roleList = FXCollections.observableArrayList("Utilisateur","Administrateur");
	
	@FXML
	private void initialize() {
		roleValue.setItems(roleList);
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
    private void chooseFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.bmp", "*.gif"));
        selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile!=null) {
        	Image image = new Image(selectedFile.toString());
        	photo.setImage(image);
        }
        
    }
	
	@SuppressWarnings("unchecked")
	@FXML
	private void modifUser() {
		JSONArray userArray = JSONAccess.getJSON();
		System.out.println(user);
		for(int i=0;i<userArray.size();i++) {
			JSONObject userObject = (JSONObject) userArray.get(i);
			if(userObject.get("login").equals(user.get("login"))) {
				userObject.put("nom",nomValue.getText());
				userObject.put("prenom", prenomValue.getText());
		        userObject.put("role",roleValue.getValue().toString());
		        userObject.put("mail", mailValue.getText());
				userObject.put("adresse",adrValue.getText());
				userObject.put("dateNaissance", ddnValue.getValue().toString());
				if(selectedFile != null) {
					userObject.put("photo", selectedFile.toString());
				}
		        userArray.set(i, userObject);
		        JSONAccess.writeToJSON(userArray);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	public void setUser(JSONObject userObject) {
		user=userObject;
		nomValue.setText((String) user.get("nom"));
		prenomValue.setText((String) user.get("prenom"));
		roleValue.setValue((String) user.get("role"));
		telValue.setText((String) user.get("tel"));
		mailValue.setText((String) user.get("mail"));
		adrValue.setText((String) user.get("adresse"));
		ddnValue.setValue(LocalDate.parse((CharSequence) user.get("dateNaissance")));
		Image image = new Image((String) user.get("photo"));
		photo.setImage(image);
	}
}
