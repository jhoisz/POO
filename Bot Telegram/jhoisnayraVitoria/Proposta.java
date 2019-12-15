package jhoisnayraVitoria;

public class Proposta {
	private Servico servico;
	private Prestador prestador;
	private double preco;
	private int prazo;
	private boolean aceito;
	
	public Proposta(Servico servico, Prestador prestador, double valor, int prazo) {
		this.setServico(servico);
		this.setPrestador(prestador);
		this.setPreco(valor);
		this.setPrazo(prazo);
		this.aceito = false;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double valor) {
		this.preco = valor;
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public boolean isAceito() {
		return aceito;
	}

	public void setAceito(boolean aceito) {
		this.aceito = aceito;
	}

}
