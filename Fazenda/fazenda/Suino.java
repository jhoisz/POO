package fazenda;

import java.util.Date;
import java.util.GregorianCalendar;

public class Suino extends Animal {

	public Suino(int numero, String nome, Date nascimento, int genero,
			double valorDeCompra, double valorDeVenda) {
		super(numero, nome, nascimento, genero, valorDeCompra, valorDeVenda);
	}
	
	public Suino(int numero, String nome, Date nascimento, int genero,
			double valorDeCompra, double valorDeVenda, boolean vacinado, boolean morto, boolean abatido) {
		super(numero, nome, nascimento, genero, valorDeCompra, valorDeVenda, vacinado, morto, abatido);
	}
	
	@Override
	public boolean isJovem() {
		GregorianCalendar idade = new GregorianCalendar();
		GregorianCalendar hoje = new GregorianCalendar();
		idade.setTime(getNascimento());
		idade.add(idade.MONTH, 12);
		return idade.after(hoje);
	}

	@Override
	public int getTipo() {
		return 2;
	}
	
}
