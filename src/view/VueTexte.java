package view;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Map.Entry;

import model.Laby;
import patern.Patern;

public class VueTexte implements Observer {
	
    private Laby lab;

    public VueTexte(Laby lab) {
        // ...
        this.lab = lab;
    }    

	public void afficherTab(){
		//System.out.println(lab.getHashMap().toString());
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		afficherTab();
	}

}
