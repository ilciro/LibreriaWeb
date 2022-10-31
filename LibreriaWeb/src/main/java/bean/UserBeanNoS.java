package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import model.Log;
import utilities.ConnToDb;

public class UserBeanNoS {
	enum RuoliB {
		ADMIN,
		UTENTE,
		SCRITTORE,
		EDITORE;
 }
	private int id;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String descrizione;
	private LocalDate dataDiNascita;
	private String r;
	private String via;
	private String com;
	private List<UserBeanNoS> listaDb;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public List<UserBeanNoS> getListaDb() {
		return listaDb;
	}
	public void setListaDb(List<UserBeanNoS> listaDb) {
		this.listaDb = listaDb;
	}
	
	public List<UserBeanNoS>getListaUtenti() throws SQLException  {

		List<UserBeanNoS>utentiL=new ArrayList<>();
		String listaU="select * from ispw.users";
		
		try(Connection conn=ConnToDb.generalConnection();
			PreparedStatement prepQ=conn.prepareStatement(listaU);	
			ResultSet rs=prepQ.executeQuery())
		{
			while(rs.next())
			{
				UserBeanNoS uS=new UserBeanNoS();
				uS.setId(rs.getInt(1));
				uS.setR(rs.getString(2));
				uS.setNome(rs.getString(3));
				uS.setCognome(rs.getString(4));
				uS.setEmail(rs.getString(5));
				uS.setDescrizione("utente generico");
				uS.setDataDiNascita(rs.getDate(8).toLocalDate());
				
				utentiL.add(uS);

			
			}
		
		}catch(SQLException e) {
			Log.LOGGER.log(null);
		}
		
	
			         
		return utentiL;
	}

	public List<UserBeanNoS>getListaUtente() throws SQLException  {

		
		List<UserBeanNoS>utentiL=new ArrayList<>();
		String utenteS="select* from ispw.users where idUser='"+UserBean.getInstance().getId()+"'";
		
		try(Connection conn=ConnToDb.generalConnection();
			PreparedStatement prepQ=conn.prepareStatement(utenteS);
			ResultSet rs=prepQ.executeQuery();)
		{
			while(rs.next())
			{
				UserBeanNoS uS=new UserBeanNoS();
				uS.setId(rs.getInt(1));
				uS.setR(rs.getString(2));
				uS.setNome(rs.getString(3));
				uS.setCognome(rs.getString(4));
				uS.setEmail(rs.getString(5));
				uS.setDescrizione("utente generico");
				uS.setDataDiNascita(rs.getDate(8).toLocalDate());
				
				utentiL.add(uS);

			
			}
		
		
			
		}catch(SQLException e )
		{
			Log.LOGGER.log(Level.SEVERE,"eccezione ottenuta ",e.getCause());
		}
			


			         
		return utentiL;
	}


}
