package it.unibs.ingstfw1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Gerarchia {

    HashMap <Categoria, Categoria> ramo= new HashMap<Categoria,Categoria>();

    public Gerarchia(HashMap <Categoria, Categoria> _ramo){
        this.ramo=_ramo;
    }
    public static Gerarchia creaRamo(){
        HashMap<Categoria, Categoria> links=new HashMap<Categoria,Categoria>();
        Gerarchia finale= new Gerarchia(links);
        System.out.println("inserisci i dati della gerarchia radice");
        CampoNativo primo=new CampoNativo("stato di conservazione","",true);
        CampoNativo primos=new CampoNativo("descrizione libera","",false);
        ArrayList <CampoNativo> campiIniziali=new ArrayList<>();
        campiIniziali.add(primo);
        campiIniziali.add(primos);
        Categoria fantoccio=new Categoria("inesistente","",campiIniziali);
        finale.ramo.put(Categoria.creaCategoria(fantoccio.getCampiNativi()),fantoccio);
        String choice="1";
        do{
            System.out.println(finale.vediPadri());
            choice=Utilita.leggiStringaNonVuota("inserisci 1 se vuoi inserire una sottocategoria altrimenti 0");
            boolean nomeNuovo=false;
            String nomePadre;
            do{
                nomePadre=Utilita.leggiStringaNonVuota("inserisci il nome del padre");
                if(finale.checkPadreNome(nomePadre)){
                    nomeNuovo=true;
                }
                else {
                    System.out.println("non esiste tale padre, scegli uno dei possibili padri");
                    System.out.println(finale.vediPadri());
                }
            }while(nomeNuovo==false);

            finale.ramo.put(Categoria.creaCategoria(finale.findPadre(nomePadre).getCampiNativi()),finale.findPadre(nomePadre));

        }while(choice.equals("1"));

        return finale;

    }

    public Categoria findPadre(String nomePadre){
        for(Categoria x: this.ramo.keySet()){
            if(x.getNome().equals(nomePadre)){
                return x;
            }

        }
        return null;
    }

    public String vediPadri(){
        StringBuffer s=new StringBuffer();
        for(Categoria x: this.ramo.keySet()){
            s.append(x.getNome());
            s.append(", ");
        }
        return s.toString();
    }

    public boolean checkPadreNome (String nome){
        boolean esiste=false;
        for(Categoria x: this.ramo.keySet()){
            if(x.getNome().equals(nome))
                esiste=true;
        }
        return esiste;
    }
}
