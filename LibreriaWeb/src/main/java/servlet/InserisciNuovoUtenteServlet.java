package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ExceptionBean;
import bean.UserBean;
import database.UsersDao;
import model.Log;
import model.TempUser;

/**
 * Servlet implementation class InserisciNuovoUtenteServlet
 */
@WebServlet("/InserisciNuovoUtenteServlet")
public class InserisciNuovoUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ExceptionBean eB=new ExceptionBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciNuovoUtenteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		
		
		String ruolo=request.getParameter("ruoloL");
		String nome=request.getParameter("nomeL");
		String cognome=request.getParameter("cognomeL");
		String mail=request.getParameter("emailL");
		String pass=request.getParameter("passL");
		String desc=request.getParameter("descL");
		String dataN=request.getParameter("dataL");
		
		 java.util.Date utilDate;
		 java.sql.Date sqlDate;
		
		if(dataN!=null &&(ruolo.equalsIgnoreCase("W") || ruolo.equalsIgnoreCase("A") || ruolo.equalsIgnoreCase("E")))
			{
				if(!UserBean.getInstance().checkEmail(mail))
				{
					UserBean.getInstance().setIdRuolo(ruolo);
					UserBean.getInstance().setNome(nome);
					UserBean.getInstance().setCognome(cognome);
					UserBean.getInstance().setEmail(mail);
					UserBean.getInstance().setPassword(pass);
					UserBean.getInstance().setDescrizione(desc);
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					
				    
				         utilDate = format.parse(dataN);
				        sqlDate = new java.sql.Date(utilDate.getTime());
				       UserBean.getInstance().setDate(sqlDate);
				   
				    
				    TempUser.getInstance().setIdRuolo(UserBean.getInstance().getIdRuolo());
				    TempUser.getInstance().setNome(UserBean.getInstance().getNome());
				    TempUser.getInstance().setCognome(UserBean.getInstance().getCognome());
				    TempUser.getInstance().setEmail(UserBean.getInstance().getEmail());
				    TempUser.getInstance().setPassword(UserBean.getInstance().getPassword());
				    TempUser.getInstance().setDescrizione(UserBean.getInstance().getDescrizione());
				    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
					  String date = dataN;
		
					  //convert String to LocalDate
					  LocalDate localDate = LocalDate.parse(date, formatter);
				
				    TempUser.getInstance().setDataDiNascita(localDate);
				    
				    
						if(UsersDao.createUser2(TempUser.getInstance()))
							{
								
								RequestDispatcher view = getServletContext().getRequestDispatcher("/utenti.jsp"); 
								view.forward(request,response);
							
							}
						else {
							eB.setE(new SQLException(" utente non inserito"));
							request.setAttribute("bean1", eB);
							RequestDispatcher view = getServletContext().getRequestDispatcher("/utenti.jsp"); 
							view.forward(request,response);
						}
					
				}
			  }
			else
			{
				eB.setE(new SQLException(" tipo utente inserito non va bene"));
				request.setAttribute("bean1", eB);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/inserisciUtente.jsp"); 
				view.forward(request,response);
			}
		}catch(SQLException|ServletException|ParseException e)
		{
			Log.LOGGER.log(Level.SEVERE,"eccezione generata ",e.getCause());
		}
	}

}
