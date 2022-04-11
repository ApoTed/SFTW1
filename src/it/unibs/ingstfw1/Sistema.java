package it.unibs.ingstfw1;

import java.util.ArrayList;

/**
 * Classe per la gestione del sistema
 * @author Jacopo Tedeschi, Enrico Zambelli
 */
public class Sistema {


    private ArrayList <Gerarchia> listaGerarchie=new ArrayList<Gerarchia>();

    /**
     * Costruttore della classe Sistema
     * @param _listaGerarchie la lista delle gerarchie del sistema
     */
    public Sistema(ArrayList<Gerarchia>_listaGerarchie){
        this.listaGerarchie=_listaGerarchie;
    }

    /**
     * Metodo per l'aggiunta di una gerarchia al sistema
     * @param g la gerarchia da aggiungere
     */
    public void addGerarchia(Gerarchia g){
        this.listaGerarchie.add(g);
    }

    /**
     * Metodo per la visualizzazione di un sistema
     * @return la stringa corrispondente alla descrizione del sistema
     */
    public String toStringSistema(){
        StringBuffer stb=new StringBuffer();
        if(listaGerarchie.isEmpty()){
            stb.append("Il sistema non ha alcuna gerarchia");
            return stb.toString();
        }
        int i=1;
        for(Gerarchia g : listaGerarchie){
            stb.append("Gerarchia " + i +":\n");
            stb.append(g.vediRamo()+"\n");
            stb.append("\n");
            i++;
        }
        return stb.toString();
    }

    /**
     * Metodo gt per la lista delle gerarchie
     * @return la lista delle gerarchie del sistema
     */
    public ArrayList<Gerarchia> getListaGerarchie() {
        return listaGerarchie;
    }

    /**
     * Metodo per la ricerca di una categoria nel sistema in base al nome passato in ingresso
     * @param nome il nome della categoria da cercare
     * @param numGer il numero della gerarchia cercata nel sistema
     * @return la categoria cercata se presente, null altrimenti
     */
    public Categoria findCategoria(String nome, int numGer){
        for(Categoria x: this.listaGerarchie.get(numGer-1).getRamo().keySet()){
            if(x.getNome().equalsIgnoreCase(nome)){
                return x;
            }
        }
        return null;
    }

    /**
     * Metodo per il controllo del nome di una radice nel sistema
     * @param s il nome della radice da controllare
     * @return true se il nome della radice non è presente nel sistema, false altrimenti
     */
    public boolean checkNomeNuovoRadice(String s){
        boolean valido=true;
        for(Gerarchia x:this.listaGerarchie){
            if(x.getRadice().getNome().equalsIgnoreCase(s)){
                valido=false;
            }
        }
        return valido;
    }
}
