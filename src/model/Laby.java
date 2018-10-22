package model;

import java.awt.Point;
import java.awt.font.NumericShaper.Range;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;

import patern.CheminPatern;
import patern.CroixPatern;
import patern.CulDeSacPatern;
import patern.DiagoPatern;
import patern.FullPatern;
import patern.Patern;
import patern.RandomPatern;
import patern.Rotation;
import patern.TPatern;
import patern.ViragePatern;

public class Laby extends Observable {	

	public final static int TAILLEFENETRE = 1000;
	public final static int TAILLECASEPATERN = TAILLEFENETRE/10;
	public final static int TAILLEPETITECASE = (int) (TAILLECASEPATERN/Patern.TAILLEPATERN);
	public static final int RANGEAPPARITION = 1;
	private int nbCaseADevoiler = RANGEAPPARITION*(RANGEAPPARITION*2+2);
	private int nbPaternCree =0;
	HashMap<Point,Patern> lab = new HashMap<>();
	public Perso perso;

	public Laby(){
		perso = new Perso(TAILLECASEPATERN*1.5-Perso.TAILLEROND/2,TAILLECASEPATERN*1.5-Perso.TAILLEROND/2);
		buildTab();
		System.out.println("");
	}

	public void buildTab(){
		setCase((int)(perso.getX()/TAILLECASEPATERN), (int)(perso.getY()/TAILLECASEPATERN), new CroixPatern());
		//genererAutourPerso();

		setChanged();
		notifyObservers();
	}

	public void genererAutourPatern(Point p) {
		int xPat = p.x;
		int yPat = p.y;
		if (nbPaternCree < nbCaseADevoiler){
			Point haut = new Point(xPat,yPat-1);
			Point bas = new Point(xPat, yPat+1);
			Point droite = new Point (xPat+1, yPat);
			Point gauche = new Point (xPat-1, yPat);
			Point centre = new Point (xPat,yPat);

			Patern aAjouterC = null;
			Patern aAjouterG = null;
			Patern aAjouterD = null;
			Patern aAjouterH = null;
			Patern aAjouterB = null;
			if (!lab.containsKey(haut)) {
				aAjouterH = nouveauPatern(haut);
				if (aAjouterH != null)
					lab.put(haut, aAjouterH);			
			}
			nbPaternCree++;
			if (!lab.containsKey(bas)){
				aAjouterB = nouveauPatern(bas);
				if (aAjouterB != null)
					lab.put(bas, aAjouterB);
			}
			nbPaternCree++;
			if (!lab.containsKey(droite)){
				aAjouterD = nouveauPatern(droite);
				if (aAjouterD != null)
					lab.put(droite, aAjouterD);
			}
			nbPaternCree++;
			if (!lab.containsKey(gauche)){
				aAjouterG = nouveauPatern(gauche);
				if (aAjouterG != null)
					lab.put(gauche, aAjouterG);
			}
			nbPaternCree++;
			if (!lab.containsKey(centre)){
				aAjouterC = nouveauPatern(centre);
				if (aAjouterC != null)
					lab.put(centre, aAjouterC);
			}

			/*genererAutourPatern(haut);
			genererAutourPatern(bas);
			genererAutourPatern(gauche);
			genererAutourPatern(droite);*/
		} else {
			nbPaternCree = 0;
		}

		setChanged();
		notifyObservers();
	}

