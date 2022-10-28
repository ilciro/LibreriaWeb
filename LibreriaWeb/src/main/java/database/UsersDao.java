package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;

import utilities.ConnToDb;
import model.TempUser;
import model.User;
import model.Log;

public class UsersDao {

	private static Statement st = null ;
	private static String query ;
	private static ResultSet rs;
	private static PreparedStatement prepQ = null;
	private static Connection conn;
	private static int max;
	private static String r;
	private static boolean state=false;



	// use this function on controller after you had check the email
	// add an user on db after registration
	// prende i dati dall'oggetto che gli abbiamo passato 
	public static boolean createUser(User u) throws SQLException
	{
		LocalDate d=u.getDataDiNascita();
		
			
				conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				
				query= "INSERT INTO `ispw`.`users`"
						+ "(`Nome`,"
						+ "`Cognome`,"
						+ "`Email`,"
						+ "`pwd`,"
						+ "`DataDiNascita`)"
						+ "VALUES"
						+" "
						+ "(?,?,?,?,?)";
				prepQ = conn.prepareStatement(query);	
				prepQ.setString(1,User.getInstance().getNome()); 
				prepQ.setString(2,User.getInstance().getCognome()); 
				prepQ.setString(3,User.getInstance().getEmail());
				prepQ.setString(4, User.getInstance().getPassword());
				prepQ.setDate(5, java.sql.Date.valueOf(d));  
				prepQ.executeUpdate();
				state= true; 		 			 	
			
		
		conn.close();
		return state;

	}

	//Uso questa funzione quando un admin deve creare un utente 
	//tramite il terzo caso d'uso per la gestione del sito  
	public static boolean createUser2(TempUser uT) throws SQLException
	{

		LocalDate d=uT.getDataDiNascita();
		

			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query= "INSERT INTO `ispw`.`users`"
					+ "(`idRuolo`,"
					+ "`Nome`,"
					+ "`Cognome`,"
					+ "`Email`,"
					+ "`pwd`,"
					+ "`descrizione`,"
					+ "`DataDiNascita`)"
					+ "VALUES (?,?,?,?,?,?,?)";
			prepQ = conn.prepareStatement(query);	
			prepQ.setString(1,uT.getIdRuolo().substring(0,1));
			prepQ.setString(2,uT.getNome()); 
			prepQ.setString(3,uT.getCognome()); 
			prepQ.setString(4,uT.getEmail());
			prepQ.setString(5, uT.getPassword());
			prepQ.setString(6, uT.getDescrizione());
			// alternativa NO se rompe tutto se passi un oggetto di tipo data java lui
			// vuole un oggetto di tipo data sql 
			prepQ.setDate(7, java.sql.Date.valueOf(d)); 
			//prepQ.setString(7,U.getInstance())
			prepQ.executeUpdate();

			state= true; // true		 			 	

		
		conn.close();
		// errore

		return state;

	}

	//check User email if we found that we return true else we return false
	//Qui viene passato dal controller un oggetto di tipo user
	public static int checkUser(User u) throws SQLException
	{
		int  status=0;
		// ritorna int per motivi legati al controller
		// anche se lo tratto come boolean
		//levato pwd se no non aggiorna
		String email = User.getInstance().getEmail();



		

			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="SELECT idUser FROM ispw.users where Email = '"+email+"'";
			rs = st.executeQuery(query);
			if(rs.next())
			{
				 int id=rs.getInt(1);
				 Log.LOGGER.log(Level.INFO,"id user {0}",id+u.getId());

					status=1;	 			

			}
			else
			{
				status=0; // false
			}

		

		conn.close();
		return status ;
	}

	//Questo check
	public static int checkTempUser(TempUser uT) throws SQLException
	{
		// ritorna int per motivi legati al controller
		// anche se lo tratto come boolean
		String email = uT.getEmail();
		int status = -1;
		

			conn = ConnToDb.generalConnection();
			st=conn.createStatement();

			query="SELECT idUser FROM ispw.users where Email = '"+email+"'";
			rs = st.executeQuery(query);
			if(rs.next())
			{
								 	
				status=1; // true
				
				// account al ready exists
			}
			else
			{
				status= 0; 
				// new account
			}


		conn.close();
		return status;
	}

