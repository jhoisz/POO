package jhoisnayraVitoria;

public class Onibus extends Veiculo {
	private int quantPessoas;
	
	Onibus(String marca, String modelo, int anoDeFabricacao,  double valorAvaliado, double valorDiaria, String placa, int quantPessoas){
		super(marca, modelo, anoDeFabricacao, placa, valorDiaria, valorAvaliado);
		this.quantPessoas = quantPessoas;
	}
	
	public double seguro() {
		return (valorAvaliado*0.2)/365;
	}
	public int getQuantPessoas() {
		return quantPessoas;
	}
	public void setQuantPessoas(int quantPessoas) {
		this.quantPessoas = quantPessoas;
	}
	public double aluguel(int dias) {
		return (valorDiaria + seguro())*dias;
	}
	public double getValorDiaria() {
		return valorDiaria;
	}
	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	public double getValorAvaliado() {
		return valorAvaliado;
	}
	public void setValorAvaliado(double valorAvaliado) {
		this.valorAvaliado = valorAvaliado;
	}
}