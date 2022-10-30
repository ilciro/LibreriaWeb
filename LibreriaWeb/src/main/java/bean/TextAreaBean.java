package bean;



import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.TempUser;
import utilities.ConnToDb;

public class TextAreaBean {
	
	private String s;
	Connection conn=null;
	ResultSet rs=null;
	Statement stmt=null;
	
	
	
	

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}
	public String generaReportL() throws SQLException, IOException
	{
		
		 s="";
				
		conn = ConnToDb.generalConnection();
		stmt=conn.createStatement();
		rs=stmt.executeQuery("select titolo,copieVendute,prezzo as totale  from ispw.libro;");
		while(rs.next())
			{

				rs.getString(1);
				rs.getInt(2);
				rs.getFloat(3);

				s+= "\n" +"Titolo :"+rs.getString(1)+"\t"+"Ricavo totale :" +rs.getInt(2)*rs.getFloat(3)+"\n";

			}

		
		  conn.close();
		return s;

	}
	
	public  String generaReportG() throws IOException, SQLException
	{
		
			s="";		   
			conn = ConnToDb.generalConnection();
			


			stmt=conn.createStatement();
			rs=stmt.executeQuery("select titolo,editore,copiRim,prezzo as totale  from ispw.giornale;");

			while(rs.next())
			{



				rs.getString(1);
				rs.getString(2);
				rs.getInt(3);
				rs.getFloat(4);



				s+=("\n "+ "Titolo :"+rs.getString(1)+"\t"+"Editore :"+rs.getString(2)+"\t"+"Ricavo totale :" +rs.getInt(3)*rs.getFloat(4)+"\n");

			}

			conn.close();
			return s;



	}
	public String generaReportR() throws SQLException, IOException
	{
				s="";
		        
		        
		       
				conn = ConnToDb.generalConnection();
				
				stmt=conn.createStatement();
				rs=stmt.executeQuery("select titolo,editore,copieRimanenti,prezzo as totale  from ispw.rivista");
				
				
		           
		            while(rs.next())
		            {
		        		
		        	

				
								rs.getString(1);
								rs.getString(2);
								rs.getInt(3);
								rs.getFloat(4);
								
										
				
		        		s+="\n"+"Titolo :"+rs.getString(1)+"\t"+"Editore :"+rs.getString(2)+"\t"+"Ricavo totale :" +rs.getInt(3)*rs.getFloat(4)+"\n";

		
		            }
		            conn.close();
		            return s;
		   			
	}
	public String getListaUtenti() throws IOException, SQLException  {

		conn= ConnToDb.generalConnection();
		

		
		
			stmt=conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM users");




			while(rs.next())
			{

				TempUser.getInstance().setId(rs.getInt(1));
				TempUser.getInstance().setIdRuolo(rs.getString(2));
				TempUser.getInstance().setNome(rs.getString(3));
				TempUser.getInstance().setCognome(rs.getString(4));
				TempUser.getInstance().setEmail(rs.getString(5));
				TempUser.getInstance().setDescrizione(rs.getString(7));
				TempUser.getInstance().setDataDiNascita(rs.getDate(8).toLocalDate());
				s+="\n"+TempUser.getInstance().getId()+"\t"+TempUser.getInstance().getIdRuolo()+"\t"+TempUser.getInstance().getNome()+"\t"+TempUser.getInstance().getCognome()+
						"\t"+TempUser.getInstance().getEmail()+"\t"+TempUser.getInstance().getDescrizione()+"\t"+TempUser.getInstance().getDataDiNascita().toString()+"\n";
				
		}
		
		
			
				conn.close();
				return s;
			         
		
	}


	
	

	


}
