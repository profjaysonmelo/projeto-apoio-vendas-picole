package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.ProdutoController;
import model.Produto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroProdutosUI extends JInternalFrame {
	private JTextField txtDescricao;
	private JTextField txtPrecoUnitario;
	private Produto produto;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProdutosUI frame = new CadastroProdutosUI();
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
	public CadastroProdutosUI() {
		
		setClosable(true);
		setTitle("Cadastro de Produtos");
		setBounds(100, 100, 598, 214);
		
		JPanel jpCadastroProdutos = new JPanel();
		jpCadastroProdutos.setBorder(new TitledBorder(null, "Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (produto != null && produto.getId() > 0) {
						produto.setDescricao(txtDescricao.getText());
						produto.setPrecoUnitario(Double.parseDouble(txtPrecoUnitario.getText()));
						new ProdutoController().atualizar(produto);
						JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso");
					} else {
						Produto produto = new Produto();
						produto.setDescricao(txtDescricao.getText());
						produto.setPrecoUnitario(Double.parseDouble(txtPrecoUnitario.getText()));
						
						new ProdutoController().salvar(produto);
						JOptionPane.showMessageDialog(null, "Produto salvo com sucesso");
					}
					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao salvar produto");
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(jpCadastroProdutos, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpCadastroProdutos, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		
		JLabel jlDescricao = new JLabel("Descri\u00E7\u00E3o:");
		
		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		
		JLabel jlPrecoUnitario = new JLabel("Pre\u00E7o Unit\u00E1rio:");
		
		txtPrecoUnitario = new JTextField();
		txtPrecoUnitario.setColumns(10);
		GroupLayout gl_jpCadastroProdutos = new GroupLayout(jpCadastroProdutos);
		gl_jpCadastroProdutos.setHorizontalGroup(
			gl_jpCadastroProdutos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCadastroProdutos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpCadastroProdutos.createSequentialGroup()
							.addComponent(jlDescricao)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDescricao, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
						.addGroup(gl_jpCadastroProdutos.createSequentialGroup()
							.addComponent(jlPrecoUnitario)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPrecoUnitario, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_jpCadastroProdutos.setVerticalGroup(
			gl_jpCadastroProdutos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCadastroProdutos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlDescricao)
						.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlPrecoUnitario)
						.addComponent(txtPrecoUnitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(199, Short.MAX_VALUE))
		);
		jpCadastroProdutos.setLayout(gl_jpCadastroProdutos);
		getContentPane().setLayout(groupLayout);

	}
	
	public void setProdutoEdicao(Produto produto) {
		this.produto = produto;
		preencheFormulario();
	}
	
	private void preencheFormulario() {
		if(produto != null) {
			txtDescricao.setText(produto.getDescricao());
			txtPrecoUnitario.setText(produto.getPrecoUnitario().toString());
		}
	}
}
