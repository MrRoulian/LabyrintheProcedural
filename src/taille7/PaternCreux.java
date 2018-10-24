package taille7;

import patern.Patern;
import patern.Rotation;

public class PaternCreux extends Patern{

	private static PaternCreux instance;
	
	private PaternCreux() {
		super(Rotation.r0);
		for (int i = 0 ; i < TAILLEPATERN*TAILLEPATERN ; i++){
			structure[i] = false;
		}
		structure[0] = true;
		structure[1] = true;
		structure[2] = true;
		structure[4] = true;
		structure[5] = true;
		structure[6] = true;
		structure[7] = true;
		structure[14] = true;
		structure[28] = true;
		structure[35] = true;
		structure[42] = true;
		structure[43] = true;
		structure[44] = true;
		structure[46] = true;
		structure[47] = true;
		structure[48] = true;
		structure[41] = true;
		structure[34] = true;
		structure[20] = true;
		structure[13] = true;

		structure[16] = true;
		structure[17] = true;
		structure[18] = true;
		structure[23] = true;
		structure[24] = true;
		structure[25] = true;
		structure[30] = true;
		structure[31] = true;
		structure[32] = true;
		
	}


	public static Patern getInstance(Rotation r) {
		return instance ==null?new PaternCreux():instance;
	}
}
