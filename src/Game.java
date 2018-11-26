package projlab;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Game {
	public static GameView gw; //nem kene publicnak lennnie
	
	//A j�t�kban l�v� �sszes j�t�kosnak a list�ja.
	public static List<Player> players = new ArrayList<Player>();
	
	//A jelenleg eletbenlevo jatekosok listaja.
	public static List<Player> activePlayers = new ArrayList<Player>();
	
	//A soron kovetkezo jatekos.
	public static Player nextPlayer;
	
	//A j�t�kban l�v� �sszes doboznak a list�ja.
	public static List<Box> activeBoxes = 	new ArrayList<Box>();
	
	//A targetmezore kerult dobozok listaja.
	public static List<Box> boxesOnTarget = 	new ArrayList<Box>();
	
	//A p�ly�t reprezent�l� tagv�ltoz�
	public static Map m = new Map();
	
	//A jatek elinditasaert felelos fuggveny a kivalasztott palya indexevel lehet parameterezni.
	public static void StartGame(int mapIndex){
		//kivalasztott palya betoltese.
		m.LoadMap(mapIndex);		
		for(Player p : players)
			activePlayers.add(p);	
		//Elso jatekos kivalasztasa.
		nextPlayer = activePlayers.get(0);	
	}
	
	//A jatekos egy korenek az implementacioja
	public static void step(int key) {
		Player p = nextPlayer;
		if (key == KeyEvent.VK_W) {
			p.Move(Direction.Up);
        }
        else if (key == KeyEvent.VK_D) {
        	p.Move(Direction.Right);
        }
        else if (key == KeyEvent.VK_S) {
            p.Move(Direction.Down);
        }
        else if (key == KeyEvent.VK_A) {
        	p.Move(Direction.Left);
        }
        else if(key == KeyEvent.VK_O) {
        	p.Pour(Floortype.Oil);
        }
		else if(key == KeyEvent.VK_H) {
			p.Pour(Floortype.Honey);
		}
		
		
		//Lepes utan minden jatekban levo dobozra meg kell vizsgalni, 
		//hogy nem kerult e olyan helyre ahonnan nem lehet elmozgatni.
		for(int i = 0; i<activeBoxes.size(); i++){
			Box b = activeBoxes.get(i);
			if(b.CheckBoxBlocked()){
				b.field.SetBlocked();
				Game.activeBoxes.remove(b);
			}
		}
		
		//Kovetezo jatekos beallitasa.
		if(activePlayers.size() > 0){
			if(activePlayers.indexOf(p) != activePlayers.size() - 1)
				nextPlayer = activePlayers.get(activePlayers.indexOf(p) + 1);
			else nextPlayer = activePlayers.get(0);
			
			Game.gw.playerNext.setText(nextPlayer.getID().toString() + ". l�p");
			
			Game.gw.points.setText("");
			for(Player pp : players){
				String temp = "";
				temp += Game.gw.points.getText() + " " + pp.getID() + ". j�t�kos: " + getPointsFor(pp) + "||";
				Game.gw.points.setText(temp);
			}
			
		}
		else{
			EndGame();// Ha nincsen tobb jatekos akkor a jatek veget er.
			return;
		}

		gw.repaint();
		if(CheckGameEnd()){
			EndGame();
			return;
		}
	}
	
	//A param�terk�nt kapott Player-nek kisz�molja a pontjait
	private static int getPointsFor(Player p){
		int i = 0;
		for(Box b : boxesOnTarget){
			if(b.pusher.equals(p)) i++;
		}
		return i;
	}
	
	//A j�t�k v�g�nek a felt�te ker�l ellen�rz�sre.
	private static boolean CheckGameEnd() {
		return activeBoxes.size() == 0;
	}

	//Visszaadja a j�t�kosok list�j�t.
	public static List<Player> GetPlayers(){
		return players;
	}
	
	//Kit�rli a jelenletgi j�t�kost az �sszes j�t�kos k�z�l.
	public static void DelActivePlayer(Player p){
		gw.RemoveDrawable(p.getView());		
		activePlayers.remove(p);		
	}
	
	//Kit�rli a dobozt az aktyv dobozok k�z�l..
	public static void DelActiveBox(Box b){
		gw.RemoveDrawable(b.getView());
		activeBoxes.remove(b);
	}
	
	//J�t�k v�g�t megval�syt� f�ggv�ny.
	public static void EndGame(){
		int point = -1;
		Player winner = null;
		for(Player p : players){
			if(getPointsFor(p) > point){
				point = getPointsFor(p);
				winner = p;
			}
		}
				
		Object[] options = {"Kil�p�s!"};
		int n = JOptionPane.showOptionDialog(null,
											"J�t�k v�ge! " + winner.getID().toString() + ". j�t�kos nyert. Pontsz�m: " + point,
											"J�t�k v�ge.",
											JOptionPane.NO_OPTION,
											JOptionPane.QUESTION_MESSAGE,
											null,
											options,
											options[0]);
		
		
		System.exit(0);
	
		return;
	}
}
