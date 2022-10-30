package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ExceptionBean;
import bean.SystemBean;
import bean.UserBean;
import bean.UserBeanNoS;
import model.Log;
import model.TempUser;
import model.User;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ExceptionBean eB=new ExceptionBean();
	private static UserBeanNoS us=new UserBeanNoS();
	private static String ut="/utenti.jsp";
	private static String bean1="bean1";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String genera=request.getParameter("buttonG");
		String aggiungi=request.getParameter("aggiungiB");
		String modif=request.getParameter("modifB");
		String cancella=request.getParameter("cancB");
		String indietro=request.getParameter("indB");
		String id=request.getParameter("idU");

		
		
		try {
			if(id==null)
			{
				eB.setE(new NumberFormatException(" id nullo "));
				request.setAttribute(bean1,eB);
				request.setAttribute("bean2", SystemBean.getIstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher(ut); 
				view.forward(request,response);
			}
			if(genera!=null && genera.equals("genera lista") && (us.getListaUtenti())!=null)  
			{
				
					us.setListaDb(us.getListaUtenti());
					if(us.getListaDb()!=null)
					{
						
						request.setAttribute("bean",us);
						RequestDispatcher view = getServletContext().getRequestDispatcher(ut); 
						view.forward(request,response);
					}
					//vedere else
				
				
			}
			

			if(aggiungi!=null && aggiungi.equals("aggiungi"))
			{
					
				
				RequestDispatcher view = getServletContext().getRequestDispatcher("/inserisciUtente.jsp"); 
				view.forward(request,response);
				
			}
			
			if(modif!=null &&modif.equals("modifica"))
			{
				UserBean.getInstance().setId(Integer.parseInt(id));
				
				
				request.setAttribute("bean",TempUser.getInstance());
				request.setAttribute("bean2", us);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaUtente.jsp"); 
				view.forward(request,response);
				
			}
			if(cancella!=null && cancella.equals("cancella"))
			{
				
				UserBean.getInstance().setId(Integer.parseInt(id));
				User.getInstance().setId(Integer.parseInt(id));

			
				
				if(UserBean.getInstance().deleteUser(User.getInstance()))
				{
				RequestDispatcher view = getServletContext().getRequestDispatcher(ut); 
				view.forward(request,response);
				}
					
			
			
			}
			if(indietro!=null && indietro.equals("indietro"))
			{
				SystemBean.getIstance().setId(0);
				request.setAttribute("bean",us);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/admin.jsp"); 
				view.forward(request,response);
			}
		
		
		} catch (IOException | SQLException e) {
			Log.LOGGER.log(Level.SEVERE," eccezione ottenuta {}" , e.getMessage());

	}
	}
	

}
