package it.unibs.ingstfw1;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utente c=new Utente("bello", "12");
		ArrayList <Utente> l=new ArrayList<Utente>();
		DatiUtenti x=new DatiUtenti(l, c);
		Utilita.menuAccesso(x);

	}

}
