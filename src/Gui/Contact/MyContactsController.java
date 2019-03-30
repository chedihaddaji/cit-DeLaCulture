/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Contact;

import Entities.User;
import Services.UserService;
import Utils.SingletonNavigation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class MyContactsController implements Initializable {
    
    @FXML
    private AnchorPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       VBox Container = new VBox();  // main container for all data specific to a contact

       // Scroll pane to display all the found contacts
        ScrollPane scrollPane = new ScrollPane(Container);
        scrollPane.setPrefSize(816, 250);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        AnchorPane.setTopAnchor(scrollPane, 0.);
        Container.setPrefWidth(816);
        Container.setPrefHeight(250);
        
        content.setRightAnchor(scrollPane, 0.);
        content.setBottomAnchor(scrollPane, 0.);
        content.setLeftAnchor(scrollPane, 0.);
    
        Container.setPadding(new Insets(30,30,30,30));
        
        for(User u : SingletonNavigation.getInstance().getLoggedInUser().getFriendList())
        {
            //HBOX
            HBox Hb = new HBox();
          
             // contact avatar : first element
            ImageView imgView = new ImageView("/Uploads/"+u.getAvatar());
            imgView.setFitHeight(80);
            imgView.setFitWidth(80);
            
            Label lb = new Label(u.getNom()+" "+u.getPrenom());
            lb.setMinWidth(300);
            lb.setStyle(dayLabel);
            Label lbt = new Label(u.getTypeCompte());
            lbt.setStyle(st);
            
            VBox vb = new VBox();

            
            vb.getChildren().add(lb);
          
            vb.getChildren().add(lbt);
            
            VBox vbb = new VBox();
            
            HBox hbb = new HBox();
            Button message = new Button("Envoyer message");
            Button remove = new Button("Supprimer");
            message.setMinWidth(130);
            message.setMinHeight(40);
            message.setStyle(AButton);
            remove.setMinWidth(130);
            remove.setMinHeight(40);
            remove.setStyle(DButton);
            Label sep1 = new Label("");
            sep1.setMinHeight(10);
            Label sep = new Label("");
            sep.setMinWidth(50);
                        vbb.getChildren().add(sep1);
            vbb.getChildren().add(hbb);
            
            message.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {
                     //To DO 
                    System.err.println("message redirect");
                }
            });
            
            remove.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {
                    UserService us = new UserService();
                    String conList = us.getContactListString(SingletonNavigation.getInstance().getLoggedInUser().getId());
                    SingletonNavigation.getInstance().getLoggedInUser().getFriendList().remove(u);
                    
                    String newConList = "";
                    if(conList.indexOf(String.valueOf(u.getId())) == 0)
                    {
                        
                        if(removeCharAt(conList,conList.indexOf(String.valueOf(u.getId()))).equals(""))
                            newConList = ",";
                        else
                            newConList = removeCharAt(conList,conList.indexOf(String.valueOf(u.getId())));
                    }
                    else
                    {
                        newConList = removeTwoCharAt(conList,conList.indexOf(String.valueOf(u.getId())));
                    }
                    us.setContactList(SingletonNavigation.getInstance().getLoggedInUser().getId(),newConList);
                    updateList();
                }
            });
                
            
            
            
            
            
            hbb.getChildren().add(message);
            hbb.getChildren().add(sep);
            hbb.getChildren().add(remove);
            
            Hb.getChildren().add(imgView);
            Hb.getChildren().add(vb);
            Hb.getChildren().add(vbb);
        
           // Add all the event elements to the event container
            
      
          
            
            
            
            Container.getChildren().add(Hb);
        }
        
        scrollPane.setStyle("-fx-background : none");
        Container.setStyle("-fx-background : none");
        content.getChildren().add(scrollPane);
        
    }    
    public void updateList()
    {
        VBox Container = new VBox();  // main container for all data specific to a contact

       // Scroll pane to display all the found contacts
        ScrollPane scrollPane = new ScrollPane(Container);
        scrollPane.setPrefSize(816, 250);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        AnchorPane.setTopAnchor(scrollPane, 0.);
        Container.setPrefWidth(816);
        Container.setPrefHeight(250);
        
        content.setRightAnchor(scrollPane, 0.);
        content.setBottomAnchor(scrollPane, 0.);
        content.setLeftAnchor(scrollPane, 0.);
    
        Container.setPadding(new Insets(30,30,30,30));
        
        for(User u : SingletonNavigation.getInstance().getLoggedInUser().getFriendList())
        {
            //HBOX
            HBox Hb = new HBox();
          
             // contact avatar : first element
            ImageView imgView = new ImageView("/Uploads/"+u.getAvatar());
            imgView.setFitHeight(80);
            imgView.setFitWidth(80);
            
            Label lb = new Label(u.getNom()+" "+u.getPrenom());
            lb.setMinWidth(300);
            lb.setStyle(dayLabel);
            Label lbt = new Label(u.getTypeCompte());
            lbt.setStyle(st);
            
            VBox vb = new VBox();

            
            vb.getChildren().add(lb);
          
            vb.getChildren().add(lbt);
            
            VBox vbb = new VBox();
            
            HBox hbb = new HBox();
            Button message = new Button("Envoyer message");
            Button remove = new Button("Supprimer");
            message.setMinWidth(130);
            message.setMinHeight(40);
            message.setStyle(AButton);
            remove.setMinWidth(130);
            remove.setMinHeight(40);
            remove.setStyle(DButton);
            Label sep1 = new Label("");
            sep1.setMinHeight(10);
            Label sep = new Label("");
            sep.setMinWidth(50);
                        vbb.getChildren().add(sep1);
            vbb.getChildren().add(hbb);
            
            message.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {
                     //To DO 
                    System.out.println("message redirect");
                }
            });
            
            remove.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {
                    UserService us = new UserService();
                    String conList = us.getContactListString(SingletonNavigation.getInstance().getLoggedInUser().getId());
                    SingletonNavigation.getInstance().getLoggedInUser().getFriendList().remove(u);
                    String newConList = removeCharAt(conList,conList.indexOf(String.valueOf(u.getId())));
                    us.setContactList(SingletonNavigation.getInstance().getLoggedInUser().getId(),newConList);
                }
            });
                
            
            
            
            
            
            hbb.getChildren().add(message);
            hbb.getChildren().add(sep);
            hbb.getChildren().add(remove);
            
            Hb.getChildren().add(imgView);
            Hb.getChildren().add(vb);
            Hb.getChildren().add(vbb);
        
           // Add all the event elements to the event container
            
      
          
            
            
            
            Container.getChildren().add(Hb);
        }
        
        
        content.getChildren().add(scrollPane);
    }
    
    public static String removeCharAt(String s, int pos) {
   StringBuffer buf = new StringBuffer( s.length() - 1 );
   buf.append( s.substring(0,pos) ).append( s.substring(pos+2) );
   return buf.toString();
}
        public static String removeTwoCharAt(String s, int pos) {
   StringBuffer buf = new StringBuffer( s.length() - 2 );
   buf.append( s.substring(0,pos-1) ).append( s.substring(pos+2) );
   return buf.toString();
}
    
     final String trans = "-fx-background-color: #ef7c5b;\n";
      final String st = "-fx-padding: 0 0 0 15;\n" +
"    -fx-spacing: 50;\n" +
"    -fx-font-weight: bold;\n" +
"    -fx-font-size: 16;";
    
        final String dayLabel = "-fx-padding: 10 0 0 15;\n" +
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
}
