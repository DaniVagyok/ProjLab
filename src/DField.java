package projlab;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//A mezõk megjelenítésért felelõs osztály
public class DField extends AbstractDrawable{
	protected Field f;
	private int X = 50;
	private int Y = 50;
	protected BufferedImage dfloortype = null;
	
	public DField(String str, Field f){
		this.LoadImage(str);
		this.f = f;
	}
	
	//Itt számolódik ki a kép helye és itt rajzolódik ki a kép
	@Override
	void Draw(Graphics g) {
		g.drawImage(i, posInPixelX, posInPixelY, posInPixelX + sizeInPixelX, posInPixelY + sizeInPixelY, 0, 0, i.getWidth(), i.getHeight(), null);
        
		if(dfloortype != null){
			g.drawImage(dfloortype, posInPixelX, posInPixelY, posInPixelX + sizeInPixelX, posInPixelY + sizeInPixelY, 0, 0, i.getWidth(), i.getHeight(), null);
		}
	}
	
	//A mezõre rajzolt talajtípus képének kirajzolásáért és annak
	//megváltozásáért felelõs függvény
	void floorChanged(Floortype _ft){
		String ft_str = "";
		switch(_ft){
			case Honey:
				ft_str = "honey.png";
				break;
			case Oil:
				ft_str = "oil.png";
				break;
		}
		try {
		    i = ImageIO.read(new File(ft_str));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
