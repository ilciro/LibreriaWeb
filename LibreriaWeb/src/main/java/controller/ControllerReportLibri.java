package controller;

import java.io.IOException;
import java.sql.SQLException;

import database.LibroDao;

public class ControllerReportLibri {
private LibroDao ld;
	
	public void generaReportLibri () throws IOException, SQLException
	{
		ld.generaReport();
		
	}
	
	public ControllerReportLibri()
	{
		ld=new LibroDao();
	}
	

}
