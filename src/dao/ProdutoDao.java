package dao;

import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class ProdutoDao {

	private static ProdutoDao instance;
	private List<Produto> listaProdutos = new ArrayList<>();
	
	public static ProdutoDao getInstance() {
		if (instance == null) {
			instance = new ProdutoDao();
		}
		return instance;
	}
	
	public void salvar(Produto produto) {
		listaProdutos.add(produto);
	}
	
	public void atualizar(Produto produto) {
		listaProdutos.set(produto.getId(), produto);
	}
	
	public void excluir(int idProduto) {
		listaProdutos.remove(idProduto);
	}
	
	public List<Produto> listar(){
		return listaProdutos;
	}
	
}
