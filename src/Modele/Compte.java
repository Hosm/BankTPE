package Modele;

public interface Compte {
	
	public double getSolde();
	public Boolean retirer(double val);
	public Boolean deposer(double val);
	
}
