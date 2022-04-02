package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.ClienteController;
import controller.ProdutoController;
import controller.VendaController;
import model.Cliente;
import model.ItemVenda;
import model.Produto;
import model.Venda;
import view.tables.ItemVendaTableModel;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class RegistroVendaUI extends JInternalFrame {
	private JTextField txtDataEmissao;
	private JTextField txtQtde;
	private JTable jtItemVenda;
	
	JComboBox<Cliente> cbCliente = new JComboBox<Cliente>();
	JComboBox<Produto> cbProduto = new JComboBox<Produto>();
	
	List<ItemVenda> itensVenda = new ArrayList<ItemVenda>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroVendaUI frame = new RegistroVendaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistroVendaUI() {
		setClosable(true);
		setTitle("Registrar Vendas");
		setBounds(100, 100, 656, 439);
		
		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<Cliente>();
		for(Cliente cliente : new ClienteController().listar()) {
			modelCliente.addElement(cliente);
		}
		cbCliente.setModel(modelCliente);
		
		DefaultComboBoxModel<Produto> modelProduto = new DefaultComboBoxModel<Produto>();
		for(Produto produto : new ProdutoController().listar()) {
			modelProduto.addElement(produto);
		}
		cbProduto.setModel(modelProduto);
		
		txtDataEmissao = new JTextField();
		txtDataEmissao.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente:");
		
		JLabel lblNewLabel_1 = new JLabel("Data de Emiss\u00E3o:");
		
		JLabel lblProduto = new JLabel("Produto:");
		
		JLabel lblNewLabel = new JLabel("Qtde:");
		
		txtQtde = new JTextField();
		txtQtde.setColumns(10);
		
		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = (Produto) cbProduto.getSelectedItem();
				int qtde = Integer.parseInt(txtQtde.getText());
				ItemVenda itemVenda = new ItemVenda();
				itemVenda.setProduto(produto);
				itemVenda.setQtde(qtde);
				itemVenda.calcularValorTotal();
				itensVenda.add(itemVenda);
				jtItemVenda.setModel(new ItemVendaTableModel(itensVenda));
			}
		});
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		
		JButton btnNewButton_2 = new JButton("Salvar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = (Cliente) cbCliente.getSelectedItem();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				try {
					LocalDate dataEmissao = LocalDate.parse(txtDataEmissao.getText(), formatter);
					Venda venda = new Venda();
					venda.setCliente(cliente);
					venda.setDataEmissao(dataEmissao);
					venda.setItensVenda(itensVenda);
					Double somaTotal = 0.0;
					for(ItemVenda item : itensVenda) {
						somaTotal += item.getValorTotal();
					}
					venda.setValorTotal(somaTotal);
					new VendaController().registrarVenda(venda);
					JOptionPane.showMessageDialog(null, "Venda registrada com sucesso");
					dispose();
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao tranformar data");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao registrar venda");
				}
			}
		});
		
		jtItemVenda = new JTable();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCliente)
							.addGap(9)
							.addComponent(cbCliente, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProduto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbProduto, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDataEmissao))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtQtde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)))
					.addContainerGap(23, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(jtItemVenda, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCliente)
						.addComponent(cbCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtDataEmissao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduto)
						.addComponent(cbProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(txtQtde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addComponent(jtItemVenda, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(20))
		);
		getContentPane().setLayout(groupLayout);

	}
}
