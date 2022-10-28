package controller;

import java.sql.SQLException;

import database.LibroDao;
import javafx.collections.ObservableList;
import raccolta.Libro;

public class ControllerBookPage {
private LibroDao lD;
	
	public ObservableList<Libro> getLibriS() throws SQLException {
		return lD.getLibriSingolo();
	}
	
	public ControllerBookPage()
	{
		lD=new LibroDao();
	}

}
