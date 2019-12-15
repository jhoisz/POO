package Questao09Rosi;

public class Principal {

	public static void main(String[] args) {
		Endereco endereco1 = new Endereco("Rua das Oliveiras", "1321", "Ininga", "64000231");
		Endereco endereco2 = new Endereco("Av. Nacoes Unidas", "2113", "Vermelha", "64000320");
		
		Pessoa pessoa = new Pessoa("Maria", 1970, 'F', endereco2);
	
		System.out.println(pessoa);
		System.out.println(pessoa.getIdade(2007));
	}
}
