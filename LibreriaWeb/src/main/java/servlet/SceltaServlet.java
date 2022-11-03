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
import bean.SystemBean;
import database.GiornaleDao;
import database.LibroDao;
import database.RivistaDao;
import raccolta.Giornale;
import raccolta.Libro;
import raccolta.Rivista;

/**
 * Servlet implementation class SceltaServlet
 */
@WebServlet("/SceltaServlet")
public class SceltaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Libro l=new Libro();
	private static LibroDao lD=new LibroDao();
	private static ExceptionBean bE=new ExceptionBean();
	private static Giornale g=new Giornale();
	private static GiornaleDao gD=new GiornaleDao();
	private static Rivista r=new Rivista();
	private static RivistaDao rD=new RivistaDao();
	private static String quantitaM=" quantita minore di 1";
	private static String bean1="bean1";
	private static String scelta="/scelta.jsp";


       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SceltaServlet() {
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
		try {
			String quantita=request.getParameter("quantitaL");
			if(SystemBean.getIstance().getType().equals("libro"))
			{
				
				if(Integer.parseInt(quantita)<1)
				{
					bE.setE(new NumberFormatException(quantitaM));
					request.setAttribute(bean1, bE);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/libri.jsp"); 
					view.forward(request,response); 
				}
				else {
				SystemBean.getIstance().setQuantita(Integer.parseInt(quantita));
				
				l.setId(SystemBean.getIstance().getId());
				float prezzo=lD.getCosto(l);				
				SystemBean.getIstance().setSpesaT(prezzo*Float.parseFloat(quantita));			
				
				request.setAttribute("bean", SystemBean.getIstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher(scelta); 
				view.forward(request,response); 
				}
			}
			else if(SystemBean.getIstance().getType().equals("giornale"))
			{
				if(Integer.parseInt(quantita)<1)
				{
					bE.setE(new NumberFormatException(quantitaM));
					request.setAttribute(bean1, bE);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/giornali.jsp"); 
					view.forward(request,response); 
				}
				else {
				SystemBean.getIstance().setQuantita(Integer.parseInt(quantita));
				
				g.setId(SystemBean.getIstance().getId());
				float prezzo=gD.getCosto(g);				
				SystemBean.getIstance().setSpesaT(prezzo*Float.parseFloat(quantita));			
				
				request.setAttribute("bean", SystemBean.getIstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher(scelta); 
				view.forward(request,response); 
				}
			}
			else if(SystemBean.getIstance().getType().equals("rivista"))
			{
				if(Integer.parseInt(quantita)<1)
				{
					bE.setE(new NumberFormatException(quantitaM));
					request.setAttribute(bean1, bE);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/giornali.jsp"); 
					view.forward(request,response); 
				}
				else {
				SystemBean.getIstance().setQuantita(Integer.parseInt(quantita));
				
				r.setId(SystemBean.getIstance().getId());
				float prezzo=rD.getCosto(r);				
				SystemBean.getIstance().setSpesaT(prezzo*Float.parseFloat(quantita));			
				
				request.setAttribute("bean", SystemBean.getIstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher(scelta); 
				view.forward(request,response); 
				}
			}
		}catch(NumberFormatException |SQLException |ServletException  e) {
			bE.setE(new NumberFormatException(" quantit� non valida"));
			RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
			view.forward(request,response); 
		}
			
		 		
	}

}
