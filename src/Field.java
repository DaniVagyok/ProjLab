package projlab;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
//A sima mezõt reprezetnáló osztály. Tartalmazhat egy itemet és a szomszádait egy Map ban.
public class Field {
	//Ezt az Itemet tartalmazza a mezõ.
	public int id;
	protected Item i;
	protected DField df;
	
	//A mezon levo modositonak a tipusa
	protected Floortype ft = Floortype.Regular;
	
	//Tagvaltozo ami azt jelzi, hogy a mezon van e doboz, ugy hogy mar nem lehet letolni rola.
	protected boolean isBlocked = false;
	
	//A megfelelõ iránybn lévõ további mezõk.
	protected Map<Direction, Field> neighbors = new TreeMap<Direction, Field>();
	
	//A mezore egy doboz ragad beallitaja a booleant true-ra.
	public void SetBlocked(){
		isBlocked = true;
	}
	
	//Visszater azzal hogy a mezon van e mozdithatatlan doboz.
	public boolean GetBlocked(){
		return isBlocked;
	}
	//A d irányban lévõ mezõ visszaadása.
	public Field GetNeighbor(Direction d){
		return neighbors.get(d);		
	}
	
	//A mezõ szomszádjának beállítása,
	public void SetNeighbor(Direction d, Field f){
		neighbors.put(d, f);
	}
	
	public void setView(DField df){
		this.df = df;
	}
	public DField getView(){ return this.df; }
	//A függvény azt modellezi, hogy a mezõre egy doboz próbál lépni/kerülni.
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
	
	//A függvény azt modellezi, hogy a mezõre egy játékos próbál lépni/kerülni.
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

	//Eltávolítja a tartalmazott itemet a mezõrõl.
	public void Remove(){ 
		i = null; // teljes mûködés miatt kell
	}
	
	//Beállítja a tagváltozójának azt az itemet ami erre a mezõre kerül.
	public void SetItem(Item i) {
		this.i = i;
	}
	
	//Mezon talalhato modostito beallitasa.
	public void SetFloortype(Floortype _ft){
		this.ft = _ft;
		this.df.floorChanged(_ft);
	}
	
	//A toString metódus felülírása.
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
