package controller;

import java.sql.SQLException;

import database.ContrassegnoDao;
import database.GiornaleDao;
import database.LibroDao;
import database.PagamentoDao;
import database.RivistaDao;
import model.Fattura;
import model.Pagamento;
import raccolta.Giornale;
import raccolta.Libro;
import raccolta.Rivista;

public class ControllerPagamentoCash {
	private ContrassegnoDao pD;
	private Fattura f;
	private PagamentoDao pagD;
	private ControllerSystemState vis= ControllerSystemState.getIstance();
	private Libro l;	
	private LibroDao lD;
	private GiornaleDao gD;
	private RivistaDao rD;
	private Giornale g;
	private Rivista r;
	

	public void controlla(String nome, String cognome, String via, String com) throws SQLException {
		
			pD.daiPrivilegi();
			pagD.daiPrivilegi();
			float spesa=vis.getSpesaT();

			//fino a qui va
			
			f.setNome(nome);
			f.setCognome(cognome);
			f.setVia(via);
			f.setCom(com);
			f.setAmmontare(spesa);
			
	 		

					
			
			pD.inserisciFattura(f);
			
			Pagamento p;
			
			p=new Pagamento(0,"", 0, "utGene",0, null);
				
			//inserire qui
			p.setMetodo("cash");
			p.setNomeUtente(nome);
			String tipo=vis.getType();
			p.setId(vis.getId());
			if(tipo.equals("libro"))
			{
				//prenod spesa da vis
				l.setId(vis.getId());
				p.setAmmontare(vis.getSpesaT());
				p.setId(l.getId());
				p.setTipo(lD.retTip(l));
				lD.aggiornaDisponibilita(l);
			}
			if(tipo.equals("giornale"))
			{
				//prenod spesa da vis
				g.setId(vis.getId());
				p.setAmmontare(vis.getSpesaT());
				p.setId(g.getId());
				p.setTipo(gD.retTip(g));
				gD.aggiornaDisponibilita(g);
				
			}
			if(tipo.equals("rivista"))
			{
				//prenod spesa da vis
				r.setId(vis.getId());
				p.setAmmontare(vis.getSpesaT());
				p.setId(r.getId());
				p.setTipo(rD.retTip(r));
				rD.aggiornaDisponibilita(r);
				
			}
			
			
			
			
			pagD.inserisciPagamento(p);
			
			
			
			
	}

	public ControllerPagamentoCash()  {
		pD = new ContrassegnoDao();
		f = new Fattura();
		pagD=new PagamentoDao();
		lD=new LibroDao();
		l=new Libro();
		gD=new GiornaleDao();
		g=new Giornale();
		rD=new RivistaDao();
		r=new Rivista();
		
		
	}

}
