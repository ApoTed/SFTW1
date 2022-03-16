package it.unibs.ingstfw1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilita {
	private static final String ERRORE_FORMATO = "Attenzione il dato inserito non e' nel formato corretto";
	private static final String ERRORE_MINIMO = "Attenzione: e' richiesto un valore maggiore o uguale a ";
	private static final String ERRORE_MASSIMO = "Attenzione: e' richiesto un valore minore o uguale a ";
	public static final String CATEGORIA_NON_PRESENTE = "Categoria non presente";
	private static Scanner lettore = creaScanner();
	
	
	
	public static boolean menuAccesso(DatiUtenti data) {
		boolean successo=false;
		String username=Utilita.leggiStringaNonVuota("Benvenuto inserisci il tuo username: ");
		String password=Utilita.leggiStringaNonVuota("inserisci la tua password: ");
		Utente temp= new Utente(username, password);
		if( data.checkConf(temp)) {
			String newUsername;
			do {
				newUsername=Utilita.leggiStringaNonVuota("inserisci il tuo nuovo nome utente");
				if(data.checkName(newUsername)==true)
					System.out.println("questo nome utente non � disponibile");
			}while(data.checkName(newUsername)==true);
				
			String newPassword=Utilita.leggiStringaNonVuota("inserisci la tua nuova password");
			data.addUtente(newUsername, newPassword, true);
			temp=new Utente(newUsername, newPassword);
			
		}
		for (int i=0;i<3;i++) {
			//accesso da utente già registrato 3 tentativi
			int tentativi = 2 - i;
			if(tentativi<2){
				String nameTry=Utilita.leggiStringaNonVuota("inserisci il tuo nome: ");
				String passwordTry=Utilita.leggiStringaNonVuota("inserisci la tua password: ");
				temp=new Utente(nameTry, passwordTry);
			}
			for (Utente toCompare : data.getListaConfiguratori()) {
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
	
	private static Scanner creaScanner() {
        Scanner creato = new Scanner(System.in);
        //creato.useDelimiter(System.getProperty("line.separator"));
        //creato.useDelimiter("\n");
        return creato;
    }

    public static String leggiStringa(String messaggio) {
        System.out.print(messaggio);
        return lettore.next();
    }

    public static String leggiStringaNonVuota(String messaggio) {
        boolean finito = false;
        String lettura = null;
        do {
            lettura = leggiStringa(messaggio);
            lettura = lettura.trim();
            if (lettura.length() > 0)
                finito = true;
            else
                System.out.println("la stringa inserita non pu� essere vuota");
        } while (!finito);

        return lettura;
    }

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
