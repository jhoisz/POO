package Lista3Questao3;

public class Professor extends Funcionario {
	int cargaHoraria;
	double valorDaHora;
	
	public Professor(String matricula, String nome, String endereco, int cargaHoraria, double valorDaHora) {
		super(matricula, nome, endereco);
		this.cargaHoraria = cargaHoraria;
		this.valorDaHora = valorDaHora;
	}
	public Professor(String matricula, String nome, int cargaHoraria, double valorDaHora) {
		super(matricula, nome);
		this.cargaHoraria = cargaHoraria;
		this.valorDaHora = valorDaHora;
	}
	
	@Override
	public double calculaSalario() {
		return cargaHoraria*valorDaHora;
	}
}
