package patern;

import java.util.ArrayList;

public class RandomPatern extends Patern {

	public RandomPatern(Rotation rotation) {
		super(rotation);
		ArrayList<Integer> list = new ArrayList<>();
		while (list.size() != (Patern.TAILLEPATERN*Patern.TAILLEPATERN)/3){
			int rand = (int)(Math.random()*Patern.TAILLEPATERN*Patern.TAILLEPATERN);
			if (!list.contains(rand)){
				list.add(rand);
			}
		}
		for (Integer integer : list) {
			this.structure[integer] = true;
		}
	}
	
	

}
