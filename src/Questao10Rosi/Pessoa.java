package Questao10Rosi;

public class Pessoa {
	private String nome;
	private int anoNascimento;
	private char sexo;
	private Endereco endereco;
	protected String cpf;
	
	public Pessoa(String nome, int anoNascimento, char sexo, Endereco endereco, String cpf) {
		this.nome = nome;
		this.anoNascimento = anoNascimento;
		this.sexo = sexo;
		this.endereco = endereco;
		this.cpf = cpf;
	}
	public String getCpf() {
		return cpf;
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
				"\nSexo: "+this.sexo+"\nEndereco: "+this.endereco+"\nCPF: "+ this.cpf; 
	}
}
