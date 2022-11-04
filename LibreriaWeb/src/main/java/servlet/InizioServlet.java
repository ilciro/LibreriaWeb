package servlet;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Log;

/**
 * Servlet implementation class InizioServlet
 */
@WebServlet("/InizioServlet")
public class InizioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InizioServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException  {
		String l=request.getParameter("buttonL");
		String g=request.getParameter("buttonG");
		String r=request.getParameter("buttonR");
		String log=request.getParameter("buttonLogin");
		String cerca=request.getParameter("buttonRic");
		try {
		if(l!=null && l.equals("libri"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/sceltaLibro.jsp"); 
			view.forward(request,response); 
		}
		else if(g!=null && g.equals("giornali"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/sceltaGiornali.jsp"); 
			view.forward(request,response); 
		}
		else if(r!=null && r.equals("riviste"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/sceltaRiviste.jsp"); 
			view.forward(request,response); 
		}
		else if(log!=null && log.equals("login"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/login.jsp"); 
			view.forward(request,response); 
		}
		else if(cerca!=null && cerca.equals("ricerca"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/ricerca.jsp"); 
			view.forward(request,response); 
		}
		}catch(ServletException |IOException  e)
		{
			Log.LOGGER.log(Level.SEVERE,"eccezione ottenuta ",e.getCause());
		}
		
	}

}
