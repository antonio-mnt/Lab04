package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getTuttiStudenti(){
		
		final String sql = "SELECT * FROM studente";
		
		List<Studente> studenti = new LinkedList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				Studente s = new Studente(matricola,cognome,nome,CDS);
				studenti.add(s);
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
	}
	
	
	
	public List<Studente> getStudentiPerCorso(String codins){
		
		final String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS " +
		                   "FROM studente s, iscrizione i, corso c " +
				           "WHERE s.matricola = i.matricola AND c.codins = i.codins AND c.codins = ? ";
		
		List<Studente> studenti = new LinkedList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codins);;

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				Studente s = new Studente(matricola,cognome,nome,CDS);
				studenti.add(s);
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
	}

}
