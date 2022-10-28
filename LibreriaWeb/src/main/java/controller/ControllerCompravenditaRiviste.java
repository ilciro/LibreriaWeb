package controller;

import java.sql.SQLException;

import database.RivistaDao;
import javafx.collections.ObservableList;
import model.User;
import raccolta.Raccolta;
import raccolta.Rivista;

public class ControllerCompravenditaRiviste {

	private RivistaDao rD;
	private Rivista r;
	private static User u=User.getInstance();


	public ControllerCompravenditaRiviste() {
		rD = new RivistaDao();
		r = new Rivista();
	}

	public ObservableList<Raccolta> getRiviste() throws SQLException {
		return rD.getRiviste();
	}
	

	public boolean disponibilitaRiviste(String i ) throws SQLException {
		 int id;

		
		 id = Integer.parseInt(i);
		
		return rD.checkDisp(r,id);
	}

	public void setTipoUser(String ruolo)
	{
		u.setIdRuolo(ruolo);
	}

	public String retTipoUser()
	{
		return u.getIdRuolo();
				
	}

}
