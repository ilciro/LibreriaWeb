package controller;

import java.sql.SQLException;

import database.LibroDao;
import raccolta.Libro;

public class ControllerVisualizzaLibro {
	private LibroDao ld;
	private Libro b;
	private ControllerSystemState vis = ControllerSystemState.getIstance() ;
	
	public ControllerVisualizzaLibro()
	{
		ld = new LibroDao();
	}
	public void setID(String i)
	{		 int tempIdLib;
	
		tempIdLib = Integer.parseInt(i) ;
		vis.setId(tempIdLib);
	}
	public int getID()
	{
		return vis.getId();
	}
	public Libro getData(int i) throws SQLException
	{
		// imposto che e' un libro nel controller
		vis.setTypeAsBook();
		return ld.getLibro(b,i);
	}

}
