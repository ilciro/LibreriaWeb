package controller;

import java.sql.SQLException;

import database.GiornaleDao;
import javafx.collections.ObservableList;
import raccolta.Giornale;

public class ControllerGiornalePage {

	private GiornaleDao gD;
	
	public ObservableList<Giornale> getGiornaliS() throws SQLException  {
		return gD.getGiornaleSingolo();
	}
	
	
	public ControllerGiornalePage() {
		gD=new GiornaleDao();
	}

}
