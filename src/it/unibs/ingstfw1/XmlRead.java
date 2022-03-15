package it.unibs.ingstfw1;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class XmlRead {


    public static String reader(String filename) throws XMLStreamException {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        StringBuffer s=new StringBuffer();
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }
        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            switch (xmlr.getEventType()) { // switch sul tipo di evento
                case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
                    System.out.println("Start Read Doc " + filename); break;
                case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
                    System.out.println("Tag " + xmlr.getLocalName());
                    for (int i = 0; i < xmlr.getAttributeCount(); i++)
                        System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
                    break;
                case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
                    System.out.println("END-Tag " + xmlr.getLocalName()); break;
                case XMLStreamConstants.COMMENT:
                    System.out.println("// commento " + xmlr.getText()); break; // commento: ne stampa il contenuto
                case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
                    if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                        System.out.println("-> " + xmlr.getText());
                    break;
            }
            xmlr.next();
        }
        return s.toString();
    }

    public static void domReader(String xmlFilePath ){
        try {

            File xmlFile = new File(xmlFilePath);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document doc = documentBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nodeList = doc.getElementsByTagName("employee");

            System.out.println("===============================================================");

            //do this the old way, because nodeList is not iterable
            for (int itr = 0; itr < nodeList.getLength(); itr++) {

                Node node = nodeList.item(itr);

                System.out.println("\nNode Name :" + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) node;

                    System.out.println("Employee id : "
                            + eElement.getAttribute("id"));
                    System.out.println("First Name : "
                            + eElement.getElementsByTagName("firstname")
                            .item(0).getTextContent());
                    System.out.println("Last Name : "
                            + eElement.getElementsByTagName("lastname").item(0)
                            .getTextContent());
                    System.out.println("Email : "
                            + eElement.getElementsByTagName("email").item(0)
                            .getTextContent());
                    System.out.println("Department : "
                            + eElement.getElementsByTagName("department").item(0)
                            .getTextContent());
                    System.out.println("Salary : ");
                            //+ eElement.getElementsByTagName("salary").item(0)
                            //.getTextContent());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
