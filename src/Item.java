package projlab;

//A Player és a Box absztakt õsosztálya
public abstract class Item {
	//Mezo amin az Item tartozkodik.
	protected Field field;
	protected Direction tempDir = null;
	
	
	//A Tárgynak beállít egy mezõt.
	public void SetField(Field f){
		this.field = f;
	}
	
	//Ha egy Itemet olyan helyre tolnak ahol van mar egy masik akkor a ket Item utkozik.
	//Ez a metodus szolgal ennek a megvalositasara.
	public abstract void Collide(Item i, Integer f);
	
	//Amikor az Itemnek nekitolodik egy masik akkor aki eredetileg a mezon allt 
	//Item hivja meg ezt a fuggvenyt azon aki probalja ot tolni. 
	public abstract void HitBy(Player p);

	public abstract void HitBy(Box b);
	
	//Az item meghal, lyukba esik vagy osszeroppantjak.	
	public abstract void Die();
}
