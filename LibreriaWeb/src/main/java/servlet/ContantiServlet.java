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
 * Servlet implementation class ContantiServlet
 */
@WebServlet("/ContantiServlet")
public class ContantiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExceptionBean bE =new ExceptionBean();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContantiServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String negozio=request.getParameter("negozioC");
    	
    	
    	//SystemBean.getIstance().setSpesaT((float)11.3);
    	SystemBean.getIstance().setMetodoP("cash");
    	
    	if(negozio==null)
    	{
    		SystemBean.getIstance().setNegScelto(false);
        	System.out.println("NEgozio :"+ negozio);


    	}
    	else
    	{
    		SystemBean.getIstance().setNegScelto(true);
        	System.out.println("NEgozio :"+ negozio);


    	}
    	if(SystemBean.getIstance().getSpesaT()>0.0 ) 
    	{
    		request.setAttribute("bean",SystemBean.getIstance());
    		
    		RequestDispatcher view = getServletContext().getRequestDispatcher("/contanti.jsp"); 
    		view.forward(request,response);
    		
    	}
    	
    	else {
    		bE.setE(new NumberFormatException("spesa totale < 0.0"));
    		request.setAttribute("bean1",bE);
    		RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
    		view.forward(request,response);
    	}
    	
	}
	

}
