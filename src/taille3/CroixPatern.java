package taille3;

import patern.Patern;
import patern.Rotation;

public class CroixPatern extends Patern{
	
	private static CroixPatern instance;
	
	/*
	 * |x   x|
	 * |     |
	 * |x   x| 
	 */

	private CroixPatern(){
		super(Rotation.r0);
		this.structure[0]=true;
		this.structure[1]=false;
		this.structure[2]=true;
		this.structure[3]=false;
		this.structure[4]=false;
		this.structure[5]=false;
		this.structure[6]=true;
		this.structure[7]=false;
		this.structure[8]=true;
		//applyRotation3();
	}
	
	public static Patern getInstance(Rotation r) {
		return instance ==null?new CroixPatern():instance;
	}
}
