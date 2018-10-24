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
	public final static int TAILLECASEPATERN = TAILLEFENETRE/22;
	public final static int TAILLEPETITECASE = (int) (TAILLECASEPATERN/Patern.TAILLEPATERN);
	public static final int RANGEAPPARITION = 10;
	private int nbCaseADevoiler = RANGEAPPARITION*(RANGEAPPARITION*2+2);
	private int indice = -1;
	HashMap<Point,Patern> lab = new HashMap<>();
	//HashMap<Point,Integer> casesAGenerer = new HashMap<>();
	ArrayList<Point> casesAGenerer = new ArrayList<Point>();
	public Perso perso;

	public Laby(){
		perso = new Perso(TAILLEFENETRE/2 + TAILLECASEPATERN/2 - Perso.TAILLEROND, TAILLEFENETRE/2 + TAILLECASEPATERN/2 - Perso.TAILLEROND);
		buildTab();
		System.out.println();
	}

	public void buildTab(){
		setCase((int)(perso.getX()/TAILLECASEPATERN), (int)(perso.getY()/TAILLECASEPATERN), new CroixPatern());

		setChanged();
		notifyObservers();
	}

	public void genererAutourPatern(Point p) {
		if(casesAGenerer.size()+1 == nbCaseADevoiler){
			indice =0 ;
			casesAGenerer.clear();
			return;
		}
		
		int xPat = p.x;
		int yPat = p.y;
		Point haut = new Point(xPat,yPat-1);
		Point bas = new Point(xPat, yPat+1);
		Point droite = new Point (xPat+1, yPat);
		Point gauche = new Point (xPat-1, yPat);
		Point centre = new Point (xPat,yPat);

		//Point persoPoint = new Point(perso.getXPatern(),perso.getYPatern());

		Patern aAjouterC = null;
		Patern aAjouterG = null;
		Patern aAjouterD = null;
		Patern aAjouterH = null;
		Patern aAjouterB = null;


		if (!casesAGenerer.contains(haut)){
			casesAGenerer.add(haut);
		}
		if (!casesAGenerer.contains(droite)){
			casesAGenerer.add(droite);
		}
		if (!casesAGenerer.contains(bas)){
			casesAGenerer.add(bas);
		}
		if (!casesAGenerer.contains(gauche)){
			casesAGenerer.add(gauche);
		}

		if (!lab.containsKey(haut)) {
			aAjouterH = nouveauPatern(haut);
			if (aAjouterH != null)
				lab.put(haut, aAjouterH);			
		}
		if (!lab.containsKey(droite)){
			aAjouterD = nouveauPatern(droite);
			if (aAjouterD != null)
				lab.put(droite, aAjouterD);
		}
		if (!lab.containsKey(bas)){
			aAjouterB = nouveauPatern(bas);
			if (aAjouterB != null)
				lab.put(bas, aAjouterB);
		}
		if (!lab.containsKey(gauche)){
			aAjouterG = nouveauPatern(gauche);
			if (aAjouterG != null)
				lab.put(gauche, aAjouterG);
		}
		if (!lab.containsKey(centre)){
			aAjouterC = nouveauPatern(centre);
			if (aAjouterC != null)
				lab.put(centre, aAjouterC);
		}

		indice++;
		genererAutourPatern(casesAGenerer.get(indice));

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
		boolean isOkPart2 = false;
		Patern potentielPaternAInserer = null;
		//Au moins un trou vers un patern existant et au moins un trou vers un patern futur
		ArrayList<Patern> potentielDejaTeste = new ArrayList<>();
		ArrayList<Patern> aLierDejaTeste = new ArrayList<>();
		Point pointFutur = null;
		while (!isOk){

			if (paternExistant.size() == 0) return null;
			Patern paternALier = paternExistant.get((int)(Math.random()*paternExistant.size()));
			boolean[] face1PaternALier = paternALier.getFace(1);
			boolean[] face2PaternALier = paternALier.getFace(2);
			boolean[] face3PaternALier = paternALier.getFace(3);
			boolean[] face4PaternALier = paternALier.getFace(4);
			potentielPaternAInserer = getRandomPatern(potentielDejaTeste);
			boolean[] face1PaternAInserer = potentielPaternAInserer.getFace(1);
			boolean[] face2PaternAInserer = potentielPaternAInserer.getFace(2);
			boolean[] face3PaternAInserer = potentielPaternAInserer.getFace(3);
			boolean[] face4PaternAInserer = potentielPaternAInserer.getFace(4);
			if (paternFutur.size() != 0){
				pointFutur = paternFutur.get((int)(Math.random()*paternFutur.size()));
			}

			//Savoir si le point futur est en haut, a droite, a gauche ou en bas du paternRandom pris
			//Ce switch = condition pour au moins une liaison vers le futur
			switch(dessusDessousGaucheDroite(positionPaternACreer, pointFutur)){
			case 1:
				//0,1,2
				isOk= !face1PaternAInserer[0];
				for (int i= 1 ; i < face1PaternAInserer.length ; i++){
					isOk |= !face1PaternAInserer[i];
				}
				break;
			case 2:
				//2,5,8
				isOk= !face2PaternAInserer[0];
				for (int i = 1; i < face2PaternAInserer.length ; i++){
					isOk |= !face2PaternAInserer[i];					
				}
				break;
			case 3:
				//6,7,8
				isOk= !face3PaternAInserer[0];
				for (int i = 1 ; i < face3PaternAInserer.length; i++){
					isOk |= !face3PaternAInserer[i];					
				}
				break;
			case 4:
				//0,3,6
				isOk= !face4PaternAInserer[0];
				for (int i = 0; i < face4PaternAInserer.length; i++){
					isOk |= !face4PaternAInserer[i];					
				}
				break;
			default:
				isOk = true;
				break;
			}

			//Lié avec un patern existant
			if (isOk){
				//Pareil pour le patern à lier
				switch(dessusDessousGaucheDroite(positionPaternACreer, getKey(paternALier))){
				//créer en bas
				case 1:
					//Si celui que j'ai décidé de lié est full mur
					if (Patern.estFullMur(face3PaternALier)){
						paternExistant.remove(paternALier);
						isOk = false;
					} else {
						for (int i = 0 ; i < Patern.TAILLEPATERN ; i++){
							isOkPart2 |= !face1PaternAInserer[i] && !face3PaternALier[i];
						}
						isOk &= isOkPart2;
						isOk &= (!structPaternAInserer[0] && !structurePaternALier[6]) || 
								(!structPaternAInserer[1] && !structurePaternALier[7]) ||
								(!structPaternAInserer[2] && !structurePaternALier[8]);
					}
					break;
					//créer à gauche
				case 2:
					if (Patern.estFullMur(face4PaternALier)){
						paternExistant.remove(paternALier);
						isOk = false;
					} else {
						for (int i = 0 ; i < Patern.TAILLEPATERN ; i++){
							isOk &= !face2PaternAInserer[i] && !face4PaternALier[i];
						}
						isOk &=	(!structPaternAInserer[2] && !structurePaternALier[0]) || 
								(!structPaternAInserer[5] && !structurePaternALier[3]) ||
								(!structPaternAInserer[8] && !structurePaternALier[6]);
					}
					break;
					//créer en haut
				case 3:

					if (Patern.estFullMur(face1PaternALier)){
						paternExistant.remove(paternALier);
						isOk = false;
					} else {
						for (int i = 0 ; i < Patern.TAILLEPATERN ; i++){
							isOk &= !face3PaternAInserer[i] && !face1PaternALier[i];
						}
						isOk &=	(!structPaternAInserer[6] && !structurePaternALier[0]) || 
								(!structPaternAInserer[7] && !structurePaternALier[1]) ||
								(!structPaternAInserer[8] && !structurePaternALier[2]);
					}
					break;
					//créer à droite
				case 4:

					if (Patern.estFullMur(face2PaternAInserer)){
						paternExistant.remove(paternALier);
						isOk = false;
					} else {
						for (int i = 0 ; i < Patern.TAILLEPATERN ; i++){
							isOk &= !face4PaternAInserer[i] && !face2PaternALier[i];
						}
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
	private int dessusDessousGaucheDroite(Point p1, Point p2){
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

	public static int calculerNbCaseEcart(Point p1, Point p2){
		Point p3 = new Point(p2.x-p1.x,p2.y-p1.y);
		return Math.abs(p3.x)+Math.abs(p3.y);
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

