package fr.testunitaire.regex;

import java.util.regex.Pattern;

public class Regex {
	public static boolean vérifierMail(String mail) {
		String regex = "^([0-9_a-z]+|[0-9_a-z]+(\\.[0-9_a-z])+)+@[a-z]+\\.[a-z]{2,3}$";
		
		return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(mail).find();
	}
    
	public static boolean vérifierTéléphone(String téléphone) {
		String regex = "^0[0-9]{9}$";
		
		return Pattern.compile(regex).matcher(téléphone).find();
	}
	
	public static boolean vérifierRobustesseMdp(String mdp) {
		Pattern majuscule = Pattern.compile("[A-Z]");
		Pattern minuscule = Pattern.compile("[a-z]");
		Pattern chiffre = Pattern.compile("[0-9]");
		Pattern caractereSpecial = Pattern.compile("[!-~&&[^0-9A-Za-z]]");
		Pattern longueurMinimum = Pattern.compile("^.{8,}$");
		
		Pattern[] tabPattern = { majuscule, minuscule, chiffre, caractereSpecial, longueurMinimum };
		
		boolean test = true;
			
		for (Pattern pattern : tabPattern){
			test = test & pattern.matcher(mdp).find();
		}
		
		return test;
	}
}
