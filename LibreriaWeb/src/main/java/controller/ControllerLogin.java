package controller;

import java.sql.SQLException;
import java.util.logging.Level;

import database.UsersDao;
import model.Log;
import model.User;

public class ControllerLogin {
	private  User user = User.getInstance();
	protected boolean esito;

	
	public boolean controlla(String m, String p) throws SQLException
	{
		
		esito = false;
		
			user.setEmail(m);
			user.setPassword(p);
			
			 if (UsersDao.checkUser(user) == 1)
			{
				// utente trovato
				// vai col login
				// vai con la specializzazione prendendo i dati dal dao
				
				// qui prendo il ruolo in base ala mail dell'utente
				String r =UsersDao.getRuolo(user);
				// predno e li assegno all'oggetto user
				UsersDao.pickData(user);
				Log.LOGGER.log(Level.INFO,"\n loggato come : {0}",r);
				ControllerSystemState.getIstance().setIsLogged(true);
				 esito = true;
			}
			
			return esito;

		
	}
	
	public String getRuoloTempUSer(String email) throws SQLException
	{
		String ruolo;
		user.setEmail(email);
		 ruolo= UsersDao.getRuolo(user);
		 return ruolo;
		
	}

}
