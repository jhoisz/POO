package main;

import javax.swing.JOptionPane;

import editora.Livro;
import editora.Revista;

public class LetraB {

	public static void main(String[] args) {		
		Livro livro = new Livro();
		Revista revista = new Revista();
		
		int op = Integer.parseInt(JOptionPane.showInputDialog("Digite a opcao:\n1 - Livro\n2 - Revista"));
		
		switch (op) {
			case 1:
				livro.introduzDados();
				livro.mostra();
				break;
			case 2:
				revista.introduzDados();
				livro.mostra();
				
				break;
			default:
				break;
		}
	}
}