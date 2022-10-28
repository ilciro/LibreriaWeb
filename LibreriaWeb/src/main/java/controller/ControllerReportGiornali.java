package controller;

import java.io.IOException;
import java.sql.SQLException;

import database.GiornaleDao;

public class ControllerReportGiornali {
private GiornaleDao gd;
	
	public void generaReportGiornali () throws IOException, SQLException 
	{
		gd.generaReport();
		
	}
	
	
	public ControllerReportGiornali()
	{
		gd=new GiornaleDao();
	}

}
