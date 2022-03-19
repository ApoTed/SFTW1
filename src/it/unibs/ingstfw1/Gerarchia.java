package it.unibs.ingstfw1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
/**
Classe per la gestione di una categoria
 @author Jacopo Tedeschi,Enrico Zambelli
 */
public class Gerarchia {

    public static final String INSERISCI_NOME = "Inserisci il nome della categoria:";
    public static final String NOME_GIA_PRESENTE = "Il nome scelto è già presente nella gerarchia";
    private HashMap <Categoria, Categoria> ramo= new HashMap<Categoria,Categoria>();
    private Categoria radice;

    /**
     * Costruttore della classe gerarchia
     * @param _ramo hashMap formata dalla categoria corrente e la categoria padre
     * @param _radice la categoria radice della gerarchia
     */
    public Gerarchia(HashMap <Categoria, Categoria> _ramo, Categoria _radice){
        this.ramo=_ramo;
        this.radice=_radice;
    }

    /**
     * Costruttore di gerarchia
     */
    public Gerarchia(){

    }

    /**
     * Metodo per la creazione di una gerarchia
     * @param nomeRadice il nome della radice della gerarchia
     * @return la gerarchia creata
     */
    public static Gerarchia creaRamo(String nomeRadice){
        HashMap<Categoria, Categoria> links=new HashMap<Categoria,Categoria>();
        //System.out.println("Inserisci i dati della gerarchia radice");
        CampoNativo primo=new CampoNativo("Stato di conservazione","",true);
        CampoNativo secondo=new CampoNativo("Descrizione libera","",false);
        ArrayList <CampoNativo> campiIniziali=new ArrayList<>();
        campiIniziali.add(primo);
        campiIniziali.add(secondo);
        Categoria fantoccio=new Categoria("inesistente","",campiIniziali);
        Gerarchia finale= new Gerarchia();
        Categoria r=Categoria.creaCategoria(fantoccio.getCampiNativi(),nomeRadice);
        finale.ramo.put(r,fantoccio);
        finale.setRadice(r);

        String choiceContinue=Utilita.leggiStringaNonVuota("Inserisci 1 se vuoi inserire una sottocategoria, 0 altrimenti:");
        while(choiceContinue.equals("1")){
            System.out.println("Possibili padri:" + finale.vediPadri());

            boolean nomePadreValido=false;
            String nomePadre;
            Categoria padre = new Categoria("","",null);
            do{
                nomePadre=Utilita.leggiStringaNonVuota("Inserisci il nome del padre:");
                if(finale.checkPadreNome(nomePadre)){
                    nomePadreValido=true;
                    padre=finale.findPadre(nomePadre);
                }
                else {
                    System.out.println("Non esiste tale padre, scegli uno dei possibili padri");
                    System.out.println("Possibili padri:" + finale.vediPadri());
                }
            }while(nomePadreValido==false);
            boolean nomeNuovo=false;
            String nomeCategoria;
            do{
                nomeCategoria=Utilita.leggiStringaNonVuota(INSERISCI_NOME);
                if(finale.checkNomeNuovo(nomeCategoria)){
                    nomeNuovo=true;
                }
                else{
                    System.out.println(NOME_GIA_PRESENTE);
                }
            }while(!nomeNuovo);

            int figli=finale.numFigli(padre);
            if(figli==0){
                System.out.println("Sono richieste due sottocategorie perchè il padre non ne ha nessuna per ora");
                finale.ramo.put(Categoria.creaCategoria(finale.findPadre(nomePadre).getCampiNativi(),nomeCategoria),finale.findPadre(nomePadre));
                nomeNuovo=false;
                do{
                    nomeCategoria=Utilita.leggiStringaNonVuota(INSERISCI_NOME);
                    if(finale.checkNomeNuovo(nomeCategoria)){
                        nomeNuovo=true;
                    }
                    else{
                        System.out.println(NOME_GIA_PRESENTE);
                    }
                }while(!nomeNuovo);
                //System.out.println("Inserire la seconda sottocategoria di: "+nomePadre);
                finale.ramo.put(Categoria.creaCategoria(finale.findPadre(nomePadre).getCampiNativi(),nomeCategoria),finale.findPadre(nomePadre));
            }
            else{
                finale.ramo.put(Categoria.creaCategoria(finale.findPadre(nomePadre).getCampiNativi(),nomeCategoria),finale.findPadre(nomePadre));
            }



            choiceContinue=Utilita.leggiStringaNonVuota("Inserisci 1 se vuoi inserire un'altra sottocategoria,0 altrimenti:");
        }

        return finale;

    }

