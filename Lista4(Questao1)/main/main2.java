package main;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import editora.Livro;
import editora.Revista;

public class main2 {

	public static void main(String[] args) {	
		
		ArrayList<Livro> livro = new ArrayList<Livro>();
		ArrayList<Revista> revista = new ArrayList<Revista>();
		int op;
		
		do {
		op = Integer.parseInt(JOptionPane.showInputDialog("Digite a opcao:\n1 - Livro\n2 - Revista\n3 - Mostrar livros\n4 - Mostrar revistas"));
		
		switch (op) {
			case 1:
				Livro aux = new Livro();
				aux.introduzDados();
				livro.add(aux);
				break;
			case 2:
				Revista aux1 = new Revista();
				aux1.introduzDados();
				revista.add(aux1);
				break;
			case 3:
				for(Livro l: livro) {
					l.mostra();
				}
				break;
			case 4:
				for(Revista r: revista) {
					r.mostra();
				}
				break;
			default:
				break;
		}
		}while(op!=0);
	}
}