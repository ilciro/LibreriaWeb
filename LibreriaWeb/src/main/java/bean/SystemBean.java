package bean;


public class SystemBean { 
	private int id;
	 public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isLogged() {
		return isLogged;
	}
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	public boolean isSearch() {
		return isSearch;
	}
	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}
	public boolean isPickup() {
		return isPickup;
	}
	public void setPickup(boolean isPickup) {
		this.isPickup = isPickup;
	}
	 public static SystemBean getIstance()
	 {
		 if (instance == null) 
		 {
			 return new SystemBean();
		 }
	 return instance;
	 }
	public static void setInstance(SystemBean instance) {
		SystemBean.instance = instance;
	}
	public float getSpesaT() {
		return spesaT;
	}
	public void setSpesaT(float spesaT) {
		this.spesaT = spesaT;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public String getMetodoP() {
		return metodoP;
	}
	public void setMetodoP(String metodoP) {
		this.metodoP = metodoP;
	}
	private String type;
	 public int getElemLista() {
		return elemLista;
	}
	public void setElemLista(int elemLista) {
		this.elemLista = elemLista;
	}
	public int getElemListaPag() {
		return elemListaPag;
	}
	public void setElemListaPag(int elemListaPag) {
		this.elemListaPag = elemListaPag;
	}
	public boolean isNegScelto() {
		return negScelto;
	}
	public void setNegScelto(boolean negScelto) {
		this.negScelto = negScelto;
	}
	private boolean isLogged ;
	 private boolean isSearch;
	 private boolean isPickup;
	 private static SystemBean instance=new SystemBean() ;
	 private float spesaT;// usato per avere importo totale 
	 private int quantita; //usato per avere quantita oggetto che compro
	 private String metodoP; //usato per vedere se contanti o cc
	 private int elemLista;
	 private int elemListaPag;
	 private boolean negScelto;

}
