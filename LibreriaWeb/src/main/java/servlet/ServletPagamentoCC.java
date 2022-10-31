package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CartaCreditoBean;
import bean.ExceptionBean;
import bean.GiornaleBean;
import bean.LibroBean;
import bean.PagamentoBean;
import bean.RivistaBean;
import bean.SystemBean;
import bean.UserBean;
import database.CartaCreditoDao;
import database.GiornaleDao;
import database.LibroDao;
import database.PagamentoDao;
import database.RivistaDao;
import model.CartaDiCredito;
import model.Log;
import model.Pagamento;
import raccolta.Giornale;
import raccolta.Libro;
import raccolta.Rivista;

/**
 * Servlet implementation class ServletPagamentoCC
 */
@WebServlet("/ServletPagamentoCC")
public class ServletPagamentoCC extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ExceptionBean eB=new ExceptionBean();
	private static CartaCreditoBean cCB=new CartaCreditoBean();
     private static CartaCreditoDao ccD=new CartaCreditoDao();
     private static CartaDiCredito cc=new CartaDiCredito();
     private static  Libro l=new Libro();
     private static LibroDao lD=new LibroDao();
     private static PagamentoBean pB=new PagamentoBean();
     private static LibroBean lB=new LibroBean();
     private static PagamentoDao pD=new PagamentoDao();
     
     private static Giornale g=new Giornale();
     private static GiornaleDao gD=new GiornaleDao();
    
     private static Rivista r=new Rivista();
     private static RivistaBean rB=new RivistaBean();
     private static RivistaDao rD=new RivistaDao();
     private static GiornaleBean gB=new GiornaleBean();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPagamentoCC() {
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
		String nome=request.getParameter("nomeU");
		String cognome=request.getParameter("cognomeU");
		String codice=request.getParameter("codiceU");
		String scad=request.getParameter("scadU");
		String pin=request.getParameter("pinU");
		try {
			
			 java.util.Date utilDate;
		     java.sql.Date sqlDate;
		if((nome==null || "".equals(nome)) || (cognome==null || "".equals(cognome)))
		{
			eB.setE(new IllegalArgumentException("nome o cognome null o vuoti"));
			request.setAttribute("bean", eB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
			view.forward(request,response); 
		}
		if(controllaPag(scad,codice,pin))
		{
			UserBean.getInstance().setNome(nome);
			UserBean.getInstance().setCognome(cognome);
			cCB.setNomeUser(UserBean.getInstance().getNome());
			cCB.setCognomeUser(UserBean.getInstance().getCognome());
			cCB.setNumeroCC(codice);
			cCB.setCiv(pin);
			
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

		  
		         utilDate = format.parse(scad);
		        sqlDate = new java.sql.Date(utilDate.getTime());
		        cCB.setScadenza(sqlDate);
		   
			
			
			
			
			//inserisco cc
			
			cc.setNomeUser(cCB.getNomeUser());
			cc.setCognomeUser(cCB.getCognomeUser());
			cc.setNumeroCC(cCB.getNumeroCC());
			cc.setScadenza((Date) cCB.getScadenza());
			cc.setCiv(cCB.getCiv());
			cc.setAmmontare(SystemBean.getIstance().getSpesaT());
			
				ccD.insCC(cc);
			
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
			//faccio pagamento
			Pagamento p;
			
			pB.setId(0);
			pB.setMetodo("cc");
			pB.setEsito(0);
			pB.setNomeUtente(UserBean.getInstance().getNome());
			pB.setAmmontare(SystemBean.getIstance().getSpesaT());
			
			p=new Pagamento(pB.getId(), pB.getMetodo(), pB.getEsito(), pB.getNomeUtente(), SystemBean.getIstance().getSpesaT(), SystemBean.getIstance().getType(), SystemBean.getIstance().getId());
			
		
				pD.inserisciPagamento(p);
			
			
			request.setAttribute("bean",UserBean.getInstance());
			request.setAttribute("bean1", SystemBean.getIstance());
			request.setAttribute("bean2", lB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/esitoPositivo.jsp"); 
			view.forward(request,response); 

		}
		}catch(SQLException|ServletException|ParseException e)
		{
			Log.LOGGER.log(Level.SEVERE,"eccezione ottenuta" ,e.getCause());

		}

		
	}
	
	public boolean controllaPag(String d, String c,String civ) {
		int x;

		 int anno;
		 int mese;
		 int giorno;
		String[] verifica=null;
		String appoggio = null;
		int cont=0;
		boolean state=false;

		appoggio = appoggio + d;
		  anno = Integer.parseInt((String) appoggio.subSequence(0, 4));
		  mese = Integer.parseInt((String) appoggio.subSequence(5, 7));
		  giorno = Integer.parseInt((String) appoggio.subSequence(8, 10));
		
		if (anno > 2020 && (mese >= 1 && mese <= 12) && (giorno >= 1 && giorno <= 31) && c.length() <= 20 && civ.length()==3 ) {
			
				
					 verifica= c.split("-");
					
					for ( x=0; x<verifica.length; x++) {
							if(verifica[x].length()==4)
							{
								cont++;
							}
					}
					if (cont==4)
					{
						state=true;
					}
					
				

		} 
		
		
		return state;

	}
	




}
