package patern;

public class CulDeSacPatern extends Patern {
	/*
	 * |x x x|
	 * |x   x|
	 * |x   x| 
	 */
	public CulDeSacPatern(Rotation rotation) {
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
		applyRotation3();
	}

}
