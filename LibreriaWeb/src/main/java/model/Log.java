package model;

import java.io.IOException;
import java.util.logging.*;

public class Log {
	public static final Logger LOGGER = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
	private FileHandler fh;
	 
	
	public Log() 
	{
		LogManager.getLogManager().reset();
		LOGGER.setLevel(Level.ALL);
		
		ConsoleHandler cH = new ConsoleHandler();
		cH.setLevel(Level.SEVERE);
		
		
		
		LOGGER.addHandler(cH);
		
		
		
		
		try {
			fh = new FileHandler(Logger.GLOBAL_LOGGER_NAME,true);
		}
		catch (SecurityException |IOException e) 
		{
			LOGGER.warning("No handler file! : "+e);
		}
		LOGGER.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		
		fh.setFormatter(formatter);
		

		
		
	}



}
