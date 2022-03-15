package it.unibs.ingstfw1;

import jdk.management.jfr.FlightRecorderMXBean;

import java.util.ArrayList;
import java.util.HashMap;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stubsss
		Utente c=new Utente("bello", "12");
		ArrayList <Utente> l=new ArrayList<Utente>();
		Utente test=new Utente("primo","12");
		DatiUtenti x=new DatiUtenti(l, c);
		x.addUtente("primo", "12", true);
		//Utilita.menuAccesso(x);
		CampoNativo primo=new CampoNativo("stato di conservazione","",true);
		CampoNativo primos=new CampoNativo("descrizione libera","",false);
		ArrayList <CampoNativo> campiIniziali=new ArrayList<>();
		campiIniziali.add(primo);
		campiIniziali.add(primos);
		//Gerarchia testGer=Gerarchia.creaRamo();
		//System.out.println(testGer.vediRamo());
		//Categoria testCat=Categoria.creaCategoria(campiIniziali);
		//System.out.println(testCat.toStringCategoria());
		Categoria radice=new Categoria("libro", "opera cartacea",campiIniziali);
		Categoria romanzo=new Categoria("romanzo", "opera cartacea storia finta",campiIniziali);
		Categoria giallo=new Categoria("giallo", "opera cartacea detective",campiIniziali);
		Categoria fantoccio=new Categoria("inesistente","",campiIniziali);
		ArrayList<CampoNativo> C2= campiIniziali;
		CampoNativo appiu=new CampoNativo("lunghezza","",true);
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
		XmlWriter.domWriter();

		XmlWriter.reader("test", g);
	}

}
