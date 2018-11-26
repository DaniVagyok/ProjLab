package projlab;

//A falat implemetnáló osztály.
public class Wall extends Field {
	
	// A Fal mezõre egy doboz próbál kerülni.
	public boolean Accept(Box b, Integer f){ 
		return false;
	}
	
	// A falra egy  játékos próbál kerülni.
	public boolean Accept(Player p, Integer f){ 
		return false;
	}
}
