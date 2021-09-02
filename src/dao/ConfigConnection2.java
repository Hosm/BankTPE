package dao;

import java.sql.*;

public class ConfigConnection2 {
	static String login = "YOUSSEF";
	static String password = "0135712";
	static Connection maConnexion;
    static String nom; 
    //static String url="jdbc:oracle:thin:@192.168.71.101:1521:DBHOSMW7";
    static String url="jdbc:oracle:thin:@192.168.137.2:1521:ORCL";
    
	private ConfigConnection2() {   }
	
	public static Connection getConnection() throws SQLException{
	        try{
	             
	            //Charge le driver jdbc
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	             
	            //Connexion
	            maConnexion= DriverManager.getConnection(url,login,password);
	            
            
	            return maConnexion;
	     
	        }     catch(ClassNotFoundException e){
	            System.out.println("Impossible de charger le pilote!");
	            return null;
	        }
	      
	        catch(SQLException e){
	            System.out.println("Connexion impossible = "+e.getMessage());
	            return null;
	        }
	 }

}