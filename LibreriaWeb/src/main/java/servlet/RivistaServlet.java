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
import bean.RivistaBean;
import bean.SystemBean;
import database.RivistaDao;
import model.Log;
import raccolta.Rivista;

/**
 * Servlet implementation class RivistaServlet
 */
@WebServlet("/RivistaServlet")
public class RivistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RivistaBean rB=new RivistaBean();
	private static RivistaDao rD=new RivistaDao();
	private static Rivista r=new Rivista();
	private static ExceptionBean eB=new ExceptionBean();
	private static String bean1="bean1";
	private static String errore="/errore.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RivistaServlet() {
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
		SystemBean.getIstance().setType("rivista");		
		String id=request.getParameter("idR");
		int lunghezza=0;
		
		try {
			lunghezza=rD.getRivisteList().size();
			SystemBean.getIstance().setElemLista(lunghezza);
			
		
			if(Integer.parseInt(id)==0)
			{
						
				
					rB.setListaR(rD.getRivisteList());		
					request.setAttribute("bean", rB);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/riviste.jsp"); 
					view.forward(request,response); 
			}
			else if(Integer.parseInt(id)>=1 && Integer.parseInt(id)<=lunghezza)
			{
				
				r.setId(Integer.parseInt(id));
				rB.setListaR(rD.getRivistaSingoloByIdLista(r));
				request.setAttribute("bean",rB);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/mostraRivista.jsp"); 
				view.forward(request,response); 
				
			}
			else if(Integer.parseInt(id)>=1 && Integer.parseInt(id)>lunghezza)
			{
				eB.setE(new NumberFormatException(" indice eccede lista"));
				request.setAttribute(bean1,eB);
				RequestDispatcher view = getServletContext().getRequestDispatcher(errore); 
				view.forward(request,response); 
			}
			else 
			{
				eB.setE(new NumberFormatException(" indice minore di 0"));
				request.setAttribute(bean1,eB);
				RequestDispatcher view = getServletContext().getRequestDispatcher(errore); 
				view.forward(request,response); 
			}
			

		 
			
		
		
		
			
		} catch (SQLException| ServletException| NumberFormatException e) {
				Log.LOGGER.log(Level.SEVERE," eccezione ottenuta {}" , e.getMessage());
		}
		
	}

}
