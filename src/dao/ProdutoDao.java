package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import util.ConnectionUtil;

public class ProdutoDao {

	private static ProdutoDao instance;
	private List<Produto> listaProdutos = new ArrayList<>();
	private Connection con = ConnectionUtil.getConnection();
	
	public static ProdutoDao getInstance() {
		if (instance == null) {
			instance = new ProdutoDao();
		}
		return instance;
	}
	
	public void salvar(Produto produto) {
		try {
			String sql = "insert into produto (descricao, preco_unitario) values (?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, produto.getDescricao());
			pstmt.setDouble(2, produto.getPrecoUnitario());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Produto produto) {
		try {
			String sql = "update produto set descricao = ?, preco_unitario = ? where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, produto.getDescricao());
			pstmt.setDouble(2, produto.getPrecoUnitario());
			pstmt.setInt(3, produto.getId());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(int idProduto) {
		try {
			String sql = "delete from produto where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idProduto);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Produto> listar(){
		List<Produto> listaProdutos = new ArrayList<>();
		try {
			String sql = "select * from produto";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setDescricao(rs.getString("descricao"));
				p.setPrecoUnitario(rs.getDouble("preco_unitario"));
				listaProdutos.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProdutos;
	}
	
}
