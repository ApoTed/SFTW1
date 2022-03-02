package it.unibs.ingstfw1;

import java.util.ArrayList;

public class Categoria {
	private String nome;
	private String descrizione;
	private ArrayList <CampoNativo> campiNativi= new ArrayList <CampoNativo>(); 
	private ArrayList <Categoria> sottoCategorie= new ArrayList <Categoria>(); 
	
	public Categoria (String _nome, String _descrizione, ArrayList <CampoNativo> _campiNativi, ArrayList <Categoria> _sottoCategorie ) {
		
		this.nome=_nome;
		this.descrizione=_descrizione;
		this.campiNativi=_campiNativi;
		this.sottoCategorie=_sottoCategorie;
		
	}

}
