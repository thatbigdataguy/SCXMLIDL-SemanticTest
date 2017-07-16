package thesis.scxmlidllamp.main;


import org.junit.Test;

import thesis.scxmlidllamp.api.Interpreter;
import thesis.scxmlidllamp.api.Monitor;
import thesis.scxmlidllamp.commonsscxml.MonitorImpl;
import thesis.scxmlidllamp.commonsscxml.SCXMLImplementationImpl;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Interpreter i = new SCXMLImplementationImpl()
				.fromURI("./resources/test2.xml");
		
	
	
		Monitor callBack = new MonitorImpl();
		System.out.println(i.step());
		
		i.addMonitor(callBack);
		
		System.out.println(i.isIn("s1"));
		i.recieve("s2");
		System.out.println(i.step());
		System.out.println(i.isIn("s2"));
		i.recieve("s3");
		System.out.println(i.step());
		System.out.println(i.isIn("s3"));
		i.recieve("s4");
		System.out.println(i.step());
		System.out.println(i.isIn("done"));
			
//
//		while (i.step() > 0) {
//			i.recieve("s4");
//			i.recieve("s3");
//			i.recieve("s2");
//			System.out.println(i.step());
//		}
//	
	
	
	}

	@Test
	public void Test(){
		assert ("test"=="test");
	}
	
	
	
}
