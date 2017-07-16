package thesis.scxmlidllamp.test;

import java.util.LinkedList;
import java.util.Queue;

import thesis.scxmlidllamp.api.Interpreter;
import thesis.scxmlidllamp.api.Monitor;
import thesis.scxmlidllamp.commonsscxml.SCXMLImplementationImpl;

public class LifeCycleTest {

	public enum Callback {
		OnExit, OnEntry, OnTransition
	}

	public void check_Callback(Callback c) {

		// CHeck if callback sequence is equal to the actual callback sequence
		if (callBackSeq.peek() == c) {
			System.out.println("check_Callback:" + c + " Expected:" + callBackSeq.peek() + " Result: True");
		} else {
			System.out.println("check_Callback:" + c + " Expected:" + callBackSeq.peek() + " Result: False");

		}
		callBackSeq.poll();

	}
	// assert(!callBackSeq.isEmpty());
	// assert(callBackSeq.poll()==c);

	static Queue<Callback> callBackSeq = new LinkedList<Callback>();

	public static void main(String args[]) {
		System.out.println("----------------------Test Life Cycle-----------------------");

		stopWatch();
		lamp();
		Test1();
		Test2();
	}

	public static void stopWatch() {

		System.out.println("----------------------Stop watch-----------------------");

		callBackSeq.clear();

		// Load SCXML from URI
		Interpreter i = new SCXMLImplementationImpl().fromURI("./resources/stopwatch.xml");
		System.out.println("Interpreter State After fromURI():" + i.getInterpreterState());
		i.step();
		System.out.println("Interpreter State After step():" + i.getInterpreterState());

		// Add sequence checking monitor to Interpreter
		Monitor m = new SequenceCheckingMonitor();
		i.addMonitor(m); // INSTANTIATED
		System.out.println("Interpreter State After addMonitor():" + i.getInterpreterState());

		// Callback sequence while processing events in a queue
		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition); // watch.start
		callBackSeq.add(Callback.OnEntry);

		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition); // watch.stop
		callBackSeq.add(Callback.OnEntry);

		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition); // watch.reset
		callBackSeq.add(Callback.OnEntry);

		// Check if the active state is "reset"
		System.out.println("isIn reset: " + i.isIn("reset"));

		i.recieve("watch.start"); // READY
		System.out.println("Interpreter State After recieve(watch.start):" + i.getInterpreterState());
		i.step(); // IDLE
		System.out.println("Interpreter State After step():" + i.getInterpreterState());

		// Check if the active state is "running"
		System.out.println("Running? =" + i.isIn("running"));
		i.recieve("watch.stop"); // READY
		System.out.println("Interpreter State After recieve(watch.stop):" + i.getInterpreterState());
		i.step(); // IDLE
		System.out.println("Interpreter State After step():" + i.getInterpreterState());

		// Check if the active state is "stopped"
		System.out.println("Stopped? =" + i.isIn("stopped"));
		i.recieve("watch.reset"); // READY
		System.out.println("Interpreter State After recieve(watch.reset):" + i.getInterpreterState());
		i.step(); // IDLE
		System.out.println("Interpreter State After step():" + i.getInterpreterState());

		// Check if the active state is "reset"
		System.out.println("Reset? =" + i.isIn("reset"));

		// Check if the active state is "Finished", should be False
		System.out.println("Finished? = " + i.isIn("done"));

	}

	public static void lamp() {

		System.out.println("----------------------Lamp-----------------------");

		callBackSeq.clear();

		Interpreter i = new SCXMLImplementationImpl().fromURI("./resources/Lamp.scxml");
		System.out.println("Interpreter State After fromURI():" + i.getInterpreterState());
		i.step();
		System.out.println("Interpreter State After step():" + i.getInterpreterState());
		Monitor m = new SequenceCheckingMonitor();

		i.addMonitor(m); // INSTANTIATED

		System.out.println("Interpreter State After addMonitor():" + i.getInterpreterState());

		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition); // off
		callBackSeq.add(Callback.OnEntry);

		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition); // on
		callBackSeq.add(Callback.OnEntry);

		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition); // off
		callBackSeq.add(Callback.OnEntry);

		System.out.println("isIn ON: " + i.isIn("on"));

		i.recieve("touch"); // READY
		System.out.println("Interpreter State After recieve(on):" + i.getInterpreterState());
		i.step(); // IDLE
		System.out.println("Interpreter State After step():" + i.getInterpreterState());
		System.out.println("OFF? =" + i.isIn("off"));
		i.recieve("touch"); // READY
		System.out.println("Interpreter State After recieve(touch):" + i.getInterpreterState());
		i.step(); // IDLE
		System.out.println("Interpreter State After step():" + i.getInterpreterState());
		System.out.println("ON? =" + i.isIn("on"));
		i.recieve("touch"); // READY
		System.out.println("Interpreter State After recieve(touch):" + i.getInterpreterState());
		i.step(); // IDLE
		System.out.println("Interpreter State After step():" + i.getInterpreterState());
		System.out.println("OFF? =" + i.isIn("off"));

		System.out.println("Finished? = " + i.isIn("done"));

	}

	public static void Test1() {

		System.out.println("----------------------Test 1-----------------------");

		callBackSeq.clear();

		Interpreter i = new SCXMLImplementationImpl().fromURI("./resources/test1.xml");
		System.out.println("Interpreter State After fromURI():" + i.getInterpreterState());
		i.step();
		System.out.println("Interpreter State After step():" + i.getInterpreterState());
		Monitor m = new SequenceCheckingMonitor();

		i.addMonitor(m); // INSTANTIATED

		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition); // s2
		callBackSeq.add(Callback.OnEntry);

		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition);
		callBackSeq.add(Callback.OnEntry);

		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition);
		callBackSeq.add(Callback.OnEntry);

		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition);
		callBackSeq.add(Callback.OnEntry);

		i.recieve("s2"); // READY
		System.out.println("Interpreter State After recieve(s2):" + i.getInterpreterState());
		i.step(); // FINISHED
		System.out.println("Interpreter State After step():" + i.getInterpreterState());
		System.out.println("Finished? =" + i.isIn("done"));

	}

	public static void Test2() {
		System.out.println("----------------------Test 2-----------------------");

		callBackSeq.clear();
		Interpreter i = new SCXMLImplementationImpl().fromURI("./resources/test2.xml");
		System.out.println("Interpreter State After fromURI():" + i.getInterpreterState());
		i.step();
		System.out.println("Interpreter State After step():" + i.getInterpreterState());

		Monitor m = new SequenceCheckingMonitor(); 

		i.addMonitor(m); // INSTANTIATED
		System.out.println("Interpreter State After addMonitor():" + i.getInterpreterState());

		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition); // s2
		callBackSeq.add(Callback.OnEntry);

		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition); // s3
		callBackSeq.add(Callback.OnEntry);

		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition); // s4
		callBackSeq.add(Callback.OnEntry);

		callBackSeq.add(Callback.OnExit);
		callBackSeq.add(Callback.OnTransition); // final
		callBackSeq.add(Callback.OnEntry);

		callBackSeq.add(Callback.OnExit);

		i.recieve("s2"); // READY
		System.out.println("Interpreter State After recieve(s2):" + i.getInterpreterState());
		i.step(); // IDLE
		System.out.println("Interpreter State After step():" + i.getInterpreterState());

		i.recieve("s3"); // READY
		System.out.println("Interpreter State After recieve(s3):" + i.getInterpreterState());
		i.step(); // IDLE
		System.out.println("Interpreter State After step():" + i.getInterpreterState());

		i.recieve("s4"); // READY
		System.out.println("Interpreter State After recieve(s4):" + i.getInterpreterState());
		i.step(); // FINISHED
		System.out.println("Interpreter State After step():" + i.getInterpreterState());

		System.out.println("Finished? = " + i.isIn("done"));

	}

}
