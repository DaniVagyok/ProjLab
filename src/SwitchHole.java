package projlab;

import java.util.Scanner;

//SwitchHole mezõ amit egy Switch irányít
public class SwitchHole extends Field {
	//A kinyitottsaganak az allapota.
	private boolean state = false;
	
	//A mezõre egy doboz kerül. Ha nyitva van akkor a dobo meghal.
	public boolean Accept(Box b, Integer f){
		//Ha nyitva van a kapcsolo akkor az ide kerulo Item megsemmisul.
		if(state){
			b.Die();
			return true;
		}
		//Amennyiben zarva van a kapcsolo az Item ide kerulhet.
		Integer force = f;
		
		//A modosito alapjan az ero valtoztasa.
		switch(ft){
			case Honey:
				force--;
				break;
			case Oil:
				force++;
				break;
			default:
				break;
		}

		//Ha az ero elfogy akkor akkor a lepes sikertelen.
		if(force <= 0) return false;
		
		//Ha van Item a mezon akkor az ide erkezo Item osszeutkozik az itt tartozkodoval.
		if(i != null) 
			i.Collide(b, force);
		
		//Ha nincs Item a mezon akkor az idelepesnek nincsen akadalya.
		if(i == null) {
			this.i = b;
			return true;
		}
		return false;
		
	}
	
	//A mezõre Player kerül ha a SwitchHole nyitva van akkor a Player meghal.
	public boolean Accept(Player p, Integer f){
		//Ha nyitott allapotban van a SwitcHole akkor az ide kerulo Item elhuny.
		if(state){
			p.Die();
			return true;
		}
		
		Integer force = f;
		
		//A mezon levo modosito impresszalja az erot.
		switch(ft){
			case Honey:
				force--;
				break;
			case Oil:
				force++;
				break;
			default:
				break;
		}

		//Ha az ero elfogy akkor akkor a lepes sikertelen.
		if(force <= 0) return false;
		
		//Ha van Item a mezon akkor az ide erkezo Item osszeutkozik az itt tartozkodoval.
		if(i != null) 
			i.Collide(p, force);
		
		//Ha nincs Item a mezon akkor az idelepesnek nincsen akadalya.
		if(i == null) {
			this.i = p;
			return true;
		}
		return false;
		
	}

	//Kinyitja a SwitchHolet
	public void Open(){
		this.state = true;
	}
	
	//Becsukja a SwitchHolet
	public void Close(){
		this.state = false;
	}
	
	public String toString(){
		String str = "Nb: ";
		for(Direction d : neighbors.keySet()){
			str += neighbors.get(d).id + ":" + d;
		}
		String item = "null";
		if(i != null)
			item = i.toString();
		return id + " " + this.getClass().getName().substring(8) + " " 
			   + ft.toString() + " " + item + "\n" + str
			   + "\n" + state;
	}
	
	public boolean getState(){ return this.state; }
}
