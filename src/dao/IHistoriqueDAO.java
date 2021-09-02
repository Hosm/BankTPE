package dao;

import java.util.List;

import Modele.Historique;

public interface IHistoriqueDAO {
	public void addHistorique(Historique h);
	public void updateHistorique(Historique h);
	public List<Historique> listHistorique(int numCompte);
}
