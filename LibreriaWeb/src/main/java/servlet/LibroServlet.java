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
import bean.LibroBean;
import bean.SystemBean;
import database.LibroDao;
import raccolta.Libro;



/**
 * Servlet implementation class LibroServlet
 */
@WebServlet("/LibroServlet")
public class LibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibroBean lB=new LibroBean();
	private LibroDao lD=new LibroDao();
	private Libro l=new Libro();
	private ExceptionBean eB=new ExceptionBean();
	private int lunghezza;
	

    /**
     * @see HttpServlet#HttpServlet()
     */
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SystemBean.getIstance().setType("libro");		
		String id=request.getParameter("idL");
		
		
		try {
			lunghezza=lD.getLibriSingoloList().size();
			SystemBean.getIstance().setElemLista(lunghezza);
			
		
			if(Integer.parseInt(id)==0)
			{
						
				
					lB.setMiaLista(lD.getLibriSingoloList());			
					request.setAttribute("bean", lB);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/libri.jsp"); 
					view.forward(request,response); 
			}
			else if(Integer.parseInt(id)>=1 && Integer.parseInt(id)<=lunghezza)
			{
				
				l.setId(Integer.parseInt(id));
				lB.setMiaLista(lD.getLibriSingoloByIdLista(l));
				request.setAttribute("bean",lB);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/mostraLibro.jsp"); 
				view.forward(request,response); 
				
			}
			else if(Integer.parseInt(id)>=1 && Integer.parseInt(id)>lunghezza)
			{
				eB.setE(new NumberFormatException(" indice eccede lista"));
				request.setAttribute("bean1",eB);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
				view.forward(request,response); 
			}
			else 
			{
				eB.setE(new NumberFormatException(" indice minore di 0"));
				request.setAttribute("bean1",eB);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
				view.forward(request,response); 
			}
			

		 
			
		
		
		
			
		} catch (SQLException| ServletException| NumberFormatException e) {
			eB.setE(e);
		
			request.setAttribute("bean1",eB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
			view.forward(request,response); 
		}
		
		
		
		
	}

}
