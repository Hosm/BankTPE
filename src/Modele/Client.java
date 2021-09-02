
package Modele;


public class Client {
    
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private int accounts;
    

    public Client(int id, String name, String prenom, String address,String ville) {
		super();
		this.id = id;
		this.nom = name;
		this.prenom = prenom;
		this.adresse = address;
		this.ville = ville;
	}
    public Client(int id, String name, String prenom, String address,String ville,int accounts) {
		super();
		this.id = id;
		this.nom = name;
		this.prenom = prenom;
		this.adresse = address;
		this.ville = ville;
		this.accounts = accounts;
	}


	public Client() {
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return nom;
	}


	public void setName(String name) {
		this.nom = name;
	}


	public String getForName() {
		return prenom;
	}


	public void setForName(String forName) {
		this.prenom = forName;
	}


	public String getAddress() {
		return adresse;
	}


	public void setAddress(String address) {
		this.adresse = address;
	}

	public String getCity() {
		return ville;
	}


	public void setCity(String ville) {
		this.ville = ville;
	}

	public int getAccounts() {
		return accounts;
	}


	public void setAccounts(int accounts) {
		this.accounts = accounts;
	}
	
	public String toString(){
		return "client:"+id+", "+prenom+", "+nom +", num de compte:" + accounts + ", ville: "+ ville +", address: " + getAddress();
	}
    
}
