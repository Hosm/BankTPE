package dao;

import java.util.ArrayList;
import java.util.List;
import Modele.Client;
import Modele.CompteBancaire;
import Modele.Historique;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Client> ListCli = new ArrayList<Client>();
		IClientDAO clientdao = new ClientDAO();
		ListCli= clientdao.listClient  ();
		
		for(Client c:ListCli){
			System.out.println(c.toString());
		}
		Client cli=new Client(4,"Amine","Mohamed","Oulad oujih n�85 ","kenitra",4114);
		// clientdao.addClient(cli);
		clientdao.updateClient(cli);
		System.out.println("--------------------");
		for(Client c:ListCli){
			System.out.println(c.toString());
		}
		
		ICompteBancaireDAO compteBancaireDAO = new CompteBancaireDAO();
		List<CompteBancaire> Listcb= new ArrayList<CompteBancaire>();
		//CompteBancaire cb= compteBancaireDAO.getIDCompteBancaire(1);
		
		//cb.transferer(2000, 4112);
		//cb.retirer(2220);
	
		//System.out.println(compteBancaireDAO.getIDCompteBancaire(5));
		Listcb = compteBancaireDAO.listCompteBancaire();
		for(CompteBancaire c:Listcb){
			System.out.println(c.toString());
		}
	
		System.out.println("--------------------");
		IHistoriqueDAO HistoriqueDAO = new HistoriqueDAO();
		
		for(Historique c:HistoriqueDAO.listHistorique(1)){
			System.out.println(c.toString());
		}
		
	}

}
