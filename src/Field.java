package projlab;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
//A sima mez�t reprezetn�l� oszt�ly. Tartalmazhat egy itemet �s a szomsz�dait egy Map ban.
public class Field {
	//Ezt az Itemet tartalmazza a mez�.
	public int id;
	protected Item i;
	protected DField df;
	
	//A mezon levo modositonak a tipusa
	protected Floortype ft = Floortype.Regular;
	
	//Tagvaltozo ami azt jelzi, hogy a mezon van e doboz, ugy hogy mar nem lehet letolni rola.
	protected boolean isBlocked = false;
	
	//A megfelel� ir�nybn l�v� tov�bbi mez�k.
	protected Map<Direction, Field> neighbors = new TreeMap<Direction, Field>();
	
	//A mezore egy doboz ragad beallitaja a booleant true-ra.
	public void SetBlocked(){
		isBlocked = true;
	}
	
	//Visszater azzal hogy a mezon van e mozdithatatlan doboz.
	public boolean GetBlocked(){
		return isBlocked;
	}
	//A d ir�nyban l�v� mez� visszaad�sa.
	public Field GetNeighbor(Direction d){
		return neighbors.get(d);		
	}
	
	//A mez� szomsz�dj�nak be�ll�t�sa,
	public void SetNeighbor(Direction d, Field f){
		neighbors.put(d, f);
	}
	
	public void setView(DField df){
		this.df = df;
	}
	public DField getView(){ return this.df; }
	//A f�ggv�ny azt modellezi, hogy a mez�re egy doboz pr�b�l l�pni/ker�lni.
	public boolean Accept(Box b, Integer f){
		Integer force = f;
		
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
		
		if(force <= 0) return false;
		
		
		if(i != null) 
			i.Collide(b, force);
		
		if(i == null) {
			this.i = b;
			return true;
		}
		else return false;
	}
	
	//A f�ggv�ny azt modellezi, hogy a mez�re egy j�t�kos pr�b�l l�pni/ker�lni.
	public boolean Accept(Player p, Integer f){
		Integer force = f;
		
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

		
		if(force <= 0) return false;
		
		if(i != null) 
			i.Collide(p, force);
		
		if(i == null) {
			this.i = p;
			return true;
		}
		else return false;
	}

	//Elt�vol�tja a tartalmazott itemet a mez�r�l.
	public void Remove(){ 
		i = null; // teljes m�k�d�s miatt kell
	}
	
	//Be�ll�tja a tagv�ltoz�j�nak azt az itemet ami erre a mez�re ker�l.
	public void SetItem(Item i) {
		this.i = i;
	}
	
	//Mezon talalhato modostito beallitasa.
	public void SetFloortype(Floortype _ft){
		this.ft = _ft;
		this.df.floorChanged(_ft);
	}
	
	//A toString met�dus fel�l�r�sa.
	@Override
	public String toString(){
		String str = "Nb: ";
		for(Direction d : neighbors.keySet()){
			str += neighbors.get(d).id + ":" + d;
		}
		String item = "null";
		if(i != null)
			item = i.toString();
		return id + " " + this.getClass().getName().substring(8) + " " + ft.toString() + " " + item + "\n" + str;
	}
}
