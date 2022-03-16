package it.unibs.ingstfw1;

import java.util.ArrayList;

public class Categoria {
	private String nome;



	private String descrizione;
	private ArrayList <CampoNativo> campiNativi= new ArrayList <CampoNativo>(); 

	
	public Categoria (String _nome, String _descrizione, ArrayList <CampoNativo> _campiNativi ) {
		
		this.nome=_nome;
		this.descrizione=_descrizione;
		this.campiNativi=_campiNativi;
	}



	public static Categoria creaCategoria(ArrayList <CampoNativo> campi){//devo ritornare una categoria
		String name=Utilita.leggiStringaNonVuota("inserisci nome: ");
		ArrayList <CampoNativo> copia=new ArrayList<>();
		copia.addAll(campi);
		String choice="1";
		String descrizione=Utilita.leggiStringaNonVuota("inserisci descrizione");
		Categoria creata=new Categoria("","", null);
		while(choice.equals("1")){


			choice=Utilita.leggiStringaNonVuota("1 se vuoi inserire un nuovo campo altrimenti 0 ");

			if(choice.equals("1")) {
				ArrayList<String> nomi = new ArrayList<String>();
				for (CampoNativo x : copia) {
					nomi.add(x.getNomeCampo());
				}
				copia.add(CampoNativo.creaCampo(nomi));
			}
			else
				System.out.println(" questa categoria ha " + copia.size() + " campi");

		}
		creata=new Categoria(name,descrizione, copia);


		return creata;

	}

	public String toStringCategoria() {
		StringBuffer str = new StringBuffer();
		str.append(this.nome + " : " + this.descrizione + "\n");

		if (!campiNativi.isEmpty()) {
			str.append("Campi Nativi : \n");
			for (CampoNativo cn : campiNativi) {
				str.append("\t"+cn.toString());
			}

		}
		return str.toString();
	}
	public ArrayList<CampoNativo> getCampiNativi() {
		return campiNativi;
	}
	public String getNome() {
		return nome;
	}




}
