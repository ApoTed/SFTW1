package it.unibs.ingstfw1;

import java.util.ArrayList;

public class DatiUtenti {
	
	private ArrayList <Utente> listaConfiguratori = new ArrayList <Utente>();
	private Utente standardConf;
	
	public DatiUtenti (ArrayList <Utente> _listaConfiguratori, Utente _standardConf) {
		this.listaConfiguratori =_listaConfiguratori;
		this.standardConf=_standardConf;
	}
	public boolean checkCredenziali(String userName, String password) {
		boolean presente=false;
		
		Utente temp=new Utente (userName, password);
		for(Utente x : this.listaConfiguratori) {
			if(Utente.sameUtente(x, temp))
				presente=true;
		}
		
		return presente;
	}
	
	public boolean checkConf(Utente u) {
		boolean corretto=false;
		if(Utente.sameUtente(u, this.standardConf))
			corretto=true;
		return corretto;
	}
	
	public boolean checkName (String name) {
		boolean presente=false;
		for (Utente x: this.listaConfiguratori) {
			if(x.getUsername().equals(name))
				presente=true;
		}
		return presente;
	}
	public boolean addUtente(String name, String password, boolean conf) {
		boolean successo=false;
		if(conf==true) {
			Configuratore c=new Configuratore(name, password);
			this.listaConfiguratori.add(c);
			
		}
		return successo;
	}

	public ArrayList <Utente> getListaConfiguratori() {
		return listaConfiguratori;
	}

	public void setListaConfiguratori(ArrayList <Utente> listaConfiguratori) {
		this.listaConfiguratori = listaConfiguratori;
	}
	

}
