package dao;

import java.util.List;

import Modele.CompteBancaire;

public interface ICompteBancaireDAO {
	public void addCompteBancaire(CompteBancaire p);
	public List<CompteBancaire> listCompteBancaire();
	public CompteBancaire recherche(int clé);
	public boolean transfereTo(Double val,int clé);
	public CompteBancaire getIDCompteBancaire(int code);
	public void updateCompteBancaire(CompteBancaire c);
	public void deleteCompteBancaire(String ref);
    public boolean isAuthentified(int numCompte,int password);
}
