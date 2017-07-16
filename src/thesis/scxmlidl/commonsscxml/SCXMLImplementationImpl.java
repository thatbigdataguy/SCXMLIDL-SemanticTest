package thesis.scxmlidl.commonsscxml;

import thesis.scxmlidl.api.Interpreter;
import thesis.scxmlidl.api.SCXMLImplementation;

public class SCXMLImplementationImpl implements SCXMLImplementation{

	@Override
	public Interpreter fromURI(String url) {
		// TODO Auto-generated method stub
		Interpreter i = new CommonsInterpreter(url);
		return i;
	}

}
