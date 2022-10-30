package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SystemBean;

/**
 * Servlet implementation class ModificaServlet
 */
@WebServlet("/ModificaServlet")
public class ModificaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String boxL=request.getParameter("boxL");
		String boxG=request.getParameter("boxG");
		String boxR=request.getParameter("boxR");
		String indietro=request.getParameter("annullaB");
		
		if(boxL!=null && boxL.equals("libri"))
		{
			SystemBean.getIstance().setType("libro");
			
			request.setAttribute("bean1",SystemBean.getIstance());
			RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaLibro.jsp"); 
    		view.forward(request,response);
		}
		if(boxG!=null && boxG.equals("giornali"))
		{
			SystemBean.getIstance().setType("giornale");
			request.setAttribute("bean1",SystemBean.getIstance());
			RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaGiornale.jsp"); 
    		view.forward(request,response);
		}
		if(boxR!=null && boxR.equals("riviste"))
		{
			//iniziato
			SystemBean.getIstance().setType("rivista");			
			request.setAttribute("bean1",SystemBean.getIstance());
			RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaRivista.jsp"); 
    		view.forward(request,response);
		}
		if(indietro!=null && indietro.equals("indietro"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/homeAdmin.jsp"); 
    		view.forward(request,response);
		}
	}

}
