import patern.Rotation;
import patern.TPatern;

public class Test {

	public static void main(String[] args) {
		TPatern vg = new TPatern(Rotation.r90);
		int i =0;
		for (boolean b : vg.getStructure()) {
			if (i%3 == 2){
				System.out.println(b?"x ":"  ");
			} else {
				System.out.print(b?"x ":"  ");
			}
				
			i++;
		}

	}

}
