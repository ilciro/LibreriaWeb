package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	private ExceptionBean eB=new ExceptionBean();
	private String appoggio="";
	private boolean state=false;
	private int cont;
	private CartaCreditoBean cCB=new CartaCreditoBean();
     private CartaCreditoDao ccD=new CartaCreditoDao();
     private CartaDiCredito cc=new CartaDiCredito();
     private Libro l=new Libro();
     private LibroDao lD=new LibroDao();
     private PagamentoBean pB=new PagamentoBean();
     private LibroBean lB=new LibroBean();
     private PagamentoDao pD=new PagamentoDao();
     private Pagamento p;
     private Giornale g=new Giornale();
     private GiornaleDao gD=new GiornaleDao();
     private  java.util.Date utilDate;
     private java.sql.Date sqlDate;
     private Rivista r=new Rivista();
     private RivistaBean rB=new RivistaBean();
     private RivistaDao rD=new RivistaDao();
     private GiornaleBean gB=new GiornaleBean();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nomeU");
		String cognome=request.getParameter("cognomeU");
		String codice=request.getParameter("codiceU");
		String scad=request.getParameter("scadU");
		String pin=request.getParameter("pinU");
		
		if((nome==null || nome=="") || (cognome==null || cognome==""))
		{
			eB.setE(new IllegalArgumentException("nome o cognome null o vuoti"));
			request.setAttribute("bean", eB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
			view.forward(request,response); 
		}
		if(controllaPag(scad,codice,pin)==true)
		{
			UserBean.getInstance().setNome(nome);
			UserBean.getInstance().setCognome(cognome);
			cCB.setNomeUser(UserBean.getInstance().getNome());
			cCB.setCognomeUser(UserBean.getInstance().getCognome());
			cCB.setNumeroCC(codice);
			cCB.setCiv(pin);
			
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

		    try {
		         utilDate = format.parse(scad);
		        sqlDate = new java.sql.Date(utilDate.getTime());
		        System.out.println(sqlDate);
		        cCB.setScadenza(sqlDate);
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
			
			
			
			
			
			//inserisco cc
			
			cc.setNomeUser(cCB.getNomeUser());
			cc.setCognomeUser(cCB.getCognomeUser());
			cc.setNumeroCC(cCB.getNumeroCC());
			cc.setScadenza((Date) cCB.getScadenza());
			cc.setCiv(cCB.getCiv());
			cc.setAmmontare(SystemBean.getIstance().getSpesaT());
			try {
				ccD.insCC(cc);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
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
			
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			pB.setId(0);
			pB.setMetodo("cc");
			pB.setEsito(0);
			pB.setNomeUtente(UserBean.getInstance().getNome());
			pB.setAmmontare(SystemBean.getIstance().getSpesaT());
			
			p=new Pagamento(pB.getId(), pB.getMetodo(), pB.getEsito(), pB.getNomeUtente(), SystemBean.getIstance().getSpesaT(), SystemBean.getIstance().getType(), SystemBean.getIstance().getId());
			
			try {
				pD.inserisciPagamento(p);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("bean",UserBean.getInstance());
			request.setAttribute("bean1", SystemBean.getIstance());
			request.setAttribute("bean2", lB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/esitoPositivo.jsp"); 
			view.forward(request,response); 

		}

		
	}
	
	public boolean controllaPag(String d, String c,String civ) {
		int x;

		 int anno;
		 int mese;
		 int giorno;
		String[] verifica=null;


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
