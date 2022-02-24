package fr.testunitaire.icône;

import java.util.ArrayList;

import fr.testunitaire.ihm.App;
import javafx.scene.image.Image;

public class Icone {
	private static final String PATH_IMAGE_ICON = "img/icon/";

	private ArrayList<Image> icone;
	
	public Icone() {
		icone = new ArrayList<Image>();

		String[] FileName = { "connexion-16px", "connexion-24px", "connexion-32px", "connexion-64px",
				"connexion-128px", "connexion-256px", "connexion-512px" };

		for (String fileName : FileName) {
			icone.add(new Image(App.class.getResource(PATH_IMAGE_ICON + fileName + ".png").toString()));
		}
	}

	public ArrayList<Image> getIcone() {
		return icone;
	}
}
