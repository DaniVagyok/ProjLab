package projlab;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

//A j�t�k f�ablaka
public class MainWindow extends JFrame{
	
	private JPanel cards;
	private GameView game;
	private MenuView menu;

	//Komponensek inicializ�l�sa
	private void initComponents() {
		this.setFocusable(true);
		menu = new MenuView(this);
		game = new GameView();
		Game.gw = game;
		cards = new JPanel(new CardLayout());
		cards.add(game, "game");
		cards.add(menu, "menu");
	    CardLayout cl = (CardLayout)(cards.getLayout());
	    cl.show(cards, "menu");
		add(cards);
		
		//A gomblenyom�sok kezel�s�rt felel�s esem�nykezel�
		//Tov�bbadja a Game oszt�lynak a lenyomott billenty� k�dj�t
		this.addKeyListener(new KeyListener() {
	        public void keyTyped(KeyEvent e) {}

	        @Override
	        public void keyPressed(KeyEvent e) {
	        	System.out.println(e.getKeyCode());
	            Game.step(e.getKeyCode());
	        }

	        public void keyReleased(KeyEvent e) {}
        });
	}
	
	@SuppressWarnings("unchecked")
    public MainWindow() {
        super("Killer Sokoban");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(350, 150));
        initComponents();
    }
	
	//A p�lya kiv�laszt�sa ut�n a j�t�k elind�t�sa
	//Kiv�lasztja a megfelel� JPanelt (Game)
	public void StartGame() {		
		CardLayout cl = (CardLayout)(cards.getLayout());
		 this.setSize(new Dimension(600, 600));
		cl.show(cards, "game");
		game.repaint();
	}
	
	public static void main(String[] args) {
        // Megjelen�tj�k az ablakot
		MainWindow sf = new MainWindow();
        sf.setVisible(true);
    }
}
