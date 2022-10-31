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

import bean.NegozioBean;
import database.NegozioDao;
import model.Log;
import model.Negozio;

/**
 * Servlet implementation class RititoNegozioServlet
 */
@WebServlet("/RitiroNegozioServlet")
public class RitiroNegozioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static NegozioBean nB=new NegozioBean();
	private static NegozioDao nD=new NegozioDao();
	private static Negozio n1=new Negozio();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RitiroNegozioServlet() {
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
		
		String negozio=request.getParameter("negS");
		
	
		nB.setNome(negozio);
		n1.setNome(nB.getNome());
		
		
		
		try {
			
			nB.setIsOpen(nD.checkOpen(n1));
			nB.setIsValid(nD.checkRitiro(n1));
			
			if((nB.getIsValid()) && (nB.getIsOpen()))
			{
				request.setAttribute("bean1", nB);

					RequestDispatcher view = getServletContext().getRequestDispatcher("/index.html"); 
					view.forward(request,response); 
			}
			else
			{
			
				nB.setIsValid(false);
				nB.setIsOpen(false);
				request.setAttribute("bean1", nB);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/negozio.jsp"); 
				view.forward(request,response); 
			}
			
			
		} catch (SQLException |ServletException e) {
			Log.LOGGER.log(Level.SEVERE,"eccezione ottenuta" ,e.getCause());

		}

	}
}
		
		
		
	
