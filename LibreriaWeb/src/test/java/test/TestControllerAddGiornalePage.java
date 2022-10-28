package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import controller.ControllerAddGiornalePage;
import raccolta.Giornale;

class TestControllerAddGiornalePage {

	private ControllerAddGiornalePage cAGP=new ControllerAddGiornalePage();
	private static final String MESSAGERO="il messagero";

	@Test
	void testCheckData() throws SQLException {
		 Giornale g;

		boolean status;
		String titolo=MESSAGERO;
		String tipologia="giornaliero";
		String editore=MESSAGERO;
		String lingua="it";
		LocalDate data=LocalDate.of(2021, 2, 10);
		int disp=1;
		float prezzo=(float)1.3;
		int copieRim=100;
		
		g=new Giornale();
		g.setTitolo(titolo);
		g.setTipologia(tipologia);
		g.setEditore(editore);
		g.setLingua(lingua);
		g.setDataPubb(data);
		g.setDisponibilita(disp);
		g.setPrezzo(prezzo);
		g.setCopieRimanenti(copieRim);
		status=cAGP.checkData(g);
		assertNotEquals(false,status);


	}
	
	@Test
	void testCheckDataE() throws SQLException {
		 Giornale g;

		boolean status;
		String titolo=MESSAGERO;
		String tipologia="giornaliero";
		String editore=MESSAGERO;
		String lingua="it";
		LocalDate data=null;
		int disp=1;
		float prezzo=(float)1.3;
		int copieRim=100;
		
		g=new Giornale();
		g.setTitolo(titolo);
		g.setTipologia(tipologia);
		g.setEditore(editore);
		g.setLingua(lingua);
		g.setDataPubb(data);
		g.setDisponibilita(disp);
		g.setPrezzo(prezzo);
		g.setCopieRimanenti(copieRim);
		status=cAGP.checkData(g);
		assertEquals(false,status);


	}

}
