package jhoisnayraVitoria;

public class Contratante {

	private String email;
	private String nome;
	private String telefone;
	private boolean servicoAtivo = false;
	private int avaliacaoMedia = 0;

	public Contratante(String email, String nome, String telefone) {
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

	public boolean isServicoAtivo() {
		return servicoAtivo;
	}

	public void setServicoAtivo(boolean servicoAtivo) {
		this.servicoAtivo = servicoAtivo;
	}

	public int getAvaliacaoMedia() {
		return avaliacaoMedia;
	}

	public void setAvaliacaoMedia(int avaliacaoMedia) {
		this.avaliacaoMedia = avaliacaoMedia;
	}
}
