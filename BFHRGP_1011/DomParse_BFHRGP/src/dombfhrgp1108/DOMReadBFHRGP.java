package dombfhrgp1108;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMReadBFHRGP {

	public static void main(String[] args){
	
		
        try {
        	//Fajl betoltes
        	File file = new File("C:\\Users\\gulyi\\eclipse-workspace\\DomParseBFHRGP\\src\\dombfhrgp1108\\orarendBFHRGP.xml");
            
            //Dokumentum olvaso letrehozasa
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();            
            dbf.setValidating(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler(new hibakezeles());


            
            //Dokumentum letrehozasa fajlbol
            Document doc = db.parse(file);
            
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("ora");         
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                System.out.println("\n" + node.getNodeName() + " " + (i + 1));
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element Elem = (Element) node;
                    System.out.println("Ora tipus: "+ Elem.getAttribute("tipus"));
                    System.out.println("Targy: "+ Elem.getElementsByTagName("targy").item(0).getTextContent());
                    System.out.println("Idopont: " + "2024." + Elem.getElementsByTagName("nap").item(0).getTextContent());
                    System.out.println("Kezdete: " + Elem.getElementsByTagName("tol").item(0).getTextContent());
                    System.out.println("Vege: " + Elem.getElementsByTagName("ig").item(0).getTextContent());
                    System.out.println("Helyszin: "+ Elem.getElementsByTagName("helyszin").item(0).getTextContent());
                    System.out.println("Oktato: "+ Elem.getElementsByTagName("oktato").item(0).getTextContent());
                    System.out.println("Szak: "+ Elem.getElementsByTagName("szak").item(0).getTextContent());
         
                }
            }
        }
        catch (ParserConfigurationException pce) {pce.printStackTrace();}
        catch(SAXException se) { }
        catch(IOException ioe) { }
	}
}
