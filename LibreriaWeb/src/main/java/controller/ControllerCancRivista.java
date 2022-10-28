package controller;

import java.sql.SQLException;

import database.RivistaDao;
import raccolta.Rivista;

public class ControllerCancRivista {
	private Rivista r;
	private RivistaDao rd;

	public void cancella(int id) throws  SQLException {
		r.setId(id);
		rd.cancella(r);
	}

	public ControllerCancRivista()
	{
		r=new Rivista();
		rd=new RivistaDao();
	}

}
