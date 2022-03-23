package controller;

import dao.VendaDao;
import model.Venda;

public class VendaController {

	public void registrarVenda(Venda venda) throws Exception {
		if (venda.getDataEmissao() == null) {
			throw new Exception("Data Inválida");
		}
		if (venda.getCliente() == null) {
			throw new Exception("Cliente Inválido");
		}
		VendaDao.getInstance().registrarVenda(venda);
	}
	
}
