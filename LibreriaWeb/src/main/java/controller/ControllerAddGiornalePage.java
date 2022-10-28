package controller;

import java.sql.SQLException;

import database.GiornaleDao;
import raccolta.Giornale;

public class ControllerAddGiornalePage {
	private GiornaleDao gD;
	private boolean status = false;


	public boolean checkData(Giornale giornale) throws SQLException 
	{
		if(giornale.getDataPubb()==null )
		{
			return status;
		}
		else
		{
			gD.creaGiornale(giornale);
			status = true;
			return status;
		}
	}

	public ControllerAddGiornalePage()
	{
		gD=new GiornaleDao();
	}

}
