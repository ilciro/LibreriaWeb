package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Negozio;
import utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NegozioDao {
	private  Connection conn =null;
	private  ResultSet rs=null;
	private Statement stmt=null;
	private boolean state=false,state1=false;
	private PreparedStatement prepQ=null;
	private int disp;
	private int aperto;
	
	
	
    
	public ObservableList<Negozio> getNegozi() throws SQLException
	{
		Negozio shop; 
		
		 ObservableList<Negozio> listOfNegozi;
		listOfNegozi=FXCollections.observableArrayList();

		

			 conn= ConnToDb.generalConnection();
			 prepQ=conn.prepareStatement("SELECT  "
						+ "`negozio`.`nome`,"
		        		+ "    `negozio`.`via`,"
		        		+ "    `negozio`.`isValid`,"
		        		+ "    `negozio`.`isOpen`"
		        		+ "FROM `ispw`.`negozio`");
	 			rs=prepQ.executeQuery();
			
				while (rs.next())
				{
					shop = new Negozio(rs.getString(1),rs.getString(2),rs.getBoolean(3),rs.getBoolean(4));
					listOfNegozi.add(shop);
				}
		conn.close();
		return listOfNegozi;
	}
	
	public Boolean setOpen(Negozio shop, boolean i) throws SQLException 
	{
		// vanno messe  le query
		
		
		
			
				 conn= ConnToDb.generalConnection();
				 prepQ=conn.prepareStatement("update ispw.negozio set isOpen =? where nome='"+shop.getNome()+"'");
				
					prepQ.setBoolean(1, i);
					prepQ.executeUpdate();
					
			conn.close();
		return i;
		
		
	}
	
	public boolean setRitiro(Negozio shop, boolean i ) throws SQLException
	{
		
			
				 conn= ConnToDb.generalConnection();
				 prepQ=conn.prepareStatement("update ispw.negozio set isValid =? where nome='"+shop.getNome()+"'");
				
					prepQ.setBoolean(1, i);
					prepQ.executeUpdate();
					
				conn.close();
		
		return i;
	}
	
	
	// controllo che il negozio sia aperto
	public boolean checkOpen(Negozio  shop) throws SQLException
	{
		
		 conn= ConnToDb.generalConnection();
		 stmt=conn.createStatement();
		
			 rs= stmt.executeQuery("select isOpen from ispw.negozio where  nome='"+shop.getNome()+"'");
			if(rs.next()){
				aperto=rs.getInt(1);
				if(aperto==1)
					state=true;
				else
					state=false;
			}
				
			
		conn.close();
		return state;
	}

	
	//controllo se il negozio fa PickUP
	public boolean checkRitiro(Negozio shop) throws SQLException
	{
		conn = ConnToDb.generalConnection();
		
			stmt = conn.createStatement();
			//add or id per matchare nel ControlelrPagamento
			rs = stmt.executeQuery("select isValid from ispw.negozio where nome ='"+shop.getNome()+"' ");
			if ( rs.next() ) {

					disp=rs.getInt(1);
					if (disp==1)
						state1=true;
					else
						state1=false;
			}
			
			conn.close();
			
		return state1;
	}
	
	public List<Negozio> getNegoziList() throws SQLException
	{
		Negozio shop; 
		
		 List<Negozio> listOfNegozi;
		listOfNegozi=new ArrayList<>();

		

			 conn= ConnToDb.generalConnection();
			 prepQ=conn.prepareStatement("SELECT  "
						+ "`negozio`.`nome`,"
		        		+ "    `negozio`.`via`,"
		        		+ "    `negozio`.`isValid`,"
		        		+ "    `negozio`.`isOpen`"
		        		+ "FROM `ispw`.`negozio`");
	 			rs=prepQ.executeQuery();
			
				while (rs.next())
				{
					shop = new Negozio(rs.getString(1),rs.getString(2),rs.getBoolean(3),rs.getBoolean(4));
					listOfNegozi.add(shop);
				}
		conn.close();
		return listOfNegozi;
	}

}