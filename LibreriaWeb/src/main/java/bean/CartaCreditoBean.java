package bean;

import java.sql.Date;
import java.util.logging.Level;

import model.Log;

public class CartaCreditoBean {
	private int tipo;
	private String numeroCC;
	private double limite;
	private double ammontare;
	private java.util.Date scadenza;
	private String nomeUser; 
	private float prezzoTransazine;
	private String cognomeUser; 
	private String civ;


	public CartaCreditoBean() {
		Log.LOGGER.log(Level.INFO,"costruttore cartaCredito bean");
	}
	
	public CartaCreditoBean(int tipo, String numeroCC, double limite, double ammontare, Date scadenza, String nomeUser,
			float prezzoTransazine) {
		super();
		this.tipo = tipo;
		this.numeroCC = numeroCC;
		this.limite = limite;
		this.ammontare = ammontare;
		this.scadenza = scadenza;
		this.nomeUser = nomeUser;
		this.prezzoTransazine = prezzoTransazine;
	}
	

	public CartaCreditoBean(String n,String c,String cod,java.util.Date date,String civ,float prezzo)
	{
		
		this.nomeUser=n;
		this.cognomeUser=c;
		this.numeroCC=cod;
		this.ammontare=1000.0;
		this.scadenza=date;
		this.civ=civ;
		this.prezzoTransazine=prezzo;
	}

	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getNumeroCC() {
		return numeroCC;
	}
	public void setNumeroCC(String numeroCC) {
		this.numeroCC = numeroCC;
	}
	public double getLimite() {
		return limite;
	}
	public void setLimite(double limite) {
		this.limite = limite;
	}
	public double getAmmontare() {
		return ammontare;
	}
	public void setAmmontare(double ammontare) {
		this.ammontare = ammontare;
	}
	public java.util.Date getScadenza() {
		return scadenza;
	}
	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}
	public String getNomeUser() {
		return nomeUser;
	}
	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}
	public float getPrezzoTransazine() {
		return prezzoTransazine;
	}
	public void setPrezzoTransazine(float prezzoTransazine) {
		this.prezzoTransazine = prezzoTransazine;
	}

	public String getCognomeUser() {
		return cognomeUser;
	}

	public void setCognomeUser(String cognomeUser) {
		this.cognomeUser = cognomeUser;
	}

	public String getCiv() { 
		return civ;
	}

	public void setCiv(String civ) {
		this.civ = civ;
	}

	

}
