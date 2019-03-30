/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Events;

import Entities.Event;
import Gui.LoginController;
import Services.EventService;
import Utils.SingletonNavigation;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
public class AllEventsController implements Initializable {

    @FXML
    private AnchorPane Content;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane canCenter;
    @FXML
    private AnchorPane optional;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ShowAll();
        } catch (Exception ex) {
            Logger.getLogger(AllEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(SingletonNavigation.getInstance().getLoggedInUser().getTypeCompte().equals("Artiste"))
        {
            canCenter.setLayoutX(15);
            optional.setVisible(true);
            
        }
        else
        {
            optional.setVisible(false);
            canCenter.setLayoutX(185);
        }

    }    

    @FXML
    private void loadCreate(ActionEvent event) throws Exception{
        Content = FXMLLoader.load(getClass().getResource("/Gui/Events/CreateEvent.fxml"));
        root.getChildren().set(16, Content);
    }
     @FXML
    public void ShowAll() throws Exception
    {

        // clear existing Content if it exists  
        if(Content.getChildren()!=null)
        {
            Content.getChildren().clear();
        }
        
        // get Elements to display 
        EventService es=new EventService();
        List<Event> myList=new ArrayList<Event>();
        myList = es.getAll();
        VBox Container = new VBox();  // main container for all data specific to an event
        
        // Scroll pane to display all the found events
        ScrollPane scrollPane = new ScrollPane(Container);
        scrollPane.setPrefSize(830, 520);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        AnchorPane.setTopAnchor(scrollPane, 0.);
        Container.setPrefWidth(830);
        Container.setPrefHeight(520);
        
        Content.setRightAnchor(scrollPane, 0.);
        Content.setBottomAnchor(scrollPane, 0.);
        Content.setLeftAnchor(scrollPane, 0.);
    
        Container.setPadding(new Insets(15,15,15,15));
        // iterate through all events and create an event element
        for(Event e : myList)
        {
             // Event Cover photo : first element
            ImageView imgView = new ImageView();
            imgView.setImage(new Image("/Uploads/"+e.getEventCover()));
            // Event Information : HBOX
            HBox eventInfo = new HBox();
            //eventInfo.setPadding(new Insets(15,15,15,15));
            
                // Event Date : Anchorpane
                AnchorPane Date = new AnchorPane();
                Date.setMaxWidth(100);
                Date.setMaxHeight(100);
                ImageView Calendar = new ImageView();
                Calendar.setImage(new Image("/Images/Calendar.png"));
               
                Label month = new Label(getMonthForInt(e.getEventDateStart().getMonth()).substring(0,3));
                
                Label day = new Label(String.valueOf(e.getEventDateStart().getDay()));
                month.setMinWidth(105);
                month.setStyle(monthLabel);
                day.setStyle(dayLabel);
                Date.getChildren().add(Calendar);
                Date.getChildren().add(month);
                Date.getChildren().add(day);
                // Event Name , place , date span : VBox
                VBox Vb = new VBox();
                //Vb.setPadding(new Insets(15,15,15,15));
                Vb.setMinWidth(527);
                Label Name=new Label(e.getEventName());  
                Name.setStyle(NameLabel);
                Label Datespan = new Label("De " + e.getEventDateStart().toString() + " jusqu'a " + e.getEventDateEnd().toString());
                Label Place = new Label("Place : " + e.getEventPlace());
                Vb.getChildren().add(Name);
                Vb.getChildren().add(Datespan);
                Vb.getChildren().add(Place);
                // Event action buttons : VBox
                VBox Bb = new VBox();
                Button participer = new Button("Je participe");
                participer.setStyle("-fx-background-color : #333333; -fx-text-fill : #ffffff; -fx-border-color : #ffffff; -fx-border-width : 2px;");
                participer.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {
                       String currentParticipants = es.getEventParticipantsString(e.getEventId());
                       if(currentParticipants.equals("none"))
                       {
                           currentParticipants = String.valueOf(SingletonNavigation.getInstance().getLoggedInUser().getId()) + ",";
                       }
                       else
                       {
                           currentParticipants += String.valueOf(SingletonNavigation.getInstance().getLoggedInUser().getId()) + ",";
                       }
                       es.setEventParticipants(e.getEventId(),currentParticipants);
                }
                });
                Button details = new Button("plus de détails");
                details.setStyle("-fx-background-color : #333333; -fx-text-fill : #ffffff; -fx-border-color : #ffffff; -fx-border-width : 2px;");
                details.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {  
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("EventDetails.fxml"));
                    try
                    {
                        if(Content.getChildren()!=null)
                        {
                            Content.getChildren().clear();
                        }
                        AnchorPane tmp = loader.load();
                        Content.getChildren().add(tmp);
                       // EventDetailsController secController = loader.getController();
                        //secController.setE(e);
                       // secController.Populate();
                    }
                    catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
                }
                });
                participer.setMinWidth(120);
                details.setMinWidth(120);
                Label s = new Label("");
                Label se = new Label("");
                s.setMinHeight(20);
                se.setMinHeight(10);
                Bb.getChildren().add(s);
                Bb.getChildren().add(participer);
                Bb.getChildren().add(se);
                Bb.getChildren().add(details);
                
            // Event description : TextArea
            Label desc = new Label("Description :");
            desc.setMaxHeight(30);
            //desc.setStyle(dayLabel);
            
            TextArea dt = new TextArea();
            dt.setMaxWidth(750);
            dt.setMinHeight(70);
            dt.setMaxHeight(70);
            dt.setScrollTop(20);
            dt.setText(e.getEventDescription());
            dt.setEditable(false);
            eventInfo.getChildren().add(Date);
            eventInfo.getChildren().add(Vb);
            eventInfo.getChildren().add(Bb);
            
            
            
           
            
         
           
           
           // Add all the event elements to the event container
           Container.getChildren().add(imgView);
           Container.getChildren().add(eventInfo);
           Container.getChildren().add(desc);
           Container.getChildren().add(dt);
           Label sep = new Label("");
           sep.setMinHeight(40);
           Container.getChildren().add(sep);
            
    }
        // Finally add all the events inside the Scrollpane to the main Content Anchorpane
        Content.getChildren().add(scrollPane);
        
        scrollPane.setStyle("-fx-background : #736e6b");

    }
    
    
    public void ShowbyCat(String Categorie) throws Exception
    {

        // clear existing Content if it exists  
        if(Content.getChildren()!=null)
        {
            Content.getChildren().clear();
        }
        
        // get Elements to display 
        EventService es=new EventService();
        List<Event> myList=new ArrayList<Event>();
        myList = es.getByCategorie(Categorie);
        VBox Container = new VBox();  // main container for all data specific to an event
        
        // Scroll pane to display all the found events
        ScrollPane scrollPane = new ScrollPane(Container);
        scrollPane.setPrefSize(830, 520);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        AnchorPane.setTopAnchor(scrollPane, 0.);
        Container.setPrefWidth(830);
        Container.setPrefHeight(520);
        
        Content.setRightAnchor(scrollPane, 0.);
        Content.setBottomAnchor(scrollPane, 0.);
        Content.setLeftAnchor(scrollPane, 0.);
    
        Container.setPadding(new Insets(15,15,15,15));
        // iterate through all events and create an event element
        for(Event e : myList)
        {
             // Event Cover photo : first element
            ImageView imgView = new ImageView();
            imgView.setImage(new Image("/Uploads/"+e.getEventCover()));
            // Event Information : HBOX
            HBox eventInfo = new HBox();
            //eventInfo.setPadding(new Insets(15,15,15,15));
            
                // Event Date : Anchorpane
                AnchorPane Date = new AnchorPane();
                Date.setMaxWidth(100);
                Date.setMaxHeight(100);
                ImageView Calendar = new ImageView();
                Calendar.setImage(new Image("/Images/Calendar.png"));
               
                Label month = new Label(getMonthForInt(e.getEventDateStart().getMonth()).substring(0,3));
                
                Label day = new Label(String.valueOf(e.getEventDateStart().getDay()));
                month.setMinWidth(105);
                month.setStyle(monthLabel);
                day.setStyle(dayLabel);
                Date.getChildren().add(Calendar);
                Date.getChildren().add(month);
                Date.getChildren().add(day);
                // Event Name , place , date span : VBox
                VBox Vb = new VBox();
                //Vb.setPadding(new Insets(15,15,15,15));
                Vb.setMinWidth(527);
                Label Name=new Label(e.getEventName());  
                Name.setStyle(NameLabel);
                Label Datespan = new Label("De " + e.getEventDateStart().toString() + " jusqu'a " + e.getEventDateEnd().toString());
                Label Place = new Label("Place : " + e.getEventPlace());
                Vb.getChildren().add(Name);
                Vb.getChildren().add(Datespan);
                Vb.getChildren().add(Place);
                // Event action buttons : VBox
                VBox Bb = new VBox();
                Button participer = new Button("Je participe");
                participer.setStyle("-fx-background-color : #333333; -fx-text-fill : #ffffff; -fx-border-color : #ffffff; -fx-border-width : 2px;");
                participer.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {
                       String currentParticipants = es.getEventParticipantsString(e.getEventId());
                       if(currentParticipants.equals("none"))
                       {
                           currentParticipants = String.valueOf(SingletonNavigation.getInstance().getLoggedInUser().getId()) + ",";
                       }
                       else
                       {
                           currentParticipants += String.valueOf(SingletonNavigation.getInstance().getLoggedInUser().getId()) + ",";
                       }
                       es.setEventParticipants(e.getEventId(),currentParticipants);
                }
                });
                Button details = new Button("plus de détails");
                details.setStyle("-fx-background-color : #333333; -fx-text-fill : #ffffff; -fx-border-color : #ffffff; -fx-border-width : 2px;");
                details.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {  
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("EventDetails.fxml"));
                    try
                    {
                        if(Content.getChildren()!=null)
                        {
                            Content.getChildren().clear();
                        }
                        AnchorPane tmp = loader.load();
                        Content.getChildren().add(tmp);
                       // EventDetailsController secController = loader.getController();
                        //secController.setE(e);
                       // secController.Populate();
                    }
                    catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
                }
                });
                participer.setMinWidth(120);
                details.setMinWidth(120);
                Label s = new Label("");
                Label se = new Label("");
                s.setMinHeight(20);
                se.setMinHeight(10);
                Bb.getChildren().add(s);
                Bb.getChildren().add(participer);
                Bb.getChildren().add(se);
                Bb.getChildren().add(details);
                
            // Event description : TextArea
            Label desc = new Label("Description :");
            desc.setMaxHeight(30);
            //desc.setStyle(dayLabel);
            
            TextArea dt = new TextArea();
            dt.setMaxWidth(750);
            dt.setMinHeight(70);
            dt.setMaxHeight(70);
            dt.setScrollTop(20);
            dt.setText(e.getEventDescription());
            dt.setEditable(false);
            eventInfo.getChildren().add(Date);
            eventInfo.getChildren().add(Vb);
            eventInfo.getChildren().add(Bb);
            
            
            
           
            
         
           
           
           // Add all the event elements to the event container
           Container.getChildren().add(imgView);
           Container.getChildren().add(eventInfo);
           Container.getChildren().add(desc);
           Container.getChildren().add(dt);
           Label sep = new Label("");
           sep.setMinHeight(40);
           Container.getChildren().add(sep);
            
    }
        // Finally add all the events inside the Scrollpane to the main Content Anchorpane
        Content.getChildren().add(scrollPane);
        
        scrollPane.setStyle("-fx-background : #736e6b");

    }
    
    
    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
    
    final String monthLabel = "-fx-padding: 20;\n" +
            "    -fx-text-fill: #ffffff;\n" +
