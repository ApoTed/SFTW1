package it.unibs.ingstfw1;

import java.util.ArrayList;

/**
 * Classe per la gestione degli utenti del sistema
 * @author Jacopo Tedeschi,Enrico Zambelli
 */
public class DatiUtenti {
	
	private ArrayList <Utente> listaUtenti = new ArrayList <Utente>();
	private Utente standardConf;

	/**
	 * Costruttore della classe DatiUtenti
	 * @param _listaUtenti lista degli utenti del programma
	 * @param _standardConf credenziale predefinite per l'accesso
	 */
	public DatiUtenti (ArrayList <Utente> _listaUtenti, Utente _standardConf) {
		this.listaUtenti =_listaUtenti;
		this.standardConf=_standardConf;
	}

	/**
	 * Mertodo per il controllo delle credenziali di un utente
	 * @param userName username dell'utente
	 * @param password password dell'utente
	 * @return true se le credenziali sono corrette, false altrimenti
	 */
	public boolean checkCredenziali(String userName, String password) {
		boolean presente=false;
		
		Utente temp=new Utente (userName, password);
		for(Utente x : this.listaUtenti) {
			if(Utente.sameUtente(x, temp))
				presente=true;
		}
		
		return presente;
	}

	/**
	 * Metodo per il controllo delle credenziali predefinite
	 * @param u utente del quale si vuole verificare l'inseriemnto delle credenziali predefinite
	 * @return true le credenziali predefinite sono corrette,false altrimenti
	 */
	public boolean checkConf(Utente u) {
		boolean corretto=false;
		if(Utente.sameUtente(u, this.standardConf))
			corretto=true;
		return corretto;
	}

	/**
	 * Metodo per verificare se uno username è già usato da un altro utente
	 * @param name lo username di cui si vuole verificare la presenza
	 * @return
	 */
	public boolean checkName (String name) {
		boolean presente=false;
		for (Utente x: this.listaUtenti) {
			if(x.getUsername().equals(name))
				presente=true;
		}
		return presente;
	}

	/**
	 * Metodo per làaggiunta di un configuratore alla lista degli utenti
	 * @param name username dell'utente
	 * @param password password dell'utente
	 * @param conf boolean che indica se l'utente è un configuratore o meno
	 * @return
	 */
	public boolean addUtente(String name, String password, boolean conf) {
		boolean successo=false;
		if(conf==true) {
			Configuratore c=new Configuratore(name, password);
			this.listaUtenti.add(c);
			
		}
		return successo;
	}

	/**
	 * Metodo get per la lista degli utenti
	 * @return la lista degli utenti
	 */
	public ArrayList <Utente> getListaUtenti() {
		return listaUtenti;
	}

	/**
	 * Metodo set per la lista degli utenti
	 * @param listaUtenti la lista da settare
	 */
	public void setListaUtenti(ArrayList <Utente> listaUtenti) {
		this.listaUtenti = listaUtenti;
	}
	

}
