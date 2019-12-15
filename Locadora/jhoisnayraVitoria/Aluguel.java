package jhoisnayraVitoria;
import java.util.Date;

public class Aluguel {
	Veiculo v;
	int cpf, dias;
	double valor;
	Date inicio, fim;
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public int getDias() {
		return dias;
	}
	
	public void setDias(int dias) {
		this.dias = dias;
	}
	public Veiculo getVeiculo() {
		return v;
	}
	public void setVeiculo(Veiculo v) {
		this.v = v;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getInicio() {
		return inicio;
	}
	@SuppressWarnings("deprecation")
	public Date getFim() {
		fim = inicio;
		fim.setDate(inicio.getDate()+dias);
		return fim;
	}
}