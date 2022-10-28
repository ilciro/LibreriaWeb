package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import database.UsersDao;
import model.Log;

public class ControllerUserPage {
	public void getUtenti() throws IOException, SQLException  {
		 UsersDao.getListaUtenti();
	}
	
	public ControllerUserPage()
	{
		Log.LOGGER.log(Level.INFO,"ControllerUserPage");
	}
	

}
