package model;

import patern.Patern;

public class Perso {
	
	public static final int TAILLEROND = Laby.TAILLEPETITECASE/2;
	public static final double ACCELERATION = Laby.TAILLECASEPATERN/10;
	public static final double MAXSPEED = Laby.TAILLECASEPATERN*5;
	private double speedX = 6,speedY = 6;
	private double x,y;
	private int xPatern,yPatern;
	private int xCaseDansPatern, yCaseDansPatern;
	
	public Perso(double x, double y){
		//point en haut à gauche
		this.x=x;
		this.y=y;
	}

	public String getPosition() {
		return "x:"+x+", y:"+y;
	}

	public void haut() {
		/*if (speedY-ACCELERATION >= -MAXSPEED)
			speedY-=ACCELERATION;*/
		y-=speedY;
		updatePositionDiscrete();
	}
	
	public void bas(){
		/*if (speedY+ACCELERATION <= MAXSPEED)
			speedY+=ACCELERATION;*/
		y+=speedY;
		updatePositionDiscrete();
	}
	
	public void gauche(){
		/*if (speedX-ACCELERATION >= -MAXSPEED)
			speedX-=ACCELERATION;*/
		x-=speedX;
		updatePositionDiscrete();
	}
	
	public void droite(){
		/*if (speedX+ACCELERATION <= MAXSPEED)
			speedX+=ACCELERATION;*/
		x+=speedX;
		updatePositionDiscrete();
	}
	
	private void updatePositionDiscrete(){
		xCaseDansPatern = (int) ((x+TAILLEROND/2)/Laby.TAILLEPETITECASE);
		yCaseDansPatern = (int) ((y+TAILLEROND/2)/Laby.TAILLEPETITECASE);
		xPatern = xCaseDansPatern / Patern.TAILLEPATERN;
		yPatern = yCaseDansPatern / Patern.TAILLEPATERN;
		xCaseDansPatern%=Patern.TAILLEPATERN;
		yCaseDansPatern%=Patern.TAILLEPATERN;
	}
	
	public int calculerNumCaseDansPatern(){
		return (int)(yCaseDansPatern * Patern.TAILLEPATERN + xCaseDansPatern);
	}
	
	public boolean estDansUnMur(Laby lab){
		if (lab.getCase(xPatern, yPatern) != null){
			boolean[] structDuPatern = lab.getCase(xPatern, yPatern).getStructure();
			//return structDuPatern[calculerNumCaseDansPatern()];			
		}
		return false;
	}

	public double getX() {
		return x;
	}
	
	public int getXPatern(){
		return xPatern;
	}

	public double getY() {
		return y;
	}
	
	public int getYPatern(){
		return yPatern;
	}
	
	public double getSpeedX() {
		return speedX;
	}

	public double getSpeedY() {
		return speedY;
	}
	
	public int getxCaseDansPatern() {
		return xCaseDansPatern;
	}

	public int getyCaseDansPatern() {
		return yCaseDansPatern;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	@Override
	public String toString() {
		return "Perso [x=" + x + ", y=" + y + "]";
	}

}
