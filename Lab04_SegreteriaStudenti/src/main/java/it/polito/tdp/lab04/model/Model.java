package it.polito.tdp.lab04.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.IscrizioneDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO cdao;
	private StudenteDAO sdao;
	private IscrizioneDAO idao;
	
	public Model() {
		cdao = new CorsoDAO();
		sdao = new StudenteDAO();
		idao = new IscrizioneDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return cdao.getTuttiICorsi();
	}
	
	public Studente getStudente(Integer matricola){
		
		for(Studente s: sdao.getTuttiStudenti()) {
			if(matricola.equals(s.getMatricola())) {
				return s;
			}
		}
		
		throw new NullPointerException("La matricola cercata non esiste.");
	}
	
	public List<Studente> getStudentiPerCorso(String codins){
		return sdao.getStudentiPerCorso(codins);
	}
	
	public List<Corso> getCorsiPerStudente(int matricola){
		
		getStudente(matricola);
		
		return cdao.getCorsiPerStudente(matricola);
		
	}
	
	public boolean verificaIscrizione(Integer matricola, String codins) {
		
		getStudente(matricola);
		
		Iscrizione tempI = new Iscrizione(matricola,codins);
		
		if(idao.getTutteIscrizioni().contains(tempI))
			return true;
		else
			return false;

	}

}
