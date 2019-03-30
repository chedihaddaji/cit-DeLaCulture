/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Contact;

import Entities.ContactRequest;
import Entities.Event;
import Entities.User;
import Services.ContactRequestService;
import Services.EventService;
import Services.UserService;
import Utils.SingletonNavigation;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ContactRequestsController implements Initializable {
    @FXML
    private AnchorPane content;
    private static final Effect frostEffect =
    new BoxBlur(5,5, 3);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ShowAll();
        } catch (Exception ex) {
            Logger.getLogger(ContactRequestsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @FXML
    public void ShowAll() throws Exception
    {
        // clear existing content if it exists  
        if(content.getChildren()!=null)
        {
            content.getChildren().clear();
        }
        
        // get Elements to display 
        ContactRequestService cs = new ContactRequestService();
        List<ContactRequest> myList=new ArrayList<ContactRequest>();
        myList = cs.getByUserId(SingletonNavigation.getInstance().getLoggedInUser().getId());
        
        EventService es=new EventService();
        
        VBox Container = new VBox();  // main container for all data specific to a contact request
        
        // Scroll pane to display all the found contact requests
        ScrollPane scrollPane = new ScrollPane(Container);
        scrollPane.setPrefSize(1050, 500);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        AnchorPane.setTopAnchor(scrollPane, 0.);
        Container.setPrefWidth(1050);
        Container.setPrefHeight(500);
        
        content.setRightAnchor(scrollPane, 0.);
        content.setBottomAnchor(scrollPane, 0.);
        content.setLeftAnchor(scrollPane, 0.);
    
        Container.setPadding(new Insets(30,30,30,30));
       
        // iterate through all events and create an event element
        UserService us = new UserService();
        for(ContactRequest c : myList)
        {
            //HBOX
            HBox Hb = new HBox();
          
            
            User u = us.getById(c.getSender_id());
             // sender avatar : first element
            ImageView imgView = new ImageView("/Uploads/"+u.getAvatar());
            imgView.setFitHeight(120);
            imgView.setFitWidth(120);
            
            VBox vb = new VBox();
            
            Label lb = new Label(u.getNom()+" "+u.getPrenom());
            lb.setMinWidth(600);
            lb.setStyle(dayLabel);
            Label lbt = new Label(u.getTypeCompte());
            lbt.setStyle(st);
            
            
            vb.getChildren().add(lb);
            vb.getChildren().add(lbt);
            
            VBox vbb = new VBox();
            Button accept = new Button("Accepter");
            Button deny = new Button("Rejeter");
            accept.setMinWidth(130);
            accept.setMinHeight(40);
            accept.setStyle(AButton);
            deny.setMinWidth(130);
            deny.setMinHeight(40);
            deny.setStyle(DButton);
            Label sep1 = new Label("");
            sep1.setMinWidth(50);
            Label sep = new Label("");
            sep.setMinWidth(50);
            
            accept.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {
                       UserService us = new UserService();
                       String currentContList = us.getContactListString(SingletonNavigation.getInstance().getLoggedInUser().getId());
                        if(currentContList.equals(","))
                       {
                           currentContList = String.valueOf(u.getId()) + ",";
                       }
                       else
                       {
                           currentContList += String.valueOf(u.getId()) + ",";
                       }
                       us.setContactList(SingletonNavigation.getInstance().getLoggedInUser().getId(),currentContList);
                       ContactRequestService cs = new ContactRequestService();
                       SingletonNavigation.getInstance().getLoggedInUser().setFriendList(us.getContactList(SingletonNavigation.getInstance().getLoggedInUser().getId()));
                       cs.delete(c);
                       try
                       {
                       SingletonNavigation.getInstance().getNavCont().loadContactRequests(event);
                       }
                       catch(Exception e)
                       {
                            Logger.getLogger(ContactRequestsController.class.getName()).log(Level.SEVERE, null, e);;
                       }
                       
                }
            });
            
            deny.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {
                    ContactRequestService cs = new ContactRequestService();
                    cs.delete(c);
                    try
                    {
                       SingletonNavigation.getInstance().getNavCont().loadContactRequests(event);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                       
                }
            });
                
            
            
            
            
            
            vbb.getChildren().add(sep1);
            vbb.getChildren().add(accept);
            vbb.getChildren().add(sep);
            vbb.getChildren().add(deny);
            
            Hb.getChildren().add(imgView);
            Hb.getChildren().add(vb);
            Hb.getChildren().add(vbb);
        
           // Add all the event elements to the event container
            Container.getChildren().add(Hb);
      
          
            
        }
        // Finally add all the events inside the Scrollpane to the main content Anchorpane
        scrollPane.setStyle("-fx-background : none");
        Container.setStyle("-fx-background : none");
        content.getChildren().add(scrollPane);
    }
     final String trans = "-fx-background-color: #ef7c5b;\n";
      final String st = "-fx-padding: 0 0 0 15;\n" +
"    -fx-spacing: 50;\n" +
"    -fx-font-weight: bold;\n" +
"    -fx-font-size: 16;";
    
        final String dayLabel = "-fx-padding: 15;\n" +
"    -fx-spacing: 0;\n" +
"    -fx-text-fill: #ffffff;\n" +
"    -fx-font-weight: bold;\n" +
"    -fx-font-size: 20;";
        
         final String AButton = "-fx-background-color: #6fb52c;\n" +
"    -fx-border-width: 2px;\n" +
"    -fx-text-fill: #ffffff;\n" +
"    -fx-font-weight: bold;\n" +
"    -fx-border-color: #ffffff;\n" +
"    -fx-font-size: 17;";
         
       final String DButton = "-fx-background-color: #ff1919;\n" +
"    -fx-border-width: 2px;\n" +
"    -fx-text-fill: #ffffff;\n" +
"    -fx-font-weight: bold;\n" +
"    -fx-border-color: #ffffff;\n" +
"    -fx-font-size: 17;";
   /* 
    @FXML
    public void test()
    {
        ContactRequest c = new ContactRequest();
        ContactRequestService cs = new ContactRequestService();
        List<ContactRequest> tmp = cs.getByUserId(2);
        for(ContactRequest c2 : tmp)
        {
            System.out.println(c2.getReciever_id());
        }
    }*/
    
}
