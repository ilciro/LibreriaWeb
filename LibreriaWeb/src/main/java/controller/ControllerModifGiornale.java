package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import database.GiornaleDao;
import javafx.collections.ObservableList;
import raccolta.Giornale;

public class ControllerModifGiornale {
	private GiornaleDao gd;
	private Giornale g;
	
	public ObservableList<Giornale> getGiornaliById(int id) throws SQLException   {
		g.setId(id);
		return gd.getGiornaliSingoloById(g);
	}
	
		
		public void checkData(String[] info, LocalDate d, int dispo, float prezzo,
				int copie) throws SQLException  {
			

			g.setTitolo(info[0]);
			g.setTipologia(info[1]);
			g.setEditore(info[2]);
			g.setLingua(info[3]);
			g.setDataPubb(d);
			g.setDisponibilita(dispo);
			g.setPrezzo(prezzo);
			g.setCopieRimanenti(copie);
			
						gd.aggiornaGiornale(g);
			
		}
		
		public ControllerModifGiornale()
		{
			gd=new GiornaleDao();
			g=new Giornale();
		}
	


}
