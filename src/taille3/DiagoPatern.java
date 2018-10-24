package taille3;

import patern.Patern;
import patern.Rotation;

public class DiagoPatern extends Patern {

	private static DiagoPatern instance0, instance90, instance180, instance270;
	
	/*
	 * |x    |
	 * |     |
	 * |    x| 
	 */
	private DiagoPatern(Rotation rotation) {
		super(rotation);
		this.structure[0]=true;
		this.structure[1]=false;
		this.structure[2]=false;
		this.structure[3]=false;
		this.structure[4]=false;
		this.structure[5]=false;
		this.structure[6]=false;
		this.structure[7]=false;
		this.structure[8]=true;
		applyRotation3();
	}
	
	public static Patern getInstance(Rotation r) {
		switch(r){
		case r0:
			return instance0 == null?new DiagoPatern(r):instance0;
		case r180:
			return instance180 == null?new DiagoPatern(r):instance180;
		case r90:
			return instance90 == null?new DiagoPatern(r):instance90;
		case r270:
			return instance270 == null?new DiagoPatern(r):instance270;
		}
		return instance0;
	}
	
	

}
