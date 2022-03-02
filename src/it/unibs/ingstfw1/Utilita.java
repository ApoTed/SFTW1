package it.unibs.ingstfw1;

import java.util.Scanner;

public class Utilita {
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
			//accesso da utente gi� registrato 3 tentativi
			
			for (Utente toCompare : data.getListaUtenti()) {
				if( Utente.sameUtente(toCompare, temp)) {
					
					successo=true;
				}
				else {
					int tentativi=2-i;
					System.out.println("Le credenziali inserite non sono corrette, hai " + tentativi + "tentativi");
				}
			}
			
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

}
