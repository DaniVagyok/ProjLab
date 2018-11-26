package projlab;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DSwitchHole extends DField{
	protected SwitchHole sh;
	
	private int X = 50;
	private int Y = 50;
	
	BufferedImage second; //closed image first
	
	public void setSH(Field _sh){
		this.sh = (SwitchHole)_sh;
	}
	public DSwitchHole(String str, String str2, Field f){
		super(str, f);
		this.LoadImage(str);
		this.sh = (SwitchHole)f;
		
		/*
		 * DSwitchHole-nak 2 kepe van allapottol fuggoen
		 * str - nyitott
		 * str2 - zart
		 * */
		try {
			second = ImageIO.read(new File(str2));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
	}
	
	//Itt számolódik ki a kép helye és itt rajzolódik ki a kép
	//Ennek az objektumnak két képe van
	@Override
	void Draw(Graphics g) {
		BufferedImage drawThis = null;
		if(sh.getState()) //nyitva == true?
			drawThis = i;
		else 
			drawThis = second;
		
		g.drawImage(drawThis, posInPixelX, posInPixelY, posInPixelX + sizeInPixelX, posInPixelY + sizeInPixelY, 0, 0, i.getWidth(), i.getHeight(), null);
        
	}
}
