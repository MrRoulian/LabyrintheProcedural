package patern;

public class CroixPatern extends Patern{
	
	/*
	 * |x   x|
	 * |     |
	 * |x   x| 
	 */

	public CroixPatern(){
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
}
