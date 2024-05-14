import Presentation.Controller.LibraryController;
import Presentation.PresentationManager;
import Presentation.View.LibraryView;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PresentationManager();
            }
        });
    }
}