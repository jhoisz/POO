package riHappy;

public class Principal {
	public void imprimeTaxa(Taxa t) {
		System.out.println("Taxa: " + t.calculaTaxa());
	}
	public static void main(String[] args) {
		//Taxa t = new Taxa();
		//Brinquedo b = new Brinquedo("Barbie de Luxo", 21, 60, 120);
		
		
		BrinquedoNacional bn = new BrinquedoNacional("Aviao 31", 15, 25, 500, "Sul");
		BrinquedoEstrangeiro bes = new BrinquedoEstrangeiro("Robô", 61, 320, 30, "EUA");
		
		
		Principal p = new Principal();
		
		p.imprimeTaxa(bn);
		p.imprimeTaxa(bes);
		
		System.out.println("Brinquedo: "+ bes+ "\nInventario: "+ bes.inventario());
	}
}