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
import bean.FatturaBean;
import bean.GiornaleBean;
import bean.LibroBean;
import bean.PagamentoBean;
import bean.RivistaBean;
import bean.SystemBean;
import bean.UserBean;
import database.ContrassegnoDao;
import database.GiornaleDao;
import database.LibroDao;
import database.PagamentoDao;
import database.RivistaDao;
import model.Fattura;
import model.Log;
import model.Pagamento;
import raccolta.Giornale;
import raccolta.Libro;
import raccolta.Rivista;

/**
 * Servlet implementation class ServletPagamentoContanti
 */
@WebServlet("/ServletPagamentoContanti")
public class ServletPagamentoContanti extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ExceptionBean bE=new ExceptionBean();
	private static  PagamentoBean pB=new PagamentoBean();
	private static FatturaBean fB=new FatturaBean();
	private static PagamentoDao pD=new PagamentoDao();
	private static ContrassegnoDao fD=new ContrassegnoDao();
	private static LibroBean lB=new LibroBean();
	private static LibroDao lD=new LibroDao();
	private static Libro l=new Libro();
	private static Giornale g=new Giornale();
	private static GiornaleDao gD=new GiornaleDao();
	private static GiornaleBean gB=new GiornaleBean();
	private static Rivista r=new Rivista();
	private static RivistaDao rD=new RivistaDao();
	private static RivistaBean rB=new RivistaBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPagamentoContanti() {
        super();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nomeU");
		String cognome=request.getParameter("cognomeU");
		String via=request.getParameter("viaU");
		String com=request.getParameter("comunicaU");
		
		UserBean.getInstance().setNome(nome);
		UserBean.getInstance().setCognome(cognome);
		UserBean.getInstance().setVia(via);
		UserBean.getInstance().setCom(com);
		try {
		if(((nome==null || "".equals(nome))||(cognome==null || "".equals(cognome)) || (via==null || "".equals(via))))
		{
			bE.setE(new Exception("dati non corretti "));
			request.setAttribute("bean1",bE);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
			view.forward(request,response); 
			
		}
		else {
			
			Fattura f;
			Pagamento p;
			//faccio pagamento
				if(SystemBean.getIstance().getType().equals("libro"))
				{
					l.setId(SystemBean.getIstance().getId());					
					lB.setTitolo(lD.getTitolo(l));
				}
				else if(SystemBean.getIstance().getType().equals("giornale"))
				{
					g.setId(SystemBean.getIstance().getId());
					gB.setTitolo(gD.getNome(g));
				}
				else if(SystemBean.getIstance().getType().equals("rivista"))
				{
					r.setId(SystemBean.getIstance().getId());
					rB.setTitolo(rD.getNome(r));
				}
			
			
			pB.setId(0);
			pB.setMetodo("cash");
			pB.setEsito(0);
			pB.setNomeUtente(UserBean.getInstance().getNome());
			pB.setAmmontare(SystemBean.getIstance().getSpesaT());
			
			p=new Pagamento(pB.getId(), pB.getMetodo(), pB.getEsito(), pB.getNomeUtente(), SystemBean.getIstance().getSpesaT(), SystemBean.getIstance().getType(), SystemBean.getIstance().getId());
			
			//faccio fattura
			fB.setNome(nome);
			fB.setCognome(cognome);
			fB.setVia(via);
			fB.setCom(com);
			fB.setNumero("0");
			fB.setAmmontare(SystemBean.getIstance().getSpesaT());
			
			f=new Fattura(fB.getNome(),fB.getCognome(),fB.getVia(),fB.getCom(),fB.getNumero(),SystemBean.getIstance().getSpesaT());
			
			
				pD.inserisciPagamento(p);
				fD.inserisciFattura(f);
			
			request.setAttribute("bean",UserBean.getInstance());
			request.setAttribute("bean1", SystemBean.getIstance());
			request.setAttribute("bean2", lB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/esitoPositivo.jsp"); 
			view.forward(request,response); 
			
		}
		
		}catch(SQLException| ServletException e)
		{
			Log.LOGGER.log(Level.SEVERE,"eccezione ottenuta" ,e.getCause());

		}
	}

}
