package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import model.Log;
import model.Pagamento;
import model.User;
import utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PagamentoDao {
	
private static String eccezione="eccezione ottenuta:";
private String query=null;

	
	

		

	public void inserisciPagamento(Pagamento p) throws SQLException {
		


		query="INSERT INTO ispw.pagamento(metodo,esito,nomeUtente,spesaTotale,eMail,tipoAcquisto,idProd) values (?,?,?,?,?,?,?)";

		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				)
		{
			


			prepQ.setString(1,p.getMetodo()); // 
			prepQ.setInt(2,p.getEsito());
			prepQ.setString(3,p.getNomeUtente());
			prepQ.setFloat(4,p.getAmmontare());
			prepQ.setString(5, User.getInstance().getEmail());
			prepQ.setString(6,p.getTipo());
			prepQ.setInt(7, p.getId());
			prepQ.execute();
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
					ResultSet rs=prepQ.executeQuery())
			{
				prepQ.setInt(1, 0);
		
				prepQ.executeUpdate();

			}catch(SQLException e)
			{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
			}



	}
	public ObservableList<Pagamento> getPagamenti() throws SQLException  {

			ObservableList<Pagamento> catalogo=FXCollections.observableArrayList();
			query="SELECT id_op,metodo,esito,nomeUtente,spesaTotale,tipoAcquisto,idProd from ispw.pagamento where eMail=?";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
			{
				
			prepQ.setString(1, User.getInstance().getEmail());
			while(rs.next())
			{


				catalogo.add(new Pagamento (rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getFloat(5),rs.getString(6)));

			}
			}catch(SQLException e)
			{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
			}
		return catalogo;
	}
	
	public List<Pagamento> getPagamentiList() throws SQLException  {

		List<Pagamento> catalogo=new ArrayList<>();
		query="SELECT id_op,metodo,esito,nomeUtente,spesaTotale,tipoAcquisto,idProd from ispw.pagamento where nomeUtente=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
			
		prepQ.setString(1,User.getInstance().getNome());
		
		while(rs.next())
		{


			catalogo.add(new Pagamento (rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getFloat(5),rs.getString(6)));

		}
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
	return catalogo;
}

	
		
	public int retUltimoOrdine() throws SQLException 
	{
		int id=0;
		
			query="select count(*) as massimo from ispw.pagamento";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
			{
		
			while ( rs.next() ) {
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
		int row=0;
		query="delete from ispw.pagamento"
				+"where id_op=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())

		{
				
			prepQ.setInt(1, idC);
			
			
			
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