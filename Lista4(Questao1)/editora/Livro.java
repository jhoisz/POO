package editora;

import javax.swing.JOptionPane;

public class Livro extends Publicacoes {
	private String autor;

	public void mostra() {
		super.mostra();
		JOptionPane.showMessageDialog(null, "Autor: " + autor);
	}
	public void introduzDados() {
		super.introduzDados();
		String autor = JOptionPane.showInputDialog("Digite o autor: ");
		this.autor = autor;
	}
}
