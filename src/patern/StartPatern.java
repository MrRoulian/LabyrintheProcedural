package patern;


public class StartPatern extends Patern {
	
	private static StartPatern instance0;

	private StartPatern() {
		super(Rotation.r0);
		for (int i = 0 ; i < Patern.TAILLEPATERN*Patern.TAILLEPATERN ; i++){
			structure[i] = false;
		}
	}
	
	public static Patern getInstance(){
		return instance0 ==null?new StartPatern():instance0;
	}
	

}
