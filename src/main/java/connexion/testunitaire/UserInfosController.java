package connexion.testunitaire;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class UserInfosController {
	private Stage stage = new Stage();
	private JSONObject user = new JSONObject();
	@FXML private TextField nomValue;
	@FXML private TextField prenomValue;
	@FXML private ComboBox roleValue;
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
    private void chooseFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));
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
