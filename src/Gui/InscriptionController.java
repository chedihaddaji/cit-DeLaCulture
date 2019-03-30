/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class InscriptionController implements Initializable {

    @FXML
    private ImageView bck;

    private static final Effect frostEffect =
    new BoxBlur(10, 10, 10);
    @FXML
    private JFXComboBox<String> accountType;
    @FXML
    private TextField interets;
    @FXML
    private JFXCheckBox Cmusique;
    @FXML
    private JFXCheckBox Ccinema;
    @FXML
    private JFXCheckBox CTheatre;
    @FXML
    private JFXCheckBox CPeintre;
    @FXML
    private JFXCheckBox CDance;
    @FXML
    private AnchorPane toload;
    @FXML
    private JFXComboBox<String> Domaine;
    @FXML
    private JFXComboBox<String> SousDomaine;
    @FXML
    private JFXTextField nom;
    @FXML
    private RadioButton homme;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField mdp;
    @FXML
    private JFXTextField tel;
    @FXML
    private RadioButton femme;
    @FXML
    private DatePicker date;
    @FXML
    private JFXTextArea bio;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bck.setEffect(frostEffect);
        accountType.setItems(FXCollections.observableArrayList("Amateur d'art", "Artiste"));
        accountType.setValue("Amateur d'art");
        toload.setVisible(false);
        interets.setDisable(true);
        Domaine.setItems(FXCollections.observableArrayList("Musique", "Cinema", "Theatre", "Peintre", "Dance"));
        Domaine.setValue("Musiqe");
        SousDomaine.setItems(FXCollections.observableArrayList("Chant", "Arrangement", "Production", "Instrument"));
        SousDomaine.setValue("Chant");
        homme.setSelected(true);
    }    

    @FXML
    private void load(ActionEvent event) {
        if(accountType.getValue().equals("Amateur d'art"))
            toload.setVisible(false);
            else
            toload.setVisible(true);
    }

    @FXML
    private void loadSec(ActionEvent event) {
        switch(Domaine.getValue())
        {
            case "Musique" :
                SousDomaine.setItems(FXCollections.observableArrayList("Chant", "Arrangement", "Production", "Instrument"));
                SousDomaine.setValue("Chant");
                break;
            case "Cinema" :
                SousDomaine.setItems(FXCollections.observableArrayList("Acteur", "Producteur", "Directeur Artistique", "Cadreur"));
                SousDomaine.setValue("Acteur");
                break;    
            case "Theatre" :
                SousDomaine.setItems(FXCollections.observableArrayList("Acteur", "Producteur", "Staff"));
                SousDomaine.setValue("Acteur");
                break;
            case "Peintre" :
                SousDomaine.setItems(FXCollections.observableArrayList("Peinteur", "Collecteur", "etc"));
                SousDomaine.setValue("Peinteur");
                break;
            case "Dance" :
                SousDomaine.setItems(FXCollections.observableArrayList("Danceur", "choreographe", "etc"));
                SousDomaine.setValue("Danceur");
                break;
            default:
                break;
        }
    }

    @FXML
    private void Int(ActionEvent event) {
        String res = "";
        if(Cmusique.isSelected())
            res = "Musique";
        if(Ccinema.isSelected())
        {
            if(Cmusique.isSelected())
                res += ",";
            res += "Cinema";
        }
        if(CTheatre.isSelected())
        {
            if(Cmusique.isSelected() || Ccinema.isSelected())
                res += ",";
            res += "Theatre";
        }
        if(CPeintre.isSelected())
        {
            if(Cmusique.isSelected() || Ccinema.isSelected() || CTheatre.isSelected())
                res += ",";
            res += "Peintre";
        }
        if(CDance.isSelected())
        {
            if(Cmusique.isSelected() || Ccinema.isSelected() || CTheatre.isSelected() || CPeintre.isSelected())
                res += ",";
            res += "Dance";
        }
        
        interets.setText(res);
                
    }

    @FXML
    private void h(ActionEvent event) {
        femme.setSelected(false);
    }

    @FXML
    private void f(ActionEvent event) {
        homme.setSelected(false);
    }

    @FXML
    private void Next(ActionEvent event) {
        String sexe = "";
        if(femme.isSelected())
            sexe = "femme";
        else
            sexe = "homme";
        String token ="";
        User u = new User(nom.getText(),prenom.getText(),email.getText(),mdp.getText(),token,Integer.valueOf(tel.getText()),Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),accountType.getValue(),sexe, interets.getText(), "avatar");
        UserService us = new UserService();
        if(accountType.getValue().equals("Amateur d'art"))
        us.insertAmateur(u);
        else
        {
            u.setPieceIdentite("");
            u.setDomaine(Domaine.getValue());
            u.setSousDomaine(SousDomaine.getValue());
            u.setBio(bio.getText());
            us.insertArtiste(u);
        }
        
    }
    
}
