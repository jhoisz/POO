package fazenda;

import java.util.ArrayList;
import java.util.Date;

public class Fazenda {

	private int numero;
	private String nome;
	private ArrayList<Animal> animais = null;

	public Fazenda(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
	}

	public ArrayList<Animal> getAnimais() {
		if (animais == null) { 
			DAOAnimal daoA = new DAOAnimal();
			animais = daoA.pesquisarPorFazenda(this.numero);
		}
		return animais;
	}

	public void compra(Animal a) {
		if (a.podeSerComercializado()) {
		  this.animais.add(a);
		}
	}

	public void venda(int numAnimal) {
		Animal vendido = pesquisarAnimal(numAnimal);
		if (vendido.podeSerComercializado()) { 
		  vendido.venda(new Date());
		} 
	}

	public void abate(int numAnimal) {
		Animal a = pesquisarAnimal(numAnimal);
		if (a == null || !a.isAtivo()) {
			return;
		}
		a.abate(new Date());
	}

	public void morte(int numAnimal) {
		Animal a = pesquisarAnimal(numAnimal);
		if (a == null || !a.isAtivo()) {
			return;
		}
		a.morte(new Date());
	}

	public void vacina(int numAnimal) {
		Animal a = pesquisarAnimal(numAnimal);
		if (a == null || !a.isAtivo()) {
			return;
		}
		a.vacina(new Date());
	}

	public Animal pesquisarAnimal(int numAnimal) {
		for (Animal a : animais) {
			if (a.getNumero() == numAnimal) {
				return a;
			}
		}
		return null;
	}

	public int listarResumoDeAnimais(int tipo, boolean jovem, boolean macho) {
		int quant = 0;
		for (Animal a : animais) {
			if (tipo == 0 || a.getTipo() == tipo) {
			  if ((a.isJovem() == jovem) && (a.isMacho() == macho) && a.isAtivo()) {
				quant++;
			  }
		    }
		}
		return quant;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