	private Patern nouveauPatern(Point positionPaternACreer) {
		Point haut = new Point(positionPaternACreer.x,positionPaternACreer.y-1);
		Point bas = new Point(positionPaternACreer.x, positionPaternACreer.y+1);
		Point droite = new Point (positionPaternACreer.x+1, positionPaternACreer.y);
		Point gauche = new Point (positionPaternACreer.x-1, positionPaternACreer.y);

		Patern pHaut = lab.get(haut);
		Patern pBas = lab.get(bas);
		Patern pGauche = lab.get(gauche);
		Patern pDroite = lab.get(droite);

		ArrayList<Patern> paternExistant = new ArrayList<>();
		ArrayList<Point> paternFutur = new ArrayList<>();

		if (pHaut != null){
			paternExistant.add(pHaut);
		} else {
			paternFutur.add(haut);
		}
		if (pBas != null){
			paternExistant.add(pBas);
		} else {
			paternFutur.add(bas);
		}
		if (pGauche != null){
			paternExistant.add(pGauche);
		} else {
			paternFutur.add(gauche);
		}
		if (pDroite != null){
			paternExistant.add(pDroite);
		} else {
			paternFutur.add(droite);
		}

		boolean isOk = false;
		Patern potentielPaternAInserer = null;
		//Au moins un trou vers un patern existant et au moins un trou vers un patern futur
		ArrayList<Patern> potentielDejaTeste = new ArrayList<>();
		ArrayList<Patern> aLierDejaTeste = new ArrayList<>();
		Point pointFutur = null;
		while (!isOk){

			if (paternExistant.size() == 0) return null;
			Patern paternALier = paternExistant.get((int)(Math.random()*paternExistant.size()));
			boolean[] structurePaternALier = paternALier.getStructure();
			potentielPaternAInserer = getRandomPatern(potentielDejaTeste);
			boolean[] structPaternAInserer = potentielPaternAInserer.getStructure();
			if (paternFutur.size() != 0){
				pointFutur = paternFutur.get((int)(Math.random()*paternFutur.size()));
			}

			//Savoir si le point futur est en haut, a droite, a gauche ou en bas du paternRandom pris
			switch(dessusDessousGaucheDroite(positionPaternACreer, pointFutur)){
			case 1:
				//0,1,2
				isOk= !structPaternAInserer[0];
				for (int i= 1 ; i < Patern.TAILLEPATERN-1 ; i++){
					isOk |= !structPaternAInserer[i];
				}
				break;
			case 2:
				//2,5,8
				isOk= !structPaternAInserer[Patern.TAILLEPATERN-1];
				for (int i = 2*Patern.TAILLEPATERN-1; i < Patern.TAILLEPATERN*Patern.TAILLEPATERN ; i+= Patern.TAILLEPATERN){
					isOk |= !structPaternAInserer[i];					
				}
				break;
			case 3:
				//6,7,8
				isOk= !structPaternAInserer[Patern.TAILLEPATERN*Patern.TAILLEPATERN - Patern.TAILLEPATERN];
				for (int i = Patern.TAILLEPATERN*Patern.TAILLEPATERN - Patern.TAILLEPATERN + 1 ; i < Patern.TAILLEPATERN*Patern.TAILLEPATERN; i++){
					isOk |= !structPaternAInserer[i];					
				}
				break;
			case 4:
				//0,3,6
				isOk= !structPaternAInserer[0];
				for (int i = Patern.TAILLEPATERN ; i < Patern.TAILLEPATERN*Patern.TAILLEPATERN - Patern.TAILLEPATERN ; i+= Patern.TAILLEPATERN){
					isOk |= !structPaternAInserer[i];					
				}
				break;
			default:
				isOk = true;
				break;
			}
			int i =0;
			int seuil = 4;
			if (isOk){
				//Pareil pour le patern à lier
				switch(dessusDessousGaucheDroite(positionPaternACreer, getKey(paternALier))){
				//créer en bas
				case 1:				
					if (structurePaternALier[6] && structurePaternALier[7] && structurePaternALier[8]){
						paternExistant.remove(paternALier);
						isOk = false;

					} else {
						isOk &= (!structPaternAInserer[0] && !structurePaternALier[6]) || 
								(!structPaternAInserer[1] && !structurePaternALier[7]) ||
								(!structPaternAInserer[2] && !structurePaternALier[8]);
					}
					break;
					//créer à gauche
				case 2:
					if (structurePaternALier[0] && structurePaternALier[3] && structurePaternALier[6]){
						paternExistant.remove(paternALier);
						isOk = false;
					} else {
						isOk &=	(!structPaternAInserer[2] && !structurePaternALier[0]) || 
								(!structPaternAInserer[5] && !structurePaternALier[3]) ||
								(!structPaternAInserer[8] && !structurePaternALier[6]);
					}
					break;
					//créer en haut
				case 3:

					if (structurePaternALier[0] && structurePaternALier[1] && structurePaternALier[2]){
						paternExistant.remove(paternALier);
						isOk = false;
					} else {
						isOk &=	(!structPaternAInserer[6] && !structurePaternALier[0]) || 
								(!structPaternAInserer[7] && !structurePaternALier[1]) ||
								(!structPaternAInserer[8] && !structurePaternALier[2]);
					}
					break;
					//créer à droite
				case 4:

					if (structurePaternALier[2] && structurePaternALier[5] && structurePaternALier[8]){
						paternExistant.remove(paternALier);
						isOk = false;
					} else {
						isOk &=	(!structPaternAInserer[0] && !structurePaternALier[2]) || 
								(!structPaternAInserer[3] && !structurePaternALier[5]) ||
								(!structPaternAInserer[6] && !structurePaternALier[8]);
					}
					break;
				}

				if(aLierDejaTeste.size() == paternExistant.size()){
					return null;
				}
			}
		}		
		return potentielPaternAInserer;
	}

