package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ItemVenda;
import model.Venda;
import util.ConnectionUtil;

public class VendaDao {

	private static VendaDao instance;
	private List<Venda> listaVendas = new ArrayList<>();
	private Connection con = ConnectionUtil.getConnection();
	
	public static VendaDao getInstance() {
		if (instance == null) {
			instance = new VendaDao();
		}
		return instance;
	}
	
	public void registrarVenda(Venda venda) {
		try {
			
			String sql = "insert into venda (data_emissao, valor_total, id_cliente) values (?, ?, ?)"; 
			
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setDate(1, java.sql.Date.valueOf(venda.getDataEmissao()));
			pstmt.setDouble(2, venda.getValorTotal());
			pstmt.setInt(3, venda.getCliente().getId());
			
			int key = pstmt.executeUpdate();
			
			if (key > 0) {
			
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				int idVenda = rs.getInt(1);
			
			
				String sqlItemVenda = "insert into item_venda (qtde, valor_total, id_produto, id_venda) values (?, ?, ?, ?)";
				PreparedStatement pstmtItemVenda = con.prepareStatement(sqlItemVenda);
				for (ItemVenda item : venda.getItensVenda()) {
					pstmtItemVenda.setInt(1, item.getQtde());
					pstmtItemVenda.setDouble(2, item.getValorTotal());
					pstmtItemVenda.setInt(3, item.getProduto().getId());
					pstmtItemVenda.setInt(4, idVenda);
					pstmtItemVenda.execute();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
