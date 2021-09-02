package application;

import Modele.CompteBancaire;

public interface ControlledScreen {
    
    //This method will allow the injection of the Parent ScreenPane
    public void setScreenParent(ScreensController screenPage);
    public void setComptebancaire(CompteBancaire cb);
}
