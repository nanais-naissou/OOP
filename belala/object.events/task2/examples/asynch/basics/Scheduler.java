package examples.asynch.basics;

public class Scheduler {
	private static Scheduler self;

	static Scheduler self() {
		return self;
	}

	private Runnable next;

	Scheduler() {
		self = this;
	}

	void loop() {
		while (next != null) {
			Runnable r = next;
			next = null;
			r.run();
		}
	}

	public void post(Runnable r) {
		if (next != null)
			throw new IllegalStateException();
		next = r;
	}

}
