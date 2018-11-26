package projlab;

//A Switch et megval�s�t� oszt�ly a SwitchHole t nyitja ki ha egy doboz kerl r�.
public class Switch extends Field {
	//SwitchHole amit a Switch ir�ny�t
	private SwitchHole sh; 
	
	//A mez�re egy dobozt tolnak, kinytja azt a SwitchHolet ami hozz� van 
	//rendelve ezzel a vielked�s�t egy lyukkal teszi azonoss�.
	public boolean Accept(Box b, Integer f){
		//Toloero elmentese.
		Integer force = f;
		
		//A mezon levo modosito alapjan a az ero valtoztatasa.		
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

		//Ha az ero nulla ala csokkent a tolas sikertelen.
		if(force <= 0) return false;
		
		//Ha van Item a mezon ahova masik tolodni akar akkor a ketto Item utkozik.
		if(i != null) 
			i.Collide(b, force);

		//Ha a mezo ures ahova tolodni probal egy masik akkor a lepesben nincs fennakadas.
		if(i == null) {
			this.i = b;

			if(sh != null)
				sh.Open();
			
			if(sh == null){
				System.out.println("szar");
			}
			
			
			
			return true;
		}
		else return false;
		
	}
	
	//A SwitchHole ra egy Player ker�l ami azt eredm�nyezi hogy a SwitchHole ami hozz� van rendelve az becsuk�dik.
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

		//Ha az ero nulla ala csokken 
		if(force <= 0) return false;
		
		//Ha a mezon van Item akkor a ket Item utkozik.
		if(i != null) 
			i.Collide(p, force);
		
		//Ha nincsen Item a mezon akkor a lepes nem utkozik akadalyba.
		if(i == null) {
			this.i = p;

			if(sh != null)
				sh.Close();
			
			return true;
		}
		else return false;
	}
	
	public void SetSwitchHole(Field field){
		this.sh = (SwitchHole)field;
	}

	
}
