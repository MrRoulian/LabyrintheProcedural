package patern;

public class FullPatern extends Patern{
	
	/*
	 * |x x x|
	 * |x x x|  en rotation 0
	 * |x x x| 
	 */
	public FullPatern(Rotation r){
		super(r);		
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

}
