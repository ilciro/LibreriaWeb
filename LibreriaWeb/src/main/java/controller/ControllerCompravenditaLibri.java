package controller;

import java.sql.SQLException;

import database.LibroDao;
import javafx.collections.ObservableList;
import model.User;
import raccolta.Raccolta;

public class ControllerCompravenditaLibri {
	private LibroDao lD;
	User u=User.getInstance();



	public  boolean disponibilitaLibro(String i ) throws SQLException {
	
		boolean status=false;
		 int id;
		
		
		
		
		
			 id = Integer.parseInt(i);
			 status=lD.checkDisp(id);
			 return status;
				
	}

	public ControllerCompravenditaLibri() {
		lD = new LibroDao();
	}

	public ObservableList<Raccolta> getLibri() throws SQLException {
		return lD.getLibri();
	}
	
	/*
	 * Metodo udato per tornare tipo utente in base a se è loggato o no
	 */
	public String retTipoUser()
	{
		return u.getIdRuolo();
	}
	//usato nel caso se non è loggato-> "utente"
	public void setTipoUser(String ruolo)
	{
		u.setIdRuolo(ruolo);	
	}



	

}
