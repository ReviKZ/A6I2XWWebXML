package A6I2XW;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class A6I2XWDomRead {
	 
	public static void main(String[] args) {

	        try {

	            File xmlFile = new File("A6I2XWXML.xml");

	            DocumentBuilderFactory factory =
	                    DocumentBuilderFactory.newInstance();

	            DocumentBuilder builder =
	                    factory.newDocumentBuilder();

	            Document document = builder.parse(xmlFile);

	            document.getDocumentElement().normalize();

	            System.out.println("Gyökérelem: "
	                    + document.getDocumentElement().getNodeName());

	            NodeList nodeList =
	                    document.getDocumentElement().getChildNodes();

	            for (int i = 0; i < nodeList.getLength(); i++) {

	                Node node = nodeList.item(i);

	                if (node.getNodeType() == Node.ELEMENT_NODE) {

	                    Element element = (Element) node;

	                    System.out.println("\n---------------------------");
	                    System.out.println("Csomópont neve: "
	                            + element.getNodeName());

	                    NamedNodeMap attributes =
	                            element.getAttributes();

	                    if (attributes.getLength() > 0) {

	                        System.out.println("Attribútumok:");

	                        for (int j = 0; j < attributes.getLength(); j++) {

	                            Node attr = attributes.item(j);

	                            System.out.println(attr.getNodeName()
	                                    + " = "
	                                    + attr.getNodeValue());
	                        }
	                    }

	                    NodeList childNodes =
	                            element.getChildNodes();

	                    System.out.println("Adatok:");

	                    for (int k = 0; k < childNodes.getLength(); k++) {

	                        Node child = childNodes.item(k);

	                        if (child.getNodeType() == Node.ELEMENT_NODE) {

	                            System.out.println(
	                                    child.getNodeName()
	                                    + ": "
	                                    + child.getTextContent()
	                            );
	                        }
	                    }

	                    System.out.println("---------------------------");
	                }
	            }

	        } catch (Exception e) {

	            e.printStackTrace();
	        }
	    }
}
