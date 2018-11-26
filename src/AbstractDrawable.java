package projlab;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//Minden játékbeli kirajzolható elem grafikus 
//megjelenítéséért felelõs osztályok õsosztálya
public abstract class AbstractDrawable implements Comparable<AbstractDrawable>{
	protected int z = 0;
	protected BufferedImage i;
	
	public int posInPixelX = 0;
	public int posInPixelY = 0;
	public int sizeInPixelX = 100;
	public int sizeInPixelY = 100;
	
	abstract void Draw(Graphics g);
	
	//Kép betöltése fájlból
	public void LoadImage(String str){
		try {
		    i = ImageIO.read(new File(str));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	@Override
	public int compareTo(AbstractDrawable other) {
	    return Integer.compare(this.z, other.z);
	}

}
