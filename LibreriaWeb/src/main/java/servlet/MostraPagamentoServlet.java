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
import bean.PagamentoBean;
import bean.SystemBean;
import bean.UserBean;
import database.PagamentoDao;
import model.Log;
import model.User;

/**
 * Servlet implementation class MostraPagamentoServlet
 */
@WebServlet("/MostraPagamentoServlet")
public class MostraPagamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ExceptionBean eB=new ExceptionBean();
	private static PagamentoBean pB=new PagamentoBean();
	private static PagamentoDao pD=new PagamentoDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostraPagamentoServlet() {
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
		String u= request.getParameter("uL");
		UserBean.getInstance().setNome(u);
		User.getInstance().setNome(UserBean.getInstance().getNome());


		try {
		if( u==null)
		{
			eB.setE(new Exception("nome utente null"));
			request.setAttribute("bean",eB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
			view.forward(request,response); 
		}
		else if( u.equals(""))
		{
			eB.setE(new Exception("nome utente =''"));
			request.setAttribute("bean",eB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
			view.forward(request,response); 
		}
		else {
			
				pB.setPagamentiList(pD.getPagamentiList());
				SystemBean.getIstance().setElemListaPag(pD.getPagamentiList().size());
			
			request.setAttribute("bean1",pB);
			request.setAttribute("bean2", SystemBean.getIstance());
			RequestDispatcher view = getServletContext().getRequestDispatcher("/annullaOrdine.jsp"); 
			view.forward(request,response); 
			
			
		}
		
		}catch(SQLException | ServletException e)
		{
			Log.LOGGER.log(Level.SEVERE,"eccezione ottenuta" ,e.getCause());
		}
	}

}
