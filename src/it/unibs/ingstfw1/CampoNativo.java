package it.unibs.ingstfw1;

import java.util.ArrayList;

/**
 * Classe per la gestione di un campo nativo di una categoria
 * @author Jacopo Tedeschi,Enrico Zambelli
 */
public class CampoNativo {
	private String nomeCampo;
	private String descrizione;
	private boolean obbligatoria;

	/**
	 * Costruttore della classe CampoNativo
	 * @param _nomeCampo il nome del campo
	 * @param _descrizione la descrizione del campo nativo
	 * @param _obbligatoria l'obbligatorietà o meno di compilazione del campo
	 */
	public CampoNativo(String _nomeCampo, String _descrizione, boolean _obbligatoria) {
		
		this.nomeCampo=_nomeCampo;
		this.descrizione=_descrizione;
		this.obbligatoria=_obbligatoria;
	}

	/**
	 * Metodo per la creazione di un campo nativo, che controlla che tale campo nativo non sia già presente.
	 * @param nameToCompare
	 * @return il campo nativo creato
	 */
	public static CampoNativo creaCampo(ArrayList <String> nameToCompare){
		String nome=Utilita.leggiStringaNonVuota("Inserisci il nome campo:");
		boolean diverso=false;
		while(diverso==false){

			if(!nameToCompare.contains(nome)){
				diverso=true;
			}
			if(diverso==false){
				nome=Utilita.leggiStringaNonVuota("Il nome inserito non è valido, inserire un nuovo nome:");
			}
		}
		boolean obbligo=false;
		String choice=Utilita.leggiStringaNonVuota("Inserisci 1 se il campo è obbligatorio, 0 altrimenti : ");
		if(choice.equals("1")){
			obbligo=true;
		}
		if(choice.equals("0")){
			obbligo=false;
		}
		CampoNativo c=new CampoNativo(nome, "", obbligo);
		return c;
	}
	/*
	da eliminare
	 */
	public boolean checkDescrizione() {
		boolean corretto=true;
		if(obbligatoria) {
			if( descrizione.isEmpty()) {
				corretto=false;
			}
		}
		return corretto;
	}
	public String getNomeCampo() {
		return nomeCampo;
	}

	/**
	 * Metodo per la visualizzazione di un campo nativo
	 * @return nomeCampo .
	 */
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(nomeCampo + "\n");
		return str.toString();
	}
	//public ArrayList<String> nomiCampi(CampoNativo c)//metodo per ottenre solo la lista di nomi dei campi

	public String getDescrizione() {
		return descrizione;
	}

	public boolean isObbligatoria() {
		return obbligatoria;
	}

}
