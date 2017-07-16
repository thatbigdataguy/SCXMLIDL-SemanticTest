package thesis.scxmlidllamp.commonsscxml;

import thesis.scxmlidllamp.api.Monitor;

public class MonitorImpl implements Monitor{

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		System.out.println("onExit From my Monitor");
	}

	@Override
	public void onEntry() {
		// TODO Auto-generated method stub
		System.out.println("onEntry From my Monitor");
	}

	@Override
	public void onTransition() {
		// TODO Auto-generated method stub
		System.out.println("onTransition From my Monitor");
	}

}
