package patern;

import java.util.Arrays;

public abstract class Patern {
	public static final int TAILLEPATERN = 3;
	protected boolean[] structure = new boolean[TAILLEPATERN*TAILLEPATERN];
	private Rotation rotation;
	
	public Patern(Rotation rotation){
		this.rotation = rotation;
	}
	
	public void applyRotation3(){
		boolean[] structApresRotation;
		while (rotation != Rotation.r0){
			structApresRotation = new boolean[TAILLEPATERN*TAILLEPATERN];
			
			structApresRotation[0] = structure[6];
			structApresRotation[1] = structure[3];
			structApresRotation[2] = structure[0];
			structApresRotation[3] = structure[7];
			structApresRotation[4] = structure[4];
			structApresRotation[5] = structure[1];
			structApresRotation[6] = structure[8];
			structApresRotation[7] = structure[5];
			structApresRotation[8] = structure[2];
			
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
				System.err.println("Problème avec la rotation");
				break;
			}
		}
	}
	
	

	
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

}
