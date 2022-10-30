package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ExceptionBean;
import bean.SystemBean;

/**
 * Servlet implementation class CCServlet
 */
@WebServlet("/CCServlet")
public class CCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExceptionBean bE=new ExceptionBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CCServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String negozio1=request.getParameter("negozioC1");
		SystemBean.getIstance().setMetodoP("cc");
		if(negozio1==null)
		{
			SystemBean.getIstance().setNegScelto(false);
        	System.out.println("NEgozio :"+ negozio1);

		}
		else
		{
			SystemBean.getIstance().setNegScelto(true);
        	System.out.println("NEgozio :"+ negozio1);

		}
		if(SystemBean.getIstance().getSpesaT()>0.0) 
    	{
    		request.setAttribute("bean",SystemBean.getIstance());

    		RequestDispatcher view = getServletContext().getRequestDispatcher("/cartaCredito.jsp"); 
    		view.forward(request,response);
    		
    	}
    	else {
    		bE.setE(new NumberFormatException("spesa < di 0.0 "));
    		request.setAttribute("bean1", bE);
    		RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
    		view.forward(request,response);
    		
    	}
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
