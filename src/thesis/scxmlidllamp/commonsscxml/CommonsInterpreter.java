package thesis.scxmlidllamp.commonsscxml;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.scxml2.Context;
import org.apache.commons.scxml2.Evaluator;
import org.apache.commons.scxml2.SCXMLExecutor;
import org.apache.commons.scxml2.TriggerEvent;
import org.apache.commons.scxml2.env.SimpleErrorReporter;
import org.apache.commons.scxml2.env.jexl.JexlEvaluator;
import org.apache.commons.scxml2.io.SCXMLReader;
import org.apache.commons.scxml2.model.ModelException;
import org.apache.commons.scxml2.model.SCXML;

import thesis.scxmlidllamp.api.Interpreter;
import thesis.scxmlidllamp.api.Monitor;
import thesis.scxmlidllamp.stopWatch.StopWatch;
import thesis.scxmlidllamp.stopWatch.StopWatchFrame;

public class CommonsInterpreter implements Interpreter, CommonsInterpreterProfile {
	// Commons SCXML
	Evaluator evaluator;
	SCXMLExecutor executor;
	// parse SCXML URL into SCXML model
	SCXML scxml = null;
	
	int currentState;
	int started=0;

	public CommonsInterpreter(String url) {
		// evaluator instance which is used by SCXML engine to evaluate
		// expressions in SCXML
		evaluator = new JexlEvaluator();
		// engine to execute the scxml instance
		executor = new SCXMLExecutor(evaluator, null, new SimpleErrorReporter());

		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		File f = new File(url);

		URL SCXML = null;
		try {
			SCXML = f.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			scxml = SCXMLReader.read(SCXML);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// set state machine (scxml instance) to execute
		try {
			executor.setStateMachine(scxml);
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		currentState = Interpreter.INSTANTIATED;

	}

	// check if state is final then finished other wise stepped.
	// two possibilities, either run the state machine to full or run it
	// step by step.

	@Override
	public int step() {
		// TODO Auto-generated method stub
		if (started==0){
			System.out.println("Started");
			setExecutorContext();
			initiateExecutor();
			try {
				executor.triggerEvents();
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (executor.getStatus().isFinal()) {
				currentState = Interpreter.FINISHED;
			} else {
				currentState = Interpreter.IDLE;
			}
			
			return currentState;
		}else{
			started=1;
			System.out.println("Running");
			
			try {
				executor.triggerEvents();
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (executor.getStatus().isFinal()) {
				currentState = Interpreter.FINISHED;
			} else {
				currentState = Interpreter.IDLE;
			}
			
			
			return currentState;
		}
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub

		currentState = Interpreter.CANCELLED;

	}

	@Override
	public void recieve(String event) {
		// TODO Auto-generated method stub
		executor.addEvent(new TriggerEvent(event, TriggerEvent.SIGNAL_EVENT));
		currentState = Interpreter.READY;
		
	}

	@Override
	public boolean isIn(String stateId) {
		// TODO Auto-generated method stub
		return executor.getStatus().isInState(stateId);
	}

	@Override
	public void addMonitor(Monitor m) {
		// TODO Auto-generated method stub
		executor.addListener(scxml, new SimpleMonitorListener(m));
	}

	@Override
	public void setExecutorContext() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		// create root context storing variables and being used by evaluator
		Context rootContext = evaluator.newContext(null);

		// create stopWatch object and add it to rootContext
		// to be able to script with that in SCXML.
		StopWatch stopWatch = new StopWatch();
		rootContext.set("stopWatch", stopWatch);

		// set the root context for the engine
		executor.setRootContext(rootContext);
	}

	@Override
	public void initiateExecutor() {

		// TODO Auto-generated method stub
		// initiate the execution of the state machine
		try {
			executor.go();
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new StopWatchFrame(executor);
		
		started=1;
	}

	@Override
	public SCXMLExecutor getCommonsScxmlExecutor() {
		// TODO Auto-generated method stub
		return executor;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

		currentState = Interpreter.INSTANTIATED;
	}

	@Override
	public int getInterpreterState() {
		// TODO Auto-generated method stub
		return currentState;
	}

}
