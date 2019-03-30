/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.User;
import Gui.NavigationController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Dell
 */
public class SingletonNavigation {
    NavigationController NavCont;
    User loggedInUser;
    Parent root;
    static SingletonNavigation instance;
    
    private SingletonNavigation()
    {
        try
        {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Gui/Navigation.fxml"));
        root = loader.load();
        NavCont = loader.getController();
        }
        catch(Exception exception)
        {
            Logger.getLogger(SingletonNavigation.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    
    
    
    
    
    
    
    public NavigationController getNavCont() {return NavCont;}

    public User getLoggedInUser() {return loggedInUser;}

    public void setLoggedInUser(User loggedInUser) {this.loggedInUser = loggedInUser;}

    public Parent getRoot() {return root;}


    public static SingletonNavigation getInstance() 
    {
        if(instance == null)
        {
            instance = new SingletonNavigation();
            return instance;
        }
        else
            return instance;
    }

   
    
}
