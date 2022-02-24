package connexion.testunitaire;

import java.io.File;
import java.time.LocalDate;
	
public class User {
	private String login;
	private String mdp;
	private String role;
	private File photo;
	private String nom;
	private String prenom;
	private String mail;
	private String tel;
	private String adresse;
	private String dateNaissance;
	private boolean isPassConf;
	
	
	public User(String login, String mdp, String role, File photo, String nom, String prenom, String mail,
			String tel, String adresse, String dateNaissance) {
		this.login = login;
		this.mdp = mdp;
		this.role = role;
		this.photo = photo;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.tel = tel;
		this.adresse = adresse;
		this.dateNaissance = dateNaissance;
		this.isPassConf = false;
	}
	
	public User() {
		this.login = "";
		this.mdp = "";
		this.role = "";
		this.photo = null;
		this.nom = "";
		this.prenom = "";
		this.mail = "";
		this.tel = "";
		this.adresse = "";
		this.dateNaissance = "";
		this.isPassConf = false;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getPhoto() {
		return photo.toString();
	}


	public void setPhoto(String photo) {
		this.photo = new File(photo);
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getTel() {
		return tel;
	}


	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public boolean isPassConf() {
		return isPassConf;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	
	public boolean getPassConf() {
		return isPassConf;
	}


	public void setPassConf(boolean isPassConf) {
		this.isPassConf = isPassConf;
	}

}
