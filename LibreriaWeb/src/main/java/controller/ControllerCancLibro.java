package controller;

import java.sql.SQLException;

import database.LibroDao;
import raccolta.Libro;

public class ControllerCancLibro {
	private LibroDao lD;
	private Libro l;

	public void cancella(int id) throws SQLException {
		l.setId(id);
		lD.cancella(l);

	}

	public ControllerCancLibro()
	{
		lD=new LibroDao();
		l=new Libro();
	}

}
