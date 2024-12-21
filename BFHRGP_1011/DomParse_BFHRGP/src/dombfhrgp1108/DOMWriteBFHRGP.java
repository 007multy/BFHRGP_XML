package dombfhrgp1108;

import java.io.File;
import java.io.IOException;

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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMWriteBFHRGP {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException  {
		 
		//Fajl betoltes
		File file = new File("C:\\Users\\gulyi\\Documents\\GitHub\\BFHRGP_XML\\BFHRGP_1011\\DomParse_BFHRGP\\src\\dombfhrgp1108\\orarendBFHRGP.xml");
		
        //Dokumentum olvaso letrehozasa
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();            
        dbf.setValidating(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(new hibakezeles());

        
        //Dokumentum letrehozasa fajlbol
        Document doc = db.parse(file);
        
        writeNode(doc.getDocumentElement(),0);
        
		
        kiirxml(doc);
	}
	
	public static void writeNode(Node node, int indent) {
	    // sor behuzas
	    for (int i = 0; i < indent; i++) {
	        System.out.print("  ");
	    }

	    // elemek es attributumok kiirasa
	    if (node.getNodeType() == Node.ELEMENT_NODE) {
	        Element element = (Element) node;
	        System.out.print("<" + element.getNodeName());

	        NamedNodeMap attributes = element.getAttributes();
	        for (int i = 0; i < attributes.getLength(); i++) {
	            Node attribute = attributes.item(i);
	            System.out.print(" " + attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
	        }
	        System.out.println(">");
	    } else if (node.getNodeType() == Node.TEXT_NODE) {
	        String textContent = node.getTextContent().trim();
	        if (!textContent.isEmpty()) {
	            System.out.println(textContent);
	        }
	    }

	    // gyerek elemek feldolgozasa rekurziv modon
	    NodeList children = node.getChildNodes();
	    for (int i = 0; i < children.getLength(); i++) {
	    	writeNode(children.item(i), indent + 1);
	    }

	    if (node.getNodeType() == Node.ELEMENT_NODE) {
	        for (int i = 0; i < indent; i++) {
	            System.out.print("  ");
	        }
	        System.out.println("</" + node.getNodeName() + ">");
	    }
	}
 
	//Uj fajl letrehozasa
	public static void kiirxml(Document doc) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("orarendBFHRGP1.xml"));
		transformer.transform(source, result);
		System.out.println("\nOrarendBFHRGP1 sikeresen letre lett hozva");
		 
	}
	
	
}

	
