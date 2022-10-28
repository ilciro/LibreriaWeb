package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Pagamento;
import model.User;
import utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PagamentoDao {
	
private Connection conn=null;
private PreparedStatement prepQ=null;
private Statement st=null;
private ResultSet rs=null;
	

	
	

		

	public void inserisciPagamento(Pagamento p) throws SQLException {
		String query="";

		String m=p.getMetodo();
		int esito=p.getEsito();
		String nomeU=p.getNomeUtente();
		float amm=p.getAmmontare();
		String email=User.getInstance().getEmail();
		String tipologia=p.getTipo();
		int idProdotto=p.getId();
		

		query="INSERT INTO ispw.pagamento(metodo,esito,nomeUtente,spesaTotale,eMail,tipoAcquisto,idProd) values (?,?,?,?,?,?,?)";


		
			conn=ConnToDb.generalConnection();
		
			prepQ=conn.prepareStatement(query);
		
			


			prepQ.setString(1,m); // 
			prepQ.setInt(2,esito);
			prepQ.setString(3,nomeU);
			prepQ.setFloat(4,amm);
			prepQ.setString(5, email);
			prepQ.setString(6,tipologia);
			prepQ.setInt(7, idProdotto);
			prepQ.execute();

		conn.close();
			 
		}
		
	public void daiPrivilegi() throws SQLException 
	{

		
			conn = ConnToDb.generalConnection();
		
			 prepQ= conn.prepareStatement(" SET SQL_SAFE_UPDATES=0");
		
			prepQ.executeUpdate();


		conn.close();

	}
	public ObservableList<Pagamento> getPagamenti() throws SQLException  {

			ObservableList<Pagamento> catalogo=FXCollections.observableArrayList();
			conn= ConnToDb.generalConnection();
			 st=conn.createStatement();
			 rs = st.executeQuery("SELECT id_op,metodo,esito,nomeUtente,spesaTotale,tipoAcquisto,idProd from ispw.pagamento where eMail='"+User.getInstance().getEmail()+"'");
			
			while(rs.next())
			{


				catalogo.add(new Pagamento (rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getFloat(5),rs.getString(6)));

			}
		conn.close();
		return catalogo;
	}
	
	public List<Pagamento> getPagamentiList() throws SQLException  {

		List<Pagamento> catalogo=new ArrayList<>();
		conn= ConnToDb.generalConnection();
		 st=conn.createStatement();
		 rs = st.executeQuery("SELECT id_op,metodo,esito,nomeUtente,spesaTotale,tipoAcquisto,idProd from ispw.pagamento where nomeUtente='"+User.getInstance().getNome()+"'");
		
		while(rs.next())
		{


			catalogo.add(new Pagamento (rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getFloat(5),rs.getString(6)));

		}
	conn.close();
	return catalogo;
}

	
		
	public int retUltimoOrdine() throws SQLException 
	{
		int id=0;
		
				conn = ConnToDb.generalConnection();
				st = conn.createStatement();
				 rs = st.executeQuery("select count(*) as massimo from ispw.pagamento");
		
			while ( rs.next() ) {
				id=rs.getInt("massimo");

			}
		conn.close();
		return id;
		
	}

	public boolean annullaOrdine(int idC) throws SQLException
	{
		boolean state=false;
		
		conn = ConnToDb.generalConnection();
			prepQ= conn.prepareStatement("delete from ispw.pagamento where id_op='"+idC+"'");
				
		
			
			
			
			prepQ.executeUpdate();
				
				
					
			state=true;
				
			
			
		

		return state;

		

	}


}