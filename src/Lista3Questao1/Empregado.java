package Lista3Questao1;

public abstract class Empregado {
	String nome;
	String endereco;
	
	public Empregado(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}
	public Empregado(String nome) {
		this.nome = nome;
	}
	public abstract double calcularSalario();
}
