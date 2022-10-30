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
import bean.GiornaleBean;
import bean.SystemBean;
import database.GiornaleDao;
import model.Log;
import raccolta.Giornale;

/**
 * Servlet implementation class ModificaGiornaleServletFinale
 */
@WebServlet("/ModificaGiornaleServletFinale")
public class ModificaGiornaleServletFinale extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static GiornaleBean gB=new GiornaleBean();
	private static Giornale g=new Giornale();
	private static GiornaleDao gD=new GiornaleDao();
	private static String modGiornaleF="/modificaGiornaleFinale.jsp";	
    private static ExceptionBean eB=new ExceptionBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaGiornaleServletFinale() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    	String buttonG=request.getParameter("buttonGenera");
		String buttonM=request.getParameter("modifB");
		String buttonI=request.getParameter("indietroB");

		String tA=request.getParameter("titoloA");
		String tipA=request.getParameter("tipA");
		String lingua=request.getParameter("linguaA");
		String ed=request.getParameter("edA");
		String data=request.getParameter("dataA");
		String copie=request.getParameter("copieA");
		String disp=request.getParameter("dispA");
		String prezzo=request.getParameter("pA");

		 int id=SystemBean.getIstance().getId();
		  
		 gB.setId(id);
			g.setId(SystemBean.getIstance().getId());
			
			  java.util.Date utilDate;
		   java.sql.Date sqlDate;

		 if(buttonG!=null && buttonG.equals("genera"))
			{
				
				
					gB.setMiaListaG(gD.getGiornaliListSingolo(g));
					request.setAttribute("bean",gB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(modGiornaleF); 
					view.forward(request,response);
				
			}
		 if(buttonM!=null && buttonM.equals("modifica"))
			{
				
				gB.setTitolo(tA);
				gB.setTipologia(tipA);
				gB.setLingua(lingua);
				gB.setEditore(ed);
				
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

				
			         utilDate = format.parse(data);
			        sqlDate = new java.sql.Date(utilDate.getTime());
			        gB.setDate(sqlDate);
			  
				gB.setCopieRimanenti(Integer.parseInt(copie));
				gB.setDisponibilita(Integer.parseInt(disp));
				gB.setPrezzo(Float.parseFloat(prezzo));
				
			
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
				  String date = data;

				 
				  LocalDate localDate = LocalDate.parse(date, formatter);
			
			
				
				g.setTitolo(gB.getTitolo());
				g.setTipologia(gB.getTipologia());
				g.setLingua(gB.getLingua());
				g.setEditore(gB.getEditore());
				g.setDataPubb(localDate);
				g.setCopieRimanenti(gB.getCopieRimanenti());
				g.setDisponibilita(gB.getDisponibilita());
				g.setPrezzo(gB.getPrezzo());
				
				
				
					if(gB.aggiornaGiornale(g)==1)
					{
							request.setAttribute("bean", gB);
					        RequestDispatcher view = getServletContext().getRequestDispatcher(modGiornaleF); 
							view.forward(request,response);
					  
					}
					else {
						eB.setE(new SQLException(" aggiornamento non avvenuto"));
						request.setAttribute("bean1", eB);
						RequestDispatcher view = getServletContext().getRequestDispatcher(modGiornaleF); 
						view.forward(request,response);
					}
				
			
			}
		 if(buttonI!=null && buttonI.equals("indietro") )
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaGiornale.jsp"); 
				view.forward(request,response);
			}
		 
	} catch (SQLException|ServletException|ParseException e) {
		Log.LOGGER.log(Level.SEVERE," eccezione ottenuta {}",e.getMessage());
	}
    }

}
