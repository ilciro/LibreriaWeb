package database;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	private  PreparedStatement prepQ =null; 
	private  Connection conn =null;
	private  int q; // quantita'
	private  ResultSet rs=null;
	private Statement stmt=null;
	private boolean state=false;
	private int disp=0;
	private int id=0;
	private ControllerSystemState vis=ControllerSystemState.getIstance();

	private static final String LIBRO = "libro";


	public float getCosto(Libro l) throws SQLException
	{
		float prezzo=(float) 0.0;
		
			conn = ConnToDb.generalConnection();

			stmt = conn.createStatement();

			rs = stmt.executeQuery("select * from libro where idProd ='"+l.getId()+"'");
			while ( rs.next() ) {
				prezzo=rs.getFloat("prezzo");
			}
		conn.close();
		return prezzo;

	}

	public void aggiornaDisponibilita(Libro l) throws SQLException
	{
		int d=vis.getQuantita();
		int i=getQuantita(l);
		
		int rim=i-d;
		
		
			conn = ConnToDb.generalConnection();
			prepQ=conn.prepareStatement("update ispw.libro set copieRimanenti= ? where Cod_isbn='"+l.getCodIsbn()+"'or idProd='"+l.getId()+"'");
			prepQ.setInt(1,rim);			
			prepQ.executeUpdate();
		conn.close();
		


	}

	public void daiPrivilegi() throws SQLException
	{

		
			conn = ConnToDb.generalConnection();
			stmt = conn.prepareStatement("SET SQL_SAFE_UPDATES=0");
		
			conn.close();
			

	}
	

	public ObservableList<Raccolta> getLibri() throws SQLException
	{
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();

		
		conn= ConnToDb.generalConnection();
		stmt=conn.createStatement();
		rs=stmt.executeQuery("SELECT * FROM libro");
		while(rs.next())
		{
			
				
					f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
					f.createRaccoltaFinale2(LIBRO,rs.getInt(2),rs.getString(3),rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));
					catalogo.add(f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));
					
				
			
		}
		
		

		return catalogo;
	}

	public ObservableList<Raccolta> getLibriByName(String s) throws SQLException
	{
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		
		
		conn= ConnToDb.generalConnection();
		String libroSE="SELECT * FROM libro "
				+"where titolo = '"+s
				+"' OR autore = '"+s+"'";

		stmt=conn.createStatement();
		rs=stmt.executeQuery(libroSE);
		while(rs.next())
		{
			
				f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
				f.createRaccoltaFinale2(LIBRO,rs.getInt(2),rs.getString(3),rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));
				catalogo.add(f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));
			
				
			
		}
		
		conn.close();
		Log.LOGGER.log(Level.INFO,"\n Catalogo :{0}",catalogo);
		return catalogo;

	}

	public Libro getLibro(Libro l,int id) throws SQLException
	{
		conn= ConnToDb.generalConnection();
		stmt=conn.createStatement();
		String libroId="SELECT * FROM libro"
				+"where idProd = '"+id+"'";
		rs=stmt.executeQuery(libroId);
		if (rs.next())
		{
			f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
			
			
			f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

			l=(Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15));
		
			
		}
		
		return l;

	}

	public LibroDao()
	{
		f=new Factory();
	}

	public int retId(Libro l) throws SQLException {
		conn = ConnToDb.generalConnection();
		
			stmt = conn.createStatement();

			rs = stmt.executeQuery("select idProd from libro where Cod_isbn ='"+l.getCodIsbn()+"'");
			while ( rs.next() ) {
				id=rs.getInt("idProd");
			}
			conn.close();
		return id;



	}
	

	public String retTip(Libro l) throws SQLException {

		String categoria=l.getCategoria();
		
			conn = ConnToDb.generalConnection();

			stmt = conn.createStatement();
			//add or id per matchare nel ControlelrPagamento

			rs = stmt.executeQuery("select categoria from libro where Cod_isbn ='"+l.getCodIsbn()+"' or idProd='"+l.getId()+"' ");
			while ( rs.next() ) {
				categoria=rs.getString("categoria");

			}
		conn.close();
		return categoria;


	}

	public void aggiornaCopieVendute(Libro l,int n) throws SQLException
	{

		
			conn = ConnToDb.generalConnection();
			stmt = conn.prepareStatement("update libro set copieVendute=copievendute+'"+n+"' where Cod_isbn='"+l.getCodIsbn()+"'");


		conn.close();


	}

	// Creo il libro nel terzo caso d'uso per l'aggiunta manuale
	public boolean creaLibrio(Libro l) throws SQLException
	{



		
				conn = ConnToDb.generalConnection();
				
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
				prepQ = conn.prepareStatement(query);	
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
			
			
		conn.close();

		return state;


	}

	// uso questa funzione quando clicco sul pulsante acquista dopo aver
	//inserito la quantita da acquistare
	public int getDisp(Libro l) throws SQLException
	{
					
				conn = ConnToDb.generalConnection();
				stmt=conn.createStatement();

				

				rs=  stmt.executeQuery(
						"SELECT disp FROM ispw.libro where idProd='"+l.getId()+"'");
				if(rs.next())
				{
				disp = rs.getInt(1);
				if (disp >= 1)
					disp=1;
				else
				{
					disp=-1;
				}
				conn.close();
			
				}
		return disp;
	}

	public int getQuantita(Libro l) throws SQLException
	{
		
			
				conn = ConnToDb.generalConnection();
				stmt = conn.createStatement();
				
				rs=  stmt.executeQuery(	"SELECT copieRimanenti FROM `ispw`.`libro` where `idProd` = '"+l.getId()+"' ");
				if (rs.next()) {
					q = rs.getInt(1);
				}

			
		

		


		return q;
	}

	// Uso questo pulseante quando clicco sul pulsante mostra libro 
	public boolean checkDisp(int id) throws SQLException
	{
		
				conn = ConnToDb.generalConnection();
				stmt = conn.createStatement();

				String lDisp="SELECT disp FROM ispw.libro"
						+"where idProd = '"+id+"'";

				rs=  stmt.executeQuery(lDisp);
				if(rs.next())
				{
					disp = rs.getInt(1);
					if (disp == 1)
						state=true;
					
				
					Log.LOGGER.log(Level.INFO, "libro trovato");
				}
				

		return state;
	}

	//fare singoli get dal db con associazione alle funzioni 
	//o fare associazioni dal contoller
	public String getNome(Libro l) throws SQLException
	{
		String name=null;
		conn= ConnToDb.generalConnection();
		stmt=conn.createStatement();
		rs=stmt.executeQuery("SELECT titolo FROM ispw.libro where idProd = '"+l.getId()+"' ");
		if (rs.next())
		{
			name = rs.getString(1);
		}
		
		conn.close();
		return name;
	}

	public ObservableList<Libro> getLibriSingolo() throws SQLException
	{
		conn= ConnToDb.generalConnection();
		ObservableList<Libro> catalogo=FXCollections.observableArrayList();
		stmt=conn.createStatement();
		rs=stmt.executeQuery("SELECT * FROM libro");



		while(rs.next())
		{

			
				f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
				
				
				f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

				catalogo.add((Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));

				

		}

		Log.LOGGER.log(Level.INFO,"{0}",catalogo);
		return catalogo;

	}

	public void cancella(Libro l) throws SQLException {
		
			int row=0;
				conn = ConnToDb.generalConnection();
				
				prepQ=conn.prepareStatement("delete  FROM ispw.libro where idProd = "+l.getId()+" ;");
				row=prepQ.executeUpdate();
			
		conn.close();

		Log.LOGGER.log(Level.INFO,"Libro cancellato : .{0}",row);
	}

	public ObservableList<Libro> getLibriSingoloById(Libro l) throws SQLException
	{
		conn= ConnToDb.generalConnection();
		ObservableList<Libro> catalogo=FXCollections.observableArrayList();
		stmt=conn.createStatement();
		rs=stmt.executeQuery("SELECT * FROM libro where idProd="+l.getId()+"");

		while(rs.next())
		{

			
					f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
				
				
					f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

					catalogo.add((Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));

				

		}

		Log.LOGGER.log(Level.INFO,"{0}",catalogo);
		return catalogo;

	}

	public void aggiornaLibro(Libro l) throws SQLException,NullPointerException
	{


		int rowAffected=0;

		conn = ConnToDb.generalConnection();
		stmt=conn.createStatement();


		

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
				+ " WHERE `idProd` ="+l.getId()+";";
		prepQ=conn.prepareStatement(query);

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


		rowAffected = prepQ.executeUpdate();
		prepQ.close();

		Log.LOGGER.log(Level.INFO, "row affected .{0}", rowAffected);

	}	

	public void generaReport() throws SQLException, IOException
	{
		FileWriter w=null;
		w=new FileWriter("ReportFinale\\riepilogoLibro.txt");
		
		   try (BufferedWriter b=new BufferedWriter (w)){
		

		
		
			conn = ConnToDb.generalConnection();
			stmt=conn.createStatement();
			


			stmt=conn.createStatement();
			rs=stmt.executeQuery("select titolo,copieVendute,prezzo as totale  from libro;");


			while(rs.next())
			{



				rs.getString(1);
				rs.getInt(2);
				rs.getFloat(3);


				b.write("Titolo :"+rs.getString(1)+"\t"+"Ricavo totale :" +rs.getInt(2)*rs.getFloat(3)+"\n");




				b.flush();


			}

		}
		   conn.close();
		

	}
	
	public void incrementaDisponibilita(Libro l) throws SQLException
	{
		int d=vis.getQuantita();
		int i=getQuantita(l);
		
		int rim=i+d;
		
		
			conn = ConnToDb.generalConnection();
			prepQ=conn.prepareStatement("update ispw.libro set copieRimanenti= ? where Cod_isbn='"+l.getCodIsbn()+"'or idProd='"+l.getId()+"'");
			prepQ.setInt(1,rim);			
			prepQ.executeUpdate();
		conn.close();
		
		

	}
	
	public String getTitolo(Libro l) throws SQLException
	{
		String titolo="";
			
				conn = ConnToDb.generalConnection();
				stmt = conn.createStatement();
				
				rs=  stmt.executeQuery(	"SELECT titolo from ispw.libro where `idProd` = '"+l.getId()+"' ");
				if (rs.next()) {
					titolo = rs.getString("titolo");
				}


		
		return titolo;
	}
	
	public List<Libro> getLibriSingoloList() throws SQLException
	{
		conn= ConnToDb.generalConnection();
		List<Libro> catalogo=new ArrayList<>();
		stmt=conn.createStatement();
		rs=stmt.executeQuery("SELECT * FROM ispw.libro");



		while(rs.next())
		{

			
				f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
				
				
				f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

				catalogo.add((Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));

				

		}

		Log.LOGGER.log(Level.INFO,"{0}",catalogo);
		return catalogo;

	}
	
	public List<Libro> getLibriSingoloByIdLista(Libro l) throws SQLException
	{
		conn= ConnToDb.generalConnection();
		List<Libro> catalogo=new ArrayList<>();
		stmt=conn.createStatement();
		rs=stmt.executeQuery("SELECT * FROM libro where idProd="+l.getId()+"");

		while(rs.next())
		{

			
					f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
				
				
					f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

					catalogo.add((Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));

				

		}
		Log.LOGGER.log(Level.INFO,"{0}",catalogo);


		return catalogo;

	}


}