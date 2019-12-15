package campeonato;

public interface Prova {
	int getPontuacao();
	void setDataEvento(int ano, int mes, int dia);
	int getDiaEvento();
	int getMesEvento();
	int getAnoEvento();
	int getNumElementos();
	void setPontuacao(int p);
}