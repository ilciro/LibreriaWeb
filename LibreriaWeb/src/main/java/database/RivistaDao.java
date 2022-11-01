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
import raccolta.Raccolta;
import raccolta.Rivista;
import utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RivistaDao {
	private  Factory f;
	private  Statement st = null ;
	private  String query ;
	private  PreparedStatement prepQ = null; 
	private  Connection conn ;
	private  int q;
	private  ResultSet rs;
	private int id = 0;
	private boolean state=false;
	private String riv="SELECT * from RIVISTA;";

	private ControllerSystemState vis=ControllerSystemState.getIstance();
	private static final String RIVISTA="rivista";



	
	public float getCosto(Rivista r) throws SQLException
	{
		float prezzo=(float) 0.0;
		  conn = ConnToDb.generalConnection();
         st = conn.createStatement();

         rs = st.executeQuery("select * from rivista  where id='"+r.getId()+"'");
         while ( rs.next() ) {
              prezzo=rs.getFloat("prezzo");

         }
		return prezzo;
		
	}
	
	public void aggiornaDisponibilita(Rivista r) throws SQLException
	{
		int d=vis.getQuantita();
		int i=getQuantita(r);
		
		int rim=i-d;
		

			conn = ConnToDb.generalConnection();
			prepQ=conn.prepareStatement("update ispw.rivista set copieRimanenti= ? where titolo='"+r.getTitolo()+"'or id='"+r.getId()+"'");
			prepQ.setInt(1,rim);			
			prepQ.executeUpdate();


		conn.close();


		}

	public void daiPrivilegi() throws SQLException
	{

		
			  conn = ConnToDb.generalConnection();
			  prepQ = conn.prepareStatement(" SET SQL_SAFE_UPDATES=0");
			         prepQ.executeUpdate();

	            
	         
			
			 conn.close();
			 
		 

		 Log.LOGGER.log(Level.INFO,"LibroDao. privilegi");

}
	
	public ObservableList<Raccolta> getRiviste() throws SQLException
	{
		 conn= ConnToDb.generalConnection();

		
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		 
		st=conn.createStatement();
		rs=st.executeQuery(riv);
            while(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add(f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, null,rs.getInt(11)));
		
					
        		
            }
		return catalogo;
		
	}
	
	public List<Rivista> getRivisteList() throws SQLException
	{
		 conn= ConnToDb.generalConnection();

		
		List<Rivista> catalogo=new ArrayList<>();
		 
		st=conn.createStatement();
		rs=st.executeQuery(riv);
            while(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add((Rivista) f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, null,rs.getInt(11)));
		
					
        		
            }
		return catalogo;
		
	}
	
	
	public ObservableList<Raccolta> getRivisteByName(String s) throws SQLException
	{
		 conn= ConnToDb.generalConnection();

		
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		 
		st=conn.createStatement();
		String rivistaSE="SELECT * FROM ispw.rivista"
				+"where titolo = ?"
				+" OR autore = ?";
			prepQ.setString(1,s);
			prepQ.setString(2,s);
				rs=st.executeQuery(rivistaSE);
            while(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),null, null,null,rs.getString(4),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add(f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, null,rs.getInt(11)));
		
				
        		
            }
            conn.close();
			
		Log.LOGGER.log(Level.INFO,"{0}",catalogo);
		return catalogo;
		
	}


	public Rivista getRivista(Rivista r,int id) throws SQLException
	{

		 conn= ConnToDb.generalConnection();
		 st=conn.createStatement();
		 String rivistaId="SELECT * FROM rivista"
				 +"where id =? ";
		 prepQ.setInt(1, id);
		 rs=st.executeQuery(rivistaId);
        while (rs.next())
        {
        	f.createRaccoltaFinale1(RIVISTA, rs.getString(1),null, null,rs.getString(4),rs.getString(5),null);
			f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
			r=(Rivista) (f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, null,rs.getInt(11)));
        }
             return r;
	}

	public RivistaDao()
	{
		f=new Factory();
	}
	
	public int retId(Rivista r) throws SQLException {
		
		String titolo=r.getTitolo();
		  conn = ConnToDb.generalConnection();
		 
         st = conn.createStatement();
         String rivistaT="select id from rivista"
        		 +"where titolo =?";
         prepQ=conn.prepareStatement(rivistaT);
         prepQ.setString(1, titolo);
         rs = prepQ.executeQuery(rivistaT);
         while ( rs.next() ) {
              id=rs.getInt("id");

         }
		 conn.close();
		return id;

		
		
	}

	public String retTip(Rivista r) throws SQLException {
		
		String categoria=null;
		  conn = ConnToDb.generalConnection();
		 
          st = conn.createStatement();

         rs = st.executeQuery("select tipologia from rivista where titolo ='"+r.getTitolo()+"' or id='"+r.getId()+"'");
         while ( rs.next() ) {
              categoria=rs.getString("tipologia");

         }
		 
				conn.close();
			
			
		return categoria;

		
	}
	
	public String getNome(Rivista r) throws SQLException
	{
		String name=null;

	 conn= ConnToDb.generalConnection();
	 st=conn.createStatement();
			 rs=st.executeQuery("SELECT titolo FROM rivista where id ='"+r.getId()+"'");
        if (rs.next())
        {
        	name = rs.getString(1);
        }
        else {
        	Log.LOGGER.log(Level.INFO,"non ho torvato un cazzo e ritorno null");
            return null;

        }	
        return name;
   }

	public int getDisp(Rivista r) throws SQLException
	{
		int disp = 0;
		
			conn = ConnToDb.generalConnection();
				st=conn.createStatement();


				rs=st.executeQuery("SELECT disp FROM ispw.rivista where id ='"+r.getId()+"'");
				if(rs.next())
				{
					disp = rs.getInt(1);

				
					if(disp==1)
						 disp=1;
					if (disp == 0)
						disp=0;
				}
				
			
		
		
		return disp;
	}
	
	public int getQuantita(Rivista r) throws SQLException
	{
        
		
			
				conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				
				rs=  st.executeQuery(
						"SELECT copieRimanenti FROM ispw.rivista where id =' "+r.getId()+"'");
				if (rs.next()) {
					q = rs.getInt(1);
				}			
			
		

		return q;
	}

	public boolean checkDisp(Rivista r,int id) throws SQLException
	{
		int disp=0;
		r.setId(id);
		
			conn = ConnToDb.generalConnection();
			st=conn.createStatement();


			rs=  st.executeQuery("SELECT disp FROM ispw.rivista where id = '"+r.getId()+"'");
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
			
	 	return state;
	
	}

	public ObservableList<Rivista> getRivistaSingolo() throws SQLException {
		
		conn= ConnToDb.generalConnection();
		ObservableList<Rivista> catalogo=FXCollections.observableArrayList();
		
		st=conn.createStatement();
		 
             rs=st.executeQuery(riv);

            while(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add((Rivista) f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, null,rs.getInt(11)));
		} 
            conn.close();
		Log.LOGGER.log(Level.INFO,"{0}",catalogo);
		return catalogo;
		
	}

	public Boolean creaRivista(Rivista r) throws SQLException {
    	
    		
				conn = ConnToDb.generalConnection();
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
			 	prepQ = conn.prepareStatement(query);	
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
			
			
		
		

		return state;
		
		
	}

	public void cancella(Rivista r) throws SQLException {

		 int row=0;

			
				conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				
				prepQ=conn.prepareStatement("delete  FROM ispw.rivista where id = '"+r.getId()+"'");
				 row=prepQ.executeUpdate();
				
		
		
		Log.LOGGER.log(Level.INFO,"rivista cancellata .{0}",row);

		
		
	}

	public ObservableList<Rivista> getRivistaSingoloById(Rivista r) throws SQLException {
		ObservableList<Rivista> catalogo=FXCollections.observableArrayList();

		
		conn= ConnToDb.generalConnection();
		st=conn.createStatement();
		rs=st.executeQuery("SELECT * from RIVISTA where id='"+r.getId()+"'");

            if(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add((Rivista) f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, rs.getString(6),rs.getInt(11)));
            }
          conn.close();            
		
		return catalogo;
		
	}
	
	public List<Rivista> getRivistaSingoloByIdLista(Rivista r) throws SQLException {
		List<Rivista> catalogo=new ArrayList<>();

		
		conn= ConnToDb.generalConnection();
		st=conn.createStatement();
		rs=st.executeQuery("SELECT * from RIVISTA where id='"+r.getId()+"'");

            if(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add((Rivista) f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, rs.getString(6),rs.getInt(11)));
            }
          conn.close();            
		
		return catalogo;
		
	}

	public void aggiornaRivista(Rivista r) throws SQLException {
		 int rowAffected=0;


		
			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			

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
		 			+ "`copieRimanenti` =? WHERE `id` = "+r.getId()+";";
		 		
		 	prepQ=conn.prepareStatement(query);
			
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
		

			rowAffected = prepQ.executeUpdate();
			prepQ.close();
			
            Log.LOGGER.log(Level.INFO,"row affected .{0}",rowAffected);

	 }	
	
	public void generaReport() throws SQLException, IOException
	{
				FileWriter w;
		        w=new FileWriter("ReportFinale\\riepilogoRiviste.txt");
		        
		        
		        try (BufferedWriter b=new BufferedWriter (w)){
				conn = ConnToDb.generalConnection();
				
				st=conn.createStatement();
				rs=st.executeQuery("select titolo,editore,copieRimanenti,prezzo as totale  from ispw.rivista");
				
				
		           
		            while(rs.next())
		            {
		        		
		        	

				
								rs.getString(1);
								rs.getString(2);
								rs.getInt(3);
								rs.getFloat(4);
								
										
				
		        		b.write("Titolo :"+rs.getString(1)+"\t"+"Editore :"+rs.getString(2)+"\t"+"Ricavo totale :" +rs.getInt(3)*rs.getFloat(4)+"\n");




		     			b.flush();


		        			
		        		
		            }
		     st.close();

				
	}
	conn.close();
		
	}
			
	
	
	

	public void incrementaDisponibilita(Rivista r) throws SQLException {
		int d=vis.getQuantita();
		int i=getQuantita(r);
		
		int rim=i+d;
		

			conn = ConnToDb.generalConnection();
			prepQ=conn.prepareStatement("update ispw.rivista set copieRimanenti= ? where titolo='"+r.getTitolo()+"'or id='"+r.getId()+"'");
			prepQ.setInt(1,rim);			
			prepQ.executeUpdate();


		
		
	}

	

		
}