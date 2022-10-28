package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import model.Log;

public class ConnToDb {
	protected static Connection conn = null;

	static Config c =new Config();
	private static String connessione="Tentativo di conessione al server..........\\\\n";
	protected static String url2;
	private static boolean status=false;
	

	public static  boolean initailConnection()
	{

		try
		{
			Class.forName(c.getDriver());

			conn = DriverManager.getConnection(c.getUrl(), c.getUser(),c.getPwd());
			Log.LOGGER.log(Level.INFO,"Connesso initial..........\\n");

			status= true;

		} 
		catch (SQLException | ClassNotFoundException  e1)
		{
			e1.printStackTrace();
			Log.LOGGER.log(Level.SEVERE,"Errore in mysql..........\\n");

		} 
		
		return status;
	}

	public static boolean connection() throws SQLException {
		
		boolean status=false;

		try 
		{
			if(initailConnection()) 
			{
				//actuac DB project

				 url2 = "jdbc:mysql://localhost/ispw?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				Class.forName(c.getDriver());
				
				Log.LOGGER.log(Level.INFO,connessione);

				conn = DriverManager.getConnection(url2, c.getUser(),c.getPwd());
				Log.LOGGER.log(Level.INFO,"Connesso standard..........\\n");

				status= true;
			}
			
		} 
		catch (SQLException | ClassNotFoundException  e1) 
		{
			e1.printStackTrace();
			Log.LOGGER.log(Level.SEVERE,"Errore mysql..........\\n");

		} 
		

		return status;
	}
	
	public static Connection generalConnection()
	{

		try
		{
			url2 = "jdbc:mysql://localhost/ispw?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Class.forName(c.getDriver());
			Log.LOGGER.log(Level.INFO,connessione);
			conn = DriverManager.getConnection(url2, c.getUser(),c.getPwd());
			Log.LOGGER.log(Level.INFO,"Connesso standard..........\\n");

		} 
		catch (SQLException  | ClassNotFoundException e1)
		{
			e1.printStackTrace();
			Log.LOGGER.log(Level.SEVERE,"Errore di sql..........\\n");

		} 
		
		return conn;
	
	}
	private ConnToDb(){
		
	}

}
