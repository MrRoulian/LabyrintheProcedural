package taille3;

import patern.Patern;
import patern.Rotation;

public class TPatern extends Patern{	

	private static TPatern instance0, instance90, instance180, instance270;
	
	/*
	 * |x   x|
	 * |     |  en rotation 0
	 * |x x x| 
	 */
	private TPatern(Rotation r){
		super(r);		
		this.structure[0]=true;
		this.structure[1]=false;
		this.structure[2]=true;
		this.structure[3]=false;
		this.structure[4]=false;
		this.structure[5]=false;
		this.structure[6]=true;
		this.structure[7]=true;
		this.structure[8]=true;
		applyRotation();
	}
	
	public static Patern getInstance(Rotation r) {
		switch(r){
		case r0:
			return instance0 == null?new TPatern(r):instance0;
		case r180:
			return instance180 == null?new TPatern(r):instance180;
		case r90:
			return instance90 == null?new TPatern(r):instance90;
		case r270:
			return instance270 == null?new TPatern(r):instance270;
		}
		return instance0;
	}
}
