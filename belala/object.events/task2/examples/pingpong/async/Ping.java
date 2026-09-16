package examples.pingpong.async;

public class Ping implements Runnable {

    int nrounds;
    Pong pong;
    PingPong pp;

    Ping(PingPong pp, Pong pong) {
        this.pp = pp;
        this.pong = pong;
        pp.post(this);
    }

    public void pong(Pong pong) {
        nrounds++;
        if (nrounds % 1_000_000 == 0)
            System.out.printf(" %d rounds\n", nrounds);
        pp.post(this);
    }

    @Override
    public void run() {
        pong.ping(this);
    }
}
