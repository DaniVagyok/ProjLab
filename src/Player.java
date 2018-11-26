package projlab;

import java.util.Scanner;

//J�t�kos oszt�ly megval�s�t�sa.
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
	
	//A felhaszn�l� inputja alpj�n bek�vetkezett mozg�s
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
			System.out.println("�res");
		}
	}
	
	//A Playernek egy Item tol�dik neki
	public void Collide(Item i, Integer f){
		temporaryForce = f - 0; //0 hiszen a player nem cs�kkent a tol�si er�n ha �t tolj�k
								//csak a field amin �ll
		
		if(temporaryForce <= 0){
			Logger.Pop();	
			return;
		}
		i.HitBy(this);
	}
	
	//A Playert egy m�sik Player pr�b�lja meg eltolni.
	public void HitBy(Player p){
	}
	
	//A Playert egy doboz pr�b�lja meg eltolni.
	public void HitBy(Box b){		
		b.Push(this.tempDir); //�llapot v�ltoz�val lesz megoldva az ir�ny tov�bbad�sa.
	}
	
	//Ez a f�ggv�ny h�v�dik a j�t�kos meghal�sakor.
	public void Die(){
		Game.DelActivePlayer(this);
	}
	
	//A j�t�kost megtolt�k �s megpr�b�l tov�bb tolni.
	public void Push(Direction d){
	
		this.tempDir = d;
		Field neigh = field.GetNeighbor(d);
		//A d iranyban levo szomszed meghataroza annak amezonek amin all a jatekos.
		if(!neigh.Accept(this, temporaryForce))
			Die();
		
		field.Remove();
		this.field = neigh;
	}
	//A mez�n l�v� talajt�pus be�ll�t�sa.
	public void Pour(Floortype e){
		this.field.SetFloortype(e);
	}
	
}
