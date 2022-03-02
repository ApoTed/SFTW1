package it.unibs.ingstfw1;

public class CampoNativo {
	private String nomeCampo;
	private String descrizione;
	private boolean obbligatoria;
	
	public CampoNativo(String _nomeCampo, String _descrizione, boolean _obbligatoria) {
		
		this.nomeCampo=_nomeCampo;
		this.descrizione=_descrizione;
		this.obbligatoria=_obbligatoria;
	}
	public boolean checkDescrizione() {
		boolean corretto=true;
		if(obbligatoria) {
			if( descrizione.isEmpty()) {
				corretto=false;
			}
		}
		return corretto;
	}

}
