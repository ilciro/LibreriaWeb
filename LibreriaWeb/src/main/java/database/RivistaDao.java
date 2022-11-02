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
import raccolta.Raccolta;
import raccolta.Rivista;
import utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RivistaDao {
	private  Factory f;
	private  String query ;
	private static String eccezione="eccezione ottenuta:";
	private  int q;
	private int id = 0;
	private boolean state=false;
	private String riv="SELECT * from RIVISTA";
	private ControllerSystemState vis=ControllerSystemState.getIstance();
	private static final String RIVISTA="rivista";



	
	public float getCosto(Rivista r) throws SQLException
	{
		float prezzo=(float) 0.0;
		  query="select * from rivista  where id=?";
		  try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
		  {
			  
		  prepQ.setInt(1, r.getId());
         while ( rs.next() ) {
              prezzo=rs.getFloat("prezzo");

         }
		  }catch(SQLException e)
		  {
			  Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		  }
		return prezzo;
		
	}
	
	public void aggiornaDisponibilita(Rivista r) throws SQLException
	{
		int d=vis.getQuantita();
		int i=getQuantita(r);
		
		int rim=i-d;
		
		query="update ispw.rivista set copieRimanenti= ? where titolo=? or id=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				){
			
		
			prepQ.setInt(1,rim);	
			prepQ.setString(2,r.getTitolo());
			prepQ.setInt(3, r.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}

		}

	public void daiPrivilegi() throws SQLException
	{
		query=" SET SQL_SAFE_UPDATES=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				)
		{
				prepQ.setInt(1, 0);
			         prepQ.executeUpdate();

	            
		}  catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		

}
	
	public ObservableList<Raccolta> getRiviste() throws SQLException
	{
		 query=riv;
		 ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		 try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
		 {
            while(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add(f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, null,rs.getInt(11)));
		
					
        		
            }
		 }catch(SQLException e)
		 {
			 Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		 }
		return catalogo;
		
	}
	
	public List<Rivista> getRivisteList() throws SQLException
	{
		 query=riv;
		
		List<Rivista> catalogo=new ArrayList<>();
		 
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
            while(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add((Rivista) f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, null,rs.getInt(11)));
		
					
        		
            }
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		return catalogo;
		
	}
	
	
	public ObservableList<Raccolta> getRivisteByName(String s) throws SQLException
	{
		
		
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		 
		
		query="SELECT * FROM ispw.rivista"
				+"where titolo = ?"
				+" OR autore = ?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
			prepQ.setString(1,s);
			prepQ.setString(2,s);
				
            while(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),null, null,null,rs.getString(4),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add(f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, null,rs.getInt(11)));
		
				
        		
            }
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
			
		
		return catalogo;
		
	}


	public Rivista getRivista(Rivista r,int id) throws SQLException
	{

		 query="SELECT * FROM rivista"
				 +"where id =? ";
		 try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
		 {
		 prepQ.setInt(1, id);
		
        while (rs.next())
        {
        	f.createRaccoltaFinale1(RIVISTA, rs.getString(1),null, null,rs.getString(4),rs.getString(5),null);
			f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
			r=(Rivista) (f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, null,rs.getInt(11)));
        }
		 }catch(SQLException e)
		
		 {
			 Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		 
		 }
             return r;
	}

	public RivistaDao()
	{
		f=new Factory();
	}
	
	public int retId(Rivista r) throws SQLException {
		
		
		query="select id from rivista"
        		 +"where titolo =?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
        
         prepQ.setString(1, r.getTitolo());
        
         while ( rs.next() ) {
              id=rs.getInt("id");

         }
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		
		return id;

		
		
	}

	public String retTip(Rivista r) throws SQLException {
		
		String categoria=null;
		query="select tipologia from rivista where titolo =? or id=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
			prepQ.setString(1,r.getTitolo());
			prepQ.setInt(2, r.getId());
		
         while ( rs.next() ) {
              categoria=rs.getString("tipologia");

         }
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		 
			
		return categoria;

		
	}
	
	public String getNome(Rivista r) throws SQLException
	{
		String name=null;

	 query="SELECT titolo FROM rivista where id =?";
	 try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
		 
		prepQ.setInt(1,r.getId());
		if (rs.next())
        {
        	name = rs.getString(1);
        }
        else {
        	
            return null;

        }
		}catch(SQLException e)
	 {
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
	 }
        return name;
   }

	public int getDisp(Rivista r) throws SQLException
	{
		int disp = 0;
		
			query="SELECT disp FROM ispw.rivista where id =?";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
			{
				
				prepQ.setInt(1, r.getId());
				if(rs.next())
				{
					disp = rs.getInt(1);

				
					if(disp==1)
						 disp=1;
					if (disp == 0)
						disp=0;
				}
				
			}catch(SQLException e)
			{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
			}
		
		
		return disp;
	}
	
	public int getQuantita(Rivista r) throws SQLException
	{
        
		
			
				query="SELECT copieRimanenti FROM ispw.rivista where id =?";
				try(Connection conn=ConnToDb.generalConnection();
						PreparedStatement prepQ=conn.prepareStatement(query);
						ResultSet rs=prepQ.executeQuery())
				{prepQ.setInt(1, r.getId());
				if (rs.next()) {
					q = rs.getInt(1);
				}	
				}catch(SQLException e)
				{
					Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
				}
			
		

		return q;
	}

	public boolean checkDisp(Rivista r,int id) throws SQLException
	{
		int disp=0;
		r.setId(id);
		
		query="SELECT disp FROM ispw.rivista where id = ?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
			
		prepQ.setInt(1, r.getId());
			if(rs.next())
			{
				disp = rs.getInt(1);
				if (disp == 1)
					state=true;
				else
				{
					Log.LOGGER.log(Level.WARNING, "rivista non trovato");
					
				
				}
			}
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
			
	 	return state;
	
	}

	public ObservableList<Rivista> getRivistaSingolo() throws SQLException {
		
		
		ObservableList<Rivista> catalogo=FXCollections.observableArrayList();
		
		query=riv;
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{

            while(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add((Rivista) f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, null,rs.getInt(11)));
		} 
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		return catalogo;
		
	}

	public Boolean creaRivista(Rivista r) throws SQLException {
    	
    		
				
				query= "INSERT INTO `ispw`.`rivista`"
			 			+ "(`titolo`,"
			 			+ "`tipologia`,"
			 			+ "`autore`,"
			 			+ "`lingua`,"
			 			+ "`editore`,"
			 			+ "`Descrizione`,"
			 			+ "`dataPubblicazione`,"
			 			+ "`disp`,"
			 			+ "`prezzo`,"
			 			+ "`copieRimanenti`)"
			 			+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
				try(Connection conn=ConnToDb.generalConnection();
						PreparedStatement prepQ=conn.prepareStatement(query);
						ResultSet rs=prepQ.executeQuery())
				{
				prepQ.setString(1,r.getTitolo()); 
				prepQ.setString(2,r.getTipologia());
				prepQ.setString(3,r.getAutore());
				prepQ.setString(4,r.getLingua());
				prepQ.setString(5,r.getEditore());
				prepQ.setString(6,r.getDescrizione());
				prepQ.setDate(7, java.sql.Date.valueOf(r.getDataPubb().toString()));  
				prepQ.setInt(8, r.getDisp());
				prepQ.setFloat(9, r.getPrezzo());
				prepQ.setInt(10,r.getCopieRim());


				
				prepQ.executeUpdate();
			 	state= true; // true	
				}catch(SQLException e)
				{
					Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
				}
	
		return state;
		
		
	}

	public void cancella(Rivista r) throws SQLException {

		 int row=0;

			query="delete  FROM ispw.rivista where id =?";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
			{
				prepQ.setInt(1, r.getId());
				 row=prepQ.executeUpdate();
			}catch(SQLException e) {
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
			}
				
		
		
		Log.LOGGER.log(Level.INFO,"rivista cancellata .{0}",row);

		
		
	}

	public ObservableList<Rivista> getRivistaSingoloById(Rivista r) throws SQLException {
		ObservableList<Rivista> catalogo=FXCollections.observableArrayList();

		
		query="SELECT * from RIVISTA where id=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
			prepQ.setInt(1, r.getId());
            if(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add((Rivista) f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, rs.getString(6),rs.getInt(11)));
            }
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		return catalogo;
		
	}
	
	public List<Rivista> getRivistaSingoloByIdLista(Rivista r) throws SQLException {
		List<Rivista> catalogo=new ArrayList<>();

		
		query="SELECT * from RIVISTA where id=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
			
		prepQ.setInt(1, r.getId());

            if(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add((Rivista) f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, rs.getString(6),rs.getInt(11)));
            }
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		
		return catalogo;
		
	}

	public void aggiornaRivista(Rivista r) throws SQLException {
		 int rowAffected=0;



			query="UPDATE `ispw`.`rivista`"
		 			+ "SET"
		 			+ "`titolo` = ?,"
		 			+ "`tipologia` =?,"
		 			+ "`autore` = ?,"
		 			+ "`lingua` = ?,"
		 			+ "`editore` = ?,"
		 			+ "`Descrizione` =?,"
		 			+ "`dataPubblicazione` =?,"
		 			+ "`disp` = ?,"
		 			+ "`prezzo` = ?,"
		 			+ "`copieRimanenti` =? WHERE `id` = ?";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
			{
		 		
		 	
			
			prepQ.setString(1,r.getTitolo());
			prepQ.setString(2,r.getTipologia());
			prepQ.setString(3,r.getAutore());
			prepQ.setString(4,r.getLingua());
			prepQ.setString(5,r.getEditore());
			prepQ.setString(6,r.getDescrizione());
			prepQ.setString(7,r.getDataPubb().toString());
			prepQ.setInt(8,r.getDisp());
			prepQ.setFloat(9,r.getPrezzo());
			prepQ.setInt(10,r.getCopieRim());
			prepQ.setInt(11, r.getId());
		

			rowAffected = prepQ.executeUpdate();
			}catch(SQLException e)
			{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
			}
			
            Log.LOGGER.log(Level.INFO,"row affected .{0}",rowAffected);

	 }	
	
	public void generaReport() throws SQLException, IOException
	{
				FileWriter w;
		        w=new FileWriter("ReportFinale\\riepilogoRiviste.txt");
		        
		        
		        
		        try (BufferedWriter b=new BufferedWriter (w)){
				query="select titolo,editore,copieRimanenti,prezzo as totale  from ispw.rivista";
				try(Connection conn=ConnToDb.generalConnection();
						PreparedStatement prepQ=conn.prepareStatement(query);
						ResultSet rs=prepQ.executeQuery())
				{
				
		           
		            while(rs.next())
		            {
		        		
		        	

				
								rs.getString(1);
								rs.getString(2);
								rs.getInt(3);
								rs.getFloat(4);
								
										
				
		        		b.write("Titolo :"+rs.getString(1)+"\t"+"Editore :"+rs.getString(2)+"\t"+"Ricavo totale :" +rs.getInt(3)*rs.getFloat(4)+"\n");




		     			b.flush();


		        			
		        		
		            }
				}catch(SQLException e)
				{
					Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
				}

				
	}

		
	}
			
	
	
	

	public void incrementaDisponibilita(Rivista r) throws SQLException {
		int d=vis.getQuantita();
		int i=getQuantita(r);
		
		int rim=i+d;
		
		query="update ispw.rivista set copieRimanenti= ? where titolo=? or id=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
			prepQ.setInt(1,rim);
			prepQ.setString(2, r.getTitolo());
			prepQ.setInt(3,r.getId());
			prepQ.executeUpdate();

		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}

		
		
	}

	

		
}