package Lista3Questao3;

public class Contratado extends Professor {
	private float inss;
	
	public Contratado(String matricula, String nome, String endereco, int cargaHoraria, double valorDaHora, float inss) {
		super(matricula, nome, endereco, cargaHoraria, valorDaHora);
		this.inss = inss;
	}

	@Override
	public double calculaSalario() {
		return cargaHoraria*valorDaHora-inss;
	}
}
