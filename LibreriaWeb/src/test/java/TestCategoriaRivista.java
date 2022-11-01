package java;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import raccolta.Rivista;


class TestCategoriaRivista {
	private Rivista r=new Rivista();


	@Test
	void testSettimanale() {
		r.setTipologia("SETTIMANALE");
	}

	@Test
	void testBisettimanale() {
		r.setTipologia("BISETTIMANALE");
	}

	@Test
	void testMensile() {
		r.setTipologia("MENSILE");
		assertEquals("MENSILE",r.getTipologia());
		}

	@Test
	void testBimestrale() {
		r.setTipologia("BIMESTRALE");
	}

	@Test
	void testTrimestrale() {
		r.setTipologia("TRIMESTRALE");
		}

	@Test
	void testAnnuale() {
		r.setTipologia("ANNUALE");
		}

	@Test
	void testEstivo() {
		r.setTipologia("ESTIVO");
	}

	@Test
	void testInvernale() {
		r.setTipologia("INVERNALE");
	}

	@Test
	void testSportivo() {
		r.setTipologia("SPORTIVO");
		
	}

	@Test
	void testCinematografica() {
		r.setTipologia("CINEMATOGRAFICA");
		}

	@Test
	void testGossip() {
		r.setTipologia("GOSSIP");
		}

	@Test
	void testTelevisivo() {
		r.setTipologia("TELEVISIVO");
		}

	@Test
	void testMilitare() {
		r.setTipologia("MILITARE");
	}

	@Test
	void testInformatica() {
		r.setTipologia("INFORMATICA");
	}

}
