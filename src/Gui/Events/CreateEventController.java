/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Events;

import Entities.Event;
import Entities.User;
import Gui.NavigationController;
import javafx.event.ActionEvent;
import Services.EventService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import Utils.DatabaseConnection;
import Utils.SingletonNavigation;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;


/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CreateEventController implements Initializable {

    private static final String[] VALID_EXTENSIONS = new String[] {".png", ".jpg", "jpeg", "gif", "bmp"};

    String SelctedCover = "default_event.png";
    
    @FXML
    private ImageView Cover;
    private Event evenement;
    private boolean modif = false;
    @FXML
    private Label UpText;

    public Event getEvenement() {
        return evenement;
    }

    public void setEvenement(Event evenement) {
        this.evenement = evenement;
    }

    public boolean isModif() {
        return modif;
    }

    public void setModif(boolean modif) {
        this.modif = modif;
    }

    @FXML
    private TextField EventName;
    private TextField EventOwner;
    @FXML
    private TextField EventDescription;
    @FXML
    private DatePicker EventDateStart;
    @FXML
    private DatePicker EventDateEnd;        
    @FXML
    private TextField EventPlace;
    @FXML
    private TextField EventNumPar;
    @FXML
    private Button btnAjouter;

    /**
     * Initializes the controller class.
     */
    Connection c=DatabaseConnection.getInstance().getConnection();
    EventService ES=new EventService();
    @FXML
    private JFXComboBox<String> combo;
   
    public void fillForm()
    {
        EventName.setText(evenement.getEventName());
        EventDescription.setText(evenement.getEventDescription());
        EventPlace.setText(evenement.getEventPlace());
        EventNumPar.setText(String.valueOf(evenement.getEventNumPar()));
        combo.setValue(evenement.getCategorie());
        Cover.setImage(new Image("/Uploads/"+evenement.getEventCover()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
       /*LocalDate localDate = LocalDate.parse(evenement.getEventDateStart().toGMTString(), formatter);
        LocalDate localDate1 = LocalDate.parse(evenement.getEventDateEnd().toGMTString(), formatter);
        EventDateStart.setValue(localDate);
        EventDateEnd.setValue(localDate1);*/
        UpText.setText("Mettre a jour votre évènement");
    }
      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combo.setItems(FXCollections.observableArrayList("Musique", "Dance", "Peintre", "Cinema", "Theatre"));
        combo.setValue("Musique");
    }    
    
     private static boolean copyFileUsingStream(File source, File dest) throws Exception 
    {
        InputStream is = null;
        OutputStream os = null;
        try 
        {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);}
        } finally {
            is.close();
            os.close();
                    return true;

        }
    }

     
    @FXML
   void chooseEventCover() throws Exception
   {
       FileChooser fc = new FileChooser();
       File selectedFile = fc.showOpenDialog(null);
       if (selectedFile != null)
       {
           String fileExtension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."),selectedFile.getName().length());
           if(Arrays.asList(VALID_EXTENSIONS).contains(fileExtension))
           {
           File source = new File(selectedFile.getPath());
           File des = new File("C:\\Users\\Dell\\Desktop\\Spirit'Art\\SpiritArt\\src\\Uploads\\"+selectedFile.getName());
           this.SelctedCover = selectedFile.getName();
           if(copyFileUsingStream(source, des))
           {
              Cover.setImage(new Image("/Uploads/"+selectedFile.getName()));
           }
           
           }
           else
           {
               System.out.println("Extension Invalide");
           }
       }
       else
       {
           System.out.println("Ficher non valide");
       }
   }
    
    private long calculeNbrJour(ActionEvent event) throws Exception
    { 
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");    
        final String firstInput = EventDateStart.getValue().toString();
        final String secondInput = EventDateEnd.getValue().toString();
        final LocalDate firstDate = LocalDate.parse(firstInput, formatter);
        final LocalDate secondDate = LocalDate.parse(secondInput, formatter);
        final long days = ChronoUnit.DAYS.between(firstDate, secondDate);
        return days;
       
    }
    @FXML
    private void Ajouter(ActionEvent event)throws Exception 
    {    
            //SMS sendtext = new SMS();
    // sendtext.SendSMS("medaminejrab","espritpidev","un nouveau evenement a ete cree !","216"+"51381454","https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
    
    
    String errorlog = "";
          if(EventName.getText().equals("") || EventDescription.getText().equals("") || EventPlace.getText().equals("") || EventNumPar.getText().equals(""))
          {
              errorlog = "Vous devez d'abord remplir tous les champs !\n";
          }
        
          if(!EventNumPar.getText().matches("^[0-9]*$"))
          {
              errorlog += "Vous devez saisir une valeur numérique (Nombre de place) !\n";
          }
          
          if(EventDateStart.getValue()== null || EventDateEnd.getValue() == null)
          {
              errorlog += "Vous devez choisir une date de début/fin !";
          }
          
          else if(calculeNbrJour(event)<0)
          {
              errorlog += "Vous devez choisir un intervalle valide [(date début - date fin])!";
          }
          
        if(errorlog.equals(""))
        {    
       
        if(modif == false)
        {
         DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
         ES.ajouterEvenement(new Event(EventName.getText(),SingletonNavigation.getInstance().getLoggedInUser().getId(),EventDescription.getText(),Date.valueOf( dateformat.format(EventDateStart.getValue().atStartOfDay(ZoneId.of("GMT")).toEpochSecond()* 1000)),
         Date.valueOf( dateformat.format(EventDateEnd.getValue().atStartOfDay(ZoneId.of("GMT")).toEpochSecond()* 1000)), 
         EventPlace.getText(),Integer.valueOf(EventNumPar.getText()),"todo", SelctedCover,combo.getValue()));
         SingletonNavigation.getInstance().getNavCont().loadEvents(event);
        }
        else
        {
         DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
         Event em = new Event(EventName.getText(),SingletonNavigation.getInstance().getLoggedInUser().getId(),EventDescription.getText(),Date.valueOf( dateformat.format(EventDateStart.getValue().atStartOfDay(ZoneId.of("GMT")).toEpochSecond()* 1000)),
         Date.valueOf( dateformat.format(EventDateEnd.getValue().atStartOfDay(ZoneId.of("GMT")).toEpochSecond()* 1000)), 
         EventPlace.getText(),Integer.valueOf(EventNumPar.getText()),"todo", SelctedCover,combo.getValue());
         em.setEventId(evenement.getEventId());
         ES.modifierEvenement(em);
         SingletonNavigation.getInstance().getNavCont().loadEvents(event);
        }
    
        
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText(errorlog);
            alert.show();
        }
    
    }


    
}
