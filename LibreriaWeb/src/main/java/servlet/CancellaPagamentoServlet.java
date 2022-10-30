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
import bean.SystemBean;
import database.ContrassegnoDao;
import database.GiornaleDao;
import database.LibroDao;
import database.PagamentoDao;
import database.RivistaDao;
import model.Log;
import raccolta.Giornale;
import raccolta.Libro;
import raccolta.Rivista;

/**
 * Servlet implementation class CancellaPagamentoServlet
 */
@WebServlet("/CancellaPagamentoServlet")
public class CancellaPagamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ExceptionBean eB=new ExceptionBean();
	private static  ContrassegnoDao fD=new ContrassegnoDao();
	private static PagamentoDao pD=new PagamentoDao();
	private static Libro l=new Libro();
	private static LibroDao lD=new LibroDao();
	private static Giornale g=new Giornale();
	private static GiornaleDao gD=new GiornaleDao();
	private static Rivista r=new Rivista();
	private static RivistaDao rD=new RivistaDao();
	private static String index="/index.html";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaPagamentoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String id=request.getParameter("idCanc");
		boolean delP;
		boolean delF;
		if(Integer.parseInt(id)<0 || Integer.parseInt(id)>SystemBean.getIstance().getElemListaPag())
		{
			eB.setE(new NumberFormatException(" id pagamento maggiori di quello di utente"));
			request.setAttribute("bean",eB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
			view.forward(request,response);
		}
		else
		{
			if(SystemBean.getIstance().getType().equals("libro"))
			{
				
				if(SystemBean.getIstance().getMetodoP().equals("cash"))
				{
					
					delF=fD.annullaOrdine(Integer.parseInt(id));
					delP=pD.annullaOrdine(Integer.parseInt(id));
					if(delF && delP)
					{
						l.setId(SystemBean.getIstance().getId());
						lD.aggiornaDisponibilita(l);
						
						request.setAttribute("bean", SystemBean.getIstance());
						RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
						view.forward(request,response);
					}
					
				}
				else {
				
					delP=pD.annullaOrdine(Integer.parseInt(id));
					if( delP)
					{
						l.setId(SystemBean.getIstance().getId());
						lD.aggiornaDisponibilita(l);
						
						request.setAttribute("bean", SystemBean.getIstance());
						RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
						view.forward(request,response);
					}
				}
			}
				
			else if(SystemBean.getIstance().getType().equals("giornale"))
			{
				
				if(SystemBean.getIstance().getMetodoP().equals("cash"))
				{
					
					delF=fD.annullaOrdine(Integer.parseInt(id));
					delP=pD.annullaOrdine(Integer.parseInt(id));
					if(delF && delP)
					{
						g.setId(SystemBean.getIstance().getId());
						gD.aggiornaDisponibilita(g);
						request.setAttribute("bean", SystemBean.getIstance());
						RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
						view.forward(request,response);
					}
					
				}
			else {
				
					delP=pD.annullaOrdine(Integer.parseInt(id));
					if( delP)
					{
						g.setId(SystemBean.getIstance().getId());
						gD.aggiornaDisponibilita(g);
						
						request.setAttribute("bean", SystemBean.getIstance());
						RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
						view.forward(request,response);
					}
			}
				
		}else if(SystemBean.getIstance().getType().equals("rivista"))
			{
				
				if(SystemBean.getIstance().getMetodoP().equals("cash"))
				{
					
					delF=fD.annullaOrdine(Integer.parseInt(id));
					delP=pD.annullaOrdine(Integer.parseInt(id));
					if(delF && delP)
					{
						r.setId(SystemBean.getIstance().getId());
						rD.aggiornaDisponibilita(r);
						request.setAttribute("bean", SystemBean.getIstance());
						RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
						view.forward(request,response);
					}
					
				}
			else {
				
					delP=pD.annullaOrdine(Integer.parseInt(id));
					if( delP)
					{
						r.setId(SystemBean.getIstance().getId());
						rD.aggiornaDisponibilita(r);
						
						request.setAttribute("bean", SystemBean.getIstance());
						RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
						view.forward(request,response);
					}
				
			}
			}
		}
	}catch(SQLException |  ServletException e)
		{
			Log.LOGGER.log(Level.SEVERE," eccezione ottenuta {}",e.getMessage());
		}
	}

}
