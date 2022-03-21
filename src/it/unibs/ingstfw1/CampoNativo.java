package it.unibs.ingstfw1;

import java.util.ArrayList;

public class CampoNativo {
	private String nomeCampo;
	private boolean obbligatoria;
	
	public CampoNativo(String _nomeCampo,  boolean _obbligatoria) {
		
		this.nomeCampo=_nomeCampo;
		this.obbligatoria=_obbligatoria;
	}

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

	public String getNomeCampo() {
		return nomeCampo;
	}

	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(nomeCampo + "\n");
		return str.toString();
	}
	//public ArrayList<String> nomiCampi(CampoNativo c)//metodo per ottenre solo la lista di nomi dei campi

	public boolean isObbligatoria() {
		return obbligatoria;
	}

}
