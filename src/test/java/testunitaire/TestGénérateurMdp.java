package testunitaire;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.testunitaire.mdp.GénérateurMdp;

public class TestGénérateurMdp {
	@Test
	public void testLongueur() {
		int longueur = GénérateurMdp.générerMdp().length();

		assertTrue(longueur == 24);
	}

	@Test
	public void testMinsucule() {
		char caractère = GénérateurMdp.générerMdp().charAt(0);

		assertTrue(Character.isLowerCase(caractère));
	}

	@Test
	public void testMajuscule() {
		char caractère = GénérateurMdp.générerMdp().charAt(1);

		assertTrue(Character.isUpperCase(caractère));
	}

	@Test
	public void testChiffre() {
		char caractère = GénérateurMdp.générerMdp().charAt(2);

		assertTrue(Character.isDigit(caractère));
	}

	@Test
	public void testCaractèreSpécial() {
		char caractère = GénérateurMdp.générerMdp().charAt(3);

		assertFalse(Character.isLetter(caractère) || Character.isDigit(caractère) || Character.isWhitespace(caractère));
	}

	@Test
	public void testDifférence() {
		String mdp1 = GénérateurMdp.générerMdp();
		String mdp2 = GénérateurMdp.générerMdp();

		assertFalse(mdp1.equals(mdp2));
	}
}
