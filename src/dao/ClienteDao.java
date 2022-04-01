package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import util.ConnectionUtil;

public class ClienteDao {

	private static ClienteDao instance;
	private List<Cliente> listaClientes = new ArrayList<>();
	private Connection con = ConnectionUtil.getConnection();
	
	/*
	 * Singleton - Design Pattern
	 */
	public static ClienteDao getInstance() {
		if (instance == null) {
			instance = new ClienteDao();
		}
		return instance;
	}
	
	public void salvar(Cliente cliente) {
		try {
			String sql = "insert into cliente (nome, cpf, endereco, telefone) values (?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpf());
			pstmt.setString(3, cliente.getEndereco());
			pstmt.setString(4, cliente.getTelefone());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Cliente cliente) {
		try {
			String sql = "update cliente set nome = ?, cpf = ?, endereco = ?, telefone = ? where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpf());
			pstmt.setString(3, cliente.getEndereco());
			pstmt.setString(4, cliente.getTelefone());
			pstmt.setInt(5, cliente.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(int idCliente) {
		try {
			String sql = "delete from cliente where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idCliente);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Cliente> listar(){
		List<Cliente> listaClientes = new ArrayList<>();
		try {
			String sql = "select * from cliente";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCpf(rs.getString("cpf"));
				c.setEndereco(rs.getString("endereco"));
				c.setTelefone(rs.getString("telefone"));
				listaClientes.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaClientes;
	}
	
}
