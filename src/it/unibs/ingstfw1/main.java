package it.unibs.ingstfw1;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) throws XMLStreamException, ParserConfigurationException {
		// TODO Auto-generated method stubss
		ArrayList <Utente> l=new ArrayList<Utente>();
		DatiUtenti x=new DatiUtenti(l);
		File fileUtenti = new File("listaUtenti.xml");
		if(fileUtenti.exists() && !fileUtenti.isDirectory()) {
			x=XmlReader.leggiUtenti("listaUtenti.xml");
		}
		Utente acceduto=x.menuAccesso();
		ArrayList <Gerarchia> gs=new ArrayList<>();
		Sistema sistema=new Sistema(gs);
		File fileSistema = new File("sistema.xml");
		if(fileSistema.exists() && !fileSistema.isDirectory()) {
			sistema= XmlReader.readSis("sistema.xml");
		}

		if(acceduto instanceof Configuratore){
			String titolo="Benvenuto nel sistema di gestione baratti";
			String[] voci=new String[]{};
			Menu m=new Menu(titolo,voci);
			m.MenuConfiguratore(sistema);
		}
		System.out.println("\nFINE PROGRAMMA");
		XmlWriter.salvaSistema(sistema, "sistema.xml");
		XmlWriter.utentiWrite(x, "listaUtenti.xml");
	}

}
