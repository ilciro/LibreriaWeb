package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import database.RivistaDao;
import raccolta.Rivista;

public class ControllerAddRivistaPage {
	private RivistaDao rd;
	private Rivista r;
	private boolean status = false; 
	
	
	public boolean checkData(String [] info,  LocalDate data,
			int dispo, float prezzo, int copie, String desc) throws  SQLException {
		
		

		
		if(data!=null )
		{
			
		r.setTitolo(info[0]);
		r.setTipologia(info[1]);
		r.setAutore(info[2]);
		r.setLingua(info[3]);
		r.setEditore(info[4]);
		r.setDescrizione(desc);
		r.setDataPubb(data);
		r.setDisp(dispo);
		r.setPrezzo(prezzo);
		r.setCopieRim(copie);

		rd.creaRivista(r);
		
		status = true ;
		}
		
		return status;
	}
	
	public ControllerAddRivistaPage()
	{
		rd=new RivistaDao();
		r=new Rivista();
	}

}
