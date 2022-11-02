package database;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import controller.ControllerSystemState;
import model.Log;
import raccolta.Factory;
import raccolta.Libro;
import raccolta.Raccolta;
import utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LibroDao  {
	private Factory f;
	private  String query ;	
	private  int q; // quantita'
	private boolean state=false;
	private int disp=0;
	private int id=0;
	private ControllerSystemState vis=ControllerSystemState.getIstance();
	private static final String LIBRO = "libro";
	private static String libroTotale="SELECT * FROM libro";
	private static String eccezione="eccezione ottenuta:";


	public float getCosto(Libro l) throws SQLException
	{
		float prezzo=(float) 0.0;
		
			query="select * from libro where idProd =?";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
				{
					prepQ.setInt(1, l.getId());
					while ( rs.next() ) {
						prezzo=rs.getFloat("prezzo");
					}
				}catch(SQLException e)
			{
					Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
			}
		
		return prezzo;

	}

	public void aggiornaDisponibilita(Libro l) throws SQLException
	{
		int d=vis.getQuantita();
		int i=getQuantita(l);
		
		int rim=i-d;
		
		query="update ispw.libro set copieRimanenti= ? where Cod_isbn=? or idProd=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				)
			{
			prepQ.setInt(1,rim);	
			prepQ.setString(2, l.getCodIsbn());
			prepQ.setInt(3, l.getId());
			prepQ.executeUpdate();
			}catch(SQLException e)
		{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}

		


	}

	public void daiPrivilegi() throws SQLException
	{

		
			query="SET SQL_SAFE_UPDATES=?";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					)
				{
				prepQ.setInt(1, 0);
				prepQ.execute();
				}catch(SQLException e)
				{
					Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
				}
			
			

	}
	

	public ObservableList<Raccolta> getLibri() throws SQLException
	{
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();

		query=libroTotale;
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
			{
		while(rs.next())
		{
			
				
					f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
					f.createRaccoltaFinale2(LIBRO,rs.getInt(2),rs.getString(3),rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));
					catalogo.add(f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));
					
				
			
		}
			}catch(SQLException e)
			{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
			}
		
		

		return catalogo;
	}

	public ObservableList<Raccolta> getLibriByName(String s) throws SQLException
	{
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		
		query="SELECT * FROM libro "
				+"where titolo =?"
				+" OR autore = ?";

		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
			{prepQ.setString(1,s);
			
			prepQ.setString(2,s);
		while(rs.next())
		{
			
				f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
				f.createRaccoltaFinale2(LIBRO,rs.getInt(2),rs.getString(3),rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));
				catalogo.add(f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));
			
				
			
		}
			}catch(SQLException e)
		{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		
		return catalogo;

	}

	public Libro getLibro(Libro l,int id) throws SQLException
	{
		query=libroTotale +"where idProd = ?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
			{prepQ.setInt(1, id);
		if (rs.next())
		{
			f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
			
			
			f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

			l=(Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15));
		
			
		}
			}catch(SQLException e)
		{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		
		return l;

	}

	public LibroDao()
	{
		f=new Factory();
	}

	public int retId(Libro l) throws SQLException {
		query="select idProd from libro where Cod_isbn =?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
			{prepQ.setString(1, l.getCodIsbn());
			while ( rs.next() ) {
				id=rs.getInt("idProd");
			}
			}catch(SQLException e)
		{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
			
		return id;



	}
	

	public String retTip(Libro l) throws SQLException {

		String categoria=null;
		
			query="select categoria from libro where Cod_isbn =? or idProd=? ";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
				{prepQ.setString(1,l.getCodIsbn());
				prepQ.setInt(2, l.getId());
				
			while ( rs.next() ) {
				categoria=rs.getString("categoria");

			}
				}catch(SQLException e)
			{
					Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
			}
			return categoria;


	}

	public void aggiornaCopieVendute(Libro l,int n) throws SQLException
	{

		
			query="update libro set copieVendute=copievendute+? where Cod_isbn=?";
			
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
			{
				prepQ.setInt(1, n);
				prepQ.setString(2, l.getCodIsbn());
				prepQ.execute();
			}catch(SQLException e)
	{
		Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
	}

		


	}

	// Creo il libro nel terzo caso d'uso per l'aggiunta manuale
	public boolean creaLibrio(Libro l) throws SQLException
	{

				query= "INSERT INTO `ispw`.`libro`"
						+ "(`titolo`,"
						+ "`numeroPagine`,"
						+ "`Cod_isbn`,"
						+ "`editore`,"
						+ "`autore`,"
						+ "`lingua`,"
						+ "`categoria`,"
						+ "`dataPubblicazione`,"
						+ "`recensione`,"
						+ "`breveDescrizione`,"
						+ "`disp`,"
						+ "`prezzo`,"
						+ "`copieRimanenti`)"
						+ "VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
				try(Connection conn=ConnToDb.generalConnection();
						PreparedStatement prepQ=conn.prepareStatement(query);
						)
				{
				
				prepQ.setString(1,l.getTitolo()); 
				prepQ.setInt(2,l.getNumeroPagine());
				prepQ.setString(3,l.getCodIsbn());
				prepQ.setString(4,l.getEditore());
				prepQ.setString(5,l.getAutore());
				prepQ.setString(6,l.getLingua());
				prepQ.setString(7,l.getCategoria());
				prepQ.setDate(8, java.sql.Date.valueOf(l.getDataPubb().toString()));  
				prepQ.setString(9, l.getRecensione());
				prepQ.setString(10, l.getDesc());
				prepQ.setInt(11, l.getDisponibilita());
				prepQ.setFloat(12, l.getPrezzo());
				prepQ.setInt(13,l.getCopieRim());
				prepQ.executeUpdate();
				state= true; // true	
				}catch(SQLException e)
				{
					Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
				}
			
			

		return state;


	}

	// uso questa funzione quando clicco sul pulsante acquista dopo aver
	//inserito la quantita da acquistare
	public int getDisp(Libro l) throws SQLException
	{
				query="SELECT disp FROM ispw.libro where idProd=?";
				try(Connection conn=ConnToDb.generalConnection();
						PreparedStatement prepQ=conn.prepareStatement(query);
						ResultSet rs=prepQ.executeQuery())
				{
					prepQ.setInt(1, l.getId());
				if(rs.next())
				{
				disp = rs.getInt(1);
				if (disp >= 1)
					disp=1;
				else
				{
					disp=-1;
				}
				}
				}catch(SQLException e)
				{
					Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
				}
			
				
		return disp;
	}

	public int getQuantita(Libro l) throws SQLException
	{
		
			
				query="SELECT copieRimanenti FROM `ispw`.`libro` where `idProd` =?";
				try(Connection conn=ConnToDb.generalConnection();
						PreparedStatement prepQ=conn.prepareStatement(query);
						ResultSet rs=prepQ.executeQuery())
				{prepQ.setInt(1, l.getId());
					if (rs.next()) {
						q = rs.getInt(1);
					}
				}catch(SQLException e)
				{
					Log.LOGGER.log(Level.SEVERE,eccezione,e);
				}
		
		return q;
	}

	// Uso questo pulseante quando clicco sul pulsante mostra libro 
	public boolean checkDisp(int id) throws SQLException
	{
		
				query="SELECT disp FROM ispw.libro where idProd = ?";
				try(Connection conn=ConnToDb.generalConnection();
						PreparedStatement prepQ=conn.prepareStatement(query);
						ResultSet rs=prepQ.executeQuery())
				{

				prepQ.setInt(1, id);
				
				if(rs.next())
				{
					disp = rs.getInt(1);
					if (disp == 1)
						state=true;
					
				
					Log.LOGGER.log(Level.INFO, "libro trovato");
				}
				}catch(SQLException e)
				{Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
				
				}
				

		return state;
	}

	//fare singoli get dal db con associazione alle funzioni 
	//o fare associazioni dal contoller
	public String getNome(Libro l) throws SQLException
	{
		String name=null;
		query="SELECT titolo FROM ispw.libro where idProd = ?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{prepQ.setInt(1, l.getId());
		if (rs.next())
		{
			name = rs.getString(1);
		}
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		
		
		return name;
	}

	public ObservableList<Libro> getLibriSingolo() throws SQLException
	{
		query=libroTotale;
		ObservableList<Libro>catalogo=FXCollections.observableArrayList();
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{



		while(rs.next())
		{

			
				f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
				
				
				f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

				catalogo.add((Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));

				

		}
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}

		return catalogo;

	}

	public void cancella(Libro l) throws SQLException {
		
			int row=0;
				query="delete  FROM ispw.libro where idProd = ?";
				try(Connection conn=ConnToDb.generalConnection();
						PreparedStatement prepQ=conn.prepareStatement(query);
						)
				{prepQ.setInt(1, l.getId());
				row=prepQ.executeUpdate();
				}catch(SQLException e)
				{
					Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
				}
			
		Log.LOGGER.log(Level.INFO,"Libro cancellato : .{0}",row);
	}

	public ObservableList<Libro> getLibriSingoloById(Libro l) throws SQLException
	{
		
		ObservableList<Libro> catalogo=FXCollections.observableArrayList();
		query="SELECT * FROM libro where idProd=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
			prepQ.setInt(1,l.getId());
		while(rs.next())
		{

			
					f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
				
				
					f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

					catalogo.add((Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));

				

		}
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}

		return catalogo;

	}

	public void aggiornaLibro(Libro l) throws SQLException,NullPointerException
	{


		int rowAffected=0;

		query=" UPDATE libro "
				+ "SET "
				+ " `titolo` =?,"
				+ " `numeroPagine` = ?,"
				+ " `Cod_isbn` = ?,"
				+ " `editore` = ?,"
				+ " `autore` = ?,"
				+ " `lingua` = ?,"
				+ " `categoria` = ?,"
				+ " `dataPubblicazione` = ?,"
				+ " `recensione` = ?,"
				+ " `copieVendute` = ?,"
				+ " `breveDescrizione` =?,"
				+ " `disp` = ?,"
				+ " `prezzo` = ?,"
				+ " `copieRimanenti` =?"
				+ " WHERE `idProd` =?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				)
		{
		prepQ.setString(1,l.getTitolo());
		prepQ.setInt(2,l.getNumeroPagine());
		prepQ.setString(3,l.getCodIsbn());
		prepQ.setString(4,l.getEditore());
		prepQ.setString(5,l.getAutore());
		prepQ.setString(6,l.getLingua());
		prepQ.setString(7,l.getCategoria());
		prepQ.setString(8, l.getDataPubb().toString());
		prepQ.setString(9,l.getRecensione());
		prepQ.setInt(10,l.getNrCopie());
		prepQ.setString(11,l.getDesc());
		prepQ.setInt(12,l.getDisponibilita());
		prepQ.setFloat(13,l.getPrezzo());
		prepQ.setInt(14,l.getCopieRim());
		prepQ.setInt(15, l.getId());
	

		rowAffected = prepQ.executeUpdate();
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		

		Log.LOGGER.log(Level.INFO, "row affected .{0}", rowAffected);

	}	

	public void generaReport() throws SQLException, IOException
	{
		FileWriter w=null;
		w=new FileWriter("ReportFinale\\riepilogoLibro.txt");
		
		   try (BufferedWriter b=new BufferedWriter (w)){
			   query="select titolo,copieVendute,prezzo as totale  from libro";
			   try(Connection conn=ConnToDb.generalConnection();
						PreparedStatement prepQ=conn.prepareStatement(query);
						ResultSet rs=prepQ.executeQuery())
			   {


			while(rs.next())
			{



				rs.getString(1);
				rs.getInt(2);
				rs.getFloat(3);


				b.write("Titolo :"+rs.getString(1)+"\t"+"Ricavo totale :" +rs.getInt(2)*rs.getFloat(3)+"\n");




				b.flush();


			}

		}catch(SQLException e)
			   {
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
			   }
		   }

	}
	
	public void incrementaDisponibilita(Libro l) throws SQLException
	{
		int d=vis.getQuantita();
		int i=getQuantita(l);
		
		int rim=i+d;
		
		
			query="update ispw.libro set copieRimanenti= ? where Cod_isbn=? or idProd=?";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					)
			{
			prepQ.setInt(1,rim);
			prepQ.setString(2, l.getCodIsbn());
			prepQ.setInt(3, l.getId());
			prepQ.executeUpdate();
			}catch(SQLException e)
			{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
			}
	
	}
	
	public String getTitolo(Libro l) throws SQLException
	{
		String titolo=null;
			
					query="SELECT titolo from ispw.libro where `idProd` = ?";
					try(Connection conn=ConnToDb.generalConnection();
							PreparedStatement prepQ=conn.prepareStatement(query);
							ResultSet rs=prepQ.executeQuery())
					{
						
					prepQ.setInt(1, l.getId());
				if (rs.next()) {
					titolo = rs.getString("titolo");
				}
					}catch(SQLException e)
					{
						Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
					}
	

		
		return titolo;
	}
	
	public List<Libro> getLibriSingoloList() throws SQLException
	{
		
		List<Libro> catalogo=new ArrayList<>();
		query=libroTotale;
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{



		while(rs.next())
		{

			
				f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
				
				
				f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

				catalogo.add((Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));

				

		}
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}

		
		return catalogo;

	}
	
	public List<Libro> getLibriSingoloByIdLista(Libro l) throws SQLException
	{
		
		List<Libro> catalogo=new ArrayList<>();
		query="SELECT * FROM libro where idProd=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
			prepQ.setInt(1, l.getId());
		while(rs.next())
		{

			
					f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
				
				
					f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

					catalogo.add((Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));

				

		}
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		
		return catalogo;

	}


}