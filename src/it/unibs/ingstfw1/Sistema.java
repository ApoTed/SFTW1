package it.unibs.ingstfw1;

import java.util.ArrayList;

public class Sistema {
    private ArrayList <Gerarchia> listaGerarchie=new ArrayList<Gerarchia>();

    public Sistema(ArrayList<Gerarchia>_listaGerarchie){
        this.listaGerarchie=_listaGerarchie;
    }

    public void addGerarchia(Gerarchia g){
        this.listaGerarchie.add(g);
    }

    public String toStringSistema(){
        StringBuffer stb=new StringBuffer();
        for(Gerarchia g : listaGerarchie){
            stb.append(g.vediRamo());
        }
        return stb.toString();
    }

    public ArrayList<Gerarchia> getListaGerarchie() {
        return listaGerarchie;
    }
}
