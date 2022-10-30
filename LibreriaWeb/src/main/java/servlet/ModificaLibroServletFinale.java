package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

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
import model.Log;
import raccolta.Libro;

/**
 * Servlet implementation class ModificaLibroServletFinale
 */
@WebServlet("/ModificaLibroServletFinale")
public class ModificaLibroServletFinale extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LibroBean lB=new LibroBean();
	private static LibroDao lD=new LibroDao();
	private static Libro l=new Libro();
	
    private static ExceptionBean eB=new ExceptionBean();
    private static String modLibroF="/modificaLibroFinale.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaLibroServletFinale() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
	  java.util.Date utilDate;
	  java.sql.Date sqlDate;
		
		
		String buttonG=request.getParameter("buttonGenera");
		String buttonM=request.getParameter("modifB");
		String buttonI=request.getParameter("indietroB");
		String tA=request.getParameter("titoloA");
		String numP=request.getParameter("numA");
		String cod=request.getParameter("codA");
		String ed=request.getParameter("edA");
		String aut=request.getParameter("autA");
		String lingua=request.getParameter("lA");
		String cat=request.getParameter("catA");
		String data=request.getParameter("dataA");
		String rec=request.getParameter("recA");
		String desc=request.getParameter("descA");
		String disp=request.getParameter("dispA");
		String prezzo=request.getParameter("pA");
		String copie=request.getParameter("copA");
		
		
		  int id=SystemBean.getIstance().getId();
		  String idS=Integer.toString(id);
		  lB.setId(idS);
		  l.setId(Integer.parseInt(lB.getId()));
		
			
		
		if(buttonG!=null && buttonG.equals("genera"))
		{
			l.setId(SystemBean.getIstance().getId());
			
	
				lB.setMiaLista(lD.getLibriSingoloByIdLista(l));
				request.setAttribute("bean",lB);
				RequestDispatcher view = getServletContext().getRequestDispatcher(modLibroF); 
				view.forward(request,response);
			
		}
		if(buttonM!=null && buttonM.equals("modifica"))
		{
			
			lB.setTitolo(tA);
			lB.setNumeroPagine(Integer.parseInt(numP));
			lB.setCodIsbn(cod);
			lB.setEditore(ed);
			lB.setAutore(aut);
			lB.setLingua(lingua);
			lB.setCategoria(cat);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

			
		         utilDate = format.parse(data);
		        sqlDate = new java.sql.Date(utilDate.getTime());
		        
		        Log.LOGGER.log(Level.INFO," data : {0}",utilDate +"" + sqlDate);
		       
		        
		
			lB.setRecensione(rec);
			lB.setDesc(desc);
			lB.setDisponibilita(Integer.parseInt(disp));
			lB.setPrezzo(Float.parseFloat(prezzo));
			lB.setNrCopie(Integer.parseInt(copie));
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
			  String date = data;

			 
			  LocalDate localDate = LocalDate.parse(date, formatter);
		
		
			
			l.setTitolo(lB.getTitolo());
			l.setNumeroPagine(lB.getNumeroPagine());
			l.setCodIsbn(lB.getCodIsbn());
			l.setEditore(lB.getEditore());
			l.setAutore(lB.getAutore());
			l.setLingua(lB.getLingua());
			l.setCategoria(lB.getCategoria());
			l.setDataPubb(localDate);
			l.setRecensione(lB.getRecensione());
			l.setDesc(lB.getDesc());
			l.setDisponibilita(lB.getDisponibilita());
			l.setPrezzo(lB.getPrezzo());
			l.setNrCopie(lB.getCopieRim());
			
				if(lB.aggiornaLibro(l)==1)
				{
						request.setAttribute("bean", lB);
				        RequestDispatcher view = getServletContext().getRequestDispatcher(modLibroF); 
						view.forward(request,response);
				  
				}
				else {
					eB.setE(new SQLException(" aggiornamento non avvenuto"));
					request.setAttribute("bean1", eB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(modLibroF); 
					view.forward(request,response);
				}
			
		
		}
		if(buttonI!=null && buttonI.equals("indietro") )
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaLibro.jsp"); 
			view.forward(request,response);
		}
		}catch(SQLException|ServletException|ParseException e)
		{
			Log.LOGGER.log(Level.SEVERE,"eccezione ottenuta ",e.getCause());
		}
    }

}
