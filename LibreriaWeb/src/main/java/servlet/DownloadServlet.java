package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;

import bean.GiornaleBean;
import bean.LibroBean;
import bean.RivistaBean;
import bean.SystemBean;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LibroBean lB=new LibroBean();
	private static GiornaleBean gB=new GiornaleBean();
	private static RivistaBean rB=new RivistaBean();
	private static String bean1="bean1";
	private static String index="/index.html";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
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
		
		if(SystemBean.getIstance().isNegScelto()==true)
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/negozio.jsp"); 
			view.forward(request,response); 
		}
		else {
		//mettere if su tipo ogetto
		try {
			if(SystemBean.getIstance().getType().equals("libro"))
			{
				lB.scarica();		
				lB.leggi(SystemBean.getIstance().getId());		
				request.setAttribute(bean1,SystemBean.getIstance());
				request.setAttribute("bean","lB");
				RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
				view.forward(request,response); 
			}
			else if(SystemBean.getIstance().getType().equals("giornale"))
			{
				gB.scarica();
				gB.leggi(SystemBean.getIstance().getId());
				request.setAttribute(bean1,SystemBean.getIstance());
				request.setAttribute("bean","gB");
				RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
				view.forward(request,response); 
			}
			else if(SystemBean.getIstance().getType().equals("rivista"))
			{
				rB.scarica();
				rB.leggi(SystemBean.getIstance().getId());
				request.setAttribute(bean1,SystemBean.getIstance());
				request.setAttribute("bean","rB");
				RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
				view.forward(request,response); 
			}
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
			}
		}
	}

}
