package projlab;

//A falat implemetn�l� oszt�ly.
public class Wall extends Field {
	
	// A Fal mez�re egy doboz pr�b�l ker�lni.
	public boolean Accept(Box b, Integer f){ 
		return false;
	}
	
	// A falra egy  j�t�kos pr�b�l ker�lni.
	public boolean Accept(Player p, Integer f){ 
		return false;
	}
}
