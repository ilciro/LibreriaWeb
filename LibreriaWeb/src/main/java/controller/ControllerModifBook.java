package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import database.LibroDao;
import javafx.collections.ObservableList;
import raccolta.Libro;

public class ControllerModifBook {
	private LibroDao ld;
	private Libro l;
	
	
	public ObservableList<Libro> getLibriById(int id) throws SQLException {
		l.setId(id);
		return ld.getLibriSingoloById(l);
	}
	
	
	
	public ControllerModifBook()
	{
		ld=new LibroDao();
		l=new Libro();
	}
	
	
	public void checkData(String []info,String recensione,String descrizione,LocalDate data,String[] infoCosti) throws NullPointerException, SQLException 
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
		
		
		ld.aggiornaLibro(l);
	}
	
	


}
