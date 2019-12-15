package ListaDaRosi;

public class Conta {
	private  String nome;
	private int numero;
	private double saldo;
	private double limite;
	private Cliente cliente;
	
	public void alteraCliente(Cliente novoCliente){
		cliente = novoCliente;
	}
	public double getSaldo() {
		return saldo;
	}
	void alteraSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
}
