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
import bean.GiornaleBean;
import bean.SystemBean;
import database.GiornaleDao;
import raccolta.Giornale;

/**
 * Servlet implementation class GiornaleServlet
 */
@WebServlet("/GiornaleServlet")
public class GiornaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static GiornaleBean gB=new GiornaleBean();
	private static GiornaleDao gD=new GiornaleDao();
	private static int lunghezza;
	private static Giornale g=new Giornale();
	private static ExceptionBean eB=new ExceptionBean();
	private static String errore="/errore.jsp";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiornaleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SystemBean.getIstance().setType("giornale");
		
		String id=request.getParameter("idG");
		
		try {
			 lunghezza=gD.getGiornaliList().size();
			 SystemBean.getIstance().setElemLista(lunghezza);
		
			if(Integer.parseInt(id)==0)
			{
						
				
					gB.setMiaListaG(gD.getGiornaliList());			
					request.setAttribute("bean", gB);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/giornali.jsp"); 
					view.forward(request,response); 
			}
			else if(Integer.parseInt(id)>=1 && Integer.parseInt(id)<=lunghezza)
			{
				
				g.setId(Integer.parseInt(id));
				gB.setMiaListaG(gD.getGiornaliListSingolo(g));
				request.setAttribute("bean",gB);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/giornaleSingolo.jsp"); 
				view.forward(request,response); 
				
			}
			else if(Integer.parseInt(id)>=1 && Integer.parseInt(id)>lunghezza)
			{
				eB.setE(new NumberFormatException(" indice eccede lista"));
				request.setAttribute("bean1",eB);
				RequestDispatcher view = getServletContext().getRequestDispatcher(errore); 
				view.forward(request,response); 
			}
			else 
			{
				eB.setE(new NumberFormatException(" indice minore di 0"));
				request.setAttribute("bean1",eB);
				RequestDispatcher view = getServletContext().getRequestDispatcher(errore); 
				view.forward(request,response); 
			}
			
		} catch (SQLException e) {
		
			eB.setE(e);
			request.setAttribute("bean",eB);
			RequestDispatcher view = getServletContext().getRequestDispatcher(errore); 
			view.forward(request,response); 
		}
	}
    

}