    /**
     * Metodo che controlla se il nome in ingresso è presente nella gerarchia
     * @param s il nome di cui si vuole verificare la presenza o meno
     * @return true se il nome non è presente, false altrimenti
     */
    public boolean checkNomeNuovo(String s){
        boolean nuovo=true;
        for(Categoria x: this.ramo.keySet()){
            if(x.getNome().equals(s)){
                nuovo=false;
            }

        }
        return nuovo;
    }

    /**
     * Metodo che restituisce il numero dei figli della categoria passata in ingresso
     * @param padre la categoria di cui si vuole determinare il numero di figli
     * @return il numero de figli della categoria in ingresso
     */
    public int numFigli(Categoria padre){
        int figli=0;
        for(Categoria x:this.ramo.keySet()){
            if(this.ramo.get(x).getNome().equals(padre.getNome())){
                figli++;
            }
        }
        return figli;

    }

    /**
     * Metodo che restituisce la categoria padre ricevendo il nome in ingresso
     * @param nomePadre il nome del padre da cercare
     * @return la categoria padre, null se non è presente
     */
    public Categoria findPadre(String nomePadre){
        for(Categoria x: this.ramo.keySet()){
            if(x.getNome().equals(nomePadre)){
                return x;
            }

        }
        return null;
    }

    /**
     * Metodo che restituisce la lista dei padri di una gerarchia
     * @return la lista delle categorie che sono padri separate dalla virgola.
     */
    public String vediPadri(){
        StringBuffer s=new StringBuffer();
        for(Categoria x: this.ramo.keySet()){
            s.append(x.getNome());
            s.append(", ");
        }
        return s.toString();
    }

    /**
     * Metodo set della radice di una gerarchia.
     * @param radice la categoria da settare come radice
     */
    public void setRadice(Categoria radice) {
        this.radice = radice;
    }

    /**
     * Metodo per la visualizzazione di una gerarchia
     * @return stringa corrispondente alla descrizione di una gerarchia
     */
    public String vediRamo(){
        ArrayList<Categoria> nonVisti=new ArrayList<Categoria>();
        StringBuffer s=new StringBuffer();
        s.append("La radice è "+this.radice.getNome()+". ");
        for(Categoria x: this.ramo.keySet()){
            nonVisti.add(x);
        }
        nonVisti.remove(this.radice);
        ArrayList <Categoria> figliAlti=new ArrayList<Categoria>();
        for(Categoria x:this.ramo.keySet()){
            if(this.ramo.get(x).getNome().equals(this.radice.getNome())){
                figliAlti.add(x);
            }
        }
        if(this.numFigli(this.radice)==0){
            System.out.println("non ha sottocategorie");
        }
        else{
            s.append("Le sottocategorie di "+this.radice.getNome()+ " sono: ");
        }

        for(Categoria x: figliAlti){
            s.append(x.getNome()+"   ");
        }

        do{
            s.append("\n");
            ArrayList<Categoria> figliBassi=new ArrayList<Categoria>();
            for(Categoria x: figliAlti){
                s.append("\n");
                ArrayList <Categoria> figlietti=new ArrayList<Categoria>();//aggiungi un invio qui
                for(Categoria y: this.ramo.keySet()){
                    if(this.ramo.get(y).getNome().equals(x.getNome())){
                        figlietti.add(y);
                    }
                }
                if(!figlietti.isEmpty()){
                    s.append("Le sottocategorie di "+x.getNome()+" sono: ");
                    for(Categoria j:figlietti){
                        s.append(j.getNome()+"    ");
                    }
                    figliBassi.addAll(figlietti);
                }
                else{
                    s.append(x.getNome()+" non ha sottocagorie");
                }



            }
            figliAlti.clear();
            figliAlti.addAll(figliBassi);

        }while(!figliAlti.isEmpty());
        return s.toString();


    }

    /**
     * Metodo che verifica la presenza di una padre ricevendo il nome in ingresso
     * @param nome il nome del padre di cui verificare la presenza
     * @return true se il nome corrisponde ad una categoria padre, false altrimenti
     */
    public boolean checkPadreNome (String nome){
        boolean esiste=false;
        for(Categoria x: this.ramo.keySet()){
            if(x.getNome().equals(nome))
                esiste=true;
        }
        return esiste;
    }

    /**
     * Metodo get per il ramo di una gerarchia
     * @return il ramo della gerarchia
     */
    public HashMap<Categoria, Categoria> getRamo() {
        return ramo;
    }

    /**
     * Metodo get per la radice della gerarchia
     * @return la radice della gerarchia
     */
    public Categoria getRadice() {
        return radice;
    }
}
