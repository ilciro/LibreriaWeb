package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import controller.ControllerAddBookPage;

class TestControllerAddBookPage {

	private ControllerAddBookPage cABP=new ControllerAddBookPage();
	private String[] infoG =new String[6];
	private String[] infoC=new String[6];
	private static final String MONDADORI="mondadori";

	@Test
	void testCheckData() throws SQLException {
		boolean state;
		String titolo="ricette a base di pessce";
		int numPag=100;
		String codIsbn="852416321";
		String editore=MONDADORI;
		String autore=MONDADORI;
		String lingua="it";
		String categoria="culinaria";
		LocalDate data = LocalDate.of(2005,8,8);
		String recensione="bello e divertente";
		String desc="Ti fa venire voglia di mangiare";
		int disp=1;
		float prezzo=(float)15.2;
		int copieRim=80;
		
		infoG[0]=titolo;
		infoG[2]=autore;
		infoG[3]=lingua;
		infoG[4]=editore;
		infoG[5]=categoria;
		
		infoC[0]=String.valueOf(numPag);
		infoC[1]=codIsbn;
		infoC[2]=String.valueOf(copieRim);
		infoC[3]=String.valueOf(disp);
		infoC[4]=String.valueOf(prezzo);
		infoC[5]=String.valueOf(copieRim);
		
		state=cABP.checkData(infoG, recensione, desc, data, infoC);
		
		assertNotEquals(false,state);
}

	@Test
	void testCheckDataE() throws SQLException {
		boolean state;
		String titolo="ricette a base di pessce";
		int numPag=100;
		String codIsbn="852416321";
		String editore=MONDADORI;
		String autore=MONDADORI;
		String lingua="it";
		String categoria="culinaria";
		LocalDate data =null;
		String recensione="bello e divertente";
		String desc="Ti fa venire voglia di mangiare";
		int disp=1;
		float prezzo=(float)15.2;
		int copieRim=80;
		
		infoG[0]=titolo;
		infoG[2]=autore;
		infoG[3]=lingua;
		infoG[4]=editore;
		infoG[5]=categoria;
		
		infoC[0]=String.valueOf(numPag);
		infoC[1]=codIsbn;
		infoC[2]=String.valueOf(copieRim);
		infoC[3]=String.valueOf(disp);
		infoC[4]=String.valueOf(prezzo);
		infoC[5]=String.valueOf(copieRim);
		
		state=cABP.checkData(infoG, recensione, desc, data, infoC);
		
		assertEquals(false,state);
}

}
