package projlab;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
	
public class Map {
	public List<Field> map = new ArrayList<Field>();
	        
	//Ez a metódus tölti be a kiválasztott pályát
	public void LoadMap(int id){
		int playerID = 0;
		try{
			
			int drawX = 0;
			int drawY = 0;
			
			File inputFile = new File("map.xml");
			
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	     
	        
	        //Megfelelo Map kivalasztasa
	        NodeList mapList = doc.getElementsByTagName("map");	        
	        Element selectedMap = null;
	        for (int temp = 0; temp < mapList.getLength(); temp++) {
	        	 Node nNode = mapList.item(temp);
	        	 Element eElement = (Element) nNode;
	             if(Integer.parseInt(eElement.getAttribute("id")) == id){
	            	 selectedMap = eElement; //vigyazni kell nincs lekezelve, ha ide nem jut el
	             }
	        } 
	        //Mapon belül 0. tag, a Field id-k kinyerése (fields) tag
	        NodeList fieldidlist = selectedMap.getElementsByTagName("fields");
	        Node nNode = fieldidlist.item(0);
	        Element eElement = (Element) nNode;
	        //fields tagen belül Fieldek kinyerése és hozzaadasa Maphoz
	        NodeList fieldids = eElement.getElementsByTagName("Field");
	        for (int temp = 0; temp < fieldids.getLength(); temp++) {
	        	 Node n = fieldids.item(temp);
	        	 Element e = (Element) n;
	        	 Field f = null;
	        	 DField df = null;
	        	 switch(e.getAttribute("type")){
		        	 case "field":
		        		 f = new Field();
		        		 df = new DField("field.png", f);		        		 
		        		 break;
		        	 case "switchhole":
		        		 f = new SwitchHole();
		        		 df = new DSwitchHole("switchhole_opened.png","switchhole_closed.png",   f);
		        		 ((DSwitchHole)df).setSH(f);
		        		 break;
		        	 case "wall":
		        		 f = new Wall();
		        		 f.SetBlocked();
		        		 df = new DField("wall.png", f);
		        		 break;
		        	 case "hole":
		        		 f = new Hole();
		        		 df = new DField("hole.png", f);	
		        		 break;
		        	 case "switch":
		        		 f = new Switch();
		        		 df = new DField("switch.png", f);
		        		 break;
		        	 case "target":
		        		 f = new Target();
		        		 df = new DField("target.png", f);
		        		 break;
		             default:
		            	 System.out.println("BAJ VAN olyan mezõtípus érkezett ami nem letezik\nNe modositsd az xml-t pls.\nSzolj egy hozzaertonek");
		            	 break;
	        	 }
	        	 f.setView(df);
        		 Game.gw.AddDrawable(df);
        		 
        		 
	        	 f.id = Integer.parseInt(e.getAttribute("id"));
	        	 map.add(f);
	        }
	        //mapon belül fieldek részletes informacioi
	        NodeList fieldList = selectedMap.getElementsByTagName("field");
	        for (int temp = 0; temp < fieldList.getLength(); temp++) {
	        	 Node nn = fieldList.item(temp);
	        	 Element field = (Element) nn;
	        	 
	        	 String id_0 = field.getElementsByTagName("id").item(0).getTextContent();
	        	 //find field in map by id
	        	 Field f = findFieldById(id_0);
	        	 
	        	 
	        	 
	        	 if(field.getElementsByTagName("type").item(0).getTextContent().equals("Switch")){
	        		 Switch asd = (Switch)f;
	        		 asd.SetSwitchHole(findFieldById(field.getElementsByTagName("sh").item(0).getTextContent()));
	        	 }
	        	 
	        	 //item
	        	 AbstractDrawable ad_item = null;
	        	 switch(field.getElementsByTagName("item").item(0).getTextContent()){
		        	 case "Player":
		        		 Player p = new Player();
		        		 Game.players.add(p);
		        		 f.i = p;
		        		 p.field = f;
		        		 p.setID(playerID++);
		        		 ad_item = new DPlayer(p);
		        		 p.SetView(ad_item);
		        		 break;
		        	 case "Box":
		        		 Box b = new Box();
		        		 Game.activeBoxes.add(b);
		        		 f.i = b;
		        		 b.field = f;
		        		 
		        		 ad_item = new DBox(b);
		        		 b.SetView(ad_item);
		        		 break;
		        	 case "null":
		        		 break;
	        	 }
	        	 
	        	 //floortype
	        	 switch(field.getElementsByTagName("floortype").item(0).getTextContent()){
		        	 case "regular":
		        		 f.ft = Floortype.Regular;
		        		 break;
		        	 case "oil":
		        		 f.ft = Floortype.Oil;
		        		 break;
		        	 case "honey":
		        		 f.ft = Floortype.Honey;
		        		 break;
	        	 }
	        	 
	        	 //Switchhole
	        	 if(f instanceof Switch){
	        		 String shID = field.getElementsByTagName("sh").item(0).getTextContent();
	        		 
	        		 ((Switch)f).SetSwitchHole(findFieldById(shID));
	        	 }
	        	 
	        	 //neighbors...
	        	 Node neighboorsTag = field.getElementsByTagName("neighboors").item(0);
	        	 Element neighboors_element = (Element) neighboorsTag;
	        	 NodeList neighboors = neighboors_element.getElementsByTagName("neighboor");
	        	 boolean hasRightNeighbor = false;
	        	 for (int i = 0; i < neighboors.getLength(); i++) {
		        	 Node nb = neighboors.item(i);
		        	 Element neighbor = (Element) nb;
		        	 
		        	 
		        	 
		        	 Field neighborF = findFieldById(neighbor.getAttribute("id"));
		        	 switch(neighbor.getAttribute("dir")){
			        	 case "up":
			        		 f.SetNeighbor(Direction.Up, neighborF);
			        		 neighborF.SetNeighbor(Direction.Down, f);
		        			 break;
			        	 case "down":
			        		 f.SetNeighbor(Direction.Down, neighborF);
			        		 neighborF.SetNeighbor(Direction.Up, f);
		        			 break;
			        	 case "left":
			        		 f.SetNeighbor(Direction.Left, neighborF);
			        		 neighborF.SetNeighbor(Direction.Right, f);
		        			 break;
			        	 case "right":
			        		 hasRightNeighbor = true;
			        		 f.SetNeighbor(Direction.Right, neighborF);
			        		 neighborF.SetNeighbor(Direction.Left, f);
		        			 break;
		        	 }
	        	 }

	        	 f.getView().posInPixelX = drawX;
	        	 f.getView().posInPixelY = drawY;
	        	 
	        	 if(ad_item != null){
	        		 Game.gw.AddDrawable(ad_item);
	        		 ad_item.posInPixelX = drawX;
	        		 ad_item.posInPixelY = drawY;
	        	 }
	        	 if(hasRightNeighbor){
	        		 drawX += f.getView().sizeInPixelX;
	        	 }
	        	 else{
	        		 drawX = 0;
	        		 drawY += f.getView().sizeInPixelY;
	        	 }
	        }
		}
		
		//kivételek
		catch(ParserConfigurationException pe){ System.out.println(pe);	}
		catch(SAXException se){ System.out.println(se);	}
		catch(IOException ie){	System.out.println(ie);}
		
		
    }
	
	//Mezo megkerese az id alapjan.
	private Field findFieldById(String id_0) {
		for(Field f : map){
			if(f.id == Integer.parseInt(id_0)){
				return f;
			}
		}
		return null;
	}
}

	           