package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrincipalUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUI frame = new PrincipalUI();
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
	public PrincipalUI() {
		setTitle("Sistemas de Vendas de Picol\u00E9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 973, 564);
		
		JMenuBar jbPrincipal = new JMenuBar();
		setJMenuBar(jbPrincipal);
		
		JMenu jmCadastros = new JMenu("Cadastros");
		jbPrincipal.add(jmCadastros);
		
		JMenuItem jmiProdutos = new JMenuItem("Produtos");
		jmiProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProdutosUI cadProduto = new CadastroProdutosUI();
				cadProduto.setVisible(true);
				contentPane.add(cadProduto, 0);
			}
		});
		jmCadastros.add(jmiProdutos);
		
		JMenuItem jmiClientes = new JMenuItem("Clientes");
		jmCadastros.add(jmiClientes);
		
		JMenu jmConsultas = new JMenu("Consultas");
		jbPrincipal.add(jmConsultas);
		
		JMenuItem jmiConsultaProdutos = new JMenuItem("Produtos");
		jmiConsultaProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaProdutosUI consultaProdUI = new ConsultaProdutosUI();
				consultaProdUI.setVisible(true);
				contentPane.add(consultaProdUI, 0);
			}
		});
		jmConsultas.add(jmiConsultaProdutos);
		
		JMenuItem jmiConsultaClientes = new JMenuItem("Clientes");
		jmConsultas.add(jmiConsultaClientes);
		
		JMenu jmVendas = new JMenu("Vendas");
		jbPrincipal.add(jmVendas);
		
		JMenuItem jmiRegistrarVendas = new JMenuItem("Registrar Vendas");
		jmVendas.add(jmiRegistrarVendas);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 251, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}

}
