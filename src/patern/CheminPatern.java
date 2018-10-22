package patern;

public class CheminPatern extends Patern {
	/*
	 * |x x x|
	 * |     |
	 * |x x x| 
	 */
	public CheminPatern(Rotation rotation) {
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

}
