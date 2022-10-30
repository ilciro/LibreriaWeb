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
import bean.LibroBean;
import bean.SystemBean;
import database.LibroDao;
import raccolta.Libro;

/**
 * Servlet implementation class ModificaLibroServlet
 */
@WebServlet("/ModificaLibroServlet")
public class ModificaLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LibroBean lB=new LibroBean();
	private static LibroDao lD=new LibroDao();
	private static ExceptionBean eB=new ExceptionBean();
	private static Libro l=new Libro();
	private static String modLibro="/modificaLibro.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaLibroServlet() {
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
    	try {
		
		String genera=request.getParameter("generaB");
		String indietro=request.getParameter("buttonB");
		String buttonA=request.getParameter("buttonA");
		String buttonM=request.getParameter("buttonM");
		String buttonC=request.getParameter("buttonCanc");
		String idCanc=request.getParameter("cancL");
		String id=request.getParameter("idL");
		
		if(id==null || id.equals(""))
				{
					eB.setE(new NullPointerException("valore nullo o vuoto"));
					
					request.setAttribute("bean1",eB);
					request.setAttribute("bean",lB);
					

				RequestDispatcher view = getServletContext().getRequestDispatcher(modLibro); 
				view.forward(request,response);
				}
		
			if(genera!=null && genera.equals("genera lista"))
			{
				
					lB.setMiaLista(lD.getLibriSingoloList());
					request.setAttribute("bean", lB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(modLibro); 
					view.forward(request,response);
				
			}
			if(indietro!=null && indietro.equals("indietro"))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/admin.jsp"); 
				view.forward(request,response);
			}
			if(buttonA!=null && buttonA.equals("aggiungi"))
			{
				
				RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiLibro.jsp"); 
				view.forward(request,response);
			}
			if(buttonM!=null && buttonM.equals("modifica") ){
					
					
					SystemBean.getIstance().setId(Integer.parseInt(id));
					l.setId(SystemBean.getIstance().getId());
					lB.setMiaLista(lD.getLibriSingoloByIdLista(l));
					request.setAttribute("bean",lB);
					request.setAttribute("bean2",SystemBean.getIstance());
					RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaLibroFinale.jsp"); 
					view.forward(request,response);
				}
			
			if(buttonC!=null && buttonC.equals("cancella"))
			{
				lB.setId(idCanc);
				l.setId(Integer.parseInt(lB.getId()));
				if(lB.cancella(l)==1)
				{

					RequestDispatcher view = getServletContext().getRequestDispatcher(modLibro); 
					view.forward(request,response);
				}
				//vedere if
			}
			
		} catch (SQLException | ServletException e) {
			e.printStackTrace();
		}
		
	}

}
