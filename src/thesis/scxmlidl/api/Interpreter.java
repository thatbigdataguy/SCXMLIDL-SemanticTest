package thesis.scxmlidl.api;

public interface Interpreter {
	static final int CANCELLED = -2;
	static final int FINISHED = -1;
	static final int FAULTED = 0;
	static final int IDLE = 1;
	static final int INSTANTIATED = 2;
	static final int READY = 3;

	int step();

	void cancel();

	void reset();

	void recieve(String event);

	void addMonitor(Monitor mon);

	boolean isIn(String stateId);

	int getInterpreterState();
};
