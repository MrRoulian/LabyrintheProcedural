package patern;

import java.util.Arrays;

public abstract class Patern {
	public static final int TAILLEPATERN = 3;
	protected boolean[] structure = new boolean[TAILLEPATERN*TAILLEPATERN];
	private Rotation rotation;

	public Patern(Rotation rotation){
		this.rotation = rotation;
	}
	
	static void rotateCW() {
	    
	}

	public void applyRotation(){
		boolean[] structApresRotation;
	    final int M = TAILLEPATERN;
	    final int N = TAILLEPATERN;
		while (rotation != Rotation.r0){
			
			structApresRotation = new boolean[TAILLEPATERN*TAILLEPATERN];

			for (int r = 0; r < M; r++) {
		        for (int c = 0; c < N; c++) {
		        	structApresRotation[c*TAILLEPATERN+M-1-r] = structure[r*TAILLEPATERN+c];
		        }
		    }

			structure = structApresRotation;

			switch (rotation){
			case r270:
				rotation = Rotation.r180;
				break;
			case r180:
				rotation = Rotation.r90;
				break;
			case r90:
				rotation = Rotation.r0;
				break;
			case r0:
				System.err.println("Probleme avec la rotation");
				break;
			}
		}
	}
	
	//valeur des faces
	/*   _1_
	 * 4|_ _|2
	 *    3
	 */

	public boolean[] getFace(int face){
		boolean[] res = new boolean[TAILLEPATERN];
		int j=0;
		switch(face){
		case 1 :
			//0.1.2
			for (int i= 0 ; i <= Patern.TAILLEPATERN - 1 ; i++){
				res[j] = structure[i];
				j++;
			}
			break;
		case 2 :
			//2.5.8
			for (int i = Patern.TAILLEPATERN-1; i <= Patern.TAILLEPATERN*Patern.TAILLEPATERN ; i+= Patern.TAILLEPATERN){
				res[j] = structure[i];
				j++;					
			}
			break;
		case 3 :
			//6.7.8
			for (int i = Patern.TAILLEPATERN*Patern.TAILLEPATERN - Patern.TAILLEPATERN ; i <= Patern.TAILLEPATERN*Patern.TAILLEPATERN-1; i++){
				res[j] = structure[i];
				j++;			
			}
			break;
		case 4 :
			//0.3.6
			for (int i = 0 ; i <= Patern.TAILLEPATERN*Patern.TAILLEPATERN - Patern.TAILLEPATERN ; i+= Patern.TAILLEPATERN){
				res[j] = structure[i];
				j++;				
			}
			break;
		default :
			break;
		}
		return res;
	}
	
	public static boolean estFullMur(boolean[] tab){
		for (boolean b : tab) {
			if (!b){
				return false;
			}
		}
		return true;
	}
	
	//public abstract Patern getInstance(Rotation r);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rotation == null) ? 0 : rotation.hashCode());
		result = prime * result + Arrays.hashCode(structure);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patern other = (Patern) obj;
		if (rotation != other.rotation)
			return false;
		if (!Arrays.equals(structure, other.structure))
			return false;
		return true;
	}

	public boolean[] getStructure(){
		return structure;
	}

	public String toString(){
		String res = "\n";
		int i =0;
		for (boolean b : structure) {
			res+=b?"x ":"  ";
			if (i%TAILLEPATERN == TAILLEPATERN-1){
				res+="\n";
			}

			i++;
		}		
		return res;
	}
	
	public String toString(int positionJoueurDansLePartern){
		String res = "\n";
		int i =0;
		for (boolean b : structure) {
			if (i != positionJoueurDansLePartern){
				res+=b?"x ":"  ";			
			} else {
				res += "O ";
			}
			if (i%TAILLEPATERN == TAILLEPATERN-1){
				res+="\n";
			}	
			i++;
		}		
		return res;
	}

}
