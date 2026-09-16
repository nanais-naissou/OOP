package examples.pingpong;

public class Ping {
	int nrounds;

	public void run(Pong pong) {
		try {
			pong.ping(this);
		} catch (StackOverflowError ex) {
			System.out.println("nrounds=" + nrounds);
			System.out.println(" -> depth=" + (2 * nrounds));
		}
	}

	public void pong(Pong pong) {
		nrounds++;
		pong.ping(this);
	}
}