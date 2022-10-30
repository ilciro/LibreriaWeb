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
import bean.UserBeanNoS;
import model.Log;
import model.User;

/**
 * Servlet implementation class ModificaUtenteFinaleServlet
 */
@WebServlet("/ModificaUtenteFinaleServlet")
public class ModificaUtenteFinaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserBeanNoS us=new UserBeanNoS();
	private static ExceptionBean eB=new ExceptionBean();
	
    private static String utenti="/utenti.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaUtenteFinaleServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	 final java.util.Date utilDate;
         final java.sql.Date sqlDate;
		try {
		String ruolo=request.getParameter("ruoloL");
		String nome=request.getParameter("nomeL");
		String cognome=request.getParameter("cognomeL");
		String mail=request.getParameter("mailL");
		String pass=request.getParameter("passL");
		String desc=request.getParameter("descL");
		String data=request.getParameter("dataL");
		String genera=request.getParameter("generaB");
		String mod=request.getParameter("modB");
		String ind=request.getParameter("indB");
		if(genera!=null  && genera.equals("genera"))
		{
			//generare lista
			
			
				us.setListaDb(us.getListaUtente());
				
				if(us.getListaDb()!=null)
				{
					
					
					
					request.setAttribute("bean3",us);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaUtente.jsp"); 
					view.forward(request,response);
				}
				else {
					
					eB.setE(new SQLException(" lista non popolata"));
					request.setAttribute("bean1",eB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(utenti); 
					view.forward(request,response);
				}
			
			
		}
		if(mod!=null && mod.equals("modifica") && (ruolo.equalsIgnoreCase("W") || ruolo.equalsIgnoreCase("A") || ruolo.equalsIgnoreCase("E") || ruolo.equalsIgnoreCase("U")))
			{
				if(UserBean.getInstance().checkEmail(mail)!=false)
				{
					UserBean.getInstance().setIdRuolo(ruolo);
					UserBean.getInstance().setNome(nome);
					UserBean.getInstance().setCognome(cognome);
					UserBean.getInstance().setEmail(mail);
					UserBean.getInstance().setPassword(pass);
					UserBean.getInstance().setDescrizione(desc);
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					
				    
				         utilDate = format.parse(data);
				        sqlDate = new java.sql.Date(utilDate.getTime());
				       UserBean.getInstance().setDate(sqlDate);
				    
				    User.getInstance().setIdRuolo(UserBean.getInstance().getIdRuolo());
				    User.getInstance().setNome(UserBean.getInstance().getNome());
				    User.getInstance().setCognome(UserBean.getInstance().getCognome());
				    User.getInstance().setEmail(UserBean.getInstance().getEmail());
				    User.getInstance().setPassword(UserBean.getInstance().getPassword());
				    User.getInstance().setDescrizione(UserBean.getInstance().getDescrizione());
				    User.getInstance().setId(UserBean.getInstance().getId());
				    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
					  String date = data;
		
					  //convert String to LocalDate
					  LocalDate localDate = LocalDate.parse(date, formatter);
				
					User.getInstance().setDataDiNascita(localDate);
					 
					 
						if(UserBean.getInstance().aggiornaUtente(User.getInstance())==1)
						 {
							RequestDispatcher view = getServletContext().getRequestDispatcher(utenti); 
							view.forward(request,response);
						 }
						
					
				    
			}
		}
		
		
		if(ind!=null && ind.equals("indietro"))
		{
			
				RequestDispatcher view = getServletContext().getRequestDispatcher(utenti); 
				view.forward(request,response);
			
		}
	}catch(SQLException | ServletException |ParseException e)
		{
			Log.LOGGER.log(Level.SEVERE," eccezione ottenuta ",e);
		}
	}

}
