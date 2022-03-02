package it.unibs.ingstfw1;

public class Utente {
	
	private String username;
	private String password;
	
	public Utente(String _username, String _password) {
		this.setUsername(_username);
		this.setPassword(_password);
	}
	
	public static boolean sameUtente(Utente u1, Utente u2) {
		boolean equal=false;
		if(u1.username.equals(u2.username) && u1.password.equals(u2.password)) {
			equal=true;
		}
		return equal;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
