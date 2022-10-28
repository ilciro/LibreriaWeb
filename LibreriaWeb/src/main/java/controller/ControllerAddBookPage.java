package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import database.LibroDao;
import raccolta.Libro;

public class ControllerAddBookPage {
	private LibroDao ld;
	private Libro l;
	private boolean status = false;
	
	//funzione di aggiunta dei libri
	//e verifica dei dati inseriti 
	
	public boolean checkData(String[] info,String recensione,String descrizione,LocalDate data,String[] infoCosti) throws SQLException
	{

		
		if( infoCosti[1].length()>10 || data==null )
		{
			return status;

		}
		else
		{
		l.setTitolo(info[0]);
		l.setNumeroPagine(Integer.parseInt(infoCosti[0]));
		l.setCodIsbn(infoCosti[1]);
		l.setEditore(info[4]);
		l.setAutore(info[2]);
		l.setLingua(info[3]);
		l.setCategoria(info[5]);
		l.setDataPubb(data);
		l.setRecensione(recensione);
		l.setDesc(descrizione);
		l.setDisponibilita(Integer.parseInt(infoCosti[3]));
		l.setPrezzo(Float.parseFloat(infoCosti[4]));
		l.setCopieRim(Integer.parseInt(infoCosti[5]));
		
		
		ld.creaLibrio(l);
		status = true;
		
		return status;
		}
	}
	// qui chiamo la funzione del dao
	
	public ControllerAddBookPage()
	{
		ld=new LibroDao();
		l=new Libro();
	}


}
