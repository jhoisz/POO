package editora;

import javax.swing.JOptionPane;

public class Revista extends Publicacoes {
	private int numero;
			
	public void mostra() {
		super.mostra();
		JOptionPane.showMessageDialog(null, "Numero: " + numero);
	}
	public void introduzDados() {
		super.introduzDados();
		int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero: "));
		this.numero = numero;
	}
}