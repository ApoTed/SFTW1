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
            stb.append("\n");
        }
        return stb.toString();
    }

    public ArrayList<Gerarchia> getListaGerarchie() {
        return listaGerarchie;
    }

    public Categoria findCategoria(String nome){
        for(Gerarchia g: this.getListaGerarchie()){
            for(Categoria c: g.getRamo().keySet()){
                if (c.getNome().equalsIgnoreCase(nome)){
                    return c;
                }
            }
        }
        return null;
    }

    public boolean checkNomeNuovoRadice(String s){
        boolean valido=true;
        for(Gerarchia x:this.listaGerarchie){
            if(x.getRadice().getNome().equals(s)){
                valido=false;
            }
        }
        return valido;
    }
}
