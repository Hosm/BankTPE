package dao;

import java.util.List;

import Modele.Client;

public interface IClientDAO {
		public void addClient(Client p);
		public List<Client> listClient();
		public List<Client> recherche(String clé);
		public Client getClient(int codeclient);
		public void updateClient(Client c);
		public void deleteClient(String ref);

}
