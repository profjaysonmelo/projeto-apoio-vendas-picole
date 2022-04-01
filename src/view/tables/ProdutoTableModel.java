package view.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Produto;

public class ProdutoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_DESCRICAO = 0;
	private static final int COL_PRECO_UNITARIO = 1;

	private List<Produto> valores;       

	//Esse � um construtor, que recebe a nossa lista de clientes
	public ProdutoTableModel(List<Produto> valores) {
		this.valores = new ArrayList<Produto>(valores);
	}

	public int getRowCount() {
		//Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		//Quantas colunas tem a tabela? Nesse exemplo, s� 2.
		return 2;
	}

	public String getColumnName(int column) {
		//Qual � o nome das nossas colunas?
		if (column == COL_DESCRICAO) return "Descri��o";
		if (column == COL_PRECO_UNITARIO) return "Pre�o Unit�rio";
		return ""; //Nunca deve ocorrer
	}

	public Object getValueAt(int row, int column) {
		//Precisamos retornar o valor da coluna column e da linha row.
		Produto produto = valores.get(row);
		if (column == COL_DESCRICAO)
			return produto.getDescricao();
		else 
			if (column == COL_PRECO_UNITARIO) 
					return produto.getPrecoUnitario();
		return ""; //Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Produto produto = valores.get(rowIndex);
		//Vamos alterar o valor da coluna columnIndex na linha rowIndex com o valor aValue passado no par�metro.
		//Note que vc poderia alterar 2 campos ao inv�s de um s�.
		if (columnIndex == COL_DESCRICAO)
			produto.setDescricao(aValue.toString());
		else 
			if (columnIndex == COL_PRECO_UNITARIO) 
				produto.setPrecoUnitario(Double.parseDouble(aValue.toString()));
	}

	public Class<?> getColumnClass(int columnIndex) {
		//Qual a classe das nossas colunas? Como estamos exibindo texto, � string.
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//Indicamos se a c�lula da rowIndex e da columnIndex � edit�vel. Nossa tabela toda �.
		return true;
	}
	//J� que esse tableModel � de clientes, vamos fazer um get que retorne um objeto cliente inteiro.
	//Isso elimina a necessidade de chamar o getValueAt() nas telas. 
	public Produto get(int row) {
		return valores.get(row);
	}
	
}
