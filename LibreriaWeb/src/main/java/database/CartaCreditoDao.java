package database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;

import model.CartaDiCredito;
import utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CartaCreditoDao {
	private PreparedStatement stmt=null;

	private  String n;
	private  String cog;
	private Connection conn=null;
	private ResultSet rs=null;
	private Statement stmt1=null;
	private PreparedStatement prepQ=null;



	public ObservableList<CartaDiCredito> getCarteCredito(String nome) throws SQLException 
	{
		String cod;
		/*
		 * uare funzione internet
		 */
		ObservableList<CartaDiCredito> catalogo=FXCollections.observableArrayList();
		String listaCC="select nomeP,cognomeP,codiceCarta from cartacredito "
				+"where nomeP=?";
		
			conn=ConnToDb.generalConnection();

			stmt1= conn.createStatement();
			prepQ.setString(1, nome);

				rs=stmt1.executeQuery(listaCC);
			

				while(rs.next())
				{
					n=rs.getString(1);
					cog=rs.getString(2);
					cod=rs.getString(3);


					catalogo.add(new CartaDiCredito(n,cog,cod, null, cod,0));


				}
				
				conn.close();

			

		return catalogo;


	}	

	public void daiPrivilegi() throws SQLException
	{

		
			conn= ConnToDb.generalConnection();
			stmt = conn.prepareStatement(" SET SQL_SAFE_UPDATES=0");
			stmt.executeUpdate();


		conn.close();



	}
	public void insCC(CartaDiCredito cc) throws SQLException
	{

		String query;
		n = cc.getNomeUser();
		String c = cc.getCognomeUser(); 
		String num=cc.getNumeroCC();
		String pin=cc.getCiv();
		Float amm= cc.getPrezzoTransazine();		 
		
			conn=ConnToDb.generalConnection();
			query="insert into cartacredito (nomeP,cognomeP,codiceCarta,scad,codicePin,ammontare)  values(?,?,?,?,?,?)";
			stmt=conn.prepareStatement(query);


			stmt.setString(1,n);
			stmt.setString(2, c);
			stmt.setString(3, num);
			stmt.setDate(4, (Date) cc.getScadenza());
			stmt.setString(5,pin);
			stmt.setFloat(6, amm);
			stmt.executeUpdate();





		conn.close();


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
		
			conn=ConnToDb.generalConnection();
			stmt1=conn.createStatement();
			prepQ.setString(1,codice);
			 rs=stmt1.executeQuery(popolaDati);
			

				while(rs.next())
				{
					n=rs.getString(1);
					cog=rs.getString(2);
					cod=rs.getString(3);



				}

				cc.setNomeUser(n);
				cc.setCognomeUser(cog);
				cc.setNumeroCC(cod);
				
	
		conn.close();




		return cc;



	}


}
