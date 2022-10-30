package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
import model.User;

/**
 * Servlet implementation class MostraPagamentoServlet
 */
@WebServlet("/MostraPagamentoServlet")
public class MostraPagamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExceptionBean eB=new ExceptionBean();
	private PagamentoBean pB=new PagamentoBean();
	private PagamentoDao pD=new PagamentoDao();
       
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u= request.getParameter("uL");
		UserBean.getInstance().setNome(u);
		User.getInstance().setNome(UserBean.getInstance().getNome());


		
		if( u==null)
		{
			eB.setE(new Exception("nome utente null"));
			request.setAttribute("bean",eB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
			view.forward(request,response); 
		}
		else if( u=="")
		{
			eB.setE(new Exception("nome utente =''"));
			request.setAttribute("bean",eB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/errore.jsp"); 
			view.forward(request,response); 
		}
		else {
			try {
				pB.setPagamentiList(pD.getPagamentiList());
				SystemBean.getIstance().setElemListaPag(pD.getPagamentiList().size());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("bean1",pB);
			request.setAttribute("bean2", SystemBean.getIstance());
			RequestDispatcher view = getServletContext().getRequestDispatcher("/annullaOrdine.jsp"); 
			view.forward(request,response); 
			
			
		}
		
	}

}
