package controller;

import java.util.List;

import dao.ClienteDao;
import model.Cliente;

public class ClienteController {

	public void salvar(Cliente cliente) throws Exception {
		if (cliente.getNome() == null || cliente.getNome().length() < 3) {
			throw new Exception("Nome inválido");
		}
		ClienteDao.getInstance().salvar(cliente);
	}
	
	public void atualizar(Cliente cliente) throws Exception {
		if (cliente.getNome() == null || cliente.getNome().length() < 3) {
			throw new Exception("Nome inválido");
		}
		ClienteDao.getInstance().atualizar(cliente);
	}
	
	public void excluir(int idCliente) throws Exception {
		if (idCliente == 0) {
			throw new Exception("Nenhum cliente selecionado");
		}
		ClienteDao.getInstance().excluir(idCliente);
	}
	
	public List<Cliente> listar(){
		return ClienteDao.getInstance().listar();
	}
		
}
