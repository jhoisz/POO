package Questao10Rosi;

import javax.swing.JOptionPane;


public class Principal {

	public static void main(String[] args) {
			ConjuntoPessoas cp = new ConjuntoPessoas(3);
		
			while(true) {
		
				int opcao = Integer.parseInt(
						JOptionPane.showInputDialog("Escolha uma das opcoes:" +
						"\n1 - Insere uma pessoa\n2 - Lista pessoas\n3 - Remove pessoa\n0 - Sair"));
				switch (opcao) {
				case 1:
					System.out.println("Digite o endereco: LOGRADOURO, NUMERO, BAIRRO E CEP");
					Endereco endereco = new Endereco(StdIn.readString(), StdIn.readString(), StdIn.readString(), StdIn.readString());
					System.out.println("Digite os dados da pessoa: NOME, ANO DE NASCIMENTO, SEXO E CPF");
					Pessoa pessoa = new Pessoa(StdIn.readString(), StdIn.readInt(), StdIn.readChar(), endereco, StdIn.readString());
					cp.inserePessoa(pessoa);
					break;
				case 2: 
					System.out.println(cp);
					break;
				case 3:
					String cpf = JOptionPane.showInputDialog("Pessoa a ser removida");
					cp.removePessoa(cpf);
					break;
				default: return;
				}
			}
		
		
		/*ConjuntoPessoas cp = new ConjuntoPessoas(3);
		
		Endereco endereco1 = new Endereco("Rua das Oliveiras", "1321", "Ininga", "64000231");
		Endereco endereco2 = new Endereco("Av. Nacoes Unidas", "2113", "Vermelha", "64000320");
	
		Pessoa pessoa1 = new Pessoa("Maria", 1970, 'F', endereco1, "06831073266");
		Pessoa pessoa2 = new Pessoa("Joao", 1980, 'M', endereco2, "38659836459");
		cp.inserePessoa(pessoa1);
		cp.inserePessoa(pessoa2);
		
		cp.exibirPessoas();
		
		System.out.println("\n\n----------------");
		cp.removePessoa("06831073266");
		System.out.println("\n\n----------------");
		cp.exibirPessoas();
		*/
	}
}