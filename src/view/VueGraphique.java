package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controler.*;
import model.*;
import patern.Patern;

public class VueGraphique implements Observer {
	
	private JFrame frame = new JFrame("Lab");
    private JPanel panel = new JPanel();
    private Laby lab;
    private JComponent dessinLaby;

    public VueGraphique(Laby lab) {
        // ...
        this.lab = lab;
        buildFrame();
    }

    @SuppressWarnings("serial")
	public void buildFrame() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = (JPanel) frame.getContentPane();

        // initialize panels
        panel = new JPanel();
        
        contentPane.setPreferredSize(new Dimension(Laby.TAILLEFENETRE,Laby.TAILLEFENETRE));
        
        dessinLaby = new JComponent() {
        	public void paint(Graphics g){
        		int i,j,k;
        		for (Entry<Point, Patern> entry : lab.getHashMap().entrySet()) {
        			k=0;
        			Point p = entry.getKey();
        			//System.out.println(p + " \n" + entry.getValue());
        			for (boolean b : entry.getValue().getStructure()){
        				i = (int) (p.x*Laby.TAILLECASEPATERN+k%Patern.TAILLEPATERN*Laby.TAILLEPETITECASE);
        				j = (int) (p.y*Laby.TAILLECASEPATERN+k/Patern.TAILLEPATERN*Laby.TAILLEPETITECASE);
        				//System.out.println(i + " " + j);
        				if (b) {
        	        		g.setColor(Color.BLACK);
        				} else {
        	        		g.setColor(Color.LIGHT_GRAY);
        				}
    					g.fillRect(	i + (((int)lab.getXPerso() - (Laby.TAILLEFENETRE/2 + Laby.TAILLECASEPATERN/2 - Perso.TAILLEROND/2))*-1),
    								j + (((int)lab.getYPerso() - (Laby.TAILLEFENETRE/2 + Laby.TAILLECASEPATERN/2 - Perso.TAILLEROND/2))*-1),
    								Laby.TAILLEPETITECASE+1, Laby.TAILLEPETITECASE+1);
        				k++;
        			}
        		}
        		//dessin du perso
        		g.setColor(Color.RED);
                g.fillOval(Laby.TAILLEFENETRE/2 + Laby.TAILLECASEPATERN/2 - Perso.TAILLEROND/2, Laby.TAILLEFENETRE/2 + Laby.TAILLECASEPATERN/2 - Perso.TAILLEROND/2, Perso.TAILLEROND, Perso.TAILLEROND);
        	}
		};
		
		frame.addKeyListener(new Clavier(lab));
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX()/Laby.TAILLECASEPATERN;
				int y = e.getY()/Laby.TAILLECASEPATERN;
				System.out.println(e.getX()+"/"+x + " " + e.getY()+"/"+y);
				System.out.println(lab.getCase(x, y));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        contentPane.add(panel);
        contentPane.add(dessinLaby);

        frame.pack();
        frame.setVisible(true);
    }
    

	@Override
	public void update(Observable arg0, Object arg1) {
        dessinLaby.repaint();
	}

}
