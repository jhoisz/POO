package fazenda;

import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Animal {

	private int numero;
	private String nome;
	private Date nascimento, inativacao, vacinacao;
	private int genero;		// 0 - M, 1 - F
	private Fazenda fazenda;
	// private int codigoFazendo; // ESTA ERRADO! NAO É POO!
	private double valorDeCompra, valorDeVenda;
	private boolean vacinado, morto, abatido, vendido;
	
	public Animal(int numero, String nome, Date nasc, int genero, double valorDeCompra, double valorDeVenda) {
		this.numero = numero;
		this.nome = nome;
		this.nascimento = nasc;
		this.genero = genero;
		this.valorDeCompra = valorDeCompra;
		this.valorDeVenda = valorDeVenda;
		this.vacinado = false;
		this.morto = false;
		this.abatido = false;		
	}

	public Animal(int numero, String nome, Date nasc, int genero, double valorDeCompra, double valorDeVenda, boolean vacinado, boolean morto, boolean abatido) {
		this.numero = numero;
		this.nome = nome;
		this.nascimento = nasc;
		this.genero = genero;
		this.valorDeCompra = valorDeCompra;
		this.valorDeVenda = valorDeVenda;
		this.vacinado = vacinado;
		this.morto = morto;
		this.abatido = abatido;		
	}

	public void vacina(Date data) {
		this.vacinado = true;
		this.vacinacao = data;
	}
	
	public void abate(Date data) {
		this.abatido = true;
		this.inativacao = data;
	}
	
	public void morte(Date data) {
		this.morto = true;
		this.inativacao = data;
	}
	
	public void venda(Date data) {
		this.vendido = true;
		this.inativacao = data;
		
	}
	
	public boolean podeSerComercializado() {
		GregorianCalendar atual = new GregorianCalendar();
		
		if (this.isAtivo()) {
			// Verifica se o animal é adulto
			if (!this.isJovem()) {
				// Verifica se o animal foi vacinado
				if (this.isVacinado())
				{
					return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}

	public abstract int getTipo();
	
	public abstract boolean isJovem();
	
	public boolean isMacho() {
		return (genero == 0);
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

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	public Fazenda getFazenda() {
		return fazenda;
	}

	public void setFazenda(Fazenda fazenda) {
		this.fazenda = fazenda;
	}

	public double getValorDeCompra() {
		return valorDeCompra;
	}

	public void setValorDeCompra(double valorDeCompra) {
		this.valorDeCompra = valorDeCompra;
	}

	public double getValorDeVenda() {
		return valorDeVenda;
	}

	public void setValorDeVenda(double valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}
	
	public boolean isAbatido() {
		return abatido;
	}

	public void setAbatido(boolean abatido) {
		this.abatido = abatido;
	}

	public boolean isMorto() {
		return morto;
	}

	public void setMorto(boolean morto) {
		this.morto = morto;
	}

	public boolean isAtivo() {
		return !(abatido || vendido || morto);
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Date getInativacao() {
		return inativacao;
	}

	public void setInativacao(Date inativacao) {
		this.inativacao = inativacao;
	}

	public boolean isVacinado() {
		return vacinado;
	}
	
	public void setVacinado(boolean v) {
		vacinado = v;
	}

	public boolean isVendido() {
		return vendido;
	}

}
