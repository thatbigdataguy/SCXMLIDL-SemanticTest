package thesis.scxmlidllamp.lamp;

import org.apache.commons.scxml2.SCXMLExecutor;



public class LampFrame {

	
	private SCXMLExecutor executor;
    private Lamp stopWatch;
    

    public LampFrame(SCXMLExecutor executor) {
        super();

       
        this.executor = executor;
        this.stopWatch = (Lamp) executor.getRootContext().get("stopWatch");
    }
}
