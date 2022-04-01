package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.ProdutoController;
import model.Produto;
import view.tables.ProdutoTableModel;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaProdutosUI extends JInternalFrame {
	private JTable jtProdutos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaProdutosUI frame = new ConsultaProdutosUI();
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
	public ConsultaProdutosUI() {
		setClosable(true);
		setTitle("Consulta de Produtos");
		setBounds(100, 100, 792, 284);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = new ProdutoTableModel(new ProdutoController().listar()).get(jtProdutos.getSelectedRow());
				try {
					new ProdutoController().excluir(produto.getId());
					JOptionPane.showMessageDialog(null, "Produto excluído com sucesso");
					jtProdutos.setModel(new ProdutoTableModel(new ProdutoController().listar()));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao excluir produto");
				}
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = new ProdutoTableModel(new ProdutoController().listar()).get(jtProdutos.getSelectedRow());
				CadastroProdutosUI cadProdutosUI = new CadastroProdutosUI();
				cadProdutosUI.setProdutoEdicao(produto);
				cadProdutosUI.setVisible(true);
				getParent().add(cadProdutosUI, 0);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnAtualizar = new JButton("Atualizar dados");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtProdutos.setModel(new ProdutoTableModel(new ProdutoController().listar()));
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAtualizar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 756, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(71)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnExcluir)
						.addComponent(btnEditar)
						.addComponent(btnAtualizar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		jtProdutos = new JTable();
		jtProdutos.setModel(new ProdutoTableModel(new ProdutoController().listar()));
		scrollPane.setViewportView(jtProdutos);
		getContentPane().setLayout(groupLayout);

	}
}
