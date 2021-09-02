package Modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banque {

	int id;
	String name;
	String address;
	String ville ;
	List<Client> Clients = new ArrayList<Client>();
	

	public Banque() {
		super();
	}

	public Banque(int id ,String name) {
		this.id = id;
		this.name = name;
	}

	public Banque( int id ,String name, String address, String ville) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.ville = ville;
	}
	

	public void ajouter(Client client) {
		Clients.add(client);
	}


	public String toString() {
		String result = "";
		result = id + " " + name + " " + address + " " + ville + "\n";
		for (Iterator<Client> i = Clients.iterator(); i.hasNext();) {
			Client cust = i.next();
			result += cust.toString() + "\n";
			result += "------------\n";
		}
		return result;
	}

	public void setUnitTest(String value) {
		// faire rien
	}

	
	

	public List<Client> getClients() {
		return Clients;
	}

	public void setClients(List<Client> customers) {
		// this.setClients = setClients;
		// do nothing
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	/*public String getUnitTest() {
		if (id == 0 && !unitTestDone) {
			unitTestDone = true;
			logBuffer = new StringBuffer();
			logBuffer.append("-----Creating customers-----").append("\n");
			Client c1 = new Client(1, "Dupont", "Jean",
					"10, rue des lilas", this.ville);
			ajouter(c1);
			Client c2 = new Client(2, "Poulain", "Amélie",
					"20, rue des mimosas", this.ville);
			ajouter(c2);
			logBuffer.append("-----Creating accounts-----").append("\n");
			Verification checkingAccount1 = new Verification(this, "1234", 150);
			ajouter(checkingAccount1);
			Enregistrement savingAccount1 = new Enregistrement(this, "12399", 100, 0.06);
			ajouter(savingAccount1);
			Verification checkingAccount2 = new Verification(this, "9234", 1501);
			ajouter(checkingAccount2);
			Enregistrement savingAccount2 = new Enregistrement(this, "92399", 1000, 0.07);
			ajouter(savingAccount2);
			c1.addAccount(checkingAccount1);
			c1.addAccount(savingAccount1);
			c2.addAccount(checkingAccount2);
			c2.addAccount(savingAccount2);
			logBuffer.append("-----writings-----").append("\n");
			savingAccount1.déposer(700.00);
			checkingAccount1.déposer(360.00);
			checkingAccount1.déposer(10.00);
			checkingAccount1.déposer(20.00);
			try {
				checkingAccount1.retirer(10.00);
				checkingAccount1.retirer(20.00);
				checkingAccount1.retirer(60.00);
			} catch (Exception ex) {
				// ex.printStackTrace();
			}
			checkingAccount2.déposer(10.00);
			try {
				checkingAccount2.retirer(10660.00);
			} catch (Exception ex) {
				// ex.printStackTrace();
			}
			logBuffer.append("-----Bank Application balances-----")
					.append("\n");
			logBuffer.append(this.toString()).append("\n");
			logBuffer.append("-----Unit testing-----").append("\n");
			String result;
			if (logBuffer
					.indexOf("2 Amélie Poulain Checking Account:        9234 noAvailableFunds") == -1)
				result = "*******************bank test fails***********************\n";
			else
				result = "*******************bank test ok***********************\n";
			unitTestResult = result + "\n" + logBuffer.toString() + "\n"
					+ result;
		}
		return unitTestResult;
	}
*/
}
