package fazenda;

import java.util.Date;
import java.util.GregorianCalendar;

public class Bovino extends Animal {

	public Bovino(int numero, String nome, Date nascimento, int genero,
			double valorDeCompra, double valorDeVenda) {
		super(numero, nome, nascimento, genero, valorDeCompra, valorDeVenda);
	}

	
	public Bovino(int numero, String nome, Date nascimento, int genero,
			double valorDeCompra, double valorDeVenda, boolean vacinado, boolean morto, boolean abatido) {
		super(numero, nome, nascimento, genero, valorDeCompra, valorDeVenda, vacinado, morto, abatido);
	}
	
	@Override
	public boolean isJovem() {
		GregorianCalendar idade = new GregorianCalendar();
		GregorianCalendar hoje = new GregorianCalendar();
		idade.setTime(this.getNascimento());
		idade.add(idade.MONTH, 23);
		return idade.after(hoje);
	}

	@Override
	public int getTipo() {
		return 1;
	}	
}
