package Lista3Questao3;

public abstract class Funcionario {
	String matricula, nome, endereco;
	
	public Funcionario(String nome, String matricula, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
		this.matricula = matricula;
	}
	public Funcionario(String nome, String matricula) {
		this.nome = nome;
		this.matricula = matricula;
	}
	public abstract double calculaSalario();
}