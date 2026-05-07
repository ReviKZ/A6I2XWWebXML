package A6I2XW;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class xPathModifyA6I2XW {

    public static void main(String[] args) {
        String neptunkod = "A6I2XW";

        try {
            File inputFile = new File("student" + neptunkod + ".xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();

            XPath xpath = XPathFactory.newInstance().newXPath();

            Node node = (Node) xpath.evaluate("/class/student[@id='01']", document, XPathConstants.NODE);

            if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
                Element student = (Element) node;

                setText(student, "keresztnev", "Bence");
                setText(student, "vezeteknev", "Szabó");
                setText(student, "becenev", "Beni");
                setText(student, "kor", "23");

                System.out.println("Az id='01' azonosítójú student módosított adatai:");
                System.out.println("--------------------------------------------------");
                System.out.println("Student id : " + student.getAttribute("id"));
                System.out.println("Keresztnév : " + getText(student, "keresztnev"));
                System.out.println("Vezetéknév : " + getText(student, "vezeteknev"));
                System.out.println("Becenév    : " + getText(student, "becenev"));
                System.out.println("Kor        : " + getText(student, "kor"));
                System.out.println("--------------------------------------------------");
                
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(new File("student" + neptunkod + ".xml"));

                transformer.transform(source, result);

                System.out.println("Az XML fájl módosítása megtörtént.");
            } else {
                System.out.println("Nem található id='01' azonosítójú student elem.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setText(Element element, String tagName, String value) {
        element.getElementsByTagName(tagName).item(0).setTextContent(value);
    }

    private static String getText(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
