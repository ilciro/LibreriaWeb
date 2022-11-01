package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import controller.ControllerSystemState;
import database.UsersDao;
import model.TempUser;
import model.User;

class TestUsersDao {

	private static User u=User.getInstance();
	private static TempUser tu=TempUser.getInstance();
	private static ControllerSystemState vis=ControllerSystemState.getIstance();
	private boolean state=false;
	private int state1;
	private static final String ALDOBAGLIO1 = "Aldo1Baglio1@gmail.com";
	private static final String ALDOBAGLIO = "AldoBaglio@gmail.com";
	private static final String LUCIODALLA="luciodalla@gmail.com";
	private static final String LUCIODALLA1="luciodalla1@gmail.com";


	
	@Test
	void testCreateUser() throws SQLException  {
		u.setNome("lucio");
		u.setCognome("dalla");
		u.setEmail(LUCIODALLA);
		u.setPassword("lucio851");
		u.setDataDiNascita(LocalDate.of(1940, 11,5));
		state=UsersDao.createUser(u);
		assertEquals(true,state);
	}

	@Test
	void testCreateUser2() throws SQLException  {
		tu.setNome("aldo");
		tu.setCognome("baglio");
		tu.setEmail(ALDOBAGLIO);
		tu.setPassword("aldooooo852");
		tu.setDescrizione("passeggero sbagiato nella subaru");
		tu.setDataDiNascita(LocalDate.of(1970, 3, 6));
		state=UsersDao.createUser2(tu);
		assertEquals(true,state);		
		
		
	}

	@Test
	void testCheckUser() throws SQLException  {
		u.setEmail(LUCIODALLA);
		state1=UsersDao.checkUser(u);
		assertEquals(0,state1);
		
	}

	@Test
	void testCheckTempUser() throws SQLException  {
		tu.setEmail(ALDOBAGLIO);
		state1=UsersDao.checkTempUser(tu);
		assertEquals(0,state1);
	}
	@Test
	void testCheckTempUserG() throws SQLException  {
		tu.setEmail("baoPublishing@gmail.com");
		state1=UsersDao.checkTempUser(tu);
		assertEquals(1,state1);
	}


	@Test
	void testGetRuolo() throws SQLException  {
		 String ruolo;

		u.setEmail("zerocalcare@gmail.com");
		ruolo=UsersDao.getRuolo(u);
		assertEquals(ruolo,"W");
	}

	@Test
	void testCheckResetpass() throws SQLException  {
		state=UsersDao.checkResetpass(u, "lucio852",LUCIODALLA);
		assertNotEquals(false,state);

	}

	@Test
	void testFindUser() {
		// vedere come fare
		tu.setIdRuolo("W");
		
		assertNotNull(UsersDao.findUser(tu));
	}

	@Test
	void testDeleteUser() throws  SQLException {
		u.setEmail(LUCIODALLA);
		state=UsersDao.deleteUser(u);
		assertNotEquals(false,state);
	}

	@Test
	void testDeleteTempUser() throws  SQLException {
		u.setEmail("aldobaglio@gmail.com");
		u.setIdRuolo("U");
		state=UsersDao.deleteUser(u);
		assertNotEquals(false,state);
	}

	@Test
	void testPickData() throws SQLException  {
		u.setEmail(LUCIODALLA);
		assertNotNull(UsersDao.pickData(u));
}

	@Test
	void testAggiornaNome() throws SQLException  {
		u.setNome("lucio1");
		u.setEmail(LUCIODALLA);
		assertNotNull(UsersDao.aggiornaNome(u));
}

	@Test
	void testAggiornaCognomeUser() throws SQLException  {
		u.setCognome("dalla1");
		u.setEmail(LUCIODALLA);
		assertNotNull(UsersDao.aggiornaCognome(u));
}

	@Test
	void testAggiornaEmailUserString() throws SQLException  {
		u.setEmail(LUCIODALLA);
		assertNotNull(UsersDao.aggiornaEmail(u,LUCIODALLA));
		
	}

	
	@Test
	void testAggiornaPass() throws SQLException  {
		u.setEmail(LUCIODALLA1);
		u.setPassword("lucione963");
		assertNotNull(UsersDao.aggiornaPass(u));
	}

	@Test
	void testAggiornaDesc() throws SQLException  {
		u.setEmail("lucio1dalla1@gmail.com");
		u.setDescrizione("musica spettacolare");
		assertNotNull(UsersDao.aggiornaDesc(u));
	}

