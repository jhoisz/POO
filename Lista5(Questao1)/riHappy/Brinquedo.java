package riHappy;

public abstract class Brinquedo implements Taxa {
	private String nome;
	private int codigo, quantidade;
	private double preco;
	
	public Brinquedo(String nome, int codigo, double preco, int quantidade) {
		this.nome = nome;
		this.codigo = codigo;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	public double inventario() {
		return preco*quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public String toString() {
		return "nome: "+nome+"\ncodigo: "+codigo+"\n"
				+ "preco: "+preco+"\nquantidade: "+quantidade;
	}
}