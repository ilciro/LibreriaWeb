package database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.Date;

import model.CartaDiCredito;
import model.Log;
import utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CartaCreditoDao {
	

	private  String n;
	private  String cog;
	private static String eccezione="eccezione ottenuta:";




	public ObservableList<CartaDiCredito> getCarteCredito(String nome) throws SQLException 
	{
		String cod;
		/*
		 * uare funzione internet
		 */
		ObservableList<CartaDiCredito> catalogo=FXCollections.observableArrayList();
		String listaCC="select nomeP,cognomeP,codiceCarta from cartacredito "
				+"where nomeP=?";
		
		try(Connection conn=ConnToDb.generalConnection();
			PreparedStatement prepQ=conn.prepareStatement(listaCC);
			ResultSet rs=prepQ.executeQuery())
		{
			
			prepQ.setString(1, nome);	

				while(rs.next())
				{
					n=rs.getString(1);
					cog=rs.getString(2);
					cod=rs.getString(3);


					catalogo.add(new CartaDiCredito(n,cog,cod, null, cod,0));


				}
		}catch(SQLException e)
		{
			Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
						

		return catalogo;


	}	

	public void daiPrivilegi() throws SQLException
	{

		
			String privilegi=" SET SQL_SAFE_UPDATES=?";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(privilegi);
					ResultSet rs=prepQ.executeQuery())
				{	
					prepQ.setInt(1, 0);
				}
			catch(SQLException e)
			{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
			}


	}
	public void insCC(CartaDiCredito cc) throws SQLException
	{
	 
		
			
			String query="insert into cartacredito (nomeP,cognomeP,codiceCarta,scad,codicePin,ammontare)  values(?,?,?,?,?,?)";
			
			try(Connection conn=ConnToDb.generalConnection();
								PreparedStatement prepQ=conn.prepareStatement(query);
								)
							{
			


								prepQ.setString(1,cc.getNomeUser());
								prepQ.setString(2, cc.getCognomeUser());
								prepQ.setString(3, cc.getNumeroCC());
								prepQ.setDate(4, (Date) cc.getScadenza());
								prepQ.setString(5,cc.getCiv());
								prepQ.setFloat(6, cc.getPrezzoTransazine());
								prepQ.execute();
							}catch(SQLException e)
				{
								Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
				}
			




	}

	
	public CartaDiCredito  popolaDati(CartaDiCredito cc) throws SQLException
	{
		String cod;

		String codice=cc.getNumeroCC();
		n = null;
		cog = null;
		cod = null;

		String popolaDati="select nomeP,cognomeP,codiceCarta,scad from cartacredito"+
		"where codiceCarta=?";
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(popolaDati);
				ResultSet rs=prepQ.executeQuery())
			{
		
				prepQ.setString(1,codice);

				while(rs.next())
				{
					n=rs.getString(1);
					cog=rs.getString(2);
					cod=rs.getString(3);



				}

				cc.setNomeUser(n);
				cc.setCognomeUser(cog);
				cc.setNumeroCC(cod);
			}catch(SQLException e)
		{
				Log.LOGGER.log(Level.SEVERE,eccezione,e.getCause());
		}
		return cc;


	}


}
