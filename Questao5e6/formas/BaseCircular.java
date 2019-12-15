package formas;

public class BaseCircular extends Piramide {
	protected double raio;
	
	public BaseCircular(double altura, double lado, double raio) {
		super(altura, lado);
		this.raio = raio;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double calcularVolume() {
		// TODO Auto-generated method stub
		return pi*raio*raio*altura;
	}
	public String toString() {
		return "Altura: " +altura+"\nLado: "+lado+
				"\nRaio: "+raio+"\nVolume: "+calcularVolume();
	}
}
