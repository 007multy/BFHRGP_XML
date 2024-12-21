package hu.domparse.bfhrgp;

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
            File file = new File("XMLBFHRGP.xml");
            
            //Dokumentum olvaso letrehozasa
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();            
            dbf.setValidating(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler(new hibakezeles());

            
            //Dokumentum letrehozasa fajlbol
            Document doc = db.parse(file);
            
            doc.getDocumentElement().normalize();
            
            //Gyokerelem lekerdezes
            System.out.println("Gyokerelem: "+ doc.getDocumentElement().getNodeName());
            
            //Beolvas: Cukraszda elem attributumai es alelemei
            NodeList nodeList = doc.getElementsByTagName("cukraszda_adatok");         
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                System.out.println("\n" + node.getNodeName() + " " + (i + 1));
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element Elem = (Element) node;
                    System.out.println("CukraszdaID: "+ Elem.getAttribute("CukraszdaID"));
                    System.out.println("Nev: "+ Elem.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Nyitvatartas: "+ Elem.getElementsByTagName("nyitvatartas").item(0).getTextContent());
                    System.out.println("Weboldal: "+ Elem.getElementsByTagName("weboldal").item(0).getTextContent());
                    System.out.println("Telefonszam: "+ Elem.getElementsByTagName("telefonszam").item(0).getTextContent());
         
                }
            }
            
            //Beolvas: Futar elem attributumai es alelemei
            nodeList = doc.getElementsByTagName("futar");
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                System.out.println("\n" + node.getNodeName() + " " + (i + 1));
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element Elem = (Element) node;
                    System.out.println("FutarID: "+ Elem.getAttribute("FutarID"));
                    System.out.println("Nev: "+ Elem.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Telefonszam: "+ Elem.getElementsByTagName("telefonszam").item(0).getTextContent());
                    System.out.println("CukraszdaID: "+ Elem.getElementsByTagName("CukraszdaID").item(0).getTextContent());
                }
            }
            
            //Beolvas: Termek elem attributumai es alelemei
            nodeList = doc.getElementsByTagName("termek");
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                System.out.println("\n" + node.getNodeName() + " " + (i + 1));
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element Elem = (Element) node;
                    System.out.println("TermekID: "+ Elem.getAttribute("TermekID"));
                    System.out.println("Nev: "+ Elem.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Tipus: "+ Elem.getElementsByTagName("tipus").item(0).getTextContent());
                    System.out.println("Egyseg ar: "+ Elem.getElementsByTagName("egyseg_ar").item(0).getTextContent());
                    System.out.println("CukraszdaID: "+ Elem.getElementsByTagName("CukraszdaID").item(0).getTextContent());
                    
                }
            }
                        
            //Beolvas: Vevo elem attributumai es alelemei
            nodeList = doc.getElementsByTagName("vevo");
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                System.out.println("\n" + node.getNodeName() + " " + (i + 1));
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element Elem = (Element) node;
                    System.out.println("VevoID: "+ Elem.getAttribute("VevoID"));
                    System.out.println("Nev: "+ Elem.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Iranyitoszam: "+ Elem.getElementsByTagName("iranyitoszam").item(0).getTextContent());
                    System.out.println("Varos: "+ Elem.getElementsByTagName("varos").item(0).getTextContent());
                    System.out.println("Utca: "+ Elem.getElementsByTagName("utca").item(0).getTextContent());
                    System.out.println("Hazszam: "+ Elem.getElementsByTagName("hazszam").item(0).getTextContent());
                    System.out.println("Telefonszam: "+ Elem.getElementsByTagName("telefonszam").item(0).getTextContent());
                }
            }
            
            //Beolvas: Rendeles elem attributumai es alelemei
            nodeList = doc.getElementsByTagName("rendeles");
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                System.out.println("\n" + node.getNodeName() + " " + (i + 1));
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element Elem = (Element) node;
                    System.out.println("RendelesID: "+ Elem.getAttribute("RendelesID"));
                    System.out.println("Mennyiseg: "+ Elem.getElementsByTagName("mennyiseg").item(0).getTextContent());
                    System.out.println("Fizetendo osszeg: "+ Elem.getElementsByTagName("fizetendo_osszeg").item(0).getTextContent());
                    System.out.println("TermekID: "+ Elem.getElementsByTagName("TermekID").item(0).getTextContent());
                    System.out.println("VevoID: "+ Elem.getElementsByTagName("VevoID").item(0).getTextContent());
                }
            }
            
            //Beolvas: Kartya elem attributumai es alelemei
            nodeList = doc.getElementsByTagName("kartya");
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                System.out.println("\n" + node.getNodeName() + " " + (i + 1));
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element Elem = (Element) node;
                    System.out.println("Kartyaszam: "+ Elem.getAttribute("Kartyaszam"));
                    System.out.println("Tipus: "+ Elem.getElementsByTagName("tipus").item(0).getTextContent());
                    System.out.println("Lejarati datum: "+ Elem.getElementsByTagName("lejarati_datum").item(0).getTextContent());
                    System.out.println("Bank: "+ Elem.getElementsByTagName("bank").item(0).getTextContent());
                    System.out.println("VevoID: "+ Elem.getElementsByTagName("VevoID").item(0).getTextContent());
                 
                }
            }
        }
        catch (ParserConfigurationException pce) {pce.printStackTrace();}
        catch(SAXException se) { }
        catch(IOException ioe) { }
	}
}



