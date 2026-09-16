package examples.pingpong.async;

public class PingPong {

    Runnable next;

    private static void init(PingPong pp) {
        Pong pong = new Pong();
        new Ping(pp, pong);
    }

    public static void main(String args[]) {
        PingPong pp = new PingPong();
        init(pp);
        pp.loop();
        System.out.println("ginirj"); 
    }

    private void loop() {
        while (next != null) {
        	Runnable r = next; 
            next = null;
            
            r.run();

        }
    }

    void post(Runnable r) {
        next = r;
    }
}
