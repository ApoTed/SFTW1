package it.unibs.ingstfw1;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Classe per la gestione della scrittura di un file XML
 */
public class XmlWriter {

    public static void domWriter(){
        //String xmlFilePath = "C:\\Users\\apote\\Desktop\\Dom\\testX.xml";
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("company");
            document.appendChild(root);

            // employee element
            Element employee = document.createElement("employee");

            root.appendChild(employee);

            // set an attribute to staff element
            Attr attr = document.createAttribute("id");
            attr.setValue("10");
            employee.setAttributeNode(attr);

            //you can also use staff.setAttribute("id", "1") for this

            // firstname element
            Element firstName = document.createElement("firstname");
            firstName.appendChild(document.createTextNode("James"));
            employee.appendChild(firstName);

            // lastname element
            Element lastname = document.createElement("lastname");
            lastname.appendChild(document.createTextNode("Harley"));
            employee.appendChild(lastname);

            // email element
            Element email = document.createElement("email");
            email.appendChild(document.createTextNode("james@example.org"));
            employee.appendChild(email);

            // department elements
            Element department = document.createElement("department");
            department.appendChild(document.createTextNode("Human Resources"));
            employee.appendChild(department);

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            //StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            Transformer transformer2 = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File("output.xml"));
            Source input = new DOMSource(document);
            transformer2.transform(input, output);

            // If you use
             //StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging

            //transformer.transform(domSource, stramResult);

            System.out.println("Done creating XML File");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public void salvaSistema(Sistema s){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element sistema= document.createElement("insieme gerarchie");
            document.appendChild(sistema);

            for(Gerarchia g: s.getListaGerarchie()){
                int count=0;
                Element gerarchia =document.createElement("gerarchia");
                Attr numberGerarchia= document.createAttribute("id");
                numberGerarchia.setValue(""+count);
                sistema.appendChild(gerarchia);
                ArrayList <Categoria> allCat=new ArrayList<>();
                for(Categoria x:g.getRamo().keySet()){
                    allCat.add(x);
                }





                count++;

            }







        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } ;

    }
}
