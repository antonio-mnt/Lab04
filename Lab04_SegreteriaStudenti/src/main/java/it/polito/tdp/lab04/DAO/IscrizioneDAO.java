package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Iscrizione;


public class IscrizioneDAO {
	
	
	public List<Iscrizione> getTutteIscrizioni(){
		
		final String sql = "SELECT * FROM iscrizione ";
		
		List<Iscrizione> iscrizioni = new LinkedList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String codins = rs.getString("codins");

				Iscrizione i = new Iscrizione(matricola,codins);
				iscrizioni.add(i);
			}

			conn.close();
			
			return iscrizioni;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
	}
}