	public static String getRuolo (User u) throws SQLException
	{

		String email = u.getEmail();
		

			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="SELECT idRuolo FROM ispw.users where Email = '"+email+"'";
			rs = st.executeQuery(query);
			if(rs.next())
			{
				r =rs.getString(1);
				User.getInstance().setIdRuolo(r);

			}
			

		
		
		// errore
		
			
				conn.close();
		

			
		

		return r;

	}

	// this function check if you have changed password successfully 
	public static boolean checkResetpass (User u, String pwd,String email ) throws SQLException
	{

		
			conn = ConnToDb.generalConnection();
			query="Update ispw.users SET pwd = ?  where Email = '"+email+"'";

			
			prepQ=conn.prepareStatement(query);
			prepQ.setString(1,pwd);
			prepQ.executeUpdate();			
			
			Log.LOGGER.log(Level.INFO,"update pwd ok .{0}",u.getNome());
			state= true;

		conn.close();
		// errore
		return state ;
	}

	//
	public static TempUser findUser(TempUser u)
	{
		r = TempUser.getInstance().getIdRuolo();
		u.setIdRuolo(r);
		return u;

	}

	// delete a user from db  terzo caso d'uso

	public static boolean deleteUser(User user) throws SQLException
	{
		String email = user.getEmail();
		String ruolo=user.getIdRuolo();
		
		
			/*
			 * Levo if multipli e cancello in base ad email 
			 */
			
				conn = ConnToDb.generalConnection();
				
				query="DELETE FROM ispw.users WHERE Email = ?";
				prepQ=conn.prepareStatement(query);
				prepQ.setString(1,email);
				prepQ.executeUpdate();
				Log.LOGGER.log(Level.INFO,"cancello utente user .{0}" ,ruolo);
				state= true;

			

			

		
				conn.close();
		
		return state ;
		
	}

	public static boolean deleteTempUser(TempUser uT) throws SQLException 
	{
		String email = uT.getEmail();
		

			conn = ConnToDb.generalConnection();
			
			query="DELETE FROM ispw.users WHERE Email = ?";
			prepQ=conn.prepareStatement(query);
			prepQ.setString(1,email);
			prepQ.executeUpdate();
			Log.LOGGER.log(Level.INFO,"cancello utente user .{0}" ,uT.getIdRuolo());
			state= true;



		conn.close();
		return state ;
	}

