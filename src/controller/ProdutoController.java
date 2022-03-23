package controller;

import java.util.List;

import dao.ProdutoDao;
import model.Produto;

public class ProdutoController {

	public void salvar(Produto produto) throws Exception {
		if (produto.getDescricao() == null || produto.getDescricao().trim().equals("")) {
			throw new Exception("Descrição do produto inválida");
		}
		ProdutoDao.getInstance().salvar(produto);
	}
	
	public void atualizar(Produto produto) throws Exception {
		if (produto.getDescricao() == null || produto.getDescricao().trim().equals("")) {
			throw new Exception("Descrição do produto inválida");
		}
		ProdutoDao.getInstance().atualizar(produto);
	}
	
	public void excluir(int idProduto) throws Exception {
		if (idProduto == 0) {
			throw new Exception("Nenhum produto selecionado");
		}
		ProdutoDao.getInstance().excluir(idProduto);
	}
	
	public List<Produto> listar(){
		return ProdutoDao.getInstance().listar();
	}
	
}
