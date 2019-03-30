/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Profile;

import Entities.User;
import Services.UserService;
import Utils.SingletonNavigation;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AboutmeController implements Initializable {
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label telephone;
    @FXML
    private Label sexe;
    @FXML
    private Label naissance;
    @FXML
    private Label interets;
    @FXML
    private Label domaine;
    @FXML
    private Label sousdomaine;
    @FXML
    private TextArea bio;
    @FXML
    private Label email;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
        User u = SingletonNavigation.getInstance().getLoggedInUser();
       nom.setText("Nom : " + SingletonNavigation.getInstance().getLoggedInUser().getNom() );
       prenom.setText("Prenom : " + SingletonNavigation.getInstance().getLoggedInUser().getPrenom());
       email.setText("Email : " + SingletonNavigation.getInstance().getLoggedInUser().getEmail());
       telephone.setText("Telephone : " + String.valueOf(SingletonNavigation.getInstance().getLoggedInUser().getTelephone()));
       sexe.setText("Sexe : " + SingletonNavigation.getInstance().getLoggedInUser().getSexe());
       naissance.setText("Date de naissance : " + SingletonNavigation.getInstance().getLoggedInUser().getNaissance());
       interets.setText("Interets : " + SingletonNavigation.getInstance().getLoggedInUser().getInterets());
       
    }    
    
    
    
    public void loadVisitedPorfile(User u)
    {
      /*  firstName.setText("First Name : " + u.getFirstName());
        lastName.setText("Last Name : " + u.getLastName());
        email.setText("Email : " + u.getEmail());
        phoneNumber.setText("Phone Number : " + String.valueOf(u.getPhoneNumber()));
        birthDate.setText("Birth Date : " + String.valueOf(u.getBirthDate()));
        registrationDate.setText("Registation Date : " + String.valueOf(u.getRegistrationDate()));
        address.setText("Address : " + u.getAddress());
        city.setText("City : " + u.getCity());
        zipCode.setText("Zip Code : " + String.valueOf(u.getZipCode()));
        String newsletter = "";
        if(u.getNewsletterSubscription() == true)
        {
            newsletter = "Subscribed";
        }
        else
        {
            newsletter = "Unsubscribed";
        }
        newsLetter.setText("Newsletter : " + newsletter); 
        subscribe.setVisible(false);*/
    }
}