	//position de p2 par rapport à p1
	public int dessusDessousGaucheDroite(Point p1, Point p2){
		if (p2 != null){
			if (p2.y > p1.y ){
				return 3;
			} else if (p2.y < p1.y){
				return 1;
			} else if (p2.x > p1.x ){
				return 2;
			} else if (p2.x < p1.x){
				return 4;
			}
			return 0;
			//valeur de retour
			/*   _1_
			 * 4|_ _|2
			 *    3
			 */

		} else {
			return 0;
		}
	}

	public Point getKey(Patern pat){
		for (Entry<Point, Patern> entry : lab.entrySet()){
			if (entry.getValue() == pat){
				return entry.getKey();
			}
		}
		return null;
	}

	public Patern getRandomPatern(ArrayList<Patern> dejaTeste){
		Patern res = null;
		do {
			switch ((int)(Math.random()*11+1)) {
			case 0:	
				res = new CroixPatern();
				break;
			case 1:
				res = new ViragePatern(Rotation.r0);
				break;
			case 2:
				res = new ViragePatern(Rotation.r90);
				break;
			case 3:
				res = new ViragePatern(Rotation.r180);
				break;
			case 4:
				res = new ViragePatern(Rotation.r270);
				break;
			case 5:
				res = new CheminPatern(Rotation.r0);
				break;
			case 6:
				res = new CheminPatern(Rotation.r90);
				break;
			case 7:
				res = new TPatern(Rotation.r0);
				break;
			case 8:
				res = new TPatern(Rotation.r90);
				break;
			case 9:
				res = new TPatern(Rotation.r180);
				break;
			case 10:
				res = new TPatern(Rotation.r270);
				break;
			case 11:
				res = new FullPatern(Rotation.r0);
				break;
			case 12 :
				res = new DiagoPatern(Rotation.r0);
				break;
			case 13 :
				res = new DiagoPatern(Rotation.r90);
				break;
			case 14 :
				res = new CulDeSacPatern(Rotation.r0);
				break;
			case 15:
				res = new CulDeSacPatern(Rotation.r90);
				break;
			case 16:
				res = new CulDeSacPatern(Rotation.r180);
				break;
			case 17:
				res = new CulDeSacPatern(Rotation.r270);
				break;
			default:
				break;
			}
			//res = new RandomPatern(Rotation.r0);
		} while (dejaTeste.contains(res));
		return res;
	}

	public Patern getCase(int i, int j){
		Point p = new Point(i,j);
		if (lab.containsKey(p))
			return lab.get(p);
		return null;
	}

	public void setCase(int i, int j, Patern patern){
		Point p = new Point(i,j);
		if (!lab.containsKey(p))
			lab.put(p, patern);
		else
			System.err.println("On a set une case qui était déjà set");

		setChanged();
		notifyObservers();
	}

	public void hautPerso() {
		perso.haut();

		setChanged();
		notifyObservers();
	}

	public void basPerso() {
		perso.bas();

		setChanged();
		notifyObservers();		
	}

	public void gauchePerso() {
		perso.gauche();

		setChanged();
		notifyObservers();		
	}

	public void droitePerso() {
		perso.droite();

		setChanged();
		notifyObservers();		
	}

	public HashMap<Point,Patern> getHashMap(){
		return lab;
	}

	public double getXPerso() {
		return perso.getX();
	}

	public double getYPerso() {
		return perso.getY();
	}

	public double getSpeedXPerso(){
		return perso.getSpeedX();
	}

	public double getSpeedYPerso(){
		return perso.getSpeedY();
	}

	public int getXPatern (){
		return perso.getXPatern();
	}

	public int getYPatern (){
		return perso.getYPatern();
	}

	public int getXCaseDansPatern (){
		return perso.getxCaseDansPatern();
	}

	public int getYCaseDansPatern (){
		return perso.getyCaseDansPatern();	
	}

	public Perso getPerso(){
		return perso;
	}

	public void incrementeSpeedPerso(int i) {
		perso.setSpeedX(perso.getSpeedX() + i);
		perso.setSpeedY(perso.getSpeedY() + i);

	}

}

