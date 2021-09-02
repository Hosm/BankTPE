package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modele.CompteBancaire;

public class CompteBancaireDAO implements ICompteBancaireDAO {

	@Override
	public void addCompteBancaire(CompteBancaire c) {
		// TODO Auto-generated method stub
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO ALLCOMPTEBANCAIRE (IDCB, SOLDE, COMPTENUMERO, TAUXINTERET, TRANSACTION, IDCLIENT, IDBANQUE)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?,?)");
			ps.setInt(1, c.getId());
			ps.setDouble(2, c.getSolde());
			ps.setInt(3, c.getCompteNumero());
			ps.setDouble(4, c.getTauxInteret());
			ps.setInt(5, c.gettransaction());
			ps.setInt(6, c.getClient());
			ps.setInt(7, c.getBanque());
			ps.setInt(8, c.getPassword());
			ps.executeUpdate();
			ps.close();
			connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CompteBancaire> listCompteBancaire() {
		List<CompteBancaire> listcb = new ArrayList<CompteBancaire>();
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
			PreparedStatement ps = connection.prepareStatement("select * from COMPTEBANCAIRE");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CompteBancaire c = new CompteBancaire(rs.getInt("IDCB"), rs.getDouble("SOLDE"),
						rs.getInt("COMPTENUMERO"), rs.getDouble("TAUXINTERET"), rs.getInt("TRANSACTION"),
						rs.getInt("IDCLIENT"), rs.getInt("IDBANQUE"), rs.getInt("PASSWORD"));
				listcb.add(c);
			}
			ps.close();
			connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listcb;
	}

	@Override
	public CompteBancaire recherche(int clé) {

		CompteBancaire cb = null;
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
			PreparedStatement ps = connection
					.prepareStatement("Select * from ALLCOMPTEBANCAIRE where COMPTENUMERO like ?");
			ps.setString(1, "%" + clé + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cb = new CompteBancaire(rs.getInt("IDCB"), rs.getDouble("SOLDE"), rs.getInt("COMPTENUMERO"),
						rs.getDouble("TAUXINTERET"), rs.getInt("TRANSACTION"), rs.getInt("IDCLIENT"),
						rs.getInt("IDBANQUE"), rs.getInt("PASSWORD"));
			}
			ps.close();
			connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cb;
	}

	@Override
	public CompteBancaire getIDCompteBancaire(int code) {
		CompteBancaire cb = null;
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
			PreparedStatement ps = connection.prepareStatement("Select * from ALLCOMPTEBANCAIRE where COMPTENUMERO like ?");
			ps.setString(1, "%" + code + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				cb = new CompteBancaire(rs.getInt("IDCB"), rs.getDouble("SOLDE"), rs.getInt("COMPTENUMERO"),
						rs.getDouble("TAUXINTERET"), rs.getInt("TRANSACTION"), rs.getInt("IDCLIENT"),
						rs.getInt("IDBANQUE"), rs.getInt("PASSWORD"));

			}
			ps.close();
			connection.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cb;
	}

	@Override
	public void updateCompteBancaire(CompteBancaire c) {
		// TODO Auto-generated method stub
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
			PreparedStatement ps = connection.prepareStatement(
					"update ALLCOMPTEBANCAIRE set SOLDE=?,COMPTENUMERO=?,TAUXINTERET=?,TRANSACTION=?,IDCLIENT=?,IDBANQUE=?,PASSWORD=? where IDCB=?");

			ps.setDouble(1, c.getSolde());
			ps.setInt(2, c.getCompteNumero());
			ps.setDouble(3, c.getTauxInteret());
			ps.setInt(4, c.gettransaction());
			ps.setInt(5, c.getClient());
			ps.setInt(6, c.getBanque());
			ps.setInt(7, c.getPassword());
			ps.setInt(8, c.getId());
			ps.executeUpdate();
			ps.close();
			connection.close();
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCompteBancaire(String ref) {
		Connection connection;
		try {

			connection = ConfigConnection.getConnection();
			if (connection != null) {
				PreparedStatement ps = connection.prepareStatement("delete from ALLCOMPTEBANCAIRE where IDCB=?");
				ps.setString(1, ref);
				ps.executeUpdate();
				ps.close();
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean transfereTo(Double val, int clé) {

		CompteBancaire cb = null;
		cb = recherche(clé);
		if (cb != null) {
			updateCompteBancaire(new CompteBancaire(cb.getId(), cb.getSolde() + val, cb.getCompteNumero(),
					cb.getTauxInteret(), cb.gettransaction(), cb.getClient(), cb.getBanque(), cb.getPassword()));
			return true;
		}
		return false;
	}

	@Override
	public boolean isAuthentified(int numCompte, int password) {
		CompteBancaire cb = null;
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
				PreparedStatement ps = connection
						.prepareStatement("select * from ALLCOMPTEBANCAIRE where COMPTENUMERO = ? AND PASSWORD= ?");
				ps.setInt(1, numCompte);
				ps.setInt(2, password);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					cb = new CompteBancaire(rs.getInt("IDCB"), rs.getDouble("SOLDE"), rs.getInt("COMPTENUMERO"),
							rs.getDouble("TAUXINTERET"), rs.getInt("TRANSACTION"), rs.getInt("IDCLIENT"),
							rs.getInt("IDBANQUE"), rs.getInt("PASSWORD"));
				}
				ps.close();
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if (cb == null)
			return false;
		else
			return true;
	}

}