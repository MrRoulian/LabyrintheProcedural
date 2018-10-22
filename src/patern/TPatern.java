package patern;

public class TPatern extends Patern{
	/*
	 * |x   x|
	 * |     |  en rotation 0
	 * |x x x| 
	 */
	public TPatern(Rotation r){
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
		applyRotation3();
	}
}
