package projlab;

//A lyuk mez�t implement�l� oszt�ly.
public class Hole extends Field {
	
	//A lyukra egy doboz ker�l
	public boolean Accept(Box b, Integer force){ 
		b.Die();
		return true;
	}
	
	//A lyukra egy j�t�kos ker�l
	public boolean Accept(Player p, Integer force){
		p.Die();
		return true;
	}
	
}
