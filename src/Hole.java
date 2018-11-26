package projlab;

//A lyuk mezõt implementáló osztály.
public class Hole extends Field {
	
	//A lyukra egy doboz kerül
	public boolean Accept(Box b, Integer force){ 
		b.Die();
		return true;
	}
	
	//A lyukra egy játékos kerül
	public boolean Accept(Player p, Integer force){
		p.Die();
		return true;
	}
	
}
