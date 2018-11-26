package projlab;

//A célmezõt implementáló osztály.
public class Target extends Field{
	//Egy játékost fog tárolni, aki éppen utoljára tolta rá a dobozt. (Adott körben lépõ játékos)
	//Ha egy játékos lép rá akkor nullázódik.
	Box b = null;
	
	//A mezõre egy doboz próbál lépni. Elmenti azt a Playert aki tolta,
	// hogy pontszáítások nyomonkövethetõ legyen, hogy ki tolta dobozt utoljára
	// és kinek számítódon be a pont a játék végén.
	public boolean Accept(Box b, Integer f){
		if(i != null) 
			i.Collide(b, f);
		
		if(i == null) {
			if(this.b == null){
				Game.boxesOnTarget.add(b);
				Game.activeBoxes.remove(b);
				b.pusher = Game.nextPlayer;
			}
			this.b = b;
			this.i = b;
			return true;
		}
		else return false;
	}
	
	//A target mezõre gy játékos próbál kerülni.
	public boolean Accept(Player p, Integer f){
		if(i != null) 
			i.Collide(p, f);
		
		if(i == null) {
			if(this.b != null){
				Game.boxesOnTarget.remove(b);
				Game.activeBoxes.add(b);
				b.pusher = null;
			}
			this.b = null;
			this.i = p;
			return true;
		}
		else return false;
	}
}
