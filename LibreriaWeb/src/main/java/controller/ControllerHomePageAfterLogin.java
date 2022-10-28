package controller;

import java.util.logging.Level;

import exceptions.LogoutException;
import model.Log;
import model.User;

public class ControllerHomePageAfterLogin {
	private static User u = User.getInstance();
	private static ControllerSystemState vis = ControllerSystemState.getIstance() ;

	// qui ci va la funzione di logout
	
	public static boolean logout() throws LogoutException 
	{	
		
		String n = u.getNome();
		Log.LOGGER.log(Level.INFO,"Stai sloggando con il nome di : {0}", n );
		
		if (n==null)
		{
			throw new LogoutException("Errore Logout");

		}
		else {
		u.setId(-1);
		u.setNome(null);
		u.setCognome(null);
		u.setDataDiNascita(null);
		u.setDescrizione(null);
		u.setEmail(null);
		u.setPassword(null);
		
		
			Log.LOGGER.log(Level.INFO,"Logout  utente {0}", u.getEmail());
			vis.setIsLogged(false);
			return true;
		}

	}
	private ControllerHomePageAfterLogin()
	{
		
	}

}
