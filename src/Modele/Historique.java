package Modele;

public class Historique {

	private int id;

	private String msg;
	
	private Double val;
	
	private int Numcompte;
	
	private String date;


	public Historique(int id, String msg, int numcompte,double val,String date) {
	
		this.id = id;
		this.val = val;
		this.msg = msg;
		this.Numcompte = numcompte;
		this.date=date;
	}
	public Historique(String msg, int numcompte,double val,String date) {
		
		this.val = val;
		this.msg = msg;
		this.Numcompte = numcompte;
		this.date=date;
	}


    public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getNumcompte(){
    	return Numcompte;
    }
    public void setNumcompte(int Numcompte){
    	this.Numcompte = Numcompte;
    }
	public Double getval() {
		return val;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String val) {
		this.msg = val;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTransaction(int transaction) {
		// this.transaction = transaction;
	}

}
