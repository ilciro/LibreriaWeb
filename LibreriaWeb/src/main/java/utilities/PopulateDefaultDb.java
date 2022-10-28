package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.util.logging.Level;

import org.apache.ibatis.jdbc.ScriptRunner;

import model.Log;

public class PopulateDefaultDb {
	private static Connection conn;
	private static ScriptRunner sr;
	private static boolean state=false;

	private PopulateDefaultDb()
	{

	}
	
	public static boolean populateDefaultDb() throws FileNotFoundException
	{
			createLibri();
			 createGiornale() ;
			  createRivista();
			   createUser(); 
			   createNegozio();
		
			state=true;
		
		return state;
	}

	public static boolean createLibri() throws FileNotFoundException
	{
		Log.LOGGER.log(Level.INFO,"---------Chiamo stored insLibri---------\n\n");
		
			conn=ConnToDb.generalConnection();
			sr = new ScriptRunner(conn);
			sr.setSendFullScript(true);
			Reader reader = new BufferedReader(new FileReader("FileSql/storedInsLibri.sql"));
			//Running the script
			sr.runScript(reader);
			state=true;

			
		
		return state;
	}

	public static boolean createGiornale() throws FileNotFoundException
	{
		Log.LOGGER.log(Level.INFO,"---------Chiamo stored insGiornali---------\n\n");
		
			conn=ConnToDb.generalConnection();
			sr = new ScriptRunner(conn);
			sr.setSendFullScript(true);
			Reader reader = new BufferedReader(new FileReader("FileSql/stroredInsGiornali.sql"));
			//Running the script
			sr.runScript(reader);
			state= true;
		
		return state;
	}

	private static boolean createRivista() throws FileNotFoundException
	{
		Log.LOGGER.log(Level.INFO,"---------Chiamo stored insRiviste---------\n\n");
		conn=ConnToDb.generalConnection();
			sr = new ScriptRunner(conn);
			sr.setSendFullScript(true);
			Reader reader = new BufferedReader(new FileReader("FileSql/storedInsRiviste.sql"));
			//Running the script
			sr.runScript(reader);
				state=true;
		
		
		return state;
	}

	public static boolean createUser() throws FileNotFoundException
	{
		Log.LOGGER.log(Level.INFO,"---------Chiamo stored insUtenti---------\n\n");
		
			conn=ConnToDb.generalConnection();
			sr = new ScriptRunner(conn);
			sr.setSendFullScript(true);
			Reader 	reader = new BufferedReader(new FileReader("FileSql/storedInsUtenti.sql"));
			sr.runScript(reader);
			state=true;
		
		return state;
	}

	public static boolean createNegozio() throws FileNotFoundException
	{
		Log.LOGGER.log(Level.INFO,"---------Chiamo stored insNegozi---------\n\n");
		
			conn=ConnToDb.generalConnection();
			sr = new ScriptRunner(conn);
			sr.setSendFullScript(true);
			Reader reader = new BufferedReader(new FileReader("FileSql/storedInsNegozio.sql"));
			sr.runScript(reader);
			state=true;
	return state;


	}
	

}
