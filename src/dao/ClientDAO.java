package dao;

import Modele.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements IClientDAO {

	public ClientDAO() {

	}

	@Override
	public void addClient(Client c) {
		// TODO Auto-generated method stub
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO ALLCLIENT(IDCLI, NOM, PRENOM, ADRESSE, VILLE, IDCOMPTEBANCAIRE) "
								+ "VALUES (?,?,?,?,?,?)");
				ps.setInt(1, c.getId());
				ps.setString(2, c.getName());
				;
				ps.setString(3, c.getForName());
				ps.setString(4, c.getAddress());
				ps.setString(5, c.getCity());
				ps.setInt(6, c.getAccounts());
				ps.executeUpdate();
				ps.close();
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Client> listClient() {
		List<Client> listclt = new ArrayList<Client>();
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
				PreparedStatement ps = connection.prepareStatement("select * from ALLCLIENT");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Client c = new Client(rs.getInt("IDCLI"), rs.getString("nom"), rs.getString("prenom"),
							rs.getString("adresse"), rs.getString("ville"), rs.getInt("IDCOMPTEBANCAIRE"));
					listclt.add(c);
				}
				ps.close();
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listclt;
	}

	@Override
	public List<Client> recherche(String clé) {

		List<Client> listclnt = new ArrayList<Client>();
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
				PreparedStatement ps = connection
						.prepareStatement("select * from ALLCLIENT where nom like ? or prenom like ?");
				ps.setString(1, "%" + clé + "%");
				ps.setString(2, "%" + clé + "%");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Client c = new Client(rs.getInt("IDCLI"), rs.getString("nom"), rs.getString("prenom"),
							rs.getString("adresse"), rs.getString("ville"), rs.getInt("IDCOMPTEBANCAIRE"));
					listclnt.add(c);
				}
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listclnt;
	}

	@Override
	public Client getClient(int codeClient) {
		Connection connection;
		Client c = null;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
				PreparedStatement ps = connection.prepareStatement("select * from ALLCLIENT where IDCOMPTEBANCAIRE like ?");
				ps.setInt(1, codeClient);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					c = new Client(rs.getInt("IDCLI"), rs.getString("nom"), rs.getString("prenom"),
							rs.getString("adresse"), rs.getString("ville"), rs.getInt("IDCOMPTEBANCAIRE"));
				}
				ps.close();
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (c == null)
			throw new RuntimeException("Client " + codeClient + " est introuvable");
		return c;
	}

	@Override
	public void updateClient(Client c) {
		// TODO Auto-generated method stub
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
				PreparedStatement ps = connection.prepareStatement(
						"update ALLCLIENT set nom=?,prenom=?,adresse=?,ville=?,IDCOMPTEBANCAIRE=? where IDCLI=?");

				ps.setString(1, c.getName());
				ps.setString(2, c.getForName());
				ps.setString(3, c.getAddress());
				ps.setString(4, c.getCity());
				ps.setInt(5, c.getAccounts());
				ps.setInt(6, c.getId());
				ps.executeUpdate();
				ps.close();
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteClient(String ref) {
		Connection connection;
		try {
			connection = ConfigConnection.getConnection();
			if (connection != null) {
			PreparedStatement ps = connection.prepareStatement("delete from ALLCLIENT where IDCLI=?");
			ps.setString(1, ref);
			ps.executeUpdate();
			ps.close();
			connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
