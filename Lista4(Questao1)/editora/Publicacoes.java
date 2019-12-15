package editora;

import javax.swing.JOptionPane;

public abstract class Publicacoes {
	private String nome;
	
	public void mostra() {
		JOptionPane.showMessageDialog(null, "Nome: " + nome);
	}
	public void introduzDados() {
		String nome = JOptionPane.showInputDialog("Digite o nome: ");
		this.nome = nome;
	}
}