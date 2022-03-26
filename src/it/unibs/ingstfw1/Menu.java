package it.unibs.ingstfw1;

import java.util.ArrayList;

/**
 * Classe per la gestione dei menu
 *  @author Jacopo Tedeschi,Enrico Zambelli
 */
public class Menu {

    final private static String CORNICE = "--------------------------------";
    final private static String VOCE_USCITA = "0\tEsci";
    final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata : ";
    final private static String[] VOCI_Configuratore = new String[]{"Inserimento nuova gerarchia","Visualizzazione delle gerarchie"};
    public static final int ZERO = 0;
    public static final int UNO = 1;

    private String titolo;
    private String[] voci;


    /**
     * Costruttore della classe menu
     * @param titolo titolo del menu
     * @param voci le voci del menu
     */
    public Menu(String titolo, String[] voci) {
        this.titolo = titolo;
        this.voci = voci;
    }

    /**
     * Metodo per la scelta di un'opzione del menu
     * @return il numero della scelta
     */
    public int scegli() {
        stampaMenu();
        return Utilita.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.length);
    }

    /**
     * Metodo per la stampa a video di un menu
     */
    public void stampaMenu() {
        System.out.println(CORNICE);
        System.out.println(titolo);
        System.out.println(CORNICE);
        for (int i = 0; i < voci.length; i++) {
            System.out.println((i + 1) + "\t" + voci[i]);
        }
        System.out.println();
        System.out.println(VOCE_USCITA);
        System.out.println();
    }

    /**
     * Metodo per la gestione del menu del configuratore
     * @param sistema il sistema su cui opera il configuratore
     */
    public void MenuConfiguratore(Sistema sistema){
        int risposta;
        this.setVoci(VOCI_Configuratore);
        do {
            risposta = this.scegli();
            switch (risposta){
                case 1 :
                    String nomeRadice;
                    boolean nomeRadiceNuovo=false;
                    do{
                        nomeRadice=Utilita.leggiStringaNonVuota("Inserisci il nome della radice della gerarchia:");
                        if(sistema.checkNomeNuovoRadice(nomeRadice)){
                            nomeRadiceNuovo=true;
                        }
                        else
                            System.out.println("Questo nome è già presente");
                    }while(!nomeRadiceNuovo);
                    Gerarchia creata=Gerarchia.creaRamo(nomeRadice);
                    sistema.addGerarchia(creata);
                    break;
                case 2 :
                    System.out.println(sistema.toStringSistema());
                    if(sistema.getListaGerarchie().size()==0){
                        break;
                    }
                    int scelta=0;
                    do{
                        scelta=Utilita.leggiIntero("Inserisci 1 se vuoi visualizzare in dettaglio una categoria, 0 altrimenti:", ZERO, UNO);
                        if(scelta==1){
                            Categoria toSee=Utilita.leggiCategoria(sistema);
                            if(toSee!=null){
                                System.out.println(toSee.toStringCategoria());
                            }
                        }


                    }while(scelta==1);
                default:
                    break;
            }
        }while(risposta!=0);

    }

    /**
     * Metodo get per le voci del menu
     * @return le voci del menu
     */
    public String[] getVoci() {
        return voci;
    }

    /**
     * Metodo set per le voci del menu
     * @param voci le voci da settare
     */
    public void setVoci(String[] voci) {
        this.voci = voci;
    }
}
