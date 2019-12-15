package formas;

public class BaseTriangular extends Piramide {
	protected double base;
	
	public BaseTriangular(double altura, double lado, double base) {
		super(altura, lado);
		this.base = base;
		// TODO Auto-generated constructor stub
	}
	@Override
	public double calcularVolume() {
		// TODO Auto-generated method stub
		return (base*base)/2*altura;
	}
	public String toString() {
		return "Altura: " +altura+"\nLado: "+lado+
				"\nBase: "+base+"\nVolume: "+calcularVolume()+"\n______________________________________\n";
	}
}