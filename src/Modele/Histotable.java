package Modele;

public class Histotable {

	private int id;

	private String msg;
	
	private Double val;
	
	private String date ;


	public Histotable(int id, String msg,double val,String date) {
	
		this.id = id;
		this.val = val;
		this.msg = msg;
		this.date = date;
	}



	public Double getVal() {
		return val;
	}



	public void setVal(Double val) {
		this.val = val;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
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



}
