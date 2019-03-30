/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.ContactRequest;
import Entities.User;
import Services.ContactRequestService;
import Services.UserService;
import Utils.SingletonNavigation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class SearchController implements Initializable {
    private  String input;
    @FXML
    private AnchorPane Content;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private  String style = "-fx-font-size : 16; -fx-text-fill : #ffffff; -fx-padding: 0 0 0 10";
    
    
    public void ShowAll() throws Exception
    {
        // clear existing Content if it exists  
        if(Content.getChildren()!=null)
        {
            Content.getChildren().clear();
        }
        UserService us = new UserService();
        List<User> myList = new ArrayList<User>();
        myList = us.getAll();
        
        VBox Container = new VBox(); 
        
        ScrollPane scrollPane = new ScrollPane(Container);
        scrollPane.setPrefSize(1000, 630);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        AnchorPane.setTopAnchor(scrollPane, 0.);
        Container.setPrefWidth(1000);
        Container.setPrefHeight(630);
        
        Content.setRightAnchor(scrollPane, 0.);
        Content.setBottomAnchor(scrollPane, 0.);
        Content.setLeftAnchor(scrollPane, 0.);
    
        Container.setPadding(new Insets(15,15,15,15));
        
        for(User u : myList)
        {        
            HBox Main = new HBox();

            ImageView img = new ImageView();
            img.setImage(new Image("/Uploads/"+u.getAvatar()));
            img.setFitWidth(130);
            img.setFitHeight(130);

            VBox vb = new VBox();
            vb.setMinWidth(600);
            Button nom = new Button(u.getNom()+" "+u.getPrenom());
            Label role = new Label(u.getTypeCompte());
            Label Sexe = new Label("Sexe : "+u.getSexe());
            Label interet = new Label("Intérets : "+u.getInterets());
            nom.setStyle("-fx-background-color : none; -fx-text-fill : #4f9eff; -fx-font-weight : bold; -fx-font-size :25; -fx-padding : 10 0 0 10");
            nom.setUnderline(true);
            role.setStyle("-fx-font-size : 16; -fx-font-weight: bold; -fx-text-fill : #b2ff4f; -fx-padding: 0 0 0 10");
            Sexe.setStyle(style);
            interet.setStyle(style);



            vb.getChildren().add(nom);
            vb.getChildren().add(role);
            vb.getChildren().add(Sexe);
            vb.getChildren().add(interet);


            Button add = new Button("Ajouter");
            add.setStyle("-fx-background-color : #333333; -fx-text-fill : #ffffff; -fx-border-color : #ffffff; -fx-border-width : 2px; -fx-font-size : 15; -fx-font-weight : bold;");
            add.setMinHeight(40);
            add.setMinWidth(200);
            add.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {
                    ContactRequest c = new ContactRequest(SingletonNavigation.getInstance().getLoggedInUser().getId(), u.getId());
                    ContactRequestService cs = new ContactRequestService();
                    cs.insert(c);
                    String title = "Demande envoyée";
                    String message = "Une demande e été envoyée a "+u.getNom()+" "+ u.getPrenom() +"\n pour rejoindre votre liste d'ami(e)s";

        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
                }
                });
            VBox butt = new VBox();
            Label sep = new Label("");
            sep.setMinHeight(50);
            butt.getChildren().add(sep);
            butt.getChildren().add(add);



            Separator separator = new Separator();
            separator.setOrientation(Orientation.HORIZONTAL);
            separator.setMaxWidth(950);


            Main.getChildren().add(img);
            Main.getChildren().add(vb);
            Main.getChildren().add(butt);

            
            Label t=new Label("");
            t.setMaxHeight(2);
            Container.getChildren().add(Main);
            Container.getChildren().add(t);
            Container.getChildren().add(separator);

         
           
           
           
            
        }
         scrollPane.setStyle("-fx-background : none");
        Container.setStyle("-fx-background : none");
        Content.getChildren().add(scrollPane);

    }
    
    public void ShowResults() throws Exception
    {
        
       ShowAll();
        
    }
    
}
