package thesis.scxmlidl.test;

import thesis.scxmlidl.api.Monitor;
import thesis.scxmlidl.test.LifeCycleTest.Callback;

public class SequenceCheckingMonitor implements Monitor{
	
	LifeCycleTest lct = new LifeCycleTest();


	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		lct.check_Callback(Callback.OnExit);
	}

	@Override
	public void onEntry() {
		// TODO Auto-generated method stub
		lct.check_Callback(Callback.OnEntry);
		
	}

	@Override
	public void onTransition() {
		// TODO Auto-generated method stub
		lct.check_Callback(Callback.OnTransition);

	}
	
	
}