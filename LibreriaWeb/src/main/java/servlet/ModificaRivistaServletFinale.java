package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
 * Servlet implementation class ModificaRivistaServletFinale
 */
@WebServlet("/ModificaRivistaServletFinale")
public class ModificaRivistaServletFinale extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RivistaDao rD=new RivistaDao();
	private static Rivista r=new Rivista();
	private static RivistaBean rB=new RivistaBean();
	private static java.util.Date utilDate;
    private static java.sql.Date sqlDate;
    private static ExceptionBean eB=new ExceptionBean();
    private static String modRivistaF="/modificaRivistaFinale.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaRivistaServletFinale() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buttonG=request.getParameter("buttonGenera");
		String buttonM=request.getParameter("modifB");
		String buttonI=request.getParameter("indietroB");
		String tA=request.getParameter("titoloA");
		String tipo=request.getParameter("tipoA");
		String aut=request.getParameter("autA");
		String lingua=request.getParameter("lA");
		String ed=request.getParameter("edA");
		String desc=request.getParameter("descA");
		String data=request.getParameter("dataA");
		String disp=request.getParameter("dispA");
		String prezzo=request.getParameter("pA");
		String copie=request.getParameter("copA");
		
		
		  int id=SystemBean.getIstance().getId();
		  rB.setId(id);
		  r.setId(rB.getId());
		 
		
			
		
		if(buttonG!=null && buttonG.equals("genera"))
		{
			r.setId(SystemBean.getIstance().getId());
			
			try {
				rB.setListaR(rD.getRivistaSingoloByIdLista(r));
				request.setAttribute("bean",rB);
				RequestDispatcher view = getServletContext().getRequestDispatcher(modRivistaF); 
				view.forward(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(buttonM!=null && buttonM.equals("modifica"))
		{
			
			
			rB.setTitolo(tA);
			rB.setTipologia(tipo);
			rB.setAutore(aut);
			rB.setLingua(lingua);
			rB.setEditore(ed);
			rB.setDescrizione(desc);
	
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

			try {
		         utilDate = format.parse(data);
		        sqlDate = new java.sql.Date(utilDate.getTime());
		        System.out.println(sqlDate);
		        rB.setDate(sqlDate);
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
			
			rB.setDisp(Integer.parseInt(disp));
			rB.setPrezzo(Float.parseFloat(prezzo));
			rB.setCopieRim(Integer.parseInt(copie));
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
			  String date = data;

			 
			  LocalDate localDate = LocalDate.parse(date, formatter);
		
		
			
			r.setTitolo(rB.getTitolo());
			r.setTipologia(rB.getTipologia());
			r.setAutore(rB.getAutore());
			r.setLingua(rB.getLingua());
			r.setEditore(rB.getEditore());
			r.setDescrizione(rB.getDescrizione());
			r.setDataPubb(localDate);
			r.setDisp(rB.getDisp());
			r.setPrezzo(rB.getPrezzo());
			r.setCopieRim(rB.getCopieRim());
			
			
			try {
				if(rB.aggiornaRivista(r)==1)
				{
						request.setAttribute("bean", rB);
				        RequestDispatcher view = getServletContext().getRequestDispatcher(modRivistaF); 
						view.forward(request,response);
				  
				}
				else {
					eB.setE(new SQLException(" aggiornamento non avvenuto"));
					request.setAttribute("bean1", eB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(modRivistaF); 
					view.forward(request,response);
				}
			} catch (NullPointerException | SQLException   e) {
				e.printStackTrace();
			
			
		}
		
		}
		if(buttonI!=null && buttonI.equals("indietro") )
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaRivista.jsp"); 
			view.forward(request,response);
		}
	
	}

}
