package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import utilities.ConnToDb;
import model.Fattura;
import model.Log;

public class ContrassegnoDao {
	private static String eccezione="eccezione ottenuta:";
	

	public void inserisciFattura(Fattura f) throws SQLException 
	{
		 
		
		
 		String query="insert into ispw.fattura values (?,?,?,?,?,?);";
 		
 		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				)
			{
		
       
	
             prepQ.setString(1,f.getNome());
             prepQ.setString(2,f.getCognome());
             prepQ.setString(3,f.getVia());
             prepQ.setString(4,f.getCom() );
             prepQ.setInt(5, 0);
             prepQ.setFloat(6, f.getAmmontare());
             prepQ.execute();
             
			}catch(SQLException e)
			{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
			}
         
         
        	 
	}  
	public void daiPrivilegi() throws SQLException 
	{
		String query="set sql_safe_updates=?";

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
	

	public int retUltimoOrdine() throws SQLException 
	{
		int id=0;
		 String query="select count(*) as massimo from ispw.fattura";
		 
		 try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
				{
			
			
				while(rs.next())
				{
					id=rs.getInt("massimo");

				}
				}catch(SQLException e)
		 {
					Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		 }
						
		return id;
		
		
	}
	
	public boolean annullaOrdine(int idC) throws SQLException
	{
		boolean state=false;
		String cancella="delete from fattura"+
		"where id=?";
		int row=0;
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(cancella);
				
				)
			{

				prepQ.setInt(1,idC);
				row=prepQ.executeUpdate();
				if(row==1)
					state=true;

			}catch(SQLException e)
		{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
			
			return state;

		}
}
	

		

	
	