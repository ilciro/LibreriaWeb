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
import raccolta.Giornale;
import raccolta.Raccolta;
import utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GiornaleDao {
	private Factory f;
	private  Statement stmt = null ;
	private  Connection conn=null;
	private  ResultSet rs=null;


	private  String query  ;
	private  PreparedStatement prepQ = null; 
	private  int q =0; 
	private String categoria;
	private int disp=0;
	private int id=0;

	private boolean state=false;
	private ControllerSystemState vis=ControllerSystemState.getIstance();
	private static final String GIORNALE = "giornale";






/*

	public void getDesc(Giornale g) throws SQLException 
	{	  




		
			conn = ConnToDb.generalConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery("select * from giornale where titolo ='"+g.getTitolo()+"'");


			while ( rs.next() ) {
				rs.getString("titolo");
				rs.getString("tipologia");
				rs.getString("lingua");	       
				rs.getString("editore");
				rs.getDate("dataPubblicazione");	
				rs.getInt("copiRim");	
				rs.getInt("disp");

				rs.getFloat("prezzo");


			}
		conn.close();


	}
*/

	public float getCosto(Giornale g) throws SQLException  
	{		

		float prezzo=(float) 0.0;
		conn = ConnToDb.generalConnection();
		
			stmt = conn.createStatement();

			rs = stmt.executeQuery("select * from giornale where id ='"+g.getId()+"'");
			while ( rs.next() ) {
				prezzo=rs.getFloat("prezzo");
			}
		
		
			conn.close();
		
		return prezzo;


		
	}

	public  void aggiornaDisponibilita(Giornale g) throws SQLException 
	{

		int d=vis.getQuantita();
		int i=getQuantita(g);
		
		int rim=i-d;
		

		
			conn = ConnToDb.generalConnection();
			prepQ=conn.prepareStatement("update ispw.giornale set copiRim= ? where titolo='"+g.getTitolo()+"'or id='"+g.getId()+"'");
			prepQ.setInt(1,rim);			
			prepQ.executeUpdate();



	}

	public   void daiPrivilegi() throws  SQLException 
	{


		
			conn = ConnToDb.generalConnection();
			prepQ = conn.prepareStatement(" SET SQL_SAFE_UPDATES=0");
			prepQ.executeUpdate();


		



	}

	public  ObservableList<Raccolta> getGiornali() throws SQLException   {

			ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();

		
		
			conn= ConnToDb.generalConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT * FROM giornale");
			while(rs.next())        

			{
				
				f.createRaccoltaFinale1(GIORNALE, rs.getString(1),rs.getString(2), null,rs.getString(3),rs.getString(4),null);
				f.createRaccoltaFinale2(GIORNALE,0,null,0,rs.getInt(7),rs.getFloat(8),rs.getInt(6));
				catalogo.add(f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), null, null,rs.getInt(9)));
			
				
			}


		conn.close();
		return catalogo;
	}
	
	public  List<Giornale> getGiornaliList() throws SQLException   {

		List<Giornale> catalogo=new ArrayList<>();

	
	
		conn= ConnToDb.generalConnection();
		stmt=conn.createStatement();
		rs=stmt.executeQuery("SELECT * FROM giornale");
		while(rs.next())        

		{
			
			f.createRaccoltaFinale1(GIORNALE, rs.getString(1),rs.getString(2), null,rs.getString(3),rs.getString(4),null);
			f.createRaccoltaFinale2(GIORNALE,0,null,0,rs.getInt(7),rs.getFloat(8),rs.getInt(6));
			catalogo.add((Giornale) f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), null, null,rs.getInt(9)));
		
			
		}


	conn.close();
	return catalogo;
}

	
	public  List<Giornale> getGiornaliListSingolo(Giornale g) throws SQLException   {

		List<Giornale> catalogo=new ArrayList<>();

	
	
		conn= ConnToDb.generalConnection();
		stmt=conn.createStatement();
		rs=stmt.executeQuery("SELECT * FROM ispw.giornale where id='"+g.getId()+"'");
		while(rs.next())        

		{
			
			f.createRaccoltaFinale1(GIORNALE, rs.getString(1),rs.getString(2), null,rs.getString(3),rs.getString(4),null);
			f.createRaccoltaFinale2(GIORNALE,0,null,0,rs.getInt(7),rs.getFloat(8),rs.getInt(6));
			catalogo.add((Giornale) f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), null, null,rs.getInt(9)));
		
			
		}


	conn.close();
	return catalogo;
}

	public  Giornale getGiornale(Giornale g,int id) throws SQLException  
	{



			conn=ConnToDb.generalConnection();

			stmt=conn.createStatement();
			String giornaleS="SELECT * FROM giornale where id = '"+id+"' ";
			rs=stmt.executeQuery(giornaleS);

			if (rs.next())
			{

				f.createRaccoltaFinale1(GIORNALE, rs.getString(1),rs.getString(2), null,rs.getString(3),rs.getString(4),null);
				f.createRaccoltaFinale2(GIORNALE,0,null,0,rs.getInt(7),rs.getFloat(8),rs.getInt(6));
				g=(Giornale) (f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), null, null,rs.getInt(9)));
	
			
			}
			



	conn.close();
		return g;

	}

	public GiornaleDao()
	{
		f=new Factory();
	}

	public  int retId(Giornale g) throws SQLException  {
		String titoloG=g.getTitolo();



		
			conn = ConnToDb.generalConnection();
			stmt = conn.createStatement();
			String prendiId="select id from giornale where titolo ='"+titoloG+"'";
			rs = stmt.executeQuery(prendiId);

			while ( rs.next() ) {
				id=rs.getInt("id");

			}
		
		conn.close();

		return id;



	}

	public  String retTip(Giornale g) throws SQLException  {
		String titoloG;

		titoloG=g.getTitolo();


		
			conn = ConnToDb.generalConnection();
			stmt = conn.createStatement();

			rs=stmt.executeQuery("select tipologia from ispw.giornale where titolo ='"+titoloG+"' or id ='"+g.getId()+"'");

			while ( rs.next() ) {
				categoria=rs.getString("tipologia");

			}
		
			conn.close();
		
		return categoria;


	}

	public  String getNome(Giornale g) throws SQLException  
	{

		String name = null;

			
			conn= ConnToDb.generalConnection();

			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT titolo FROM giornale where id = '"+g.getId()+"' ");
			if (rs.next())
			{
				name = rs.getString(1);
			}
			
		
			conn.close();

		

		return name;
	}

	public  int getDisp(Giornale g) throws SQLException 
	{


		
				conn = ConnToDb.generalConnection();
				stmt=conn.createStatement();
				rs=  stmt.executeQuery(
						"SELECT `disp` FROM `ispw`.`giornale` where `id` = '"+g.getId()+"'");
				if (rs.next())
				{
				
				disp = rs.getInt(1);
				if (disp >= 1)
					disp=1;
				else if (disp == 0)
					disp= 0;
			}
		
			
				conn.close();
			
		

		return disp;
	}

	public  int getQuantita(Giornale g) throws SQLException  
	{

		
			
				conn = ConnToDb.generalConnection();
			
				stmt = conn.createStatement();

				rs=  stmt.executeQuery("SELECT copiRim FROM ispw.giornale where id = '"+g.getId()+"'");
				if (rs.next()) {
					q = rs.getInt("copiRim");
				}			
			
		
			
				conn.close();
			
		

		return q;
	}

	public  boolean checkDisp(Giornale g,int id) throws SQLException  
	{
		g.setId(id);

		
			conn = ConnToDb.generalConnection();
			stmt=conn.createStatement();


			rs=  stmt.executeQuery("SELECT disp FROM ispw.giornale where id = '"+g.getId()+"' ;");
			if(rs.next())
			{
				disp = rs.getInt(1);
				if (disp >= 1)
					state=true;
				Log.LOGGER.log(Level.INFO, "giornale trovato");
			}
			
	 	return state;
	}

	public  ObservableList<Giornale> getGiornaleSingolo() throws SQLException   {

		ObservableList<Giornale> catalogo=FXCollections.observableArrayList();


		
		
			conn=ConnToDb.generalConnection();
			stmt= conn.createStatement();
			rs=stmt.executeQuery("SELECT * FROM ispw.giornale;");

			while(rs.next())
			{

				

				f.createRaccoltaFinale1(GIORNALE, rs.getString(1),rs.getString(2), null,rs.getString(3),rs.getString(4),null);
				f.createRaccoltaFinale2(GIORNALE,0,null,0,rs.getInt(7),rs.getFloat(8),rs.getInt(6));
				catalogo.add((Giornale) f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), null, null, rs.getInt(9)));





				
			}
		
		conn.close();
		return catalogo;
	}


	public  boolean creaGiornale(Giornale g) throws SQLException  {
		

			conn = ConnToDb.generalConnection();
			query= "INSERT INTO `ispw`.`giornale`"
					+ "(`titolo`,"
					+ "`tipologia`,"
					+ "`lingua`,"
					+ "`editore`,"
					+ "`dataPubblicazione`,"
					+ "`copiRim`,"
					+ "`disp`,"
					+ "`prezzo`)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?,?);"
					+ "";
			prepQ = conn.prepareStatement(query);	
			prepQ.setString(1,g.getTitolo()); 
			prepQ.setString(2,g.getTipologia());
			prepQ.setString(3,g.getLingua());
			prepQ.setString(4,g.getEditore());
			prepQ.setDate(5, java.sql.Date.valueOf(g.getDataPubb().toString())); 
			prepQ.setInt(6,g.getCopieRimanenti());
			prepQ.setInt(7, g.getDisponibilita());
			prepQ.setFloat(8, g.getPrezzo());

			prepQ.executeUpdate();

			state= true; // true		 			 	
		
		conn.close();



		return state;


	}


	public  void cancella(Giornale g) throws SQLException  {


		

			conn = ConnToDb.generalConnection();
			stmt=conn.createStatement();


			prepQ=conn.prepareStatement("delete  FROM ispw.giornale where id = '"+g.getId()+"'");
			prepQ.executeUpdate();

		conn.close();



	}

	public ObservableList<Giornale> getGiornaliSingoloById(Giornale g) throws SQLException    {

		ObservableList<Giornale> catalogo=FXCollections.observableArrayList();


		

			conn=ConnToDb.generalConnection();


			stmt= conn.createStatement();
			rs=stmt.executeQuery("SELECT * FROM ispw.giornale where id='"+g.getId()+"'");

			while(rs.next())
			{
				

					f.createRaccoltaFinale1(GIORNALE, rs.getString(1),rs.getString(2), null,rs.getString(3),rs.getString(4),null);
					f.createRaccoltaFinale2(GIORNALE,0,null,0,rs.getInt(7),rs.getFloat(8),rs.getInt(6));
					catalogo.add((Giornale) f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), null, null, rs.getInt(9)));


		}

		
			Log.LOGGER.log(Level.SEVERE," Catalogo nel dao : {0}",catalogo);
		return catalogo;

	}

	public  ObservableList<Raccolta> getGiornaliByName(String s) throws SQLException {

		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();

		String giornaleNE="SELECT * FROM giornale where titolo = '"+s+"' OR editore = '"+s+"'";
		

			conn=ConnToDb.generalConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(giornaleNE);

			while(rs.next())
			{

				
					f.createRaccoltaFinale1(GIORNALE, rs.getString(1),rs.getString(2), null,rs.getString(3),rs.getString(4),null);
					f.createRaccoltaFinale2(GIORNALE,0,null,0,rs.getInt(7),rs.getFloat(8),rs.getInt(6));
					catalogo.add((f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), null, null,rs.getInt(9))));
		
				
				
			}

		

			conn.close();
			Log.LOGGER.log(Level.SEVERE,"Catalogo : {0}",catalogo);


		return catalogo;


	}

	public  void aggiornaGiornale(Giornale g) throws SQLException  {
		


					
			conn = ConnToDb.generalConnection();

			stmt=conn.createStatement();


			query=" UPDATE `ispw`.`giornale`"
					+ "SET"
					+ "`titolo` =?,"
					+ "`tipologia` = ?,"
					+ "`lingua` = ?,"
					+ "`editore` = ?,"
					+ "`dataPubblicazione` = ?,"
					+ "`copiRim` = ?,"
					+ "`disp` = ?,"
					+ "`prezzo` = ?"
					+ "WHERE `id` = "+g.getId()+"";
			prepQ=conn.prepareStatement(query);

			prepQ.setString(1,g.getTitolo());
			prepQ.setString(2,g.getTipologia());
			prepQ.setString(3,g.getLingua());
			prepQ.setString(4, g.getEditore());
			prepQ.setString(5,g.getDataPubb().toString());
			prepQ.setInt(6,g.getCopieRimanenti());
			prepQ.setInt(7,g.getDisponibilita());
			prepQ.setFloat(8,g.getPrezzo());


			prepQ.executeUpdate();
			prepQ.close();



		conn.close();


	}	

	public   void generaReport() throws IOException, SQLException
	{
		FileWriter w;
		w=new FileWriter("ReportFinale\\riepilogoGiornali.txt");
		   try (BufferedWriter b=new BufferedWriter (w)){

			conn = ConnToDb.generalConnection();
			


			stmt=conn.createStatement();
			rs=stmt.executeQuery("select titolo,editore,copiRim,prezzo as totale  from ispw.giornale;");

			while(rs.next())
			{



				rs.getString(1);
				rs.getString(2);
				rs.getInt(3);
				rs.getFloat(4);



				b.write("Titolo :"+rs.getString(1)+"\t"+"Editore :"+rs.getString(2)+"\t"+"Ricavo totale :" +rs.getInt(3)*rs.getFloat(4)+"\n");




				b.flush();





			}




		}
		finally {
			conn.close();

		}




	}


	public void incrementaDisponibilita(Giornale g) throws SQLException {
		int d=vis.getQuantita();
		int i=getQuantita(g);
		
		int rim=i+d;
		

		
			conn = ConnToDb.generalConnection();
			prepQ=conn.prepareStatement("update ispw.giornale set copiRim= ? where titolo='"+g.getTitolo()+"'or id='"+g.getId()+"'");
			prepQ.setInt(1,rim);			
			prepQ.executeUpdate();


		conn.close();



		
	}
}
