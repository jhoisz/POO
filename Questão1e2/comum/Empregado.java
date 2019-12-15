package comum;

public abstract class Empregado {
	protected String nome;
	protected String endereco;
	
	public Empregado(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}
	public Empregado(String nome) {
		this.nome = nome;
	}
	public abstract double calcularSalario();
}
