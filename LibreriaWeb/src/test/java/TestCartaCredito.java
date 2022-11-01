package java;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import model.CartaDiCredito;

class TestCartaCredito {
	private CartaDiCredito cc=new CartaDiCredito();

	@Test
	void testGetTipo() {
		cc.setTipo(2);
		assertEquals(2,cc.getTipo());
	}

	@Test
	void testGetLimite() {
		cc.setLimite(3000.0);
		assertEquals(3000.0,cc.getLimite());
	}

	@Test
	void testGetAmmontare() {
		cc.setAmmontare(112.5);
		assertEquals(112.5,cc.getAmmontare());
	}

	@Test
	void testGetTypes() {
		assertNotNull(CartaDiCredito.getTypes());
	}
	@Test
	void testCCSQL()
	{
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		 CartaDiCredito ccSQL=new CartaDiCredito(2, "8541-9652-9956-9566", 1500.0, 312.18,date, "geronimo",(float)152.36);
		assertNotNull(ccSQL);
	}

}
