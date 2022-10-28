package controller;

import java.sql.SQLException;

import database.PagamentoDao;
import javafx.collections.ObservableList;
import model.Pagamento;

public class ControllerVisualizzaOrdine {
private PagamentoDao pD;
	
	public ObservableList<Pagamento> getDati() throws SQLException  {
		
		return pD.getPagamenti();
		}
	
	public ControllerVisualizzaOrdine()
	{
		pD=new PagamentoDao();
		
	}
}
