package Questao09Rosi;

public class Pessoa {
	private String nome;
	private int anoNascimento;
	private char sexo;
	private Endereco endereco;
	
	public Pessoa(String nome, int anoNascimento, char sexo, Endereco endereco) {
		this.nome = nome;
		this.anoNascimento = anoNascimento;
		this.sexo = sexo;
		this.endereco = endereco;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public int getIdade(int anoAtual) {
		return anoAtual - anoNascimento;
	}
	public String toString() {
		return "Nome: "+this.nome+"\nAno de nascimento: "+this.anoNascimento+
				"\nSexo: "+this.sexo+"\nEndereco: "+this.endereco; 
	}
}
