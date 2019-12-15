package Lista3Questao1;

public class Comissionado extends Empregado {
	private double salarioBase, comissao;
	private int quantidade;

	//public void setNome(String nome) {
	//	this.nome = nome;
	//}
	//public void setEndereco(String endereco) {
	//	this.endereco = endereco;
	//}
	public void setSalarioBase(double salario) {
		salarioBase = salario;
	}
	public void setComissao(double comissao) {
		this.comissao = comissao;
	}
	public void setQuantidade(int quant) {
		quantidade = quant;
	}
	public double getSalarioBase() {
		return salarioBase;
	}
	public double getComissao() {
		return comissao;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public String getNome() {
		return nome;
	}
	public String getEndereco() {
		return endereco;
	}
	
	public Comissionado(String nome, String endereco) {
		super(nome, endereco);
	}	
	public Comissionado(String nome, String endereco, double salarioBase) {
		super(nome, endereco);
		this.salarioBase = salarioBase;
	}	
	public Comissionado(String nome, String endereco, double salarioBase, double comissao) {
		super(nome, endereco);
		this.salarioBase = salarioBase;
		this.comissao = comissao;
	}
	public Comissionado(String nome, String endereco, double salarioBase, double comissao, int quantidade) {
		super(nome, endereco);
		this.salarioBase = salarioBase;
		this.comissao = comissao;
		this.quantidade = quantidade;
	}
	@Override
	public double calcularSalario() {	
		return salarioBase+(comissao*quantidade);
	}
	public String toString() {
		return this.nome+"\n"+this.endereco+"\n"+ this.salarioBase
					+"\n"+ this.comissao+"\n"+this.quantidade+"\n"+ calcularSalario()+"\n______________________";
	}
}
