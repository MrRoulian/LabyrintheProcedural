package model;

import java.awt.Point;
import java.awt.font.NumericShaper.Range;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;

import patern.Patern;
import patern.RandomPatern;
import patern.Rotation;
import patern.StartPatern;
import taille3.CheminPatern;
import taille3.CroixPatern;
import taille3.CulDeSacPatern;
import taille3.DiagoPatern;
import taille3.FullPatern;
import taille3.TPatern;
import taille3.ViragePatern;
import taille7.PaternCreux;
import taille9.EtrangePatern;
import taille9.EtrangePatern2;
import taille9.EtrangePatern3;
import taille9.EtrangePatern4;

public class Laby extends Observable {	

	public static int LARGEURFENETRE = 1000;
	public static int HAUTEURFENETRE = 1000;
	public final static int TAILLEPETITECASE = 30;
	public final static int TAILLECASEPATERN = TAILLEPETITECASE*Patern.TAILLEPATERN;
	public static final int RANGEAPPARITION = Laby.LARGEURFENETRE/Laby.TAILLECASEPATERN;
	private int nbCaseADevoiler = RANGEAPPARITION*(RANGEAPPARITION*2+2);
	private int indice= -1;
	HashMap<Point,Patern> lab = new HashMap<>();
	//HashMap<Point,Integer> casesAGenerer = new HashMap<>();
	ArrayList<Point> casesAGenerer = new ArrayList<Point>();
	public Perso perso;

	public Laby(){
		perso = new Perso(LARGEURFENETRE/2 - Perso.TAILLEROND, HAUTEURFENETRE/2 - Perso.TAILLEROND);
		buildTab();
		System.out.println();
	}

	public void buildTab(){
		setCase((int)(perso.getX()/TAILLECASEPATERN), (int)(perso.getY()/TAILLECASEPATERN), StartPatern.getInstance());

		setChanged();
		notifyObservers();
	}

	public void genererAutourPatern(Point p) {
		if(indice >= nbCaseADevoiler){
			indice = -1 ;
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

			//Lie avec un patern existant
			if (isOk){
				//Pareil pour le patern a lier
				switch(dessusDessousGaucheDroite(positionPaternACreer, getKey(paternALier))){
				//creer en bas
				case 1:
					//Si celui que j'ai decide de lie est full mur
					if (Patern.estFullMur(face3PaternALier)){
						paternExistant.remove(paternALier);
						isOk = false;
					} else {
						isOkPart2 = (!face1PaternAInserer[0] && !face3PaternALier[0]);
						for (int i = 1 ; i < Patern.TAILLEPATERN ; i++){
							isOkPart2 |= (!face1PaternAInserer[i] && !face3PaternALier[i]);
						}
					}
					break;
					//creer a gauche
				case 2:
					if (Patern.estFullMur(face4PaternALier)){
						paternExistant.remove(paternALier);
						isOk = false;
					} else {
						isOkPart2 = (!face2PaternAInserer[0] && !face4PaternALier[0]);
						for (int i = 1 ; i < Patern.TAILLEPATERN ; i++){
							isOkPart2 |= (!face2PaternAInserer[i] && !face4PaternALier[i]);
						}
					}
					break;
					//creer en haut
				case 3:
					if (Patern.estFullMur(face1PaternALier)){
						paternExistant.remove(paternALier);
						isOk = false;
					} else {
						isOkPart2 = (!face3PaternAInserer[0] && !face1PaternALier[0]);
						for (int i = 1 ; i < Patern.TAILLEPATERN ; i++){
							isOkPart2 |= (!face3PaternAInserer[i] && !face1PaternALier[i]);
						}
					}
					break;
					//creer a droite
				case 4:
					if (Patern.estFullMur(face2PaternALier)){
						paternExistant.remove(paternALier);
						isOk = false;
					} else {
						isOkPart2 = (!face4PaternAInserer[0] && !face2PaternALier[0]);
						for (int i = 1 ; i < Patern.TAILLEPATERN ; i++){
							isOkPart2 |= (!face4PaternAInserer[i] && !face2PaternALier[i]);
						}
					}
					break;
				}

				if(!isOk){
					potentielDejaTeste.add(potentielPaternAInserer);
				}

				isOk &=	isOkPart2;
			}
		}
		return potentielPaternAInserer;
	}

	//position de p2 par rapport a p1
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
			switch(Patern.TAILLEPATERN) {
			case 3:
				switch ((int)(Math.random()*11+1)) {
				case 0:	
					res = CroixPatern.getInstance(Rotation.r0);
					break;
				case 1:
					res =  ViragePatern.getInstance(Rotation.r0);
					break;
				case 2:
					res =  ViragePatern.getInstance(Rotation.r90);
					break;
				case 3:
					res =  ViragePatern.getInstance(Rotation.r180);
					break;
				case 4:
					res =  ViragePatern.getInstance(Rotation.r270);
					break;
				case 5:
					res =  CheminPatern.getInstance(Rotation.r0);
					break;
				case 6:
					res =  CheminPatern.getInstance(Rotation.r90);
					break;
				case 7:
					res =  TPatern.getInstance(Rotation.r0);
					break;
				case 8:
					res =  TPatern.getInstance(Rotation.r90);
					break;
				case 9:
					res =  TPatern.getInstance(Rotation.r180);
					break;
				case 10:
					res =  TPatern.getInstance(Rotation.r270);
					break;
				case 11:
					res =  FullPatern.getInstance(Rotation.r0);
					break;
				case 12 :
					res =  DiagoPatern.getInstance(Rotation.r0);
					break;
				case 13 :
					res =  DiagoPatern.getInstance(Rotation.r90);
					break;
				case 14 :
					res =  CulDeSacPatern.getInstance(Rotation.r0);
					break;
				case 15:
					res =  CulDeSacPatern.getInstance(Rotation.r90);
					break;
				case 16:
					res =  CulDeSacPatern.getInstance(Rotation.r180);
					break;
				case 17:
					res =  CulDeSacPatern.getInstance(Rotation.r270);
					break;
				default:
					break;
				}
				break;
			case 7:
				switch ((int)(Math.random()*1)) {
				case 0:	
					res = PaternCreux.getInstance(Rotation.r0);
					break;
				}
				break;
			case 9:
				switch((int)(Math.random()*13)){
				case 0:
					res =  EtrangePatern.getInstance(Rotation.r0);
					break;
				case 1:
					res =  EtrangePatern.getInstance(Rotation.r90);
					break;
				case 2:
					res =  EtrangePatern.getInstance(Rotation.r180);
					break;
				case 3:
					res =  EtrangePatern.getInstance(Rotation.r270);
					break;
				case 4:
					res =  EtrangePatern2.getInstance(Rotation.r0);
					break;
				case 5:
					res =  EtrangePatern3.getInstance(Rotation.r0);
					break;
				case 6:
					res =  EtrangePatern3.getInstance(Rotation.r90);
					break;
				case 7:
					res =  EtrangePatern3.getInstance(Rotation.r180);
					break;
				case 8:
					res =  EtrangePatern3.getInstance(Rotation.r270);
					break;
				case 9:
					res =  EtrangePatern4.getInstance(Rotation.r0);
					break;
				case 10:
					res =  EtrangePatern4.getInstance(Rotation.r90);
					break;
				case 11:
					res =  EtrangePatern4.getInstance(Rotation.r180);
					break;
				case 12:
					res =  EtrangePatern4.getInstance(Rotation.r270);
					break;
				}
				break;
			default:
				res = RandomPatern.getInstance(Rotation.r0);
				break;
			}

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
			System.err.println("On a set une case qui etait deja set");

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

