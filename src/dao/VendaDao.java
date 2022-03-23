package dao;

import java.util.ArrayList;
import java.util.List;

import model.Venda;

public class VendaDao {

	private static VendaDao instance;
	private List<Venda> listaVendas = new ArrayList<>();
	
	public static VendaDao getInstance() {
		if (instance == null) {
			instance = new VendaDao();
		}
		return instance;
	}
	
	public void registrarVenda(Venda venda) {
		listaVendas.add(venda);
	}
}
