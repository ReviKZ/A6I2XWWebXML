package A6I2XW;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xPathA6I2XW {

    public static void main(String[] args) {
        String neptunkod = "A6I2XW";

        try {
            File inputFile = new File("student" + neptunkod + ".xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();

            XPath xpath = XPathFactory.newInstance().newXPath();

            System.out.println("XPath lekérdezések eredményei\n");

            printQueryResult(document, xpath, "1. /class/student", "/class/student");
            printQueryResult(document, xpath, "2. /class/student[@id='02']", "/class/student[@id='02']");
            printQueryResult(document, xpath, "3. //student", "//student");
            printQueryResult(document, xpath, "4. /class/student[2]", "/class/student[2]");
            printQueryResult(document, xpath, "5. /class/student[last()]", "/class/student[last()]");
            printQueryResult(document, xpath, "6. /class/student[last()-1]", "/class/student[last()-1]");
            printQueryResult(document, xpath, "7. /class/student[position() &lt;= 2]", "/class/student[position() <= 2]");
            printQueryResult(document, xpath, "8. /class/*", "/class/*");
            printQueryResult(document, xpath, "9. //student[@*]", "//student[@*]");
            printQueryResult(document, xpath, "10. //*", "//*");
            printQueryResult(document, xpath, "11. /class/student[kor &gt; 20]", "/class/student[kor > 20]");
            printQueryResult(document, xpath, "12. //student/keresztnev | //student/vezeteknev", "//student/keresztnev | //student/vezeteknev");

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("student" + neptunkod + "1.xml"));
            transformer.transform(source, result);

            System.out.println("\nXML fájlba írás kész: student" + neptunkod + "1.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printQueryResult(Document document, XPath xpath, String title, String expression) throws Exception {
        NodeList nodeList = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);

        System.out.println("==================================================");
        System.out.println(title);
        System.out.println("Találatok száma: " + nodeList.getLength());
        System.out.println("--------------------------------------------------");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                if ("student".equals(element.getNodeName())) {
                    printStudent(element);
                } else {
                    System.out.println(element.getNodeName() + ": " + element.getTextContent().trim());
                }
            }
        }

        System.out.println();
    }

    private static void printStudent(Element student) {
        System.out.println("Student id : " + student.getAttribute("id"));
        System.out.println("Keresztnév : " + getText(student, "keresztnev"));
        System.out.println("Vezetéknév : " + getText(student, "vezeteknev"));
        System.out.println("Becenév    : " + getText(student, "becenev"));
        System.out.println("Kor        : " + getText(student, "kor"));
        System.out.println("--------------------------------------------------");
    }

    private static String getText(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
