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

    public static void salvaSistema(Sistema s){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            //sistema
            Element sistema= document.createElement("insiemeGerarchie");
            document.appendChild(sistema);
            int countGer=0;
            for(Gerarchia g: s.getListaGerarchie()){
                //gerarchia
                Element gerarchia =document.createElement("gerarchia");
                Attr numberGerarchia= document.createAttribute("id");
                numberGerarchia.setValue(""+countGer);
                sistema.appendChild(gerarchia);

                ArrayList <Categoria> allCat=new ArrayList<>();
                int countCat=0;
                for(Categoria x:g.getRamo().keySet()){
                    //categoria
                    Element categoria=document.createElement("categotia");
                    Attr numberCategoria=document.createAttribute("id");
                    numberCategoria.setValue(""+countCat);
                    gerarchia.appendChild(categoria);
                    //nome categoria
                    Element nomeCategoria=document.createElement("nomeCategoria");
                    categoria.appendChild(nomeCategoria);
                    nomeCategoria.appendChild(document.createTextNode(x.getNome()));
                    //descrizione categoria
                    Element descrizione =document.createElement("descrizione");
                    categoria.appendChild(descrizione);
                    descrizione.appendChild(document.createTextNode(x.getDescrizione()));
                    int countCampo=0;
                    //campi nativi
                    Element campiNativi=document.createElement("campiNativi");
                    categoria.appendChild(campiNativi);
                    for(CampoNativo c:x.getCampiNativi()){
                        //campoNativo
                        Element campoNativo=document.createElement("campoNativo");
                        Attr numberCampo=document.createAttribute("id");
                        numberCategoria.setValue(""+countCampo);
                        campiNativi.appendChild(campoNativo);

                        //nome campo nativo
                        Element nomeCampo=document.createElement("nomeCampo");
                        campoNativo.appendChild(nomeCampo);
                        nomeCampo.appendChild(document.createTextNode(c.getNomeCampo()));

                        //descrione campo nativo
                        Element descrizioneCampo=document.createElement("descrizioneCampo");
                        campoNativo.appendChild(descrizioneCampo);
                        descrizioneCampo.appendChild(document.createTextNode(c.getDescrizione()));

                        //obbligo descrzione campo
                        Element obbligoCampo=document.createElement("obbligoCampo");
                        campoNativo.appendChild(obbligoCampo);
                        if(c.isObbligatoria()){
                            obbligoCampo.appendChild(document.createTextNode("true"));
                        }
                        else {
                            obbligoCampo.appendChild(document.createTextNode("false"));
                        }
                        countCampo++;

                    }
                    //padre categoria
                    Element padre=document.createElement("categoriaPadre");
                    categoria.appendChild(padre);
                    padre.appendChild(document.createTextNode(g.getRamo().get(x).getNome()));
                    countCat++;

                }

                countGer++;

            }
            Transformer transformer2 = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File("testSalva.xml"));
            Source input = new DOMSource(document);
            transformer2.transform(input, output);

        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
}
