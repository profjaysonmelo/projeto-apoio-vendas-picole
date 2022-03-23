package dao;

import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDao {

	private static ClienteDao instance;
	private List<Cliente> listaClientes = new ArrayList<>();
	
	/*
	 * Singleton
	 */
	public static ClienteDao getInstance() {
		if (instance == null) {
			instance = new ClienteDao();
		}
		return instance;
	}
	
	public void salvar(Cliente cliente) {
		listaClientes.add(cliente);
	}
	
	public void atualizar(Cliente cliente) {
		listaClientes.set(cliente.getId(), cliente);
	}
	
	public void excluir(int idCliente) {
		listaClientes.remove(idCliente);
	}
	
	public List<Cliente> listar(){
		return listaClientes;
	}
	
}
