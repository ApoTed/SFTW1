package it.unibs.ingstfw1;

import java.util.ArrayList;

/**
 * Classe per la gestione di un cmapo nativo.
 * @author Jacopo Tedeschi,Enrico Zambelli
 */
public class CampoNativo {
	private String nomeCampo;
	private boolean obbligatoria;

	/**
	 * costruttore di campo nativo
	 * @param _nomeCampo nome del campo nativo
	 * @param _obbligatoria boolean che è true se la compilazione del campo è obbligatoria false altrimenti
	 */
	public CampoNativo(String _nomeCampo,  boolean _obbligatoria) {
		
		this.nomeCampo=_nomeCampo;
		this.obbligatoria=_obbligatoria;
	}

	/**
	 * metodo per la creazione del campo da parte del configuratore
	 * @param nameToCompare ArrayList contenente i nome dei campi nativi già presenti che non possono essere ripetuti
	 * @return il campo naitvo creato
	 */
	public static CampoNativo creaCampo(ArrayList <String> nameToCompare){
		String nome=Utilita.leggiStringaNonVuota("inserisci nome campo");
		boolean diverso=false;
		while(diverso==false){

			if(!nameToCompare.contains(nome)){
				diverso=true;
			}
			if(diverso==false){
				nome=Utilita.leggiStringaNonVuota("il noome inserito non è valido inserirre nuovo nome");
			}
		}
		boolean obbligo=false;
		String choice=Utilita.leggiStringaNonVuota("inserisci 1 se il campo è obbligatorio altrimenti 0");
		if(choice.equals("1")){
			obbligo=true;
		}
		if(choice.equals("0")){
			obbligo=false;
		}
		CampoNativo c=new CampoNativo(nome, obbligo);
		return c;
	}

	/**
	 * metodo per ottenre il nome del campo
	 * @return nome del campo
	 */
	public String getNomeCampo() {
		return nomeCampo;
	}

	/**
	 * metodo per ottenre una stringa per la visualizzazione del campo
	 * @return stringa contenete le informazione del campo
	 */
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(nomeCampo + "\n");
		return str.toString();
	}
	//public ArrayList<String> nomiCampi(CampoNativo c)//metodo per ottenre solo la lista di nomi dei campi

	/**
	 * metodo per ottenere un boolean che p true se è obbligatorio false altrimenti
	 * @return boolean riguardo l'obbligo del campo
	 */
	public boolean isObbligatoria() {
		return obbligatoria;
	}

}
