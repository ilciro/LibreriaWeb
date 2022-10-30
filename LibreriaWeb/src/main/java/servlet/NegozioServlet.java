package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.NegozioBean;
import database.NegozioDao;

/**
 * Servlet implementation class NegozioServlet
 */
@WebServlet("/NegozioServlet")
public class NegozioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NegozioBean nB=new NegozioBean();
	private NegozioDao nD=new NegozioDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NegozioServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			nB.setNegozi(nD.getNegoziList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("bean",nB);
		RequestDispatcher view = getServletContext().getRequestDispatcher("/negozio.jsp"); 
		view.forward(request,response);
	}

}
