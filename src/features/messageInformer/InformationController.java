package features.messageInformer;

import infraestructure.PresentationManager;

public class InformationController {
    private final PresentationManager _presentationManager;
    
    public InformationController(PresentationManager presentationManager) 
    {
        _presentationManager = presentationManager;
    }
    
    public void close(String message){
        _presentationManager.closeWindow("Information_" + message.replace(" ", "_"));
    }
}
