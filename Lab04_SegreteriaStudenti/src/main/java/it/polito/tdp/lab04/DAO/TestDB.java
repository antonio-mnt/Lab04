package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Iscrizione;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		//cdao.getTuttiICorsi();
		for(Corso c: cdao.getTuttiICorsi()) {
			System.out.println(c.toString());
		}
		
		System.out.println("////////////////////////////////////////////////////////");
		
		StudenteDAO sdao = new StudenteDAO();
		
		for(Studente d: sdao.getTuttiStudenti()) {
			System.out.println(d.toString());
		}
		
		System.out.println("////////////////////////////////////////////////////////");
		
		for(Studente d: sdao.getStudentiPerCorso("01KSUPG")) {
			System.out.println(d.toString()+ " 00000000000000000");
		}
		
		System.out.println("////////////////////////////////////////////////////////");
		
		for(Corso c: cdao.getCorsiPerStudente(146101)) {
			System.out.println(c.toString()+ " ttttttt");
		}
		
		IscrizioneDAO idao = new IscrizioneDAO();
		
		for(Iscrizione i: idao.getTutteIscrizioni()) {
			System.out.println(i.toString());
		}
		
	}

}
