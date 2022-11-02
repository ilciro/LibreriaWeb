package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import model.Log;
import model.Negozio;
import utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NegozioDao {
	
	private boolean state=false;
	private boolean state1=false;
	private String query=null;
	private static String eccezione="eccezione ottenuta:";
	
	
	
    
	public ObservableList<Negozio> getNegozi() throws SQLException
	{
		Negozio shop; 
		
		 ObservableList<Negozio> listOfNegozi;
		listOfNegozi=FXCollections.observableArrayList();

		query="SELECT `negozio`.`nome`,`negozio`.`via`,"
		        		+ "    `negozio`.`isValid`,"
		        		+ "    `negozio`.`isOpen`"
		        		+ "FROM `ispw`.`negozio`";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
	 			
			
				while (rs.next())
				{
					shop = new Negozio(rs.getString(1),rs.getString(2),rs.getBoolean(3),rs.getBoolean(4));
					listOfNegozi.add(shop);
				}
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		
		return listOfNegozi;
	}
	
	public Boolean setOpen(Negozio shop, boolean i) throws SQLException 
	{
		query="update ispw.negozio set isOpen =? where nome=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				)
		{
				
					prepQ.setBoolean(1, i);
					prepQ.setString(2,shop.getNome());
					prepQ.executeUpdate();
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
					
		
		return i;
		
		
	}
	
	public boolean setRitiro(Negozio shop, boolean i ) throws SQLException
	{
		
			query="update ispw.negozio set isValid =? where nome=?";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					)
			{
				
					prepQ.setBoolean(1, i);
					prepQ.setString(2, shop.getNome());
					prepQ.executeUpdate();
			}catch(SQLException e)
			{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
			}
					
			
		
		return i;
	}
	
	
	// controllo che il negozio sia aperto
	public boolean checkOpen(Negozio  shop) throws SQLException
	{
		int aperto=0;
		query="select isOpen from ispw.negozio where  nome=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
			prepQ.setString(1,shop.getNome());
		
			if(rs.next()){
				aperto=rs.getInt(1);
				if(aperto==1)
					state=true;
				else
					state=false;
			}
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
			
		return state;
	}

	
	//controllo se il negozio fa PickUP
	public boolean checkRitiro(Negozio shop) throws SQLException
	{
		int disp=0;
	
		query="select isValid from ispw.negozio where nome =?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
			
		prepQ.setString(1, shop.getNome());
			if ( rs.next() ) {

					disp=rs.getInt(1);
					if (disp==1)
						state1=true;
					else
						state1=false;
			}
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
			
			
		return state1;
	}
	
	public List<Negozio> getNegoziList() throws SQLException
	{
		Negozio shop; 
		
		 List<Negozio> listOfNegozi;
		listOfNegozi=new ArrayList<>();

		query="SELECT  "
						+ "`negozio`.`nome`,"
		        		+ "    `negozio`.`via`,"
		        		+ "    `negozio`.`isValid`,"
		        		+ "    `negozio`.`isOpen`"
		        		+ "FROM `ispw`.`negozio`";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
			
				while (rs.next())
				{
					shop = new Negozio(rs.getString(1),rs.getString(2),rs.getBoolean(3),rs.getBoolean(4));
					listOfNegozi.add(shop);
				}
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		
		return listOfNegozi;
	}

}