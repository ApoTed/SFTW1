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
 * classe per la gestione del salvataggio su file xml del sistema
 * @author  Enrico Zambello, Jacopo Tedeschi
 */
public class XmlWriter {
    /**
     * metodo per salvare i dati su file xml all'inteno del package
     * @param s sistema di cui si salavano i dati
     */
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
               // Attr numberGerarchia= document.createAttribute("id");
                //numberGerarchia.setValue(""+countGer);
                sistema.appendChild(gerarchia);

                ArrayList <Categoria> allCat=new ArrayList<>();
                int countCat=0;
                for(Categoria x:g.getRamo().keySet()){
                    //categoria
                    Element categoria=document.createElement("categoria");
                   // Attr numberCategoria=document.createAttribute("id");
                    //numberCategoria.setValue(""+countCat);
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
                        //Attr numberCampo=document.createAttribute("id");
                        //numberCategoria.setValue(""+countCampo);
                        campiNativi.appendChild(campoNativo);

                        //nome campo nativo
                        Element nomeCampo=document.createElement("nomeCampo");
                        campoNativo.appendChild(nomeCampo);
                        nomeCampo.appendChild(document.createTextNode(c.getNomeCampo()));

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

            //trasforma il DOM Object in un file xml da salvare in un percorso valido
            //TransformerFactory transformerFactory = TransformerFactory.newInstance();
            //Transformer transformer = transformerFactory.newTransformer();
            //DOMSource domSource = new DOMSource(document);
            //StreamResult streamResult = new StreamResult(new File(xmlFilePath)); //xmleFilePath percoso valido dove salvare nel pc

        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
}
