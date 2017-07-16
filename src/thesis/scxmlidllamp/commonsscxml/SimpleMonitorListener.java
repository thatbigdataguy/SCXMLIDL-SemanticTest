package thesis.scxmlidllamp.commonsscxml;

import java.io.Serializable;

import org.apache.commons.scxml2.SCXMLListener;
import org.apache.commons.scxml2.model.EnterableState;
import org.apache.commons.scxml2.model.Transition;
import org.apache.commons.scxml2.model.TransitionTarget;

import thesis.scxmlidllamp.api.Monitor;


public class SimpleMonitorListener implements SCXMLListener {
	
	Monitor m;
	SimpleMonitorListener (Monitor m) {
	this.m=m;
	}

	@Override
	public void onEntry(EnterableState arg0) {
		// TODO Auto-generated method stub
//		System.out.println("onEntry");
		m.onEntry();
	}

	@Override
	public void onExit(EnterableState arg0) {
		// TODO Auto-generated method stub
//		System.out.println("onExit");
		m.onExit();
	}

	@Override
	public void onTransition(TransitionTarget arg0, TransitionTarget arg1, Transition arg2, String arg3) {
		// TODO Auto-generated method stub
//		System.out.println("onTransition");
		m.onTransition();
	}

}
