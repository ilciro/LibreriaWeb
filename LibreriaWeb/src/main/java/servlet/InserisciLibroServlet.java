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
import bean.LibroBean;
import bean.SystemBean;
import database.LibroDao;
import raccolta.Libro;

/**
 * Servlet implementation class InserisciLibroServlet
 */
@WebServlet("/InserisciLibroServlet")
public class InserisciLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LibroBean lB=new LibroBean();
	private static String s;
	private  static java.util.Date utilDate;
    private static java.sql.Date sqlDate;
    private static LibroDao lD=new LibroDao();
    private static ExceptionBean eB=new ExceptionBean();
    private static Libro l=new Libro();
    private static String aggLibro="/aggiungiLibro.jsp";
   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciLibroServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		SystemBean.getIstance().setType("libro");
		String titolo=request.getParameter("titoloL");
		String nrPagL=request.getParameter("nrPagL");
		String codL=request.getParameter("codL");
		String autoreL=request.getParameter("autoreL");
		String editoreL=request.getParameter("editoreL");
		String linguaL=request.getParameter("linguaL");
		String dataL=request.getParameter("dataL");//modificare usare pagamento x conv
		String recensione=request.getParameter("recensioneL");
		String desc=request.getParameter("descL");
		String checkL=request.getParameter("checkL");
		String prezzoL=request.getParameter("prezzoL");
		String copieL=request.getParameter("copieL");
		String generaL=request.getParameter("generaL");
		String catS=request.getParameter("catS");
		String buttonC=request.getParameter("confermaB");
		String buttonA=request.getParameter("annullaB");
		
		if(generaL!=null && generaL.equals("prendi lista"))
		{
			s="";
			s+="ADOLESCENTI_RAGAZZI"+"\n";
			s+="ARTE"+"\n";
			s+="CINEMA_FOTOGRAFIA"+"\n";
			s+="BIOGRAFIE"+"\n";
			s+="DIARI_MEMORIE"+"\n";
			s+="CALENDARI_AGENDE"+"\n";
			s+="DIRITTO"+"\n";
			s+="DIZINARI_OPERE"+"\n";
			s+="ECONOMIA"+"\n";
			s+="FAMIGLIA"+"\n";
			s+="SALUTE_BENESSERE"+"\n";
			s+="FANTASCIENZA_FANTASY"+"\n";
			s+="FUMETTI_MANGA"+"\n";
			s+="GIALLI_THRILLER"+"\n";
			s+="COMPUTER_GIOCHI"+"\n";
			s+="HUMOR"+"\n";
			s+="INFORMATICA"+"\n";
			s+="WEB_DIGITAL_MEDIA"+"\n";
			s+="LETTERATURA_NARRATIVA"+"\n";
			s+="LIBRI_BAMBINI"+"\n";
			s+="LIBRI_SCOLASTICI"+"\n";
			s+="LIBRI_UNIVERSITARI"+"\n";
			s+="RICETTARI_GENERALI"+"\n";
			s+="LINGUISTICA_SCRITTURA"+"\n";
			s+="POLITICA"+"\n";
			s+="RELIGIONE"+"\n";
			s+="ROMANZI_ROSA"+"\n";
			s+="SCIENZE"+"\n";
			s+="TECNOLOGIA_MEDICINA"+"\n";
			
			lB.setListaCategorie(s);	

			
			request.setAttribute("bean",lB);
			RequestDispatcher view = getServletContext().getRequestDispatcher(aggLibro); 
			view.forward(request,response);
		}
		
		if(buttonC!=null && buttonC.equals("conferma"))
		{
			
		
			if(codL!=null && codL!="" && codL.length()<=10)
			{
				lB.setTitolo(titolo);
				lB.setCodIsbn(codL);
				lB.setNumeroPagine(Integer.parseInt(nrPagL));
				lB.setEditore(editoreL);
				lB.setAutore(autoreL);
				lB.setLingua(linguaL);
				lB.setCategoria(catS);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	
			    try {
			         utilDate = format.parse(dataL);
			        sqlDate = new java.sql.Date(utilDate.getTime());
			        System.out.println(sqlDate);
			        lB.setDate(sqlDate);
			    } catch (ParseException e) {
			        e.printStackTrace();
			    }
				
			    lB.setRecensione(recensione);
			    lB.setDesc(desc);
			    lB.setDisponibilita(0);
			    if(checkL!=null)
			    {
			    	lB.setDisponibilita(1);
			    }
			    
				lB.setPrezzo(Float.parseFloat(prezzoL));
				lB.setNrCopie(Integer.parseInt(copieL));
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
				  String date = dataL;
	
				  //convert String to LocalDate
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
			l.setCopieRim(lB.getCopieRim());
			
			
			
			  
			
			try {
				if(lD.creaLibrio(l)==true)
				{
					lB.aggiornaData(l, sqlDate);
					request.setAttribute("bean", lB);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaLibro.jsp"); 
					view.forward(request,response); 
	
				}
				else {
					//eB.setE(new LibroInsertException(" Libro non inserito.. Codice isbn non valido"));
					RequestDispatcher view = getServletContext().getRequestDispatcher(aggLibro); 
					view.forward(request,response); 
				}
			} catch (SQLException e) {
				eB.setE(e);
				request.setAttribute("bean1", eB);
				RequestDispatcher view = getServletContext().getRequestDispatcher(aggLibro); 
				view.forward(request,response); 
			}
			
			
			
			}
		}
		if(buttonA!=null && buttonA.equals("indietro"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaLibro.jsp"); 
			view.forward(request,response); 
		}
	}

}
