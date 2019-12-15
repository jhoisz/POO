package main;
import java.util.Scanner;

import empregado.Comissionado;

public class Questao2 {

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner (System.in);
		final int MAX = 5;
		Comissionado[] com = new Comissionado[MAX];
		String nome, endereco;
		double salarioBase, comissao;
		int quantidade;
		
		for(int i=0; i<MAX; i++) {
			/*System.out.println("Digite os dados do comissionado "+(i+1)+":");
			nome = StdIn.readString();
			endereco = in.nextLine();
			salarioBase = StdIn.readDouble();
			comissao = StdIn.readDouble();
			quantidade = StdIn.readInt();
			com[i] = new Comissionado(nome, endereco, salarioBase, comissao, quantidade);
			*/
			
			System.out.print("Digite o nome: ");
			nome = sc.nextLine();
			
			System.out.print("Digite o endereco: ");
			endereco = sc.nextLine();
			
			com[i] = new Comissionado(nome, endereco); 
			
			System.out.print("Digite o salario base: ");
			salarioBase = sc.nextDouble();
			com[i].setSalarioBase(salarioBase);
			
			System.out.print("Digita a comissao: ");
			comissao = sc.nextDouble();
			com[i].setComissao(comissao);
			
			System.out.print("Digite a quantidade: ");
			quantidade = sc.nextInt();
			com[i].setQuantidade(quantidade);
			sc.nextLine();
			
		}
		
		for(int i=0; i<MAX; i++) {
			System.out.println("Nome: "+ com[i].getNome()+"\nEndereco: "+com[i].getEndereco()+"\nSalario base: " +com[i].getSalarioBase()+
					"\nComissao: "+com[i].getComissao()+"\nquantidade: "+com[i].getQuantidade()+ "\nSalario total: "+ com[i].calcularSalario());
			//System.out.println("Comissionado "+(i+1)+":\n"+com[i]);
		}
		
	}
}