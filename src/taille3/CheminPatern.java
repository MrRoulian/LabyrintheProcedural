package taille3;

import patern.Patern;
import patern.Rotation;

public class CheminPatern extends Patern {
	
	private static CheminPatern instance0, instance90;
	
	/*
	 * |x x x|
	 * |     |
	 * |x x x| 
	 */
	private CheminPatern(Rotation rotation) {
		super(rotation);
		this.structure[0]=true;
		this.structure[1]=true;
		this.structure[2]=true;
		this.structure[3]=false;
		this.structure[4]=false;
		this.structure[5]=false;
		this.structure[6]=true;
		this.structure[7]=true;
		this.structure[8]=true;
		applyRotation3();
		
	}

	public static Patern getInstance(Rotation r) {
		switch(r){
		case r0:
		case r180:
			return instance0 == null?new CheminPatern(r):instance0;
		case r90:
		case r270:
			return instance90 == null?new CheminPatern(r):instance90;
		}
		return instance0;
	}

}