"    -fx-spacing: 0;\n" +
"    -fx-alignment: center;\n" +
"    -fx-font-weight: bold;\n" +
"    -fx-font-size: 20;";
    
        final String dayLabel = "-fx-padding: 45 0 0 45;\n" +
"    -fx-spacing: 0;\n" +
"    -fx-alignment: center;\n" +
"    -fx-text-fill: #000000;\n" +
"    -fx-font-weight: bold;\n" +
"    -fx-font-size: 30;";
        
         final String NameLabel = "-fx-padding: 15 0 0 0;\n" +
"    -fx-spacing: 0;\n" +
"    -fx-text-fill: #ffffff;\n" +
"    -fx-font-weight: bold;\n" +
"    -fx-font-size: 21;";

    @FXML
    private void showMusique(ActionEvent event) throws Exception{
        ShowbyCat("Musique");
    }

    @FXML
    private void showDance(ActionEvent event) throws Exception{
                ShowbyCat("Dance");

    }

    @FXML
    private void ShowPeintre(ActionEvent event) throws Exception{
                ShowbyCat("Peintre");

    }

    @FXML
    private void showCinema(ActionEvent event) throws Exception{
                ShowbyCat("Cinema");

    }

    @FXML
    private void showTheatre(ActionEvent event) throws Exception{
                ShowbyCat("Theatre");

    }

    @FXML
    private void showMine(ActionEvent event) throws Exception{
     // clear existing Content if it exists  
        if(Content.getChildren()!=null)
        {
            Content.getChildren().clear();
        }
        
        // get Elements to display 
        EventService es=new EventService();
        List<Event> myList=new ArrayList<Event>();
        myList = es.getByUserId(SingletonNavigation.getInstance().getLoggedInUser().getId());
        VBox Container = new VBox();  // main container for all data specific to an event
        
        // Scroll pane to display all the found events
        ScrollPane scrollPane = new ScrollPane(Container);
        scrollPane.setPrefSize(830, 520);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        AnchorPane.setTopAnchor(scrollPane, 0.);
        Container.setPrefWidth(830);
        Container.setPrefHeight(520);
        
        Content.setRightAnchor(scrollPane, 0.);
        Content.setBottomAnchor(scrollPane, 0.);
        Content.setLeftAnchor(scrollPane, 0.);
    
        Container.setPadding(new Insets(15,15,15,15));
        // iterate through all events and create an event element
        for(Event e : myList)
        {
             // Event Cover photo : first element
            ImageView imgView = new ImageView();
            imgView.setImage(new Image("/Uploads/"+e.getEventCover()));
            // Event Information : HBOX
            HBox eventInfo = new HBox();
            //eventInfo.setPadding(new Insets(15,15,15,15));
            
                // Event Date : Anchorpane
                AnchorPane Date = new AnchorPane();
                Date.setMaxWidth(100);
                Date.setMaxHeight(100);
                ImageView Calendar = new ImageView();
                Calendar.setImage(new Image("/Images/Calendar.png"));
               
                Label month = new Label(getMonthForInt(e.getEventDateStart().getMonth()).substring(0,3));
                
                Label day = new Label(String.valueOf(e.getEventDateStart().getDay()));
                month.setMinWidth(105);
                month.setStyle(monthLabel);
                day.setStyle(dayLabel);
                Date.getChildren().add(Calendar);
                Date.getChildren().add(month);
                Date.getChildren().add(day);
                // Event Name , place , date span : VBox
                VBox Vb = new VBox();
                //Vb.setPadding(new Insets(15,15,15,15));
                Vb.setMinWidth(527);
                Label Name=new Label(e.getEventName());  
                Name.setStyle(NameLabel);
                Label Datespan = new Label("De " + e.getEventDateStart().toString() + " jusqu'a " + e.getEventDateEnd().toString());
                Label Place = new Label("Place : " + e.getEventPlace());
                Vb.getChildren().add(Name);
                Vb.getChildren().add(Datespan);
                Vb.getChildren().add(Place);
                // Event action buttons : VBox
                VBox Bb = new VBox();
                Button participer = new Button("Modifier");
                participer.setStyle("-fx-background-color : #333333; -fx-text-fill : #ffffff; -fx-border-color : #ffffff; -fx-border-width : 2px;");
                participer.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {
                    
                    FXMLLoader loader =  new FXMLLoader(getClass().getResource("/Gui/Events/CreateEvent.fxml"));
                    try {
                        Content = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AllEventsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    CreateEventController sec = loader.getController();
                    sec.setEvenement(e);
                    sec.setModif(true);
                    sec.fillForm();
                   
                    root.getChildren().set(16, Content);
                }
                });
                Button details = new Button("Supprimer");
                details.setStyle("-fx-background-color : #ff1919; -fx-text-fill : #ffffff; -fx-border-color : #ffffff; -fx-border-width : 2px;");
                details.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {  
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Suppression produit");
                    alert.setContentText("Voulez vous vraiment supprimer:" + e.getEventName()+ "?");
                    java.util.Optional<javafx.scene.control.ButtonType> answer = alert.showAndWait();
                        if (answer.get() == javafx.scene.control.ButtonType.OK) 
                        {
                        es.SupprimerEvenement(e.getEventId());
                        try
                        {
                        showMine(event);
                        }
                        catch(Exception e)
                        {   
                        }
                        }
                }
                });
                participer.setMinWidth(120);
                details.setMinWidth(120);
                Label s = new Label("");
                Label se = new Label("");
                s.setMinHeight(20);
                se.setMinHeight(10);
                Bb.getChildren().add(s);
                Bb.getChildren().add(participer);
                Bb.getChildren().add(se);
                Bb.getChildren().add(details);
                
            // Event description : TextArea
            Label desc = new Label("Description :");
            desc.setMaxHeight(30);
            //desc.setStyle(dayLabel);
            
            TextArea dt = new TextArea();
            dt.setMaxWidth(750);
            dt.setMinHeight(70);
            dt.setMaxHeight(70);
            dt.setScrollTop(20);
            dt.setText(e.getEventDescription());
            dt.setEditable(false);
            eventInfo.getChildren().add(Date);
            eventInfo.getChildren().add(Vb);
            eventInfo.getChildren().add(Bb);
            
            
            
           
            
         
           
           
           // Add all the event elements to the event container
           Container.getChildren().add(imgView);
           Container.getChildren().add(eventInfo);
           Container.getChildren().add(desc);
           Container.getChildren().add(dt);
           Label sep = new Label("");
           sep.setMinHeight(40);
           Container.getChildren().add(sep);
            
    }
        // Finally add all the events inside the Scrollpane to the main Content Anchorpane
        Content.getChildren().add(scrollPane);
        
        scrollPane.setStyle("-fx-background : #736e6b");

    }

    @FXML
    private void recommended(ActionEvent event) throws Exception{
        // clear existing Content if it exists  
        if(Content.getChildren()!=null)
        {
            Content.getChildren().clear();
        }
        
        // get Elements to display 
        EventService es=new EventService();
        List<Event> myList=new ArrayList<Event>();
        myList = es.getAll();
        VBox Container = new VBox();  // main container for all data specific to an event
        
        // Scroll pane to display all the found events
        ScrollPane scrollPane = new ScrollPane(Container);
        scrollPane.setPrefSize(830, 520);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        AnchorPane.setTopAnchor(scrollPane, 0.);
        Container.setPrefWidth(830);
        Container.setPrefHeight(520);
        
        Content.setRightAnchor(scrollPane, 0.);
        Content.setBottomAnchor(scrollPane, 0.);
        Content.setLeftAnchor(scrollPane, 0.);
    
        Container.setPadding(new Insets(15,15,15,15));
        // iterate through all events and create an event element
        for(Event e : myList)
        {
            if(SingletonNavigation.getInstance().getLoggedInUser().getInterets().contains(e.getCategorie()))
            {
             // Event Cover photo : first element
            ImageView imgView = new ImageView();
            imgView.setImage(new Image("/Uploads/"+e.getEventCover()));
            // Event Information : HBOX
            HBox eventInfo = new HBox();
            //eventInfo.setPadding(new Insets(15,15,15,15));
            
                // Event Date : Anchorpane
                AnchorPane Date = new AnchorPane();
                Date.setMaxWidth(100);
                Date.setMaxHeight(100);
                ImageView Calendar = new ImageView();
                Calendar.setImage(new Image("/Images/Calendar.png"));
               
                Label month = new Label(getMonthForInt(e.getEventDateStart().getMonth()).substring(0,3));
                
                Label day = new Label(String.valueOf(e.getEventDateStart().getDay()));
                month.setMinWidth(105);
                month.setStyle(monthLabel);
                day.setStyle(dayLabel);
                Date.getChildren().add(Calendar);
                Date.getChildren().add(month);
                Date.getChildren().add(day);
                // Event Name , place , date span : VBox
                VBox Vb = new VBox();
                //Vb.setPadding(new Insets(15,15,15,15));
                Vb.setMinWidth(527);
                Label Name=new Label(e.getEventName());  
                Name.setStyle(NameLabel);
                Label Datespan = new Label("De " + e.getEventDateStart().toString() + " jusqu'a " + e.getEventDateEnd().toString());
                Label Place = new Label("Place : " + e.getEventPlace());
                Vb.getChildren().add(Name);
                Vb.getChildren().add(Datespan);
                Vb.getChildren().add(Place);
                // Event action buttons : VBox
                VBox Bb = new VBox();
                Button participer = new Button("Je participe");
                participer.setStyle("-fx-background-color : #333333; -fx-text-fill : #ffffff; -fx-border-color : #ffffff; -fx-border-width : 2px;");
                participer.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {
                       String currentParticipants = es.getEventParticipantsString(e.getEventId());
                       if(currentParticipants.equals("none"))
                       {
                           currentParticipants = String.valueOf(SingletonNavigation.getInstance().getLoggedInUser().getId()) + ",";
                       }
                       else
                       {
                           currentParticipants += String.valueOf(SingletonNavigation.getInstance().getLoggedInUser().getId()) + ",";
                       }
                       es.setEventParticipants(e.getEventId(),currentParticipants);
                }
                });
                Button details = new Button("plus de détails");
                details.setStyle("-fx-background-color : #333333; -fx-text-fill : #ffffff; -fx-border-color : #ffffff; -fx-border-width : 2px;");
                details.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) 
                {  
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("EventDetails.fxml"));
                    try
                    {
                        if(Content.getChildren()!=null)
                        {
                            Content.getChildren().clear();
                        }
                        AnchorPane tmp = loader.load();
                        Content.getChildren().add(tmp);
                       // EventDetailsController secController = loader.getController();
                        //secController.setE(e);
                       // secController.Populate();
                    }
                    catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
                }
                });
                participer.setMinWidth(120);
                details.setMinWidth(120);
                Label s = new Label("");
                Label se = new Label("");
                s.setMinHeight(20);
                se.setMinHeight(10);
                Bb.getChildren().add(s);
                Bb.getChildren().add(participer);
                Bb.getChildren().add(se);
                Bb.getChildren().add(details);
                
            // Event description : TextArea
            Label desc = new Label("Description :");
            desc.setMaxHeight(30);
            //desc.setStyle(dayLabel);
            
            TextArea dt = new TextArea();
            dt.setMaxWidth(750);
            dt.setMinHeight(70);
            dt.setMaxHeight(70);
            dt.setScrollTop(20);
            dt.setText(e.getEventDescription());
            dt.setEditable(false);
            eventInfo.getChildren().add(Date);
            eventInfo.getChildren().add(Vb);
            eventInfo.getChildren().add(Bb);
            
            
            
           
            
         
           
           
           // Add all the event elements to the event container
           Container.getChildren().add(imgView);
           Container.getChildren().add(eventInfo);
           Container.getChildren().add(desc);
           Container.getChildren().add(dt);
           Label sep = new Label("");
           sep.setMinHeight(40);
           Container.getChildren().add(sep);
           }
            
    }
        // Finally add all the events inside the Scrollpane to the main Content Anchorpane
        Content.getChildren().add(scrollPane);
        
        scrollPane.setStyle("-fx-background : #736e6b");
    }


    
}
