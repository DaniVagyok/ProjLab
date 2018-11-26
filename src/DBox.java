package projlab;

import java.awt.Graphics;

//A doboz grafikus megjelenését megvalósító osztály
public class DBox extends AbstractDrawable {
	Box b;
	
	public DBox(Box b){
		this.b = b;
		this.z = 1;
		this.LoadImage("box.png");
	}
	
	//Itt számolódik ki a helye a képnek és rajzolódik ki
	@Override
	void Draw(Graphics g) {

		Field t = b.field;
		DField dt = t.getView();
		
		this.posInPixelX = dt.posInPixelX;
		this.posInPixelY = dt.posInPixelY;
		
		
		
		// mekkora(kep melyik teruletére) teruletre legyen kirajzolva  		|| eredeti kep meretebol mennyi legyen kirajzolva
        //g.drawImage(i, 200, 200, sizeInPixelX + 200, sizeInPixelY + 200, 0, 0, i.getWidth(), i.getHeight(), null);
		g.drawImage(i, posInPixelX, posInPixelY, posInPixelX + sizeInPixelX, posInPixelY + sizeInPixelY, 0, 0, i.getWidth(), i.getHeight(), null);
	}

}
