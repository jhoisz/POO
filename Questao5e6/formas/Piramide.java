package formas;

import comum.FormasTridimensionais;

public class Piramide extends FormasTridimensionais {
	protected double lado;

	public Piramide(double altura, double lado) {
		super(altura);
		this.lado = lado;
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularVolume() {
		return altura/3*lado;
	}
}
