package it.unibs.ingstfw1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
    public static Gerarchia creaRamo(String nomeRadice){
        HashMap<Categoria, Categoria> links=new HashMap<Categoria,Categoria>();
        System.out.println("inserisci i dati della gerarchia radice");
        CampoNativo primo=new CampoNativo("stato di conservazione","",true);
        CampoNativo primos=new CampoNativo("descrizione libera","",false);
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

    public boolean checkNomeNuovo(String s){
        boolean nuovo=true;
        for(Categoria x: this.ramo.keySet()){
            if(x.getNome().equals(s)){
                nuovo=false;
            }

        }
        return nuovo;
    }


    public int numFigli(Categoria padre){
        int figli=0;
        for(Categoria x:this.ramo.keySet()){
            if(this.ramo.get(x).getNome().equals(padre.getNome())){
                figli++;
            }
        }
        return figli;

    }
    //metodo che inserendo il nome del padre te lo restituisce altrimenti null
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

    public void setRadice(Categoria radice) {
        this.radice = radice;
    }

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

    public boolean checkPadreNome (String nome){
        boolean esiste=false;
        for(Categoria x: this.ramo.keySet()){
            if(x.getNome().equals(nome))
                esiste=true;
        }
        return esiste;
    }
}
