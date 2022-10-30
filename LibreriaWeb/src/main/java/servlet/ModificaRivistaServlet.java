package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ExceptionBean;
import bean.RivistaBean;
import bean.SystemBean;
import database.RivistaDao;
import raccolta.Rivista;

/**
 * Servlet implementation class ModificaRivistaServlet
 */
@WebServlet("/ModificaRivistaServlet")
public class ModificaRivistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Rivista r=new Rivista();
	private RivistaDao rD=new RivistaDao();
	private RivistaBean rB=new RivistaBean();
	private ExceptionBean eB=new ExceptionBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaRivistaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String genera=request.getParameter("generaB");
		String indietro=request.getParameter("buttonB");
		String buttonA=request.getParameter("buttonA");
		String buttonM=request.getParameter("buttonM");
		String buttonC=request.getParameter("buttonCanc");
		String idCanc=request.getParameter("cancL");
		String id=request.getParameter("idL");
		
		try {
			if(genera!=null && genera.equals("genera lista"))
			{
				
					rB.setListaR(rD.getRivisteList());
					request.setAttribute("bean", rB);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaRivista.jsp"); 
					view.forward(request,response);
				
			}
			if(indietro!=null && indietro.equals("indietro"))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/admin.jsp"); 
				view.forward(request,response);
			}
			if(buttonA!=null && buttonA.equals("aggiungi"))
			{
				
				RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiRivista.jsp"); 
				view.forward(request,response);
			}
			if(buttonM!=null && buttonM.equals("modifica"))
			{
				
				if(id==null || id=="")
				{
					eB.setE(new NullPointerException("valore nullo o vuoto"));
					
					request.setAttribute("bean1",eB);
					request.setAttribute("bean",rB);
					

					RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaRivista.jsp"); 
					view.forward(request,response);
				}
				else {
					
					
					SystemBean.getIstance().setId(Integer.parseInt(id));
					r.setId(SystemBean.getIstance().getId());
					rB.setListaR(rD.getRivistaSingoloByIdLista(r));;
					request.setAttribute("bean",rB);
					request.setAttribute("bean2",SystemBean.getIstance());
					RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaRivistaFinale.jsp"); 
					view.forward(request,response);
				}
			}
			if(buttonC!=null && buttonC.equals("cancella"))
			{
				rB.setId(Integer.parseInt(idCanc));
				r.setId(rB.getId());
				if(rB.cancella(r)==1)
				{

					RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaRivista.jsp"); 
					view.forward(request,response);
				}
				else
				{
					eB.setE(new SQLException("delete fallita"));
					
					request.setAttribute("bean1",eB);
					request.setAttribute("bean",rB);					

					RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaRivista.jsp"); 
					view.forward(request,response);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
