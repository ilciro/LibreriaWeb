package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ExceptionBean;
import bean.UserBean;
import database.UsersDao;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExceptionBean eB=new ExceptionBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

		String email=request.getParameter("emailL");
		String pass=request.getParameter("passL");
		
		if((email=="" || email==null) || (pass==""|| pass==null))
		{
			eB.setE(new Exception("credenziali sbagliate"));
			request.setAttribute("bean",eB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
			view.forward(request,response); 
		}
		else {
			UserBean.getInstance().setEmail(email);
			UserBean.getInstance().setPassword(pass);
			User.getInstance().setEmail(UserBean.getInstance().getEmail());
			User.getInstance().setPassword(UserBean.getInstance().getPassword());
			
			String ruolo=UsersDao.getRuolo(User.getInstance());
			
			if(ruolo.equalsIgnoreCase("a"))
			{
				request.setAttribute("bean",UserBean.getInstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher("/admin.jsp"); 
				view.forward(request,response);
			}
			else if(ruolo.equalsIgnoreCase("u"))
			{
				request.setAttribute("bean",UserBean.getInstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher("/utente.jsp"); 
				view.forward(request,response);
			}
			else if(ruolo.equalsIgnoreCase("w"))
			{
				request.setAttribute("bean",UserBean.getInstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher("/scrittore.jsp"); 
				view.forward(request,response);
			}
			else if(ruolo.equalsIgnoreCase("e"))
			{
				request.setAttribute("bean",UserBean.getInstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher("/editore.jsp"); 
				view.forward(request,response);
			}
			else {
				
				request.setAttribute("bean",UserBean.getInstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher("/registrazione.jsp"); 
				view.forward(request,response);
				
			}
			
		}
		
	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}

}
