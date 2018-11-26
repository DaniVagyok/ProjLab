package projlab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameView extends JPanel{
	private static final long serialVersionUID = 1L;
	
	//Rajzolhato objektumo listaja.
	private List<AbstractDrawable> draws;
	
	public JLabel points;
	public JLabel playerNext;

	public GameView()
	{
		//Hatterszin beallitasa.
	    this.setBackground(Color.BLUE);
	    //Esemenyre feliratkozas.
	    this.addKeyListener(new KeyListener() {
	        public void keyTyped(KeyEvent e) {}

	        @Override
	        public void keyPressed(KeyEvent e) {
	        	System.out.println(e.getKeyCode());
	            Game.step(e.getKeyCode());
	        }

	        public void keyReleased(KeyEvent e) {}
        });
	    
	    draws = new ArrayList<AbstractDrawable>();
	    
	    //A pontok megjelenítéséért felelõs rész
	    JLabel jl = new JLabel();
	    jl.setText("0");
	    jl.setBackground(Color.white);
	    jl.setForeground(Color.red);
	    jl.setFont (jl.getFont ().deriveFont (30.0f));
	    jl.setOpaque(true);
	    points = jl;
	    
	    //A következõ játékos jelzése
	    JLabel jl2 = new JLabel();
	    jl2.setText("-");
	    jl2.setBackground(Color.white);
	    jl2.setForeground(Color.red);
	    jl2.setFont (jl.getFont ().deriveFont (30.0f));
	    jl2.setOpaque(true);
	    playerNext = jl2;
	    
	    
	    this.add(jl);
	    this.add(jl2);
	   
	}

	public void AddDrawable(AbstractDrawable d){
		draws.add(d);
	}
	public void RemoveDrawable(AbstractDrawable d){
		draws.remove(d);
	}
	
	//Minden AbstractDrawable-t kirajzolunk
	protected void paintComponent(Graphics g)
	{
	    super.paintComponent(g); 
	    Collections.sort(draws);
	    for(AbstractDrawable ad : draws){
	    	ad.Draw(g);	    	
	    }
	}	
}
