package servlet;


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TextAreaBean;

/**
 * Servlet implementation class RaccoltaServlet
 */
@WebServlet("/RaccoltaServlet")
public class RaccoltaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TextAreaBean tAB=new TextAreaBean();
	private String s;
       
    /**
     * @see HttpServlet#HttpServlet()S
     */
    public RaccoltaServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String buttonTot=request.getParameter("totaleC");
			String buttonL=request.getParameter("totaleL");
			String buttonG=request.getParameter("totaleG");
			String buttonR=request.getParameter("totaleR");
			String buttonRacc=request.getParameter("totaleRacc");
			String buttonI=request.getParameter("buttonI");
			
			
			
			
			 if(buttonTot!=null && buttonTot.equals("totale") )
			{
				
								s="";
				tAB.setS(s);
				
				
					s+=tAB.generaReportL();
					s+=tAB.generaReportG();
					s+=tAB.generaReportR();
					s+=tAB.getListaUtenti();
					tAB.setS(s);
					request.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/report.jsp"); 
					view.forward(request,response);			
				
					
			}
			else if(buttonL!=null && buttonL.equals("libri") )
			{
				s="";
				tAB.setS(s);
				
				
					s+=tAB.generaReportL();
					tAB.setS(s);
					request.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/report.jsp"); 
					view.forward(request,response);			
				
					
			}
			else if(buttonG!=null && buttonG.equals("giornale") )
			{
				s="";
				tAB.setS(s);
				
				
					
					s+=tAB.generaReportG();
					tAB.setS(s);
					request.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/report.jsp"); 
					view.forward(request,response);			
				
					
			}
			else if(buttonR!=null && buttonR.equals("rivista")  )
			{
				s="";
				tAB.setS(s);
				
				
					
					s+=tAB.generaReportR();
					tAB.setS(s);
					request.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/report.jsp"); 
					view.forward(request,response);			
				
					
			}
			else if(buttonRacc!=null && buttonRacc.equals("raccolta")  )
			{
				s="";
				tAB.setS(s);
			
				
					s+=tAB.generaReportL();
					s+=tAB.generaReportG();
					s+=tAB.generaReportR();
					tAB.setS(s);
					request.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/report.jsp"); 
					view.forward(request,response);			
				
					
			}
			else if(buttonI!=null && buttonI.equals("indietro") )
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/admin.jsp"); 
				view.forward(request,response);
			}
			
			
			
			
				
					
			} catch (SQLException | IOException e) {
		
				e.printStackTrace();
			}
		
		
	}
	
}
