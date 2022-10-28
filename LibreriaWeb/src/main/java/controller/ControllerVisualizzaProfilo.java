package controller;

import java.sql.SQLException;
import java.util.logging.Level;

import database.UsersDao;
import model.Log;
import model.User;

public class ControllerVisualizzaProfilo {
	private boolean status=false;

	public User getCredenziali() throws SQLException {

		return UsersDao.pickData(User.getInstance());

	}

	public ControllerVisualizzaProfilo()
	{
		Log.LOGGER.log(Level.INFO,"ControllerVisualizzaProfilo");
	}

	public boolean cancellaUtente() throws SQLException {
		
		
		if(UsersDao.deleteUser(User.getInstance()))
		{
			status=true;
		}
		
 
		return status;

	}
}
