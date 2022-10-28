package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;

import database.UsersDao;
import model.Log;
import model.TempUser;

public class ControllerModifUser {
private static TempUser uT=TempUser.getInstance();
	
	
	
	public ControllerModifUser()
	{
		Log.LOGGER.log(Level.INFO,"ControllerModifUserPage");
	}



	public TempUser prendiLista(int id) throws SQLException  {
		
		uT.setId(id);
		
		return UsersDao.getTempUserSingolo(uT);
		
		
	}
	
	public int  prendiIdMax() throws SQLException 
	{
		return UsersDao.maxIdUSer();
	}



	public void aggiornaUtenteByAdmin(String n, String c, String e, String p, String d, LocalDate data, String r) throws NullPointerException, SQLException {
		
		uT.setNome(n);
		uT.setCognome(c);
		uT.setEmail(e);
		uT.setPassword(p);
		uT.setDescrizione(d);
		uT.setDataDiNascita(data);
		TempUser.getInstance().setIdRuolo(r);
		

		
		UsersDao.aggiornaUtenteTemp(uT);
		
		
	}
	

}
