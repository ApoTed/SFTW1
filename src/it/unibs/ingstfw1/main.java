package it.unibs.ingstfw1;

import jdk.management.jfr.FlightRecorderMXBean;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.HashMap;

public class main {

	public static void main(String[] args) throws XMLStreamException, ParserConfigurationException {
		// TODO Auto-generated method stubss
		//Utente c=new Utente("bello", "12");
		//ArrayList <Utente> l=new ArrayList<Utente>();
		//Utente test=new Utente("primo","12");
		//DatiUtenti x=new DatiUtenti(l);
		//x.addUtente("primo", "12", true);
		//utente che ha eseguito l'accesso
		DatiUtenti x=XmlRead.leggiUtenti("listaUtenti.xml");
		Utente acceduto=x.menuAccesso();
		Sistema sistema=XmlRead.readSis("testSalva.xml");
		if(acceduto instanceof Configuratore){
			String titolo="Benvenuto nel sistema di gestione baratti";
			String[] voci=new String[]{};
			Menu m=new Menu(titolo,voci);
			m.MenuConfiguratore(sistema);
		}
		System.out.println("\nFINE PROGRAMMA");
		XmlWriter.salvaSistema(sistema);
		XmlWriter.utentiWrite(x);
	}

}
