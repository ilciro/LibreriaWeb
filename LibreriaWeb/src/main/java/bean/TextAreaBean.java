package bean;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import model.Log;
import model.TempUser;
import utilities.ConnToDb;

public class TextAreaBean {
	
	
	
	
	private  String query="";
	private static String eccezione="eccezione ottenuta:";
	private StringBuilder s;
	private String finale=null;
	private static String titolo="titolo";
	private static String ricavo="ricavo";
	private String scrivi;
	
	
	
	

	
	public String generaReportL() throws SQLException
	{
		 s=new StringBuilder();
		
		
		
			query="select titolo,copieVendute,prezzo as totale  from ispw.libro";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
				{
		while(rs.next())
			{

				rs.getString(1);
				rs.getInt(2);
				rs.getFloat(3);
				s.append("\n");
				s.append(titolo);
				s.append(rs.getString(1));
				s.append("\t");
				s.append(rs.getInt(2)*rs.getFloat(3));
				s.append("\n");
				
				 finale=s.toString();
			}

				
		
		  }catch(SQLException e){Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());			}
		return finale;

	}
	
	public  String generaReportG() throws SQLException
	{
		
		 s=new StringBuilder();
		
		

			query="select titolo,editore,copiRim,prezzo as totale  from ispw.giornale";
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
				
				s.append("\n");
				s.append(titolo);
				s.append(rs.getString(1));
				s.append("\t");
				s.append("editore");
				s.append(rs.getString(2));
				s.append(ricavo);
				s.append(rs.getInt(3)*rs.getFloat(4));
				s.append("\n");
				
				 finale=s.toString();



			
			}

			}catch(SQLException e){Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());			}
			return finale;



	}
	public String generaReportR() throws SQLException
	{
		
		
		 s=new StringBuilder();
		

			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
				{
				
				query="select titolo,editore,copieRimanenti,prezzo as totale  from ispw.rivista";
				
				
		           
		            while(rs.next())
		            {
		        		
		        	

				
								rs.getString(1);
								rs.getString(2);
								rs.getInt(3);
								rs.getFloat(4);
								
								s.append("\n");
								s.append(titolo);
								s.append(rs.getString(1));
								s.append("\t");
								s.append("editore");
								s.append(rs.getString(2));
								s.append(ricavo);
								s.append(rs.getInt(3)*rs.getFloat(4));
								s.append("\n");
								
										
							

		
		            }
		            }catch(SQLException e){Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());			}
		            return finale;
		   			
	}
	public String getListaUtenti() throws  SQLException  {

		query="SELECT * FROM users";
		s=new StringBuilder();

		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
			{


			while(rs.next())
			{

				TempUser.getInstance().setId(rs.getInt(1));
				TempUser.getInstance().setIdRuolo(rs.getString(2));
				TempUser.getInstance().setNome(rs.getString(3));
				TempUser.getInstance().setCognome(rs.getString(4));
				TempUser.getInstance().setEmail(rs.getString(5));
				TempUser.getInstance().setDescrizione(rs.getString(7));
				TempUser.getInstance().setDataDiNascita(rs.getDate(8).toLocalDate());
				
				s.append("\n");
				s.append(TempUser.getInstance().getId());
				s.append("\t");
				s.append(TempUser.getInstance().getIdRuolo());
				s.append("\n");
				s.append(TempUser.getInstance().getNome());
				s.append("\t");
				s.append(TempUser.getInstance().getCognome());
				s.append("\t");
				s.append(TempUser.getInstance().getEmail());
				s.append("\t");
				s.append(TempUser.getInstance().getDescrizione());
				s.append("\t");
				s.append(TempUser.getInstance().getDataDiNascita());
				
				finale=s.toString();
				
		}
		
		
			
				}catch(SQLException e){Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());			}
				return finale;
			         
		
	}

	public String getScrivi() {
		return scrivi;
	}

	public void setScrivi(String scrivi) {
		this.scrivi = scrivi;
	}

	


	
	

	


}
