package jhoisnayraVitoria;

public class Caminhao extends Veiculo {
	public Caminhao(String marca, String modelo, int anoDeFabricacao, String placa, double valorDiaria,
			double valorAvaliado) {
		super(marca, modelo, anoDeFabricacao, placa, valorDiaria, valorAvaliado);
	}
	private double capacidade;
	
	public double seguro() {
		return (valorAvaliado*0.08)/365;
	}
	public void setCilindrada(double capacidade) {
		this.capacidade = capacidade;
	}
	public double getCapacidade() {
		return capacidade;
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