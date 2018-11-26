package projlab;

import java.util.Scanner;
//A dobozt reprezent�l� oszt�ly amit lesz�rmazik az Item b�l.
public class Box extends Item {
	//A doboz �tk�z�se egy t�rgyal
	private	AbstractDrawable boxView;
	public Integer temporaryForce;
	
	//A dobozt utoljara tolo jatekos a pontok nyilvantartasanal  jatszik szerepet
	public Player pusher;
	
	Integer friction = 1; //fix
	public void SetView(AbstractDrawable dp){
		this.boxView = dp;
	}
	public AbstractDrawable getView(){
		return this.boxView;
	}
	
	//A fuggveny akkor hivodik amikor valamelyik Itemet olyan 
	//mezore probalnak tolni amin egy doboz all.
	//A Field hivja meg a Bbox on
	public void Collide(Item i, Integer f){
		temporaryForce = f - friction;
		
		if(temporaryForce <= 0){
			return;
		}
		i.HitBy(this);	
	}
	
	//A dobozt egy j�t�kos pr�b�lja eltolni.
	public void HitBy(Player p){
		p.Push(this.tempDir);
	}
	
	//A dobozt egy doboz pr�b�lja eltolni
	public void HitBy(Box b){		
		b.Push(this.tempDir);
	}
	
	//A doboz meghal
	public void Die(){
		Game.DelActiveBox(this);
	}
	
	//A dobozt megtolt�k �s tov�bb pr�b�l l�pni.
	public void Push(Direction d){
		this.tempDir = d;
		Field neigh = field.GetNeighbor(d);
		if(neigh.Accept(this, temporaryForce)) {
			field.Remove();
			this.field = neigh;
		}		
	}
	
	//Annak a vizsgalata, hogy a doboz olyan helyzetbe kerult e ahonnan nem lehet tovabb tolni
	//tehat ki kell venni az aktiv dobozok kozul. Es ez a fuggveenyhivo fele visszajelzii a fuggveny.
	public boolean CheckBoxBlocked(){
		if((field.GetNeighbor(Direction.Up).isBlocked && field.GetNeighbor(Direction.Right).isBlocked) ||
				(field.GetNeighbor(Direction.Right).isBlocked && field.GetNeighbor(Direction.Down).isBlocked) ||
				(field.GetNeighbor(Direction.Down).isBlocked && field.GetNeighbor(Direction.Left).isBlocked) ||
				(field.GetNeighbor(Direction.Left).isBlocked && field.GetNeighbor(Direction.Up).isBlocked)){
			System.out.println("BLOCKED");			
			return true;
		}
		return false;
	}
	
}
