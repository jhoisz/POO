package jhoisnayraVitoria;

public class Moto extends Veiculo {
	public Moto(String marca, String modelo, int anoDeFabricacao, double valorAvaliado, double valorDiaria,
			 String placa, double cilindrada) {
		super(marca, modelo, anoDeFabricacao, placa, valorDiaria, valorAvaliado);		
		}
	private double cilindrada;
	
	
	public double seguro() {
		return (valorAvaliado*0.11)/365;
	}
	public void setCilindrada(double cilindrada) {
		this.cilindrada = cilindrada;
	}
	public double getCilindrada() {
		return cilindrada;
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