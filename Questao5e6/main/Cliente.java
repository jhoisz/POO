package main;

import java.util.Scanner;

import formas.BaseCircular;
import formas.BaseTriangular;

public class Cliente {
		private static Scanner sc;

		public static void main(String[] args) {
			sc = new Scanner (System.in);
			final int MAX = 2;
			int op;
			
			BaseTriangular[] bt = new BaseTriangular[MAX];
			BaseCircular[] bc = new BaseCircular[MAX];
			
				System.out.println("Qual forma deseja calcular o volume?\n1 - Base circular\n2 - Base triangular\n");
				op = sc.nextInt();
				switch (op) {
					case 1:
						for(int j=0; j<MAX; j++) {
							System.out.println("Digite a altura: ");
							double altura = sc.nextDouble();
							System.out.println("Digite o lado: ");
							double lado = sc.nextInt();
							System.out.println("Digite a base: ");
							double raio = sc.nextDouble();
							sc.nextLine();
							System.out.println("\n_______________________________________________________________");
							bc[j] = new BaseCircular(altura, lado, raio);
						}
						for(int i = 0; i<MAX; i++) {
							System.out.println("Forma Circular: "+(i+1)+"\n"
									+bc[i]);
							System.out.println("\n_______________________________________________________________");
						}
						break;
					case 2:
						for(int j=0; j<MAX; j++) {
							System.out.println("Digite a altura: ");
							double altura = sc.nextDouble();
							System.out.println("Digite o lado: ");
							double lado = sc.nextInt();
							System.out.println("Digite o raio: ");
							double base = sc.nextDouble();
							sc.nextLine();
							System.out.println("\n_______________________________________________________________");

							bt[j] = new BaseTriangular(altura, lado, base);
						}
						for(int i = 0; i<MAX; i++) {
							System.out.println("Forma Triangular: "+(i+1)+"\n"
									+bt[i]);
							System.out.println("\n_______________________________________________________________");
						}

						break;
					default:
							break;
				}
			}
}