package taille3;

import patern.Patern;
import patern.Rotation;

public class FullPatern extends Patern{
	
	private static FullPatern instance;
	
	/*
	 * |x x x|
	 * |x x x|  en rotation 0
	 * |x x x| 
	 */
	private FullPatern(){
		super(Rotation.r0);		
		this.structure[0]=true;
		this.structure[1]=true;
		this.structure[2]=true;
		this.structure[3]=true;
		this.structure[4]=true;
		this.structure[5]=true;
		this.structure[6]=true;
		this.structure[7]=true;
		this.structure[8]=true;
		//applyRotation3();
	}	

	public static Patern getInstance(Rotation r) {
		return instance ==null?new FullPatern():instance;
	}	
	

}
