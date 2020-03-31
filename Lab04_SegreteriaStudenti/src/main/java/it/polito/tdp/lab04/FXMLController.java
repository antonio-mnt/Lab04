package it.polito.tdp.lab04;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> boxCorsi;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCompletamento;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	Integer matricola = 0;
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	}catch(NumberFormatException ne) {
    		txtArea.setText("Errore nella scrittura della matricola.\nLa matricola deve essere composta solo da numeri.");
    		txtNome.clear();
    		txtCognome.clear();
    		return;
    	}
    	
    	List<Corso> corsi;
    	try {
    		corsi = this.model.getCorsiPerStudente(matricola); 
    	}catch(NullPointerException ne) {
    		txtArea.setText(ne.getMessage());
    		txtNome.clear();
    		txtCognome.clear();
    		return;
    	}
    	
    	txtArea.clear();
    	for(Corso c: corsi) {
    		txtArea.appendText(c.toLongerString()+"\n");
    	}

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	
    	if(boxCorsi.getValue()==null) {
    		txtArea.setText("Nessun corso selezionato.");
    		return;
    	}

    	String codins = boxCorsi.getValue().getCodins();
    	
    	if(this.model.getStudentiPerCorso(codins).size() == 0) {
    		txtArea.setText("Il corso non presenta iscritti.");
    		return;
    	}
    	
    	txtArea.clear();
    	for(Studente s: this.model.getStudentiPerCorso(codins)) {
    		txtArea.appendText(s.toString()+"\n");
    	}

    }

    @FXML
    void doCompletamento(ActionEvent event) {
    	
    	Integer matricola = 0;
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	}catch(NumberFormatException ne) {
    		txtArea.setText("Errore nella scrittura della matricola.\nLa matricola deve essere composta solo da numeri.");
    		txtNome.clear();
    		txtCognome.clear();
    		return;
    	}
    	
    	Studente s = null;
    	try {
    		s = this.model.getStudente(matricola);
    	}catch(NullPointerException ne) {
    		txtArea.setText(ne.getMessage());
    		txtNome.clear();
    		txtCognome.clear();
    		return;
    	}
    	
		txtNome.setText(s.getNome().toUpperCase());
		txtCognome.setText(s.getCognome().toUpperCase());

    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	if(boxCorsi.getValue()==null) {
    		txtArea.setText("Nessun corso selezionato.");
    		return;
    	}
    	
    	String codins = boxCorsi.getValue().getCodins();
    	
    	if(this.model.getStudentiPerCorso(codins).size() == 0) {
    		txtArea.setText("Il corso non presenta iscritti.");
    		return;
    	}
    	
    	if(!txtMatricola.getText().equals("")) {
    		Integer matricola = 0;
        	try {
        		matricola = Integer.parseInt(txtMatricola.getText());
        	}catch(NumberFormatException ne) {
        		txtArea.setText("Errore nella scrittura della matricola.\nLa matricola deve essere composta solo da numeri.");
        		txtNome.clear();
        		txtCognome.clear();
        		return;
        	}
        	
        	boolean iscritto = false;
        	try {
        		iscritto = model.verificaIscrizione(matricola, codins);
        	}catch(NullPointerException ne) {
        		txtArea.setText(ne.getMessage());
        		txtNome.clear();
        		txtCognome.clear();
        		return;
        	}
        	
        	if(iscritto == true) {
        		txtArea.setText("Lo studente è iscritto al corso selezionato.");
        	}else {
        		txtArea.setText("Lo studente non è iscritto al corso selezionato.");
        	}
        	
    	}else {
    		txtArea.setText("Inserire la matricola.");
    	}
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtArea.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	boxCorsi.setValue(null);

    }
    
    void riempiBoxCorsi() {    	
    	boxCorsi.getItems().add(null);
    	boxCorsi.getItems().addAll(model.getTuttiICorsi());
    	
    }

    @FXML
    void selezionaCorso(ActionEvent event) {
    	
    }
    
    
    @FXML
    void initialize() {
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCompletamento != null : "fx:id=\"btnCompletamento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        
    	btnCompletamento.setStyle("-fx-background-color:green");

    }

	public void setModel(Model model) {
		this.model = model;
		riempiBoxCorsi();
	}
}
