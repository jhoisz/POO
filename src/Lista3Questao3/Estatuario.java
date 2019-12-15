package Lista3Questao3;

public class Estatuario extends Professor {
	float trienio, rioprev;
	
	public Estatuario(String matricula, String nome, String endereco, int cargaHoraria, double valorDaHora, float t, float r) {
		super(matricula, nome, endereco, cargaHoraria, valorDaHora);
		this.trienio = t;
		this.rioprev = r;
	}
	public Estatuario(String matricula, String nome, int cargaHoraria, double valorDaHora, float t, float r) {
		super(matricula, nome, cargaHoraria, valorDaHora);
		this.trienio = t;
		this.rioprev = r;
	}
	public Estatuario(String matricula, String nome, String endereco, int cargaHoraria, double valorDaHora) {
		super(matricula, nome, endereco, cargaHoraria, valorDaHora);
	}
	public Estatuario(String matricula, String nome, int cargaHoraria, double valorDaHora) {
		super(matricula, nome, cargaHoraria, valorDaHora);
	}
	public void setCg(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public void setVh(double valorDaHora) {
		this.valorDaHora = valorDaHora;
	}
	public void setTrienio(float trienio) {
		this.trienio = trienio;
	}
	public void setRioprev(float rioprev) {
		this.rioprev = rioprev;
	}
	@Override
	public double calculaSalario() {
		return cargaHoraria*valorDaHora+trienio+rioprev;
	}
	
	public String toString() {
		return "\nMatricula: "+matricula+"\nNome: "+nome+"\nEndereco: "+
				endereco+"\nCarga Horaria: "+cargaHoraria+"\nValor da hora: " +
				valorDaHora+"\nTrienio: "+trienio+"\nRioprev: "+ rioprev+ "\nSalario: "+
				calculaSalario()+"\n____________________________________________________________";
	}
}