	@Test
	void testAggiornaData() throws SQLException  {
		u.setEmail("lucio1dalla1@gmail.com");
		u.setDataDiNascita(LocalDate.of(1940, 5, 6));
		assertNotNull(UsersDao.aggiornaData(u));
		
	}

	@Test
	void testAggiornaTempNome() throws SQLException  {
		tu.setEmail("albobaglio@gmail.com");
		tu.setNome("aldino");
		assertNotNull(UsersDao.aggiornaTempNome(tu));

	}

	@Test
	void testAggiornaCognomeTempUser() throws SQLException  {
		tu.setEmail("albobaglio@gmail.com");
		tu.setCognome("baglino");
		assertNotNull(UsersDao.aggiornaCognome(tu));
	}

	@Test
	void testAggiornaEmailTempUserString() throws SQLException  {
		tu.setEmail("aldobaglio@gmail.com");
		assertNotNull(UsersDao.aggiornaEmail(tu, "aldinobaglino@gmail.com"));
	}

	@Test
	void testAggiornaTempUtente() throws SQLException  {
		tu.setNome("aldo2");
		tu.setCognome("baglio2");
		tu.setEmail("aldobaglio2@gmail.com");
		tu.setPassword("aldoo2oo852");
		tu.setDescrizione("passeggero sbagiato nella subaru");
		tu.setDataDiNascita(LocalDate.of(1970, 3, 6));
		tu.setIdRuolo("u");
		assertNotNull(UsersDao.aggiornaTempUtente(tu,ALDOBAGLIO1));
	}

	@Test
	void testAggiornaTempPass() throws SQLException  {
		tu.setEmail(ALDOBAGLIO1);
		tu.setPassword("aldo142");
		assertNotNull(UsersDao.aggiornaTempPass(tu));
	}

	@Test
	void testAggiornaTempDesc() throws SQLException  {
		tu.setEmail("aldo1baglio1@gmail.com");
		tu.setDescrizione("non ci arrivo a pc");
		assertNotNull(UsersDao.aggiornaTempDesc(tu));

	}

	@Test
	void testAggiornaTempData() throws SQLException  {
		tu.setEmail("aldo1baglio1@gmail.com");
		tu.setDataDiNascita(LocalDate.of(1976,5,2));
		assertNotNull(UsersDao.aggiornaTempDesc(tu));

	}

	@Test
	void testGetListaUtenti() throws IOException, SQLException {
		UsersDao.getListaUtenti();
	}

	@Test
	void testGetTempUserSingolo() throws SQLException  {
		tu.setId(2);
		assertNotNull(UsersDao.getTempUserSingolo(tu));		
	}

	@Test
	void testAggiornaUtenteUser() throws SQLException  {
		u.setId(3);
		u.setIdRuolo("a");
	 	u.setNome("pippo");
	 	u.setCognome("paperino");
	 	u.setEmail("pippo@gmail.com");
	 	u.setPassword("pippo963");
	 	u.setDescrizione("non lo so");
	 	u.setDataDiNascita(LocalDate.of(1965, 7, 7));
	 	assertNotNull(UsersDao.aggiornaUtente(u));
	}

	@Test
	void testCreateTempUser() throws SQLException  {
		vis.setIsLogged(true);
		
		tu.setNome("giulio");
		tu.setCognome("rossi");
		tu.setEmail("giuliorossi@gmail.com");
		tu.setPassword("giulio521");
		tu.setDataDiNascita(LocalDate.of(1994,11 ,9));
		
		
		state=UsersDao.createUser2(tu);
		assertEquals(true,state);
	}

	@Test
	void testMaxIdUSer() throws SQLException  {
		state1=UsersDao.maxIdUSer();
		assertNotEquals(-1,state1);
	}

	@Test
	void testAggiornaUtenteTemp() throws NullPointerException, SQLException {
		tu.setIdRuolo("e");
		tu.setId(3);
		tu.setNome("alberto");
		tu.setCognome("binachi");
		tu.setEmail("albertoBianchi@gmail.com");
		tu.setPassword("albertino415");
		tu.setDescrizione("capoccione");
		tu.setDataDiNascita(LocalDate.of(1945,4,8));
		assertNotNull(UsersDao.aggiornaUtenteTemp(tu));
	
	}
	@Test
	void testAggiornaDataNascita() throws SQLException 
	{
		tu.setEmail("pippo@ll.com");
		tu.setDataDiNascita(LocalDate.of(1974, 11, 3));
		assertNotNull(UsersDao.aggiornaTempData(tu));
	}


}
