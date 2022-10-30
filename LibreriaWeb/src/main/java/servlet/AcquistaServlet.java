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
import bean.GiornaleBean;
import bean.LibroBean;
import bean.RivistaBean;
import bean.SystemBean;
import database.GiornaleDao;
import database.LibroDao;
import database.RivistaDao;
import raccolta.Giornale;
import raccolta.Libro;
import raccolta.Rivista;

/**
 * Servlet implementation class AcquistaServlet
 */
@WebServlet("/AcquistaServlet")
public class AcquistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibroDao lD=new LibroDao();
	private Libro lib=new Libro();
	private LibroBean lB=new LibroBean();
	private ExceptionBean bE=new ExceptionBean();
	private GiornaleBean gB=new GiornaleBean();
	private GiornaleDao gD=new GiornaleDao();
	private Giornale g=new Giornale ();
	private RivistaBean rB=new RivistaBean();
	private Rivista r=new Rivista();
	private RivistaDao rD=new RivistaDao();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcquistaServlet() {
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
	
		/*TODO
		 *  vedere riuso questa servlet
		 * 
		 */
		
		try {
			String id=request.getParameter("idL");
			if(SystemBean.getIstance().getType().equals("giornale"))
			{
				if(id==null)
				{
					id="0";
					gB.setMiaListaG(gD.getGiornaliList());
					request.setAttribute("bean1",SystemBean.getIstance());
				    request.setAttribute("bean",gB); 
				    RequestDispatcher view = getServletContext().getRequestDispatcher("/giornali.jsp"); 
					view.forward(request,response); 
				}
				else if(Integer.parseInt(id)>=1 && Integer.parseInt(id)<=SystemBean.getIstance().getElemLista()) {
					gB.setId(Integer.parseInt(id));
					String titolo;
					String tipologia;
					int disp;
					g.setId(gB.getId());
					
					titolo=gD.getNome(g);
					tipologia=gD.retTip(g);
					disp=gD.getDisp(g);
					
					gB.setTitolo(titolo);
					gB.setTipologia(tipologia);
					gB.setDisponibilita(disp);
					
					SystemBean.getIstance().setId(Integer.parseInt(id));
					
					 request.setAttribute("bean",gB);  
					 request.setAttribute("bean1", SystemBean.getIstance());
					RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp"); 
					view.forward(request,response); 
				}
				else {
					
					RequestDispatcher view = getServletContext().getRequestDispatcher("/giornali.jsp"); 
					view.forward(request,response); 
				}
			
			}else if(SystemBean.getIstance().getType().equals("libro"))
			{
				
					if(id==null)
					{
						id="0";
						lB.setMiaLista(lD.getLibriSingoloList());
						request.setAttribute("bean1",SystemBean.getIstance());
					    request.setAttribute("bean",lB); 
					    RequestDispatcher view = getServletContext().getRequestDispatcher("/libri.jsp"); 
						view.forward(request,response); 
					}
					else if(Integer.parseInt(id)>=1 && Integer.parseInt(id)<=SystemBean.getIstance().getElemLista()) {
						lB.setId(id);
						String titolo;
						String tipologia;
						int disp;
						lib.setId(Integer.parseInt(lB.getId()));
						
						titolo=lD.getTitolo(lib);
						tipologia=lD.retTip(lib);
						disp=lD.getDisp(lib);
						
						lB.setTitolo(titolo);
						lB.setTipologia(tipologia);
						lB.setDisponibilita(disp);
						
						SystemBean.getIstance().setId(Integer.parseInt(id));
						
						 request.setAttribute("bean",lB);  
						 request.setAttribute("bean1", SystemBean.getIstance());
						RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp"); 
						view.forward(request,response); 
					}
					else {
						
						RequestDispatcher view = getServletContext().getRequestDispatcher("/libri.jsp"); 
						view.forward(request,response); 
					
					}
			
			}
			else if(SystemBean.getIstance().getType().equals("rivista"))
			if(id==null)
			{
				id="0";
				rB.setListaR(rD.getRivisteList());
				request.setAttribute("bean1",SystemBean.getIstance());
			    request.setAttribute("bean",rB); 
			    RequestDispatcher view = getServletContext().getRequestDispatcher("/riviste.jsp"); 
				view.forward(request,response); 
			}
			//rivista
			else if(Integer.parseInt(id)>=1 && Integer.parseInt(id)<=SystemBean.getIstance().getElemLista()) {
				rB.setId(Integer.parseInt(id));
				String titolo;
				String tipologia;
				int disp;
				r.setId(rB.getId());
				
				titolo=rD.getNome(r);
				tipologia=rD.retTip(r);
				disp=rD.getDisp(r);
				
				rB.setTitolo(titolo);
				rB.setTipologia(tipologia);
				rB.setDisp(disp);
				
				SystemBean.getIstance().setId(Integer.parseInt(id));
				
				 request.setAttribute("bean",rB);  
				 request.setAttribute("bean1", SystemBean.getIstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp"); 
				view.forward(request,response); 
			}
			else {
				
				RequestDispatcher view = getServletContext().getRequestDispatcher("/riviste.jsp"); 
				view.forward(request,response); 
			
			}
		
	
		} catch (ServletException| NumberFormatException |SQLException e) {
			bE.setE(e);
			 request.setAttribute("bean1",bE);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
			view.forward(request,response); 
		}
		

		
		
		
	}

}
