package it.unibs.ingstfw1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe contente vari metodi statici di utilità
 * @author Jacopo Tedeschi,Enrico Zambelli
 */
public class Utilita {
	private static final String ERRORE_FORMATO = "Attenzione il dato inserito non e' nel formato corretto";
	private static final String ERRORE_MINIMO = "Attenzione: e' richiesto un valore maggiore o uguale a ";
	private static final String ERRORE_MASSIMO = "Attenzione: e' richiesto un valore minore o uguale a ";
	public static final String CATEGORIA_NON_PRESENTE = "Categoria non presente";
	public static final String INSERISCI_NOME = "Inserisci il tuo nome: ";
	public static final String INSERISCI_PASSWORD = "Inserisci la tua password: ";
	public static final String ERRORE_STRINGA_VUOTA = "La stringa inserita non può essere vuota";
	private static Scanner lettore = creaScanner();


	/**
	 * Metodo per la gestione del menu di accesso
	 * @param data la lista degli utenti del sistema
	 * @return true se l'accesso va a buon fine, false altrimenti
	 */
	public static boolean menuAccesso(DatiUtenti data) {
		boolean successo=false;
		String username=Utilita.leggiStringaNonVuota("Benvenuto "+ INSERISCI_NOME);
		String password=Utilita.leggiStringaNonVuota(INSERISCI_PASSWORD);
		Utente temp= new Utente(username, password);
		if( data.checkConf(temp)) {
			String newUsername;
			do {
				newUsername=Utilita.leggiStringaNonVuota("Inserisci il tuo nuovo nome utente");
				if(data.checkName(newUsername)==true)
					System.out.println("Questo nome utente non è disponibile");
			}while(data.checkName(newUsername)==true);
				
			String newPassword=Utilita.leggiStringaNonVuota("Inserisci la tua nuova password");
			data.addUtente(newUsername, newPassword, true);
			temp=new Utente(newUsername, newPassword);
			
		}
		for (int i=0;i<3;i++) {
			//accesso da utente già registrato 3 tentativi
			int tentativi = 2 - i;
			if(tentativi<2){
				String nameTry=Utilita.leggiStringaNonVuota(INSERISCI_NOME);
				String passwordTry=Utilita.leggiStringaNonVuota(INSERISCI_PASSWORD);
				temp=new Utente(nameTry, passwordTry);
			}
			for (Utente toCompare : data.getListaUtenti()) {
				if( Utente.sameUtente(toCompare, temp)) {
					
					successo=true;
				}

			}
			if(successo==false) {

				System.out.println("Le credenziali inserite non sono corrette, hai " + tentativi + " tentativi");


			}
			if(successo==true)
				break;

		}
		if(successo==false) {
			System.out.println("Accesso fallito chiudere il programma");
		}
		else {
			System.out.println("Accesso eseguito con successo");
		}
		return successo;
		
	}

	/**
	 * Metodo per la creazione di uno scanner
	 * @return lo scanner creato
	 */
	private static Scanner creaScanner() {
        Scanner creato = new Scanner(System.in);
        creato.useDelimiter(System.getProperty("line.separator"));
        creato.useDelimiter("\n");
        return creato;
    }

	/**
	 * Metodo per la lettura di una stringa in input
	 * @param messaggio il messaggio da visualizzare
	 * @return la stringa inserita
	 */
    public static String leggiStringa(String messaggio) {
        System.out.print(messaggio);
        return lettore.next();
    }

	/**
	 * Metodo per la lettura di una stringa non vuota in input
	 * @param messaggio  il messaggio da visualizzare
	 * @return la stringa inserita
	 */
    public static String leggiStringaNonVuota(String messaggio) {
        boolean finito = false;
        String lettura = null;
        do {
            lettura = leggiStringa(messaggio);
            lettura = lettura.trim();
            if (lettura.length() > 0)
                finito = true;
            else
                System.out.println(ERRORE_STRINGA_VUOTA);
        } while (!finito);

        return lettura;
    }

	/**
	 * Metodo per la lettura di un intero in input
	 * @param messaggio  il messaggio da visualizzare
	 * @return l'intero inserito
	 */
	public static int leggiIntero(String messaggio) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			System.out.print(messaggio);
			try {
				valoreLetto = lettore.nextInt();
				finito = true;
			} catch (InputMismatchException e) {
				System.out.println(ERRORE_FORMATO);
				String daButtare = lettore.next();
			}
		} while (!finito);
		return valoreLetto;
	}

	/**
	 * Metodo per la lettura di un intero in input , con massimo e minimo
	 * @param messaggio il messaggio da visualizzare
	 * @param minimo il minimo valore da inserire
	 * @param massimo il massimo valore da inserire
	 * @return il valore inserito
	 */
	public static int leggiIntero(String messaggio, int minimo, int massimo) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			valoreLetto = leggiIntero(messaggio);
			if (valoreLetto >= minimo && valoreLetto <= massimo)
				finito = true;
			else if (valoreLetto < minimo)
				System.out.println(ERRORE_MINIMO + minimo);
			else
				System.out.println(ERRORE_MASSIMO + massimo);
		} while (!finito);

		return valoreLetto;
	}

	/**
	 * Metodo per la lettura di una categoria in input
	 * @param sistema il sistema in cui cercare la categoria
	 * @return la categoria cercata
	 */
	public static Categoria leggiCategoria(Sistema sistema){
		Categoria trovata=null;
		do {
			String nome = leggiStringaNonVuota("Inserisci il nome della categoria da visualizzare:");
			trovata = sistema.findCategoria(nome);
			if (trovata == null) {
				System.out.println(CATEGORIA_NON_PRESENTE);
			}
		}while(trovata==null);

			return trovata;
		}

}
