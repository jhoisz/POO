package Questao10Rosi;

public class ConjuntoPessoas {
	Pessoa[] pessoas;
	int indice = 0;

	public ConjuntoPessoas(int n) {
		pessoas = new Pessoa[n];
	}
	
	public void inserePessoa(Pessoa pessoa){
		if(indice<pessoas.length) {
			pessoas[indice] = pessoa;
			indice++;
		} else {
			System.out.println("Conjunto lotado!");
		}
	}
	public void exibirPessoas() {
		for(int i=0; i<indice; i++) {
			System.out.println("Pessoa "+i+": "+ pessoas[i]+"\n__________________");
		}
	}
	public boolean removePessoa(String cpf) {
		Pessoa[] aux = pessoas;
		int i, j;
		for(i=0; i<indice; i++) {			
			if(cpf.equals(pessoas[i].cpf)){
				pessoas[i] = null;
				for(j=i; j<indice; j++) {
					pessoas[j] = aux[j+1];
				}
			indice--;
			return true;
			}
		}
		return false;
	}
}