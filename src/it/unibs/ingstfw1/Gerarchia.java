package it.unibs.ingstfw1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * classe per la costruzione e la gestione della gerarchia
 * @author Enrico Zambelli, Jacopo Tedeschi
 */
public class Gerarchia {

    private HashMap <Categoria, Categoria> ramo= new HashMap<Categoria,Categoria>();
    private Categoria radice;

    public HashMap<Categoria, Categoria> getRamo() {
        return ramo;
    }

    public Categoria getRadice() {
        return radice;
    }

    public Gerarchia(HashMap <Categoria, Categoria> _ramo, Categoria _radice){
        this.ramo=_ramo;
        this.radice=_radice;
    }
    public Gerarchia(){

    }

    /**
     * metodo per la creazione della gerarchia da parte del configuratore
     * @param nomeRadice nome dellla radice che viene chiesto prima della creazione per verificarne la validità le altre radici
     * @return finale la gerarchia creata
     */
    public static Gerarchia creaRamo(String nomeRadice){
        HashMap<Categoria, Categoria> links=new HashMap<Categoria,Categoria>();
        System.out.println("inserisci i dati della gerarchia radice");
        CampoNativo primo=new CampoNativo("stato di conservazione",true);
        CampoNativo primos=new CampoNativo("descrizione libera",false);
        ArrayList <CampoNativo> campiIniziali=new ArrayList<>();
        campiIniziali.add(primo);
        campiIniziali.add(primos);
        Categoria fantoccio=new Categoria("inesistente","",campiIniziali);
        Gerarchia finale= new Gerarchia();
        Categoria r=Categoria.creaCategoria(fantoccio.getCampiNativi(),nomeRadice);
        finale.ramo.put(r,fantoccio);
        finale.setRadice(r);


        String choiceContinue=Utilita.leggiStringaNonVuota("inserisci 1 se vuoi inserire una sottocategoria altrimenti 0");
        while(choiceContinue.equals("1")){
            System.out.println(finale.vediPadri());

            boolean nomePadreValido=false;
            String nomePadre;
            Categoria padre = new Categoria("","",null);
            do{
                nomePadre=Utilita.leggiStringaNonVuota("inserisci il nome del padre");
                if(finale.checkPadreNome(nomePadre)){
                    nomePadreValido=true;
                    padre=finale.findPadre(nomePadre);
                }
                else {
                    System.out.println("non esiste tale padre, scegli uno dei possibili padri");
                    System.out.println(finale.vediPadri());
                }
            }while(nomePadreValido==false);
            boolean nomeNuovo=false;
            String nomeCatgoria;
            do{
                nomeCatgoria=Utilita.leggiStringaNonVuota("inserisci il nome della categoria");
                if(finale.checkNomeNuovo(nomeCatgoria)){
                    nomeNuovo=true;
                }
                else{
                    System.out.println("nome non valido");
                }
            }while(!nomeNuovo);

            int figli=finale.numFigli(padre);
            if(figli==0){
                System.out.println("si devono inderire 2 sottocategorie perchè il padre non ne ha nessuna per ora");
                finale.ramo.put(Categoria.creaCategoria(finale.findPadre(nomePadre).getCampiNativi(),nomeCatgoria),finale.findPadre(nomePadre));
                System.out.println("inserire la seconda sottocategoria di: "+nomePadre);
                finale.ramo.put(Categoria.creaCategoria(finale.findPadre(nomePadre).getCampiNativi(),nomeCatgoria),finale.findPadre(nomePadre));
            }
            else{
                finale.ramo.put(Categoria.creaCategoria(finale.findPadre(nomePadre).getCampiNativi(),nomeCatgoria),finale.findPadre(nomePadre));
            }



            choiceContinue=Utilita.leggiStringaNonVuota("inserisci 1 se vuoi inserire un'altra sottocategoria se hai finito premi 0");
        }

        return finale;

    }

    /**
     * metodo che controlla se un nome è già stato preso all'interno della gerarchia
     * @param s nome da controllare
     * @return nuovo boolean che è true se non è già presente false altrimenti
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
     * metodo che controlla quante sottocategorie ha una categoria
     * @param padre nome categoria da controllare
     * @return figli int che indica il numero di sottocategorie presenti
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
     * metodo che restituisce la categoria padre inserendone il nome
     * @param nomePadre nome del padre
     * @return x la categoria padre
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
     * metodo che restituisce una stringa con tutte le possibili categorie che possono avere delle sottocategorie
     * @return s.toString(); stringa contenente i nomi delle categorie
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
     * metodo per impostare il valore della radice
     * @param radice categoria che si vuole impostare come radice
     */
    public void setRadice(Categoria radice) {
        this.radice = radice;
    }

    /**
     * metodo che aggiunge una categoria con la relativa categoria padre
     * @param toAdd categoria da aggiungere
     * @param padre relativa categoria padre
     * @return la gerarchia modificate
     */
    public Gerarchia addCategoria(Categoria toAdd, Categoria padre){
        this.ramo.put(toAdd, padre);
        return this;
    }

    /**
     * metodo per la visualizzazione della gerarchia
     * @return una stringa contenete le informazioni relativealla visualizzazione della gerarchia
     */
    public String vediRamo(){
        ArrayList<Categoria> nonVisti=new ArrayList<Categoria>();
        StringBuffer s=new StringBuffer();
        s.append("la radice è "+this.radice.getNome()+". ");
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
            System.out.println("non ha sottocategorei");
        }
        else{
            s.append("le sottocategorie di "+this.radice.getNome()+ " sono: ");
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
                    s.append("le sottocategorie di "+x.getNome()+" sono: ");
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
     * metodo che controlla che il nome inserito come categoria padre sia valido
     * @param nome nome della categoria padre di cui si vuole creare 1 o più sottocategorie
     * @return esiste boolean che è true se valido false altrimenti
     */
    public boolean checkPadreNome (String nome){
        boolean esiste=false;
        for(Categoria x: this.ramo.keySet()){
            if(x.getNome().equals(nome))
                esiste=true;
        }
        return esiste;
    }
}
