package controller;

import java.sql.SQLException;

import database.GiornaleDao;
import javafx.collections.ObservableList;
import model.User;
import raccolta.Giornale;
import raccolta.Raccolta;

public class ControllerCompravenditaGiornali {
	private GiornaleDao gD;
	private Giornale g;
	private User u=User.getInstance();
	

	public ControllerCompravenditaGiornali() {
		gD = new GiornaleDao();
		g = new Giornale();

	}

	public ObservableList<Raccolta> getGiornali() throws SQLException  {
		
		return gD.getGiornali();

	}

	public boolean disponibilitaGiornale(String i ) throws SQLException  {
		int id=0;
		id= Integer.parseInt(i);
		return gD.checkDisp(g,id);
	}
	
	public void setTipoUser(String ruolo)
	{
		u.setIdRuolo(ruolo);
	}

	public String retTipoUser()
	{
		return u.getIdRuolo();
	}
	/*
	 * Resource	Date	Description
ControllerCompravenditaGiornali.java	6 days ago	Define a constant instead of duplicating this literal "SCRITTORE" 4 times. [+4 locations]
	 */


}
