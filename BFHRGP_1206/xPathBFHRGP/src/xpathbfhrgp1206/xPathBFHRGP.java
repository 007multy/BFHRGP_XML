package xpathbfhrgp1206;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xPathBFHRGP {
	public static void main(String[] args) throws Exception {
		
		File file = new File("C:\\Users\\gulyi\\Documents\\GitHub\\BFHRGP_XML\\BFHRGP_1206\\studentBFHRGP.xml");
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        doc.getDocumentElement().normalize();

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        String lekerdezes = "//class/student";
        //String lekerdezes = "/class/student[@id='02']";
        //String lekerdezes = "//student";
        //String lekerdezes = "class/student[2]";
        //String lekerdezes = "class/student[last()]";
        //String lekerdezes = "class/student[last()-1]";
        //String lekerdezes = "class/student[position()<3]";
        //String lekerdezes = "class/*";
        //String lekerdezes = "//student[@*]";
        //String lekerdezes = "//node()";
        //String lekerdezes = "//class/student[kor>20]";
        //String lekerdezes = "//student/keresztnev | //student/vezeteknev";

        NodeList BFHRGP = (NodeList) xpath.compile(lekerdezes).evaluate(doc, javax.xml.xpath.XPathConstants.NODESET);

        for (int i = 0; i < BFHRGP.getLength(); i++) {
            Node node = BFHRGP.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){

                System.out.println("\nAktualis elem: " + node.getNodeName());
                if (node.getNodeName() == "student") {
                    Element elem = (Element) node;
                    System.out.println("Id: "+elem.getAttribute("id"));
                    System.out.println("Keresztnev: "+elem.getElementsByTagName("keresztnev").item(0).getTextContent());
                    System.out.println("Vezeteknev: "+elem.getElementsByTagName("vezeteknev").item(0).getTextContent());
                    System.out.println("Becenev: "+elem.getElementsByTagName("becenev").item(0).getTextContent());
                    System.out.println("Kor: "+elem.getElementsByTagName("kor").item(0).getTextContent());
                }
            }
        }
    }
}
