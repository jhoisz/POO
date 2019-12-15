package jhoisnayraVitoria;

//import java.util.ArrayList;

public class Cliente {

	private int cpf;
	private String nome;

	public int getCpf() {
		return cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Cliente(int cpf1, String nome1) {
		cpf = cpf1;
		nome = nome1;
	}	
}
