package client;

public class Client extends ClientBuild{

	public Client(String name, int port, int heartBeatDuration, String IP) {
		super(name, port, heartBeatDuration, IP);
	}

	@Override
	protected void heartbeat() {
		
	}

	@Override
	protected void read(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(String message) {
		
	}

}
