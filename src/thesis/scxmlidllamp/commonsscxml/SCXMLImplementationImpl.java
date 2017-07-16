package thesis.scxmlidllamp.commonsscxml;

import thesis.scxmlidllamp.api.Interpreter;
import thesis.scxmlidllamp.api.SCXMLImplementation;

public class SCXMLImplementationImpl implements SCXMLImplementation{

	@Override
	public Interpreter fromURI(String url) {
		// TODO Auto-generated method stub
		Interpreter i = new CommonsInterpreter(url);
		return i;
	}

}
