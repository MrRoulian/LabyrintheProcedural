import java.awt.Point;

import model.Laby;
import patern.Patern;
import patern.Rotation;
import taille3.TPatern;
import taille3.ViragePatern;

public class Test {

	public static void main(String[] args) {
		Patern vg = ViragePatern.getInstance(Rotation.r180);
		int i =0;
		for (boolean b : vg.getStructure()) {
			if (i%3 == 2){
				System.out.println(b?"x ":"  ");
			} else {
				System.out.print(b?"x ":"  ");
			}				
			i++;
		}
		
		System.out.println("Face haut");

		for (boolean b : vg.getFace(1)) {
			if (i%3 == 2){
				System.out.println(b?"x ":"  ");
			} else {
				System.out.print(b?"x ":"  ");
			}				
			i++;
		}
		System.out.println("Face droite");

		for (boolean b : vg.getFace(2)) {
			if (i%3 == 2){
				System.out.println(b?"x ":"  ");
			} else {
				System.out.print(b?"x ":"  ");
			}				
			i++;
		}
		System.out.println("Face bas");

		for (boolean b : vg.getFace(3)) {
			if (i%3 == 2){
				System.out.println(b?"x ":"  ");
			} else {
				System.out.print(b?"x ":"  ");
			}				
			i++;
		}
		System.out.println("Face gauche");

		for (boolean b : vg.getFace(4)) {
			if (i%3 == 2){
				System.out.println(b?"x ":"  ");
			} else {
				System.out.print(b?"x ":"  ");
			}				
			i++;
		}
	}

}
