package it.unibs.ingstfw1;

import java.util.ArrayList;

public class Menu {

    final private static String CORNICE = "--------------------------------";
    final private static String VOCE_USCITA = "0\tEsci";
    final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata : ";
    final private static String[] VOCI_Configuratore = new String[]{"Inserimento nuova gerarchia","Visualizzazione delle gerarchie"};
    public static final int ZERO = 0;
    public static final int UNO = 1;

    private String titolo;
    private String[] voci;



    public Menu(String titolo, String[] voci) {
        this.titolo = titolo;
        this.voci = voci;
    }

    public int scegli() {
        stampaMenu();
        return Utilita.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.length);
    }

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

    public void MenuConfiguratore(Sistema sistema){
        int risposta;
        this.setVoci(VOCI_Configuratore);
        do {
            risposta = this.scegli();
            switch (risposta){
                case 1 :
                    Gerarchia creata=Gerarchia.creaRamo();
                    sistema.addGerarchia(creata);
                    break;
                case 2 :
                    System.out.println(sistema.toStringSistema());
                    int scelta=Utilita.leggiIntero("Inserisci 1 se vuoi visualizzare in dettaglio una categoria, 0 altrimenti:", ZERO, UNO);
                    if(scelta==UNO){
                        System.out.println(Utilita.leggiCategoria(sistema).toStringCategoria());
                    }
                default:
                    break;
            }
        }while(risposta!=0);

    }

    public String[] getVoci() {
        return voci;
    }

    public void setVoci(String[] voci) {
        this.voci = voci;
    }
}
