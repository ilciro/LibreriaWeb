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

import bean.ExceptionBean;
import bean.GiornaleBean;
import bean.SystemBean;
import database.GiornaleDao;
import model.Log;
import raccolta.Giornale;

/**
 * Servlet implementation class ModificaGiornaleServlet
 */
@WebServlet("/ModificaGiornaleServlet")
public class ModificaGiornaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static GiornaleBean gB=new GiornaleBean();
	private static GiornaleDao gD=new GiornaleDao();
	private static ExceptionBean eB=new ExceptionBean();
	private static Giornale g=new Giornale();
	private static String modGiornale="/modificaGiornale.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaGiornaleServlet() {
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
		
		
		String genera=request.getParameter("generaB");
		String indietro=request.getParameter("buttonB");
		String buttonA=request.getParameter("buttonA");
		String buttonM=request.getParameter("buttonM");
		String buttonC=request.getParameter("buttonCanc");
		String idCanc=request.getParameter("cancL");
		String id=request.getParameter("idL");
		try {
			if(id==null || id.equals(""))
			{
				eB.setE(new NullPointerException("valore nullo o vuoto"));
				
				request.setAttribute("bean1",eB);
				request.setAttribute("bean",gB);
				

				RequestDispatcher view = getServletContext().getRequestDispatcher(modGiornale); 
				view.forward(request,response);
			}
			
			if(genera!=null && genera.equals("genera lista"))
			{
				
					gB.setMiaListaG(gD.getGiornaliList());
					request.setAttribute("bean", gB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(modGiornale); 
					view.forward(request,response);
				
			}
			if(indietro!=null && indietro.equals("indietro"))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/admin.jsp"); 
				view.forward(request,response);
			}
			if(buttonA!=null && buttonA.equals("aggiungi"))
			{
				
				RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiGiornale.jsp"); 
				view.forward(request,response);
			}
			if(buttonM!=null && buttonM.equals("modifica"))
			{
				
				
					
					
					SystemBean.getIstance().setId(Integer.parseInt(id));
					g.setId(SystemBean.getIstance().getId());
					gB.setMiaListaG(gD.getGiornaliListSingolo(g));
					request.setAttribute("bean",gB);
					request.setAttribute("bean2",SystemBean.getIstance());
					RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaGiornaleFinale.jsp"); 
					view.forward(request,response);
				
			}
			if(buttonC!=null && buttonC.equals("cancella"))
			{
				gB.setId(Integer.parseInt(idCanc));
				g.setId(gB.getId());
				if(gB.cancella(g)==1)
				{

					RequestDispatcher view = getServletContext().getRequestDispatcher(modGiornale); 
					view.forward(request,response);
				}
				//vedere else
			}
		} catch (SQLException |ServletException e) {
			Log.LOGGER.log(Level.SEVERE," eccezione ottenuta {}",e.getMessage());

		}
		
	}

}
