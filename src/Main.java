import construtionSet.dependencyManager.DependencyInjectionManager;
import construtionSet.dependencyManager.IDependencyInjectionManager;
import construtionSet.dependencyInjection.contracts.IServiceLocator;
import construtionSet.dependencyInjection.implementations.ServiceLocatorImpl;
import infraestructure.PresentationManager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        IServiceLocator locator = ServiceLocatorImpl.getInstance();
        
        IDependencyInjectionManager manager = new DependencyInjectionManager();
        manager.AddDependences();
        
        var presentationManager = (PresentationManager) locator.getService(PresentationManager.class);
        presentationManager.startLogin();
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }
}