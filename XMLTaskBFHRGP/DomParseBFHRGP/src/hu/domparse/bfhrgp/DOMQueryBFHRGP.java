package hu.domparse.bfhrgp;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryBFHRGP {

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
            
            
            //1. lekerdezes: 2-es azonositoju termek kiirasa
            String TermekID = "2";
            NodeList termekList = doc.getElementsByTagName("termek");
            for (int i = 0; i < termekList.getLength(); i++) {
                Element termek = (Element) termekList.item(i);
                String termekIdAttribute = termek.getAttribute("TermekID");
                if (termekIdAttribute.equals(TermekID)) {
                    String nev = termek.getElementsByTagName("nev").item(0).getTextContent();
                    String tipus = termek.getElementsByTagName("tipus").item(0).getTextContent();
                    String ar = termek.getElementsByTagName("egyseg_ar").item(0).getTextContent();
                    System.out.println("1. lekérdezés: 2-es azonositoju termek kiirasa");
                    System.out.println("A " + TermekID + " ID-jú termékneve: " + nev);
                    System.out.println("Tipusa: " + tipus);
                    System.out.println("Ára: " + ar);
                    break;
                }
            }
            
            //2. lekerdezes: legdragabb termek kiirasa
            NodeList legdragabbtermekList = doc.getElementsByTagName("termek");

            String legdragabbNev = "";
            String legdragabbTipus = "";
            int legdragabbAr = 0;

            for (int i = 0; i < legdragabbtermekList.getLength(); i++) {
                Element termek = (Element) legdragabbtermekList.item(i);
                String nev = termek.getElementsByTagName("nev").item(0).getTextContent().trim();
                String tipus = termek.getElementsByTagName("tipus").item(0).getTextContent().trim();
                int ar = Integer.parseInt(termek.getElementsByTagName("egyseg_ar").item(0).getTextContent().trim());

                // i-edik termek ara dragabb-e mint a legdragabb
                if (ar > legdragabbAr) {
                    legdragabbAr = ar;
                    legdragabbNev = nev;
                    legdragabbTipus = tipus;
                }
            }
            
            System.out.println("\n2. lekérdezés: legdragabb termek kiirasa");
            System.out.println("Legdrágább termék:");
            System.out.println("  Név: " + legdragabbNev);
            System.out.println("  Típus: " + legdragabbTipus);
            System.out.println("  Ár: " + legdragabbAr);
            
            
            
            
         // 3 lekerdezes: 1200 ft feletti rendelest leado vevok kiirasa
            NodeList vevoList = doc.getElementsByTagName("vevo");
            NodeList rendelesList = doc.getElementsByTagName("rendeles");

            for (int i = 0; i < vevoList.getLength(); i++) {
                Element vevo = (Element) vevoList.item(i);

                String vevoNev = vevo.getElementsByTagName("nev").item(0).getTextContent().trim();
                String vevoID = vevo.getAttribute("VevoID");

                // ellenorzi hogy van-e 1200 Ft fölötti rendelése
                boolean vanrendeles = false;

                for (int j = 0; j < rendelesList.getLength(); j++) {
                    Element rendeles = (Element) rendelesList.item(j);
                    String rendelesVevoID = rendeles.getElementsByTagName("VevoID").item(0).getTextContent().trim();

                    if (rendelesVevoID.equals(vevoID)) {
                        String ar = rendeles.getElementsByTagName("fizetendo_osszeg").item(0).getTextContent().trim();
                        if (Integer.parseInt(ar) > 1200) {
                        	vanrendeles = true;
                            break;
                        }
                    }
                }

                // Csak azon vevok kerulnek kiirasra, akiknek 1200 Ft folotti a rendelesuk
                if (vanrendeles) {
                	System.out.println("\n3. lekérdezés: 1200 ft feletti rendelest leado vevok kiirasa");
                    System.out.println("Vevő neve: " + vevoNev);

                    for (int j = 0; j < rendelesList.getLength(); j++) {
                        Element rendeles = (Element) rendelesList.item(j);
                        String rendelesVevoID = rendeles.getElementsByTagName("VevoID").item(0).getTextContent().trim();

                        if (rendelesVevoID.equals(vevoID)) {
                            String termekID = rendeles.getElementsByTagName("TermekID").item(0).getTextContent().trim();
                            String mennyiseg = rendeles.getElementsByTagName("mennyiseg").item(0).getTextContent().trim();
                            String fizetendo = rendeles.getElementsByTagName("fizetendo_osszeg").item(0).getTextContent().trim();   
                            System.out.println("  Rendelés:");
                            System.out.println("    Termék ID: " + termekID);
                            System.out.println("    Mennyiség: " + mennyiseg);
                            System.out.println("    Fizetendő összeg: " + fizetendo);
                        }
                    }
                }
            }
        }
        
        
        catch (ParserConfigurationException pce) {pce.printStackTrace();}
        catch(SAXException se) { }
        catch(IOException ioe) { }
	}
}