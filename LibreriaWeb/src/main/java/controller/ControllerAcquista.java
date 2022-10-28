package controller;



import java.sql.SQLException;

import java.util.logging.Level;

import database.GiornaleDao;
import database.LibroDao;
import database.PagamentoDao;
import database.RivistaDao;
import model.Log;
import raccolta.Giornale;
import raccolta.Libro;
import raccolta.Rivista;

public class ControllerAcquista {
	private LibroDao lD;
	private GiornaleDao gD;
	private RivistaDao rD;
	private PagamentoDao pagD;
	private Libro l;
	private Giornale g;
	private Rivista r;
	private static ControllerSystemState vis = ControllerSystemState.getIstance() ;
	private String name;
	private int disp;
	private float costo;//aggiunto per costo (vedere metodo in fondo ((getCosto()))
	int rimanenza = 0;//usato per vedee nr copie 
	private static String stringaErrore="errore nella quantita desiderata";
	private static final String LIBRO = "libro";  
	private static final String RIVISTA="rivista";
	private static final String GIORNALE="giornale";
	

	
	public float totale(String isbn, int disp) throws SQLException {
		
			lD.daiPrivilegi();
			// se semo fermati qua 
			l.setId(vis.getId());
			l.setDisponibilita(disp);
			float x = lD.getCosto(l);

			Log.LOGGER.log(Level.INFO,"isbn libro : .{0}",isbn);
			lD.aggiornaDisponibilita(l);
			lD.aggiornaCopieVendute(l,disp);
			
		
			
			

		
		return x;
	}

	public float totaleG(String titolo, int disp) throws SQLException {
		float y ;
		g.setTitolo(titolo);
		g.setDisponibilita(disp);
		gD.daiPrivilegi();
		y = gD.getCosto(g);
		gD.aggiornaDisponibilita(g);
		return y;

	}

	public float totaleR(String titolo, int disp) throws SQLException {
		float z = 0 ;
		r.setTitolo(titolo);
		r.setCopieRim(disp);
		
			rD.daiPrivilegi();
			z = rD.getCosto(r);
			rD.aggiornaDisponibilita(r);
			
		
		return z;

	}

	public ControllerAcquista()    {
		lD = new LibroDao();
		gD = new GiornaleDao();
		rD = new RivistaDao();
		l = new Libro();
		g = new Giornale();
		r = new Rivista();
		pagD = new PagamentoDao();
		
		try {
			pagD.daiPrivilegi();
		} catch (SQLException e) {
			
			Log.LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		

	}

	public int getIdL(String text) throws SQLException {
		l.setCodIsbn(text);
		return lD.retId( l);
		
		
	}
	
	public String getTipL(String text) throws SQLException
	{
		l.setId(Integer.parseInt(text));
		return lD.retTip(l);
	}
	
	public int getIdG(String text) throws SQLException  {
		g.setTitolo(text);
		return gD.retId( g);
		
		
	}
	
	public String getTipG(String text) throws SQLException 
	{
		g.setTitolo(text);	
		return gD.retTip(g);
	}
	
	public int getIdR(String text) throws SQLException {
		r.setTitolo(text);
		return rD.retId( r);
		
		
	}
	
	public String getTipR(String text) throws SQLException
	{
		r.setTitolo(text);	
		return rD.retTip(r);
	}
	
	public void inserisciAmmontareL(int i) throws SQLException
	{
		l.setId(vis.getId());
		
		
			rimanenza=lD.getQuantita(l);
		
		if(rimanenza-i<0)
		 {
			Log.LOGGER.log(Level.SEVERE,stringaErrore);

		}
		
		
	}
	
	public void inserisciAmmontareG(int i) throws SQLException 
	{
		g.setId(vis.getId());
		rimanenza=gD.getQuantita(g);
		if(rimanenza-i<0)
		
		{
			Log.LOGGER.log(Level.SEVERE,stringaErrore);
		}


			
		
	}

	public void inserisciAmmontareR(int i) throws SQLException
	{
		r.setId(vis.getId());
		rimanenza=rD.getQuantita(r);
		if(rimanenza-i<0)
		{
			Log.LOGGER.log(Level.SEVERE,stringaErrore,  new SQLException());
		}

		

	}
	
	public String getType()
	{
		
		return vis.getType();
	}

	public String getNomeById() throws SQLException
	{
		
		int id = vis.getId();
		String type =vis.getType();
		if(type.equals(LIBRO))
		{
			l.setId(id);
			name = lD.getNome(l);
		}
		else if(type.equals(GIORNALE)) {
			g.setId(id);
			name = gD.getNome(g);
		}
		else if(type.equals(RIVISTA))
		{
			r.setId(id);
			name = rD.getNome(r);
			
		}
		return name ;
	}
	
	public int getDisp() throws SQLException
	{
		int id = vis.getId();
		String type =vis.getType();
		if(type.equals(LIBRO))
		{
		
			l.setId(id);
			disp = lD.getQuantita(l);
		}
		else if(type.equals(GIORNALE)) {
			g.setId(id);
			disp = gD.getQuantita(g);
			
		}
		else if(type.equals(RIVISTA))
		{
			r.setId(id);
			disp = rD.getQuantita(r);
			
		}
		return disp ;
	}
	/*
	 * metodo aggiunto per stampare appena carica la schermata anche il costo di 
	 * ogni singolo elemento(giornale,rivista o lbro)
	 */
	 
	public float getCosto() throws SQLException
	{
		String type=vis.getType();
		int id = vis.getId();

		if(type.equalsIgnoreCase(LIBRO))
		{
			l.setId(id);
			costo=lD.getCosto(l);

		}
		else if(type.equalsIgnoreCase(GIORNALE))
		{
			g.setId(id);
			costo=gD.getCosto(g);
			
		}
		else if(type.equalsIgnoreCase(RIVISTA))
		{
			r.setId(id);
			costo=rD.getCosto(r);

		}
		return costo;

		
	}
	
	
}
