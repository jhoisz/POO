package Questao10Rosi;

public class Endereco {
	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	
	public Endereco(String logradouro, String numero, String bairro, String cep) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;		
	}
	
	public String toString(){
		return this.logradouro + ", " + this.numero +
	               ", " + this.bairro + ", " + this.cep;
	}
}
