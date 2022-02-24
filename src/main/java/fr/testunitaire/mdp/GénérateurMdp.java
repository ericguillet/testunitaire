package fr.testunitaire.mdp;

import java.util.Random;

public class GénérateurMdp {
	private static Random random = new Random();

	public static String générerMdp() {
		String mdp = "";

		String minuscules = "abcdefghijklmnopqrstuvwxyz";
		String majuscules = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String chiffres = "0123456789";
		String caractèreSpéciaux = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

		mdp += caractèreAléatoire(minuscules);
		mdp += caractèreAléatoire(majuscules);
		mdp += caractèreAléatoire(chiffres);
		mdp += caractèreAléatoire(caractèreSpéciaux);

		for (int i = 0; i < 20; i++) {
			mdp += caractèreAléatoire();
		}

		return mdp;
	}

	private static char caractèreAléatoire() {
		int caractère = random.nextInt(94) + 33;

		return (char) caractère;
	}

	private static char caractèreAléatoire(String listeCaractères) {
		int caractère = random.nextInt(listeCaractères.length());

		return listeCaractères.charAt(caractère);
	}
}
