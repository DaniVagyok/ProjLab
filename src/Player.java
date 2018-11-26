package projlab;

import java.util.Scanner;

//Játékos osztály megvalósítása.
public class Player extends Item {
	private	AbstractDrawable playerView;
	
	//Jelenlegi erot tarolo valtozo.
	Integer temporaryForce;
	private Integer ID;
	//Kezdo ereje a jatekosnak.
	Integer force = 5;
	
	
	public void setID(int i){
		this.ID = i;
	}
	public Integer getID(){
		return this.ID;
	}
	public void SetView(AbstractDrawable dp){
		this.playerView = dp;
	}
	public AbstractDrawable getView(){
		return this.playerView;
	}
	
	//A felhasználó inputja alpján bekövetkezett mozgás
	public void Move(Direction d){
		this.tempDir = d;
		
		//A jelenlegi mezonek a d iranyban levo szomszedjanak meghatarozasa.
		Field neigh = field.GetNeighbor(d);		
		if(neigh != null){
			if(neigh.Accept(this, force)){
				//Jatekos jelenlegi mezojenek a torlese.
				field.Remove();
				//Uj mezo beallitasa a jatekosnak.
				this.field = neigh;
				return;
			}
		}
		else{
			System.out.println("üres");
		}
	}
	
	//A Playernek egy Item tolódik neki
	public void Collide(Item i, Integer f){
		temporaryForce = f - 0; //0 hiszen a player nem csökkent a tolási erõn ha õt tolják
								//csak a field amin áll
		
		if(temporaryForce <= 0){
			Logger.Pop();	
			return;
		}
		i.HitBy(this);
	}
	
	//A Playert egy másik Player próbálja meg eltolni.
	public void HitBy(Player p){
	}
	
	//A Playert egy doboz próbálja meg eltolni.
	public void HitBy(Box b){		
		b.Push(this.tempDir); //állapot változóval lesz megoldva az irány továbbadása.
	}
	
	//Ez a függvény hívódik a játékos meghalásakor.
	public void Die(){
		Game.DelActivePlayer(this);
	}
	
	//A játékost megtolták és megpróbál tovább tolni.
	public void Push(Direction d){
	
		this.tempDir = d;
		Field neigh = field.GetNeighbor(d);
		//A d iranyban levo szomszed meghataroza annak amezonek amin all a jatekos.
		if(!neigh.Accept(this, temporaryForce))
			Die();
		
		field.Remove();
		this.field = neigh;
	}
	//A mezõn lévõ talajtípus beállítása.
	public void Pour(Floortype e){
		this.field.SetFloortype(e);
	}
	
}
