package patern;

public class DiagoPatern extends Patern {
	/*
	 * |x    |
	 * |     |
	 * |    x| 
	 */
	public DiagoPatern(Rotation rotation) {
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
	
	

}
