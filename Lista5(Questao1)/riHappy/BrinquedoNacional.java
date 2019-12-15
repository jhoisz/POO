package riHappy;

public class BrinquedoNacional extends Brinquedo {
	private String estadoOrigem;
	
	public BrinquedoNacional(String nome, int codigo, double preco, int quantidade, String estadoOrigem) {
		super(nome, codigo, preco, quantidade);
		this.estadoOrigem = estadoOrigem;
	}
	@Override
	public double calculaTaxa() {
		return (super.getPreco()*5)/100;
	}
	public String ToString() {
		return super.toString()+ "\nestado origem: "+ estadoOrigem;
	}
}