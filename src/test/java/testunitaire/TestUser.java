package testunitaire;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import fr.testunitaire.entité.User;

class TestUser {
	
	@Test	
	public void testCtor1(String login, String mdp, String role, File photo, String nom, String prenom, String mail,
			String tel, String adr, String ddn) {
		User user = new User(login,mdp,role,photo,nom,prenom,mail,tel,adr,ddn);
		assertEquals(user.getLogin(),login);
		assertEquals(user.getMdp(),mdp);
		assertEquals(user.getRole(),role);
		assertEquals(user.getNom(),nom);
		assertEquals(user.getPrenom(),prenom);
		assertEquals(user.getMail(),mail);
		assertEquals(user.getAdresse(),adr);
		assertEquals(user.getDateNaissance(),ddn);
		assertNotNull(user);
	}
	
	@Test
	public void testCtor2() {
		User user = new User();
		assertNotNull(user);
	}
	
	@Test
	public void testGetLogin() {
		User user = new User();
		assertTrue(user.getLogin().length()>0 && user.getLogin().length()<20);
	}
	
	@Test
	public void testGetMdp() {
		User user = new User();
		String regex = "^^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!\\\"#$%&'()*+,-./:;<=>?[\\\\]^_`{|}~])(?=\\S+$).{4,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(user.getMdp());
		assertTrue(matcher.matches());
	}
	
	@Test
	public void testGetNom() {
		User user = new User();
		String regex = "^[\\p{L} .'-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(user.getNom());
		assertTrue(matcher.matches());
	}
	
	@Test
	public void testGetPrenom() {
		User user = new User();
		String regex = "^[\\p{L} .'-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(user.getPrenom());
		assertTrue(matcher.matches());
	}
	
	@Test
	public void testGetTel() {
		User user = new User();
		String regex = "^0\\d{9}$";
		assertTrue(user.getTel().length()==10 && user.getTel().matches(regex) && user.getTel().charAt(0)=='0');
	}
	
	@Test
	public void testGetMail() {
		User user = new User();
		String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";	 
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(user.getMail());
		assertTrue(matcher.matches());
	}
	
	
	@Test
	public void testGetAdr() {
		User user = new User();
		String regex = "^[a-zA-Z0-9_]+( [a-zA-Z0-9_]+)*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(user.getAdresse());
		assertTrue(matcher.matches());
	}
	
}
