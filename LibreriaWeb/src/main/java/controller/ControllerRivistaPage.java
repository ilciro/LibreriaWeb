package controller;

import java.sql.SQLException;

import database.RivistaDao;
import javafx.collections.ObservableList;
import raccolta.Rivista;

public class ControllerRivistaPage {
private RivistaDao rd;
	
	public ObservableList<Rivista> getRivistaS() throws SQLException {
		return rd.getRivistaSingolo();
	}
	
	
	public ControllerRivistaPage()
	{
		rd=new RivistaDao();
	}

}
