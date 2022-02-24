module connexion.testunitaire {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires org.json;
	requires javafx.base;
	requires json.simple;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.annotation;
	
    opens fr.testunitaire.ihm to javafx.fxml;
    exports fr.testunitaire.ihm;
    exports fr.testunitaire.entité;
}
