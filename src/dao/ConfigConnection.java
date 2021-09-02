package dao;

import java.sql.*;

public class ConfigConnection {
	static String login = "HOUSSAM";
	static String password = "koutee";
	static Connection maConnexion;
    static String nom; 
    static String url="jdbc:oracle:thin:@localhost:1521:orcl";
    
	private ConfigConnection() {   }
	
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
