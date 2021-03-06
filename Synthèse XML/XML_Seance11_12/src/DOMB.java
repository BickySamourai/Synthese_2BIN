import java.io.FileReader;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.*;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DOMB {
	
	public static void main(String[] args) throws Exception{
		XPathFactory factory = XPathFactory.newInstance();
		javax.xml.xpath.XPath xPath = factory.newXPath();
		NodeList lists = (NodeList) xPath.evaluate("//list", new InputSource(new FileReader("library.xml")), XPathConstants.NODESET);
		for(int i =0; i < lists.getLength(); i++){
			Element list = (Element) lists.item(i);
			System.out.println(list.getAttribute("name"));
			NodeList tracks = list.getElementsByTagName("track_ID");
			for(int j = 0; j < tracks.getLength(); j++){
				Node artist = (Node) xPath.evaluate("//song[track_ID = " + ((Element) tracks.item(j)).getTextContent() + "]/artist", 
						new InputSource(new FileReader("library.xml")), XPathConstants.NODE);
				Node song = (Node) xPath.evaluate("//song[track_ID = " + ((Element) tracks.item(j)).getTextContent() + "]/name", 
						new InputSource(new FileReader("library.xml")), XPathConstants.NODE);
				System.out.println(artist.getTextContent() + " - " + song.getTextContent());
			}
			System.out.println("----------------------------------");
		}
	}
}

