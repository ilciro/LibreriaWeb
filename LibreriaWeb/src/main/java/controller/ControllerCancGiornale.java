package controller;

import java.sql.SQLException;

import database.GiornaleDao;
import raccolta.Giornale;

public class ControllerCancGiornale {
	private Giornale g;
	private GiornaleDao gD;
	
	public void cancella(int id) throws SQLException {
		g.setId(id);
		gD.cancella(g);

	}
	
	
	
	public ControllerCancGiornale()
	{
		g=new Giornale();
		gD=new GiornaleDao();
	}

}
