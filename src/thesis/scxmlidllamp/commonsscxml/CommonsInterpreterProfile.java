package thesis.scxmlidllamp.commonsscxml;

import org.apache.commons.scxml2.SCXMLExecutor;

public interface CommonsInterpreterProfile {

	public void setExecutorContext();

	public void initiateExecutor();

	// get SCXML Executor Instance to get additional methods.

	public SCXMLExecutor getCommonsScxmlExecutor();

	// All state Ids in the state machine
	
}