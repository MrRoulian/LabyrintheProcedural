package controler;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Laby;
import model.Perso;

public class Clavier implements KeyListener{

	private Laby lab;
	private boolean zPressed,sPressed,qPressed,dPressed;

	public Clavier(Laby lab){
		this.lab=lab;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		/*
		 * System.out.println(lab.getPerso());
        System.out.println("Dans la case : x:"+lab.getXPerso()/Laby.TAILLECASE+", y:"+lab.getYPerso()/Laby.TAILLECASE);
		 */
		int i,j,k,l;

		switch (e.getKeyChar()) {
		case 'z':
			zPressed = true;			
			break;
		case 's':
			sPressed = true;			
			break;
		case 'q':
			qPressed = true;			
			break;
		case 'd':
			dPressed = true;			
			break;
		default:
			break;
		}

		switch(e.getKeyCode()){
		case 16:
			lab.incrementeSpeedPerso(1);
			System.out.println("speed up " + lab.getSpeedYPerso());
			break;
		case 17:
			lab.incrementeSpeedPerso(-1);
			System.out.println("speed down " + lab.getSpeedYPerso());
			break;
		}


		if (zPressed){
			/*//coin en haut à gauche past mouv
			i = (int)(lab.getXPerso()-Perso.TAILLEROND/2)/Laby.TAILLECASEPATERN;
			j = (int)(lab.getYPerso()-Perso.TAILLEROND/2-lab.getSpeedYPerso())/Laby.TAILLECASEPATERN;
			//coin en haut à droite past mouv
			k = (int)(lab.getXPerso()+Perso.TAILLEROND/2)/Laby.TAILLECASEPATERN;
			l = (int)(lab.getYPerso()-Perso.TAILLEROND/2-lab.getSpeedYPerso())/Laby.TAILLECASEPATERN;

			if (!lab.get(i, j) && !lab.getCase(k, l)){
				lab.hautPerso();
			}*/
			lab.hautPerso();
			if (lab.getPerso().estDansUnMur(lab)){
				lab.basPerso();
			}

		}
		if (sPressed){
			/*//coin en bas a gauche après mouvement
			i = (int)(lab.getXPerso()-Perso.TAILLEROND/2)/Laby.TAILLECASEPATERN;
			j = (int)(lab.getYPerso()+Perso.TAILLEROND/2+lab.getSpeedYPerso())/Laby.TAILLECASEPATERN;
			//coin en bas a droite après mouvement
			k = (int)(lab.getXPerso()+Perso.TAILLEROND/2)/Laby.TAILLECASEPATERN;
			l = (int)(lab.getYPerso()+Perso.TAILLEROND/2+lab.getSpeedYPerso())/Laby.TAILLECASEPATERN;

			if (!lab.getCase(i, j) && !lab.getCase(k, l)){
				lab.basPerso();
			}*/
			lab.basPerso();
			if (lab.getPerso().estDansUnMur(lab)){
				lab.hautPerso();
			}
		}
		if (qPressed){
			/*//coin en haut a gauche après mouvement
			i = (int)(lab.getXPerso()-Perso.TAILLEROND/2-lab.getSpeedXPerso())/Laby.TAILLECASEPATERN;
			j = (int)(lab.getYPerso()-Perso.TAILLEROND/2)/Laby.TAILLECASEPATERN;
			//coin en bas a gauche après mouvement
			k = (int)(lab.getXPerso()-Perso.TAILLEROND/2-lab.getSpeedXPerso())/Laby.TAILLECASEPATERN;
			l = (int)(lab.getYPerso()+Perso.TAILLEROND/2)/Laby.TAILLECASEPATERN;

			if (!lab.getCase(i, j) && !lab.getCase(k, l)){
				lab.gauchePerso();
			}*/

			lab.gauchePerso();
			if (lab.getPerso().estDansUnMur(lab)){
				lab.droitePerso();
			}
		}
		if (dPressed){
			/*//coin en haut a droite après mouvement
			i = (int)(lab.getXPerso()+Perso.TAILLEROND/2+lab.getSpeedXPerso())/Laby.TAILLECASEPATERN;
			j = (int)(lab.getYPerso()-Perso.TAILLEROND/2)/Laby.TAILLECASEPATERN;
			//coin en bas a droite après mouvement
			k = (int)(lab.getXPerso()+Perso.TAILLEROND/2+lab.getSpeedXPerso())/Laby.TAILLECASEPATERN;
			l = (int)(lab.getYPerso()+Perso.TAILLEROND/2)/Laby.TAILLECASEPATERN;

			if (!lab.getCase(i, j) && !lab.getCase(k, l)){
				lab.droitePerso();
			}*/

			lab.droitePerso();
			if (lab.getPerso().estDansUnMur(lab)){
				lab.gauchePerso();
			}
		}		
		lab.genererAutourPatern(new Point(lab.getXPatern(), lab.getYPatern()));		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'z':
			zPressed = false;			
			break;
		case 's':
			sPressed = false;			
			break;
		case 'q':
			qPressed = false;			
			break;
		case 'd':
			dPressed = false;			
			break;
		default:
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
