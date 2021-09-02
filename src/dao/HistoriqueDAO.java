package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modele.Historique;

public class HistoriqueDAO implements IHistoriqueDAO {

	@Override
	public void addHistorique(Historique c) {
		// TODO Auto-generated method stub
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO ALLHISTORIQUE (IDHISTO,MSG, NUMCOMPTE, VALEUR,DATEE) VALUES (null,?,?,?,?)");
			ps.setString(1, c.getMsg());
			ps.setInt(2, c.getNumcompte());
			ps.setDouble(3, c.getval());
			ps.setString(4, c.getDate());

			ps.executeUpdate();
			ps.close();
			connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateHistorique(Historique c) {
		// TODO Auto-generated method stub
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
			PreparedStatement ps = connection
					.prepareStatement("update HISTORIQUE set VALEUR=?,MSG=?,NUMCOMPTE=?,DATEE=? where IDHISTO=?");

			ps.setDouble(1, c.getval());
			ps.setString(2, c.getMsg());
			ps.setInt(3, c.getNumcompte());
			ps.setString(4, c.getDate());
			ps.setInt(5, c.getId());

			ps.executeUpdate();
			ps.close();
			connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<Historique> listHistorique(int numCompte) {
		List<Historique> listcb = new ArrayList<Historique>();
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
			PreparedStatement ps = connection.prepareStatement("select * from ALLHISTORIQUE where NUMCOMPTE like ? ORDER BY DATEE DESC");
			ps.setInt(1, numCompte);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				Historique cb = new Historique(rs.getInt("IDHISTO"),  rs.getString("MSG"), rs.getInt("NUMCOMPTE"),rs.getDouble("VALEUR"),rs.getString("DATEE"));
				listcb.add(cb);
			}

			ps.close();
			connection.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listcb;
	}

	
	

}
