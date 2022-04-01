import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import controller.ClienteController;
import controller.ProdutoController;
import controller.VendaController;
import model.Cliente;
import model.ItemVenda;
import model.Produto;
import model.Venda;

public class Principal {

	public static void main(String[] args) {
		
//		testeCliente();
//		testeProduto();
		testeVenda();
		
	}
	
	public static void testeCliente() {
		Cliente c1 = new Cliente();
		c1.setId(1);
		c1.setNome("JOAO PAULO");
		c1.setCpf("123456789");
		c1.setTelefone("48 99999-1111");
		
		Cliente c2 = new Cliente();
		c2.setId(2);
		c2.setNome("PEDRO PAULO");
		c2.setCpf("12345678900");
		c2.setTelefone("48 99999-2222");
		
		ClienteController controller = new ClienteController();
		try {
			controller.salvar(c1);
			controller.salvar(c2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		for (Cliente c : controller.listar()) {
			System.out.println(c.toString());
		}
	}
	
	public static void testeProduto() {
		Produto p1 = new Produto();
		p1.setId(1);
		p1.setDescricao("Picolé de Morango");
		p1.setPrecoUnitario(2.50);
		
		Produto p2 = new Produto();
		p2.setId(2);
		p2.setDescricao("Picolé de Uva");
		p2.setPrecoUnitario(2.50);
		
		ProdutoController controller = new ProdutoController();
		
		try {
			controller.salvar(p1);
			controller.salvar(p2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for (Produto p : controller.listar()) {
			System.out.println(p.toString());
		}
	}
	
	public static void testeVenda() {
		
		Produto p1 = new Produto();
		p1.setId(1);
		p1.setDescricao("Picolé de Morango");
		p1.setPrecoUnitario(2.50);
		
		Produto p2 = new Produto();
		p2.setId(2);
		p2.setDescricao("Picolé de Uva");
		p2.setPrecoUnitario(2.50);
		
		ItemVenda i1 = new ItemVenda();
		i1.setProduto(p1);
		i1.setQtde(2);
		i1.calcularValorTotal();
				
		ItemVenda i2 = new ItemVenda();
		i2.setProduto(p2);
		i2.setQtde(1);
		i2.calcularValorTotal();
		
		List<ItemVenda> items = new ArrayList<>();
		items.add(i1);
		items.add(i2);
		
		for (ItemVenda item : items) {
			System.out.println(item.getQtde() + " - " + item.getProduto() + " - TOTAL: " + item.getValorTotal());
		}
		
		Cliente c1 = new Cliente();
		c1.setId(1);
		c1.setNome("JOAO PAULO");
		c1.setCpf("123456789");
		c1.setTelefone("48 99999-1111");
		
		Venda venda = new Venda();
		venda.setItensVenda(items);
		venda.setCliente(c1);
		venda.setDataEmissao(LocalDate.now());
		
		VendaController controller = new VendaController();
		try {
			controller.registrarVenda(venda);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
