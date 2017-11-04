package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Server extends ServerBuild{

	public Server(int port, ServerMainBuild serverMain) {
		super(port, serverMain);
	}

	@Override
	protected void newClient(Socket client) {
		try {
			Scanner sc = new Scanner(client.getInputStream());
			String login = sc.nextLine();
			//login anfang
			Account acc = new Account(login.split("|")[0], login.split("|")[1]);
			//login ende
			this.Clients.put(acc, client);
			this.ClientPrintsreams.put(acc, new PrintStream(client.getOutputStream()));
			serverMain.setOnline(acc);
		} catch (IOException e) {e.printStackTrace();}
	}

	@Override
	protected void read(String message, AccountBuild p) {
		
	}

	@Override
	public void send(AccountBuild id, String message) {
		for(AccountBuild acc: this.ClientPrintsreams.keySet()){
			if(acc.equals(id))this.ClientPrintsreams.get(acc).println(message);
		}
	}

}
