package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import database.RivistaDao;
import javafx.collections.ObservableList;
import raccolta.Rivista;

public class ControllerModifRivista {

	private Rivista r;
	private RivistaDao rd;
	
	
	public ObservableList<Rivista> getRivistaById(int id) throws SQLException {
		r.setId(id);
		return rd.getRivistaSingoloById(r);
	}
	
	public ControllerModifRivista()
	{ 
		r=new Rivista();
		rd=new RivistaDao();
	}

	public void checkData(String [] info, LocalDate d,
			int dispo, float prezzo, int copie, int id, String desc) throws SQLException {
		
		r.setTitolo(info[0]);
		r.setTipologia(info[1]);
		r.setAutore(info[2]);
		r.setLingua(info[3]);
		r.setEditore(info[4]);
		r.setDescrizione(desc);
		r.setDataPubb(d);
		r.setDisp(dispo);
		r.setPrezzo(prezzo);
		r.setCopieRim(copie);
		r.setId(id);
		
		rd.aggiornaRivista(r);
		
		
	}

}
