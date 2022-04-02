package view.tables;

import java.util.ArrayList;
import java.util.List;
import model.ItemVenda;
import javax.swing.table.AbstractTableModel;

public class ItemVendaTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final int COL_PRODUTO = 0;
	private static final int COL_QTDE = 1;
	private static final int COL_PRECO = 2;
	private static final int COL_TOTAL = 3;

	private List<ItemVenda> valores;

	//Esse é um construtor, que recebe a nossa lista de clientes
	public ItemVendaTableModel(List<ItemVenda> valores) {
		this.valores = new ArrayList<ItemVenda>(valores);
	}

	public int getRowCount() {
		//Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		//Quantas colunas tem a tabela? Nesse exemplo, só 4.
		return 4;
	}

	public String getColumnName(int column) {
		//Qual é o nome das nossas colunas?
		if (column == COL_PRODUTO) return "Produto";
		if (column == COL_QTDE) return "Quantidade";
		if (column == COL_PRECO) return "Preço Unitário";
		if (column == COL_TOTAL) return "Total";
		return ""; //Nunca deve ocorrer
	}

	public Object getValueAt(int row, int column) {
		//Precisamos retornar o valor da coluna column e da linha row.
		ItemVenda item = valores.get(row);
		if (column == COL_PRODUTO)
			return item.getProduto().getDescricao();
		else 
			if (column == COL_PRECO) 
				return item.getProduto().getPrecoUnitario();
			else
				if (column == COL_QTDE)
					return item.getQtde();
				else
					if (column == COL_TOTAL)
						return item.getValorTotal();
		return ""; //Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ItemVenda item = valores.get(rowIndex);
		//Vamos alterar o valor da coluna columnIndex na linha rowIndex com o valor aValue passado no parï¿½metro.
		//Note que vc poderia alterar 2 campos ao invï¿½s de um sï¿½.
		if (columnIndex == COL_PRODUTO)
			item.getProduto().setDescricao(aValue.toString());
		else 
			if (columnIndex == COL_PRECO) 
				item.getProduto().setPrecoUnitario(Double.parseDouble(aValue.toString()));
			else
				if (columnIndex == COL_QTDE)
					item.setQtde(Integer.parseInt(aValue.toString()));
				else
					if (columnIndex == COL_TOTAL)
						item.setValorTotal(Double.parseDouble(aValue.toString()));
	}

	public Class<?> getColumnClass(int columnIndex) {
		//Qual a classe das nossas colunas? Como estamos exibindo texto, ï¿½ string.
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//Indicamos se a célula da rowIndex e da columnIndex é editável. Nossa tabela toda ï¿½.
		return true;
	}
	//Já que esse tableModel é de clientes, vamos fazer um get que retorne um objeto cliente inteiro.
	//Isso elimina a necessidade de chamar o getValueAt() nas telas. 
	public ItemVenda get(int row) {
		return valores.get(row);
	}
}

