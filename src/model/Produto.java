package model;

public class Produto {

	private String descricao;
	private Double precoUnitario;
	
	public Produto(String descricao, Double precoUnitario) {
		this.descricao = descricao;
		this.precoUnitario = precoUnitario;
	}
	public Produto() {
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	
	public String toString(){
		return descricao;
	}
}
