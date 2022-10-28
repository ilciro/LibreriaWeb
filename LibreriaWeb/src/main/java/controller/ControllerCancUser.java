package controller;

import java.sql.SQLException;

import database.UsersDao;
import model.TempUser;

public class ControllerCancUser {
private static TempUser u=TempUser.getInstance();
	

	public void cancellaUtente(int id) throws SQLException
	{
		u.setId(id);
		UsersDao.deleteTempUser(u);
	}

}
