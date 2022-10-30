package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String buttonR=request.getParameter("buttonR");
		String buttonRac=request.getParameter("buttonRac");
		String buttonU=request.getParameter("buttonUt");
		String buttonLog=request.getParameter("buttonLog");
		
		if(buttonR!=null && buttonR.equals("genera report"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/report.jsp"); 
			view.forward(request,response);
		
		}
		else if(buttonRac!=null && buttonRac.equals("genera raccolta"))
		{
			

			RequestDispatcher view = getServletContext().getRequestDispatcher("/raccolta.jsp"); 
			view.forward(request,response);
		}
		else if(buttonU!=null && buttonU.equals("gestione utenti"))
		{
			
			RequestDispatcher view = getServletContext().getRequestDispatcher("/utenti.jsp"); 
			view.forward(request,response);
		}
		if(buttonLog!=null && buttonLog.equals("logout"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/index.html"); 
			view.forward(request,response);
		}
	}

}
