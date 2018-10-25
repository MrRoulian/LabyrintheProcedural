package taille3;

import patern.Patern;
import patern.Rotation;

public class CulDeSacPatern extends Patern {
	
	private static CulDeSacPatern instance0, instance90, instance180, instance270;
	
	
	/*
	 * |x x x|
	 * |x   x|
	 * |x   x| 
	 */
	private CulDeSacPatern(Rotation rotation) {
		super(rotation);
		this.structure[0]=true;
		this.structure[1]=true;
		this.structure[2]=true;
		this.structure[3]=true;
		this.structure[4]=false;
		this.structure[5]=true;
		this.structure[6]=true;
		this.structure[7]=false;
		this.structure[8]=true;
		applyRotation();
	}

	public static Patern getInstance(Rotation r) {
		switch(r){
		case r0:
			return instance0 == null?new CulDeSacPatern(r):instance0;
		case r180:
			return instance180 == null?new CulDeSacPatern(r):instance180;
		case r90:
			return instance90 == null?new CulDeSacPatern(r):instance90;
		case r270:
			return instance270 == null?new CulDeSacPatern(r):instance270;
		}
		return instance0;
	}
}
