package jhoisnayraVitoria;

import java.sql.SQLException;
import java.util.ArrayList;

public class Servico {
	
	private int codServico;
	private String descricao;
	private double valor;
	private TipoDeServico tipo;
	private Contratante contratante;
	
	private Proposta propostaEscolhida = null;
	private ArrayList<Proposta> propostas;
	private int prazoMaximo;
	private String ObservacoesContratante;
	private String ObservacoesPrestador;
	private long DataFinalizado;
	private int NotaC;
	private int NotaP;
	private boolean contratado = false;
	private boolean finalizado = false;
	
	public Servico(int cod, Contratante c, String descr, double val, int prazoMax, TipoDeServico t) {
		this.codServico = cod;this.contratante = c;this.descricao = descr;this.valor = val;
		this.prazoMaximo = prazoMax;this.tipo = t;
	}
	
	public int getCodServico() {
		return codServico;
	}
	public void setCodServico(int codServico) {
		this.codServico = codServico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getPrazoMaximo() {
		return prazoMaximo;
	}
	public void setPrazoMaximo(int prazoMaximo) {
		this.prazoMaximo = prazoMaximo;
	}
	public String getObservacoesContratante() {
		return ObservacoesContratante;
	}
	public void setObservacoesContratante(String ObservacoesContratante) {
		this.ObservacoesContratante = ObservacoesContratante;
	}
	public long getDataFinalizado() {
		return DataFinalizado;
	}
	public void setDataFinalizado(long dataFinalizado) {
		DataFinalizado = dataFinalizado;
	}
	public int getNotaC() {
		return NotaC;
	}
	public void setNotaC(int notaC) {
		NotaC = notaC;
	}
	public boolean isContratado() {
		return contratado;
	}
	public void setContratado(boolean contratado) {
		this.contratado = contratado;
	}
	public int getNotaP() {
		return NotaP;
	}
	public void setNotaP(int notaP) {
		NotaP = notaP;
	}
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	public TipoDeServico getTipo() {
		return tipo;
	}
	public void setTipo(TipoDeServico tipo) {
		this.tipo = tipo;
	}
	public Contratante getContratante() {
		return contratante;
	}
	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public Proposta getPropostaEscolhida() {
		return propostaEscolhida;
	}

	public void setPropostaEscolhida(Proposta propostas) throws SQLException {
		this.propostaEscolhida = propostas;
	}

	public ArrayList<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(ArrayList<Proposta> propostas) {
		this.propostas = propostas;
	}

	public String getObservacoesPrestador() {
		return ObservacoesPrestador;
	}

	public void setObservacoesP(String observacoesPrestador) {
		this.ObservacoesPrestador = observacoesPrestador;
	}
	
}
