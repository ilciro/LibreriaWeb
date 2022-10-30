package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.NegozioBean;
import database.NegozioDao;
import model.Negozio;

/**
 * Servlet implementation class RititoNegozioServlet
 */
@WebServlet("/RitiroNegozioServlet")
public class RitiroNegozioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NegozioBean nB=new NegozioBean();
	private NegozioDao nD=new NegozioDao();
	private Negozio n1=new Negozio();

       
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String negozio=request.getParameter("negS");
		
	
		nB.setNome(negozio);
		n1.setNome(nB.getNome());
		
		
		
		try {
			
			nB.setIsOpen(nD.checkOpen(n1));
			nB.setIsValid(nD.checkRitiro(n1));
			
			if((nB.getIsValid()==true) && (nB.getIsOpen()==true))
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
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
		
		
		
	
