package projlab;

//A c�lmez�t implement�l� oszt�ly.
public class Target extends Field{
	//Egy j�t�kost fog t�rolni, aki �ppen utolj�ra tolta r� a dobozt. (Adott k�rben l�p� j�t�kos)
	//Ha egy j�t�kos l�p r� akkor null�z�dik.
	Box b = null;
	
	//A mez�re egy doboz pr�b�l l�pni. Elmenti azt a Playert aki tolta,
	// hogy pontsz��t�sok nyomonk�vethet� legyen, hogy ki tolta dobozt utolj�ra
	// �s kinek sz�m�t�don be a pont a j�t�k v�g�n.
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
	
	//A target mez�re gy j�t�kos pr�b�l ker�lni.
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
