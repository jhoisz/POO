package jhoisnayraVitoria;

public class Carro extends Veiculo {
	
	private int categoria;
	
	public Carro(String marca, String modelo, int anoDeFabricacao, double valorAvaliado, double valorDiaria, String placa, int categoria) {
		super(marca, modelo, anoDeFabricacao, placa, valorDiaria, valorAvaliado);
		this.categoria = categoria;
	}
	
	
	public double seguro() {
		return (valorAvaliado*0.03)/365;
	}
	public void setTipo(int categoria) {
		this.categoria = categoria;
	}
	public int getTipo() {
		return categoria;
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