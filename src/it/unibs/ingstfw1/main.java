package it.unibs.ingstfw1;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stubsss
		Utente c=new Utente("bello", "12");
		ArrayList <Utente> l=new ArrayList<Utente>();
		Utente test=new Utente("primo","12");
		DatiUtenti x=new DatiUtenti(l, c);
		x.addUtente("primo", "12", true);
		//Utilita.menuAccesso(x);
		//CampoNativo primo=new CampoNativo("stato di conservazione","",true);
		//CampoNativo primos=new CampoNativo("descrizione libera","",false);
		//ArrayList <CampoNativo> campiIniziali=new ArrayList<>();
		//campiIniziali.add(primo);
		//campiIniziali.add(primos);
		Gerarchia testGer=Gerarchia.creaRamo();
		System.out.println(testGer.vediRamo());
		//Categoria testCat=Categoria.creaCategoria(campiIniziali);
	//System.out.println(testCat.toStringCategoria());
	}

}
