package empregado;

import comum.Empregado;

public class Chefe extends Empregado {
	private double salarioMensal;

	public Chefe(String nome, String endereco, double salarioMensal){
		super(nome, endereco);
		this.salarioMensal = salarioMensal;
	}
	
	@Override
	public double calcularSalario() {
		return salarioMensal;
	}	
}
