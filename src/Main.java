import model.Laby;
import view.VueGraphique;
import view.VueTexte;

public class Main {	

	public static void main(String[] args) {
		Laby lab = new Laby();
		
		VueGraphique vg = new VueGraphique(lab);
		VueTexte vt = new VueTexte(lab);
		lab.addObserver(vg);
		lab.addObserver(vt);
	}
}
