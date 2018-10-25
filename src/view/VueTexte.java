package view;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Map.Entry;

import model.Laby;
import patern.Patern;

public class VueTexte implements Observer {
	
    private Laby lab;
    private int compteur = 0;

    public VueTexte(Laby lab) {
        // ...
        this.lab = lab;
    }    

	public void afficherTab(){
		if (compteur % 10 == 0)
			System.out.println(lab.getXPatern()+ " " + lab.getYPatern() + "\n" + lab.getCase(lab.getXPatern(), lab.getYPatern()).toString(lab.getPerso().calculerNumCaseDansPatern()));
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		afficherTab();
		compteur++;
	}

}
