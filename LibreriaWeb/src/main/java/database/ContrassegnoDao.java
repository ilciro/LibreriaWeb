package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.ConnToDb;
import model.Fattura;

public class ContrassegnoDao {
	private  Connection conn=null;
	private  PreparedStatement stmt=null;
	
	

	public void inserisciFattura(Fattura f) throws SQLException 
	{
		 
		
		String par1=f.getNome();
 		String par2=f.getCognome();
 		String par3=f.getVia();
 		String par4=f.getCom();
 		float par5=f.getAmmontare();
 		
 		
       
		 

			 conn = ConnToDb.generalConnection();
         
             stmt = conn.prepareStatement("insert into ispw.fattura values (?,?,?,?,?,?);");
             stmt.setString(1,par1);
             stmt.setString(2,par2);
             stmt.setString(3,par3);
             stmt.setString(4,par4 );
             stmt.setInt(5, 0);
             stmt.setFloat(6, par5);
             stmt.execute();
             

     		
             
            
       
         conn.close();
		 		 
         
         
        	 
	}  
	public void daiPrivilegi() throws SQLException 
	{
		

			  conn = ConnToDb.generalConnection();
			  stmt = conn.prepareStatement(" SET SQL_SAFE_UPDATES=0");
			         stmt.executeUpdate();

	            
	         conn.close();


		}
	

	public int retUltimoOrdine() throws SQLException 
	{
		int id=0;
		 ResultSet rs=null;

		
			conn = ConnToDb.generalConnection();
		
			stmt=conn.prepareStatement("select count(*) as massimo from ispw.fattura");
		
					
					rs=stmt.executeQuery();
			
				while(rs.next())
				{
					id=rs.getInt("massimo");

				}
			

		conn.close();
			
		return id;
		
		
	}
	
	public boolean annullaOrdine(int idC) throws SQLException
	{
		boolean state=false;
		String cancella="delete from fattura where id='"+idC+"'";
		
			conn =ConnToDb.generalConnection();
			stmt= conn.prepareStatement(cancella);
			

				
				 stmt.executeUpdate();
				
					state=true;

				conn.close();
			
			return state;

		}
}
	

		

	
	