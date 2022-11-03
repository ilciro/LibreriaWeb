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

import bean.TextAreaBean;
import model.Log;

/**
 * Servlet implementation class RaccoltaServlet
 */
@WebServlet("/RaccoltaServlet")
public class RaccoltaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static TextAreaBean tAB=new TextAreaBean();
	private static String report="/report.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()S
     */
    public RaccoltaServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	 StringBuilder s=new StringBuilder();
    	 String finale="";
		try {
			String buttonTot=request.getParameter("totaleC");
			String buttonL=request.getParameter("totaleL");
			String buttonG=request.getParameter("totaleG");
			String buttonR=request.getParameter("totaleR");
			String buttonRacc=request.getParameter("totaleRacc");
			String buttonI=request.getParameter("buttonI");
			
			
			
			
			 if(buttonTot!=null && buttonTot.equals("totale") )
			{
				
								
				tAB.setScrivi(finale);
				
				
					s.append(tAB.generaReportL());
					s.append(tAB.generaReportG());
					s.append(tAB.generaReportR());
					s.append(tAB.getListaUtenti());
					finale=s.toString();
					tAB.setScrivi(finale);
					request.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(report); 
					view.forward(request,response);			
				
					
			}
			else if(buttonL!=null && buttonL.equals("libri") )
			{
				finale="";
				tAB.setScrivi(finale);
				
					s.append(tAB.generaReportL());
					finale=s.toString();					
					tAB.setScrivi(finale);
					request.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(report); 
					view.forward(request,response);			
				
					
			}
			else if(buttonG!=null && buttonG.equals("giornale") )
			{
				tAB.setScrivi(finale);
				
				
					
					s.append(tAB.generaReportG());
					finale=s.toString();
					tAB.setScrivi(finale);
					request.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(report); 
					view.forward(request,response);			
				
					
			}
			else if(buttonR!=null && buttonR.equals("rivista")  )
			{
				finale="";
				tAB.setScrivi(finale);
				
				
					
					s.append(tAB.generaReportR());
					finale=s.toString();
					tAB.setScrivi(finale);
					request.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(report); 
					view.forward(request,response);			
				
					
			}
			else if(buttonRacc!=null && buttonRacc.equals("raccolta")  )
			{
				finale="";
				tAB.setScrivi(finale);
			
				
					s.append(tAB.generaReportL());
					s.append(tAB.generaReportG());
					s.append(tAB.generaReportR());
					finale=s.toString();
					tAB.setScrivi(finale);
					request.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(report); 
					view.forward(request,response);			
				
					
			}
			else if(buttonI!=null && buttonI.equals("indietro") )
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/admin.jsp"); 
				view.forward(request,response);
			}
			
			
			
			
				
					
			} catch (SQLException | IOException e) {
		
				Log.LOGGER.log(Level.SEVERE,"eccezione ottenuta" ,e.getCause());
			}
		
		
	}
	
}