	// Con pickData prendo i dati dall'utente creato per il login
	// per poi restituirlo in un nuovo oggetto di tipo User
	// e poi il controller lo specializza nelle 4 classi 
	public static User pickData(User u) throws SQLException
	{
		String email = u.getEmail();
		
			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			query="SELECT `idRuolo`,"
					+ "    `Nome`,"
					+ "    `Cognome`,"
					+ "    `Email`,"
					+ "    `descrizione`,"
					+ "    `DataDiNascita` "
					+ "FROM users where Email = '"+email+"' ;";
			rs = st.executeQuery(query);
			while(rs.next())
			{
				// setto i vari dati 
				u.setIdRuolo(rs.getString(1));
				u.setNome(rs.getString(2));
				u.setCognome(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setDescrizione(rs.getString(5));
				u.setDataDiNascita(rs.getDate(6).toLocalDate());



			}



	
		
				conn.close();
			
		
		// errore
		return u;
	}

	public static User aggiornaNome(User u) throws SQLException
	{
		String email = User.getInstance().getEmail();

		



			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set Nome=? where Email='"+email+"'";



			prepQ = conn.prepareStatement(query);

			prepQ.setString(1,User.getInstance().getNome() );
			prepQ.executeUpdate();  		 		



		conn.close();
		// errore
		return u;
	}

	public static User aggiornaCognome(User u) throws SQLException
	{
		String email = User.getInstance().getEmail();

		



			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set Cognome=? where Email='"+email+"'";



			prepQ = conn.prepareStatement(query);

			prepQ.setString(1,User.getInstance().getCognome() );
			prepQ.executeUpdate();  		 		

			conn.close();	


		
		// errore
		return u;
	}

	public static User aggiornaEmail(User u,String m) throws SQLException
	{
		String email = u.getEmail();
			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set Email=? where Email='"+email+"'";

			u.setEmail(m);

			prepQ = conn.prepareStatement(query);

			prepQ.setString(1,u.getEmail() );
			prepQ.executeUpdate();  		 		



		
		conn.close();
		// errore
		return u;
	}

	public static  User aggiornaPass(User u) throws SQLException {

		String email = u.getEmail();

		



			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set pwd=? where Email='"+email+"'";


			prepQ = conn.prepareStatement(query);

			prepQ.setString(1,u.getPassword());
			prepQ.executeUpdate();  		 		



	
			conn.close();
		
		// errore
		return u;
	}

	public static User aggiornaDesc(User u) throws SQLException {
		String email = u.getEmail();

		


			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set descrizione=? where Email='"+email+"'";


			prepQ = conn.prepareStatement(query);

			prepQ.setString(1,u.getDescrizione());
			prepQ.executeUpdate();  		 		



		
				conn.close();
			
		return u;
	}

	public static User aggiornaData(User u) throws SQLException {
		String email = User.getInstance().getEmail();
		LocalDate data=u.getDataDiNascita();




			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set DataDiNascita=? where Email='"+email+"'";



			prepQ = conn.prepareStatement(query);

			prepQ.setString(1,data.toString());
			prepQ.executeUpdate();  		 		




		
		
				conn.close();
			
		
		return u;
	}

	// Per il terzo caso d'uso creo e uso sempre il temp user per appoggiarmi all'utente che modifico  e quindi 

	public static TempUser aggiornaTempNome(TempUser uT) throws SQLException
	{
		String email = uT.getEmail();

		


			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE users set Nome=? where Email='"+email+"'";



			prepQ = conn.prepareStatement(query);

			prepQ.setString(1,uT.getNome() );
			prepQ.executeUpdate();  		 		



		
		conn.close();
		// errore
		return uT;
	}

	public static TempUser aggiornaCognome(TempUser uT) throws SQLException
	{
		String email = uT.getEmail();

		


			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set Cognome=? where Email='"+email+"'";



			prepQ = conn.prepareStatement(query);

			prepQ.setString(1,uT.getCognome() );
			prepQ.executeUpdate();  		 		




		
			conn.close();
		// errore
		return uT;
	}

	public static TempUser aggiornaEmail(TempUser uT,String m) throws SQLException
	{
		String email = uT.getEmail();

		



			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set Email=? where Email='"+email+"'";

			TempUser.getInstance().setEmail(m);

			prepQ = conn.prepareStatement(query);

			prepQ.setString(1,uT.getEmail() );
			prepQ.executeUpdate();  		 		



		
			conn.close();
		
		// errore
		return uT;
	}

	public static TempUser aggiornaTempUtente(TempUser uT, String emailN) throws SQLException
	{
		String email = uT.getEmail();

		
			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set idRuolo=?,Nome=?,Cognome=?,Email=?,pwd=?,descrizione=?,DataDiNascita=? where Email='"+email+"'";

			uT.setEmail(emailN);


			prepQ = conn.prepareStatement(query);


			// setto i vari dati 
			prepQ.setString(1,uT.getIdRuolo());
			prepQ.setString(2,uT.getNome() );
			prepQ.setString(3, uT.getCognome());
			prepQ.setString(4, uT.getEmail());
			prepQ.setString(5, uT.getPassword());
			prepQ.setString(6, uT.getDescrizione());
			prepQ.setString(7, uT.getDataDiNascita().toString());




			prepQ.executeUpdate();






		
			conn.close();
		
		// errore
		return uT;
	}

	public static TempUser aggiornaTempPass(TempUser uT) throws SQLException {

		String email = uT.getEmail();

		

			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set pwd=? where Email='"+email+"'";


			prepQ = conn.prepareStatement(query);

			prepQ.setString(1,uT.getPassword());
			prepQ.executeUpdate();  		 		




		
		
			conn.close();
		
		// errore
		return uT;
	}

	public static TempUser aggiornaTempDesc(TempUser uT) throws SQLException {
		String email = uT.getEmail();

		


			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set descrizione=? where Email='"+email+"'";


			prepQ = conn.prepareStatement(query);

			prepQ.setString(1,uT.getDescrizione());
			prepQ.executeUpdate();  		 		



		
				conn.close();
			
		// errore
		return uT;
	}

	public static TempUser aggiornaTempData(TempUser uT) throws SQLException {
		String email = uT.getEmail();

		



			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set DataDiNascita=? where Email='"+email+"'";


			prepQ = conn.prepareStatement(query);

			prepQ.setString(1,uT.getDataDiNascita().toString());
			prepQ.executeUpdate();  		 		



		
			conn.close();
		
		// errore
		return uT;
	}


	public static  void getListaUtenti() throws IOException, SQLException  {

		conn= ConnToDb.generalConnection();
		FileWriter w;
		w=new FileWriter("ReportFinale\\riepilogoUtenti.txt");

		
		try (BufferedWriter b=new BufferedWriter (w)) {

			st=conn.createStatement();
			rs = st.executeQuery("SELECT * FROM users");




			while(rs.next())
			{

				TempUser.getInstance().setId(rs.getInt(1));
				TempUser.getInstance().setIdRuolo(rs.getString(2));
				TempUser.getInstance().setNome(rs.getString(3));
				TempUser.getInstance().setCognome(rs.getString(4));
				TempUser.getInstance().setEmail(rs.getString(5));
				TempUser.getInstance().setDescrizione(rs.getString(7));
				TempUser.getInstance().setDataDiNascita(rs.getDate(8).toLocalDate());
				b.write(""+TempUser.getInstance().getId()+"\t"+TempUser.getInstance().getIdRuolo()+"\t"+TempUser.getInstance().getNome()+"\t"+TempUser.getInstance().getCognome()+
						"\t"+TempUser.getInstance().getEmail()+"\t"+TempUser.getInstance().getDescrizione()+"\t"+TempUser.getInstance().getDataDiNascita().toString()+"\n");
				b.flush();
			}
		}
		
		
			
				conn.close();
			         
		
	}

	public static TempUser getTempUserSingolo(TempUser uT) throws SQLException
	{
		int id=uT.getId();




		conn = ConnToDb.generalConnection();
		st=conn.createStatement();
		
		query="SELECT * FROM ispw.users where idUser = "+id+" ;";

		rs = st.executeQuery(query);
		while(rs.next())
		{

			uT.setIdRuolo(rs.getString(2));
			uT.setNome(rs.getString(3));
			uT.setCognome(rs.getString(4));
			uT.setEmail(rs.getString(5));
			uT.setPassword(rs.getString(6));
			uT.setDescrizione(rs.getString(7));
			uT.setDataDiNascita(rs.getDate(8).toLocalDate());


		}

		return uT;
	}

	public static User aggiornaUtente(User u) throws SQLException
	{

		LocalDate d=u.getDataDiNascita();

		
			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set idRuolo=?,Nome=?,Cognome=?,Email=?,pwd=?,descrizione=?,DataDiNascita=? where idUser="+u.getId()+"";



			prepQ = conn.prepareStatement(query);

			// setto i vari dati 
			prepQ.setString(1,u.getIdRuolo().substring(0,1));
			prepQ.setString(2,u.getNome() );
			prepQ.setString(3, u.getCognome());
			prepQ.setString(4, u.getEmail());
			prepQ.setString(5,u.getPassword());
			prepQ.setString(6, u.getDescrizione());
			prepQ.setString(7,d.toString());




			prepQ.executeUpdate();







		
			conn.close();
		
		return u;
	}

	public static int maxIdUSer() throws SQLException
	{
		conn=ConnToDb.generalConnection();
		st=conn.createStatement();
		st.execute("select max(idUser) from ispw.users");
		rs=st.getResultSet();
		if (rs.next())
		{
			max=rs.getInt(1);
		}
		return max;
	}

	public static TempUser aggiornaUtenteTemp(TempUser uT) throws NullPointerException, SQLException
	{
		
		//uso subsequence per prendere 1 lettera
		



			conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			
			query="UPDATE ispw.users set idRuolo=?,Nome=?,Cognome=?,Email=?,pwd=?,descrizione=?,DataDiNascita=? where idUser="+uT.getId()+"";



			prepQ = conn.prepareStatement(query);

			
			prepQ.setString(1,(String) uT.getIdRuolo().subSequence(0, 1));
			prepQ.setString(2,uT.getNome() );
			prepQ.setString(3, uT.getCognome());
			prepQ.setString(4, uT.getEmail());
			prepQ.setString(5, uT.getPassword());
			prepQ.setString(6, uT.getDescrizione());
			prepQ.setString(7, uT.getDataDiNascita().toString());

			prepQ.executeUpdate();





		
			conn.close();
		
		// errore
		return uT;
	}
	
	private UsersDao()
	{}

}