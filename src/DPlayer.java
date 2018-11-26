package projlab;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DPlayer extends AbstractDrawable {
	Player p;
	
	public DPlayer(Player p){
		this.z = 1;
		this.LoadImage("player.png");
		this.p = p;
	}
	
	//A játékos helyének kiszámolásáért és képének kirajzolásáért felelõs metódus
	@Override
	void Draw(Graphics g) {
		Field t = p.field;
		DField dt = t.getView();
		
		this.posInPixelX = dt.posInPixelX;
		this.posInPixelY = dt.posInPixelY;
		
		g.drawImage(i, posInPixelX, posInPixelY, posInPixelX + sizeInPixelX, posInPixelY + sizeInPixelY, 0, 0, i.getWidth(), i.getHeight(), null);
		
		Font font = new Font("Verdana", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString(p.getID().toString(), posInPixelX + sizeInPixelX / 2 - 10, posInPixelY + sizeInPixelY / 2);
	}
}
