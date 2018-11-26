package projlab;

//A Switch et megvalósító osztály a SwitchHole t nyitja ki ha egy doboz kerl rá.
public class Switch extends Field {
	//SwitchHole amit a Switch irányít
	private SwitchHole sh; 
	
	//A mezõre egy dobozt tolnak, kinytja azt a SwitchHolet ami hozzá van 
	//rendelve ezzel a vielkedését egy lyukkal teszi azonossá.
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
	
	//A SwitchHole ra egy Player kerül ami azt eredményezi hogy a SwitchHole ami hozzá van rendelve az becsukódik.
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
