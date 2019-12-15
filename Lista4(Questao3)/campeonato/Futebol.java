package campeonato;

public class Futebol implements Prova {
	int dia, mes, ano, nElem;
	String nome;
	char resultado;
	
	public Futebol(String nome, int n) {
		this.nome = nome;
		this.nElem = n;
	}

	@Override
	public int getPontuacao() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDataEvento(int ano, int mes, int dia) {
		this.dia = dia;
		this.ano = ano;
		this.mes = mes;
	}

	@Override
	public int getDiaEvento() {
		return dia;
	}

	@Override
	public int getMesEvento() {
		// TODO Auto-generated method stub
		return mes;
	}

	@Override
	public int getAnoEvento() {
		// TODO Auto-generated method stub
		return ano;
	}

	@Override
	public int getNumElementos() {
		// TODO Auto-generated method stub
		return nElem;
	}

	@Override
	public void setPontuacao(int p) {
		// TODO Auto-generated method stub
		
	}
	
}
