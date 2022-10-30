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
import database.RivistaDao;
import raccolta.Rivista;

/**
 * Servlet implementation class InserisciRivistaServlet
 */
@WebServlet("/InserisciRivistaServlet")
public class InserisciRivistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RivistaBean rB=new RivistaBean();
	private RivistaDao rD=new RivistaDao();
	private Rivista r=new Rivista();
	private  java.util.Date utilDate;
    private java.sql.Date sqlDate;
    private ExceptionBean eB=new ExceptionBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciRivistaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titolo=request.getParameter("titoloL");
		String cat=request.getParameter("catS");
		String autore=request.getParameter("autL");
		String lingua=request.getParameter("linguaL");
		String editore=request.getParameter("editoreL");
		String desc=request.getParameter("descL");
		String data=request.getParameter("dataL");
		String checkL=request.getParameter("checkL");
		String prezzo=request.getParameter("prezzoL");
		String copie=request.getParameter("copieL");
		String generaB=request.getParameter("generaL");
		String buttonC=request.getParameter("confermaB");
		String buttonA=request.getParameter("annullaB");
		String s="";
		if(generaB!=null && generaB.equals("prendi lista"))
		{
			s+="SETTIMANALE"+"\n";
			s+="BISETTIMANALE"+"\n";
			s+="MENSILE"+"\n";
			s+="BIMESTRALE"+"\n";
			s+="TRIMESTRALE"+"\n";
			s+="ANNUALE"+"\n";
			s+="ESTIVO"+"\n";
			s+="INVERNALE"+"\n";
			s+="SPORTIVO"+"\n";
			s+="CINEMATOGRAFICA"+"\n";
			s+="GOSSIP"+"\n";
			s+="TELEVISIVO"+"\n";
			s+="MILITARE"+"\n";
			s+="INFORMATICA"+"\n";
			
			rB.setListaCategorie(s);
			request.setAttribute("bean",rB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiRivista.jsp"); 
			view.forward(request,response);

		}
		if(buttonC!=null && buttonC.equals("conferma"))
		{
			
		
			if(data!=null )
			{
				rB.setTitolo(titolo);
				rB.setTipologia(cat);
				rB.setAutore(autore);
				rB.setLingua(lingua);
				rB.setEditore(editore);
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
				rB.setPrezzo(Float.parseFloat(prezzo));
				rB.setCopieRim(Integer.parseInt(copie));
			
			    if(checkL!=null && checkL.equals("on"))
			    {
					rB.setDisp(1);

			    }
			    
				
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
				  String date = data;
	
				  //convert String to LocalDate
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
				if(rD.creaRivista(r)==true)
				{
					rB.aggiornaData(r, sqlDate);
					request.setAttribute("bean", rB);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaRivista.jsp"); 
					view.forward(request,response); 
	
				}
				else {
					eB.setE(new SQLException(" data non corretta"));
					RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiRivista.jsp"); 
					view.forward(request,response); 
				}
			} catch (SQLException e) {
				eB.setE(e);
				request.setAttribute("bean1", eB);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiRivista.jsp"); 
				view.forward(request,response); 
			}
			
			
			
			}
		}
		if(buttonA!=null && buttonA.equals("indietro"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaRivista.jsp"); 
			view.forward(request,response); 
		}
	}

	

}
