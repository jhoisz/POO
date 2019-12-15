package jhoisnayraVitoria;

import java.sql.SQLException;
import java.util.ArrayList;

public class Prestador {
	private String email;
	private String nome;
	private String telefone;
	
	private ArrayList<TipoDeServico> tipoPrestador = new ArrayList<TipoDeServico>();
	
	public Prestador(String email, String nome, String telefone) {
		this.email = email;
		this.nome = nome;
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public ArrayList<TipoDeServico> getTipoPrestador() throws SQLException {
		return tipoPrestador;
	}

	public void setTipoPrestador(ArrayList<TipoDeServico> tipoPrestador) {
		this.tipoPrestador = tipoPrestador;
	}
}
