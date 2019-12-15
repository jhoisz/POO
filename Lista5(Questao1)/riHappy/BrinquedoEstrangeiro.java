package riHappy;

public class BrinquedoEstrangeiro extends Brinquedo {
	private String paisOrigem;
		
	public BrinquedoEstrangeiro(String nome, int codigo, double preco, int quantidade, String paisOrigem) {
		super(nome, codigo, preco, quantidade);
		this.paisOrigem = paisOrigem;
	}

	@Override
	public double calculaTaxa() {
		return (getPreco()*10)/100;
	}
}
