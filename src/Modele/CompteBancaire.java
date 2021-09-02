package Modele;

import java.util.Date;

import dao.CompteBancaireDAO;
import dao.HistoriqueDAO;
import dao.ICompteBancaireDAO;
import dao.IHistoriqueDAO;

public class CompteBancaire implements Compte {

	private int id;
	private int password;
	private double solde;
	private int compteNumero;
	private double tauxInteret;
	private int transaction;
	private int client;
	private int banque;

	public CompteBancaire(int id, double solde, int compteNumero, double tauxInteret, int transaction, int client,
			int banque, int password) {

		this.id = id;
		this.solde = solde;
		this.compteNumero = compteNumero;
		this.tauxInteret = tauxInteret;
		this.transaction = transaction;
		this.client = client;
		this.banque = banque;
		this.password = password;

	}

	public Boolean retirer(double val) {
		if (getSolde() >= val && val > 0) {
			Date current = new Date();
			setSolde(solde - val);
			Historique Histo = new Historique("Retirer", this.id, val, current.toString());
			IHistoriqueDAO hist = new HistoriqueDAO();
			hist.addHistorique(Histo);
			this.transaction++;
			ICompteBancaireDAO cb = new CompteBancaireDAO();
			cb.updateCompteBancaire(this);
			return true;
		} else
			System.out.println("Action refuser");
		return false;
	}

	public Boolean deposer(double val) {
		Date current = new Date();
		setSolde(solde + val);
		Historique Histo = new Historique("déposer", this.id, val, current.toString());
		IHistoriqueDAO hist = new HistoriqueDAO();
		hist.addHistorique(Histo);
		this.transaction++;
		ICompteBancaireDAO cb = new CompteBancaireDAO();
		cb.updateCompteBancaire(this);
		return true;
	}

	public Boolean transferer(double val, int Numcompte) {
		if (getSolde() >= val) {
			Date current = new Date();
			setSolde(solde - val);
			Historique Histo = new Historique("transferer", this.id, val, current.toString());
			IHistoriqueDAO hist = new HistoriqueDAO();
			hist.addHistorique(Histo);
			this.transaction++;
			ICompteBancaireDAO cb = new CompteBancaireDAO();
			cb.updateCompteBancaire(this);
			if (cb.transfereTo(val, Numcompte)) {
				System.out.println("transfere réussi...");
				return true;
			} else {
				System.out.println("faux numero de compte de la destination");
				return false;
			}
		} else {
			System.out.println("transfere annulé");
			return false;
		}
	}

	public boolean equals(Object o) {
		return this == o
				|| ((o instanceof CompteBancaire) && ((CompteBancaire) o).getCompteNumero() == getCompteNumero());
	}

	@Override
	public String toString() {
		return " compte Numero:  " + getCompteNumero() + " Solde:  " + getSolde();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public int getCompteNumero() {
		return compteNumero;
	}

	public void setCompteNumero(int compteNumero) {
		this.compteNumero = compteNumero;
	}

	public double getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	public int gettransaction() {
		return transaction;
	}

	public void settransaction(int transaction) {
		this.transaction = transaction;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public int getBanque() {
		return banque;
	}

	public void setBanque(int banque) {
		this.banque = banque;
	}

	/*
	 * public String getMessage() { //String result = historique.getMsg();
	 * getHistoriqueDAO if (result == null) result = "L'historique est vide";
	 * return result; }
	 */

}
