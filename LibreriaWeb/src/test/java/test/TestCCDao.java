package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import controller.ControllerSystemState;
import database.CartaCreditoDao;
import javafx.collections.ObservableList;
import model.CartaDiCredito;

class TestCCDao {

	private CartaCreditoDao cCD;
	private ControllerSystemState vis=ControllerSystemState.getIstance();
	

	@Test
	void testPrendiListaCarteCredito() throws SQLException {
		cCD=new CartaCreditoDao();
		ObservableList<CartaDiCredito> catalogo;

		catalogo=cCD.getCarteCredito("franco");
		
		assertNotNull(catalogo);
	}

	@Test
	void testInsCC() throws SQLException {
		cCD=new CartaCreditoDao();
		 CartaDiCredito cc=new CartaDiCredito();
		
		cc.setNomeUser("franco");
		cc.setNumeroCC("1964-4852-2551-5222");
		cc.setCiv("852");
		cc.setPrezzoTransazine((float)120.3);
		cc.setScadenza(null);
		cCD.insCC(cc);
		assertNotNull(cc.getNomeUser());
	}

	@Test
	void testPrendiSpesa() {
		vis.setSpesaT((float)130.5);
		assertNotEquals(vis.getSpesaT(),0);
	}

}
