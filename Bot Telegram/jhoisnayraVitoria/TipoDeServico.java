package jhoisnayraVitoria;

public class TipoDeServico {
	
	private Integer codTipoDeServico;
	private String descr;
	
	public TipoDeServico(Integer codTipoDeServico, String descr) {
		this.codTipoDeServico = codTipoDeServico;
		this.descr = descr;
	}
	
	public int getCodTipoDeServico() {
		return codTipoDeServico;
	}
	public void setCodTipoDeServico(Integer codTipoDeServico) {
		this.codTipoDeServico = codTipoDeServico;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}

}
