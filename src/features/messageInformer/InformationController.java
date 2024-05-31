package features.messageInformer;

import infraestructure.IPresentationManager;
import infraestructure.PresentationManager;

public class InformationController implements IInformationController {
    private final IPresentationManager _presentationManager;
    
    public InformationController(IPresentationManager presentationManager) 
    {
        _presentationManager = presentationManager;
    }
    
    public void closeWindow(){
        _presentationManager.closeWindow(IInformationView.class);
    }
}
