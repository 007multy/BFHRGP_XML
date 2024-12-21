package hu.domparse.bfhrgp;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyBFHRGP {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException  {
		 
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
		
		modositVevo(doc);
	}
 
	//Uj fajl letrehozasa a modositott adatokkal
	public static void modositottxml(Document doc) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("modositott_XMLBFHRGP.xml"));
		transformer.transform(source, result);
	}
	
	
	private static void modositVevo(Document doc) throws TransformerException {
		//Lekerjuk a vevo elemben tarolt adatokat
			NodeList nodeList = doc.getElementsByTagName("vevo");
	         for (int j = 0; j < nodeList.getLength(); j++)
	         {
	             Node node = nodeList.item(j);
	             System.out.println("\n" + node.getNodeName() + " " + (j + 1));
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
	         
	       //Vevok szamanak lekerese
	 		System.out.println("\nUdvozoljuk a vevoi ugyfelszolgalaton!\nAdja meg melyik vevo adatait szeretne modositani!");
			//Bekerjuk a vevo id-t aminek az adatait modositjuk
			Scanner sc = new Scanner(System.in);
			System.out.print("\nid:");
			String id = sc.nextLine();
			// Bekerjuk az uj adatokat
			System.out.print("Nev: ");
			String nev = sc.nextLine();
			System.out.print("Iranyitoszam: ");
			String iranyitoszam = sc.nextLine();
			System.out.print("Varos: ");
			String varos = sc.nextLine();
			System.out.print("Utca: ");
			String utca = sc.nextLine();
			System.out.print("Hazszamm: ");
			String hazszam = sc.nextLine();
			System.out.print("Telefonszam: ");
			String telefonszam = sc.nextLine();
			sc.close();
			//Lekerdezzek az elemeket, majd setTextContent-el modositjuk
			NodeList elemLista = doc.getElementsByTagName("vevo");
			for (int i = 0; i < elemLista.getLength(); i++) {
			Node nNode = elemLista.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				String sid = element.getAttribute("VevoID");
					if (sid.equals(id)) {
						Node node1 = element.getElementsByTagName("nev").item(0);
						node1.setTextContent(nev);
						Node node2 = element.getElementsByTagName("iranyitoszam").item(0);
						node2.setTextContent(iranyitoszam);
						Node node3 = element.getElementsByTagName("varos").item(0);
						node3.setTextContent(varos);
						Node node4 = element.getElementsByTagName("utca").item(0);
						node4.setTextContent(utca);
						Node node5 = element.getElementsByTagName("hazszam").item(0);
						node5.setTextContent(hazszam);
						Node node6 = element.getElementsByTagName("telefonszam").item(0);
						node6.setTextContent(telefonszam);
						System.out.println("Sikeres modositas");
					}
				}
			}
			modositottxml(doc); //Letrehozzuk a modositott_XMLBFHRGP-t
	}	
}
	
