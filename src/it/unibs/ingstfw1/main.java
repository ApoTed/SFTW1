package it.unibs.ingstfw1;

import jdk.management.jfr.FlightRecorderMXBean;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.HashMap;

public class main {

	public static void main(String[] args) throws XMLStreamException {
		// TODO Auto-generated method stubsss
		Utente c=new Utente("bello", "12");
		ArrayList <Utente> l=new ArrayList<Utente>();
		Utente test=new Utente("primo","12");
		DatiUtenti x=new DatiUtenti(l, c);
		x.addUtente("primo", "12", true);
		//utente che ha eseguito l'accesso
		Utente acceduto=x.menuAccesso();
		//Utilita.menuAccesso(x);
		CampoNativo primo=new CampoNativo("stato di conservazione",true);
		CampoNativo primos=new CampoNativo("descrizione libera",false);
		ArrayList <CampoNativo> campiIniziali=new ArrayList<>();
		campiIniziali.add(primo);
		campiIniziali.add(primos);
		//Gerarchia testGer=Gerarchia.creaRamo();
		//System.out.println(testGer.vediRamo());
		//Categoria testCat=Categoria.creaCategoria(campiIniziali);
		//System.out.println(testCat.toStringCategoria());
		Categoria radice=new Categoria("libro", "operacartacea",campiIniziali);
		Categoria romanzo=new Categoria("romanzo", "opera cartacea storia finta",campiIniziali);
		Categoria giallo=new Categoria("giallo", "opera cartacea detective",campiIniziali);
		Categoria fantoccio=new Categoria("inesistente","",campiIniziali);
		ArrayList<CampoNativo> C2= campiIniziali;
		CampoNativo appiu=new CampoNativo("lunghezza",true);
		C2.add(appiu);
		Categoria fiaba=new Categoria("fiaba", "opera cartacea per bimbi",C2);
		Categoria bimbi=new Categoria("bimbi", "per bimbi",C2);
		Categoria disegno=new Categoria("disegno0", "da disegnare",C2);
		Categoria romantico=new Categoria("romantico", "donne",campiIniziali);
		HashMap <Categoria, Categoria> toto=new HashMap<>();
		toto.put(radice, fantoccio);
		toto.put(romanzo, radice);
		toto.put(giallo, romanzo);
		toto.put(romantico,romanzo);
		toto.put(fiaba, bimbi);
		toto.put(bimbi, radice);
		toto.put(disegno, bimbi);
		Gerarchia g=new Gerarchia(toto, radice);
		HashMap <Categoria, Categoria> toto2=new HashMap<>();
		toto2.put(radice, fantoccio);
		toto2.put(romanzo, radice);
		Gerarchia g2=new Gerarchia(toto2, radice);
		//System.out.println(g.vediRamo());

		//XmlWriter.reader("test", g);
		//XmlRead.domReader("C:\\Users\\apote\\Desktop\\Dom\\testX.xml");
		//prova
		ArrayList<Gerarchia> listaG=new ArrayList<>();

		listaG.add(g);
		listaG.add(g2);
		Sistema sistema=new Sistema(listaG);
		String titolo="Benvenuto nel sistema di gestione baratti";
		String[] voci=new String[]{};
		Menu m=new Menu(titolo,voci);
		m.MenuConfiguratore(sistema);
		XmlWriter.salvaSistema(sistema);
		Sistema testingRead=XmlRead.readSis("testSalva.xml");
		System.out.println(testingRead.toStringSistema());

	}

}
