package fr.testunitaire.ihm;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import fr.testunitaire.dao.JSONAccess;
import fr.testunitaire.entité.User;


public class SignUpController {
	@FXML private ImageView goBack;
	@FXML private TextField login;
	@FXML private PasswordField mdp;
	@FXML private PasswordField confirmMdp;
	@FXML private ComboBox<String> role;
	@FXML private TextField nom;
	@FXML private TextField prenom;
	@FXML private TextField tel;
	@FXML private TextField mail;
	@FXML private TextField adresse;
	@FXML private DatePicker dateNaissance;
	@FXML private Button photoButton;
	@FXML private ImageView photo;
	@FXML private Label message;
	
	File selectedFile = null;
	private Stage stage;
	ObservableList<String> roleList = FXCollections.observableArrayList("Utilisateur","Administrateur");
	
	@FXML
	private void initialize() {
		role.setValue("Utilisateur");
		role.setItems(roleList);
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
	
	@FXML
    private User getUserInfos() throws IOException {
		User user = new User();
        user.setLogin(login.getText());
        user.setMdp(mdp.getText());
        user.setRole(role.getValue().toString());
        user.setNom(nom.getText());
        user.setPrenom(prenom.getText());
        user.setTel(tel.getText());
        user.setMail(mail.getText());
        user.setAdresse(adresse.getText());
        user.setDateNaissance(dateNaissance.getValue().toString());
        user.setPhoto(selectedFile.toString());
        return user;
    } 
	
    @SuppressWarnings("unchecked")
	@FXML
    private void registerUser() throws IOException {
    	if(verifyAll()) {
            JSONObject userObject = new JSONObject();
            userObject.put("login",getUserInfos().getLogin());
            userObject.put("mdp",getUserInfos().getMdp());
            userObject.put("role",getUserInfos().getRole());
            userObject.put("nom",getUserInfos().getNom());
            userObject.put("prenom",getUserInfos().getPrenom());
            userObject.put("tel",getUserInfos().getTel());
            userObject.put("mail",getUserInfos().getMail());
            userObject.put("adresse",getUserInfos().getAdresse());
            userObject.put("dateNaissance",getUserInfos().getDateNaissance());
            userObject.put("photo",getUserInfos().getPhoto());
            userObject.put("isPassConf", getUserInfos().isPassConf());
            JSONArray userArray = JSONAccess.getJSON();
            userArray.add(userObject);
            JSONAccess.writeToJSON(userArray);
            message.setText("L'utilisateur a bien été créer");
            message.setStyle("-fx-text-fill: green");
    	}
    	else {
    		message.setText("Les informations n'ont pas été correctement remplies");
            message.setStyle("-fx-text-fill: red");
    	}
    }
    
    
    private boolean verifyAll() {
    	if(verifyLogin() && verifyMdp() && verifyTel() && verifyMail()) {
    		return true;
    	}
    	else return false;
    }
    
    private boolean verifyLogin(){
    	JSONArray userArray = JSONAccess.getJSON();
		if(login.getText().equals("")) {
			return false;
		}
    	for(int i=0;i<userArray.size();i++) {
    		JSONObject user = (JSONObject) userArray.get(i);
    		if(login.getText().equals(user.get("login"))) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean verifyMdp(){
		String regex = "^^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!\\\"#$%&'()*+,-./:;<=>?[\\\\]^_`{|}~])(?=\\S+$).{4,}$";
		if(mdp.getText().matches(regex)) {
			if(confirmMdp.getText().equals(mdp.getText())) {
				return true;
			}
		}
		return false;
    }
    
    private boolean verifyTel(){
		String regex = "^0\\d{9}$";
		if(tel.getText().matches(regex) || tel.getText().equals("")) {
			return true;
		}
		return false;
    }
    
    private boolean verifyMail(){
		String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";	 
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(mail.getText());
		if(matcher.matches() || mail.getText().equals("")) {
			return true;
		}
		return false;
    }  
 }
