/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Profile;

import Services.UserService;
import Utils.SingletonNavigation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ProfileController implements Initializable {
    @FXML
    private Button versatile_btn;

    @FXML
    private ImageView CoverPicture;
    @FXML
    private Circle Avatar;
    @FXML
    private AnchorPane AvatarFilter;    
    @FXML
    private AnchorPane Content;
    @FXML
    private Button btn_updatePp;
    @FXML
    private AnchorPane root;
    @FXML
    private Button reviews1;
    @FXML
    public void UpdateProfilePicture() throws Exception
    {   FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null)
        {
           String fileExtension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."),selectedFile.getName().length());
           if(Arrays.asList(VALID_EXTENSIONS).contains(fileExtension))
           {
           File source = new File(selectedFile.getPath());
           File dest = new File("C:\\Users\\Dell\\Desktop\\Fix'it\\Fix'itDesktop\\src\\Uploads\\"+selectedFile.getName());
           if(copyFileUsingStream(source, dest))
           {
               /*UserService us = new UserService();
               us.update(SingletonNavigation.getInstance().getLoggedInUser());
               SingletonNavigation.getInstance().getLoggedInUser().setAvatar(selectedFile.getName());
               us.update(SingletonNavigation.getInstance().getLoggedInUser());
               Avatar.setFill(new ImagePattern(new Image("/Uploads/"+SingletonNavigation.getInstance().getLoggedInUser().getAvatar())));;*/
               
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
    }
    
    public void loadVersatile()
    {
       /* if(SingletonNavigation.getInstance().getLoggedInUser().getAccountType().equals("client"))
        {
            
        }
        else if (SingletonNavigation.getInstance().getLoggedInUser().getAccountType().equals("professional"))
        {
            try
            {
                Content = FXMLLoader.load(getClass().getResource("/Gui/Services/MyServices.fxml"));
                root.getChildren().set(8, Content);
                Content.setLayoutY(260);
            }
            catch(Exception e)
            {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        else if (SingletonNavigation.getInstance().getLoggedInUser().getAccountType().equals("entreprise"))
        {
            try
            {
                Content = FXMLLoader.load(getClass().getResource("/Gui/Projects/MyProjects.fxml"));
                root.getChildren().set(8, Content);
                Content.setLayoutY(260);
            }
            catch(Exception e)
            {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, e);
            }
        }*/
    }
    
    void VersatileRev() throws Exception {/*
        if(SingletonNavigation.getInstance().getLoggedInUser().getAccountType().equals("client") || SingletonNavigation.getInstance().getLoggedInUser().getAccountType().equals("entreprise"))
        {
            try
            {
            Content = FXMLLoader.load(getClass().getResource("/Gui/Projects/ComplaintStatus.fxml"));
            root.getChildren().set(8, Content);
            Content.setLayoutY(260);
            }
            catch(Exception e)
            {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, e);
            }
        }*/
       // else if (SingletonNavigation.getInstance().getLoggedInUser().getAccountType().equals("professional"))
       // {
            /*try  note
            {
                Content = FXMLLoader.load(getClass().getResource("/Gui/Services/MyServices.fxml"));
                root.getChildren().set(8, Content);
                Content.setLayoutY(260);
            }
            catch(Exception e)
            {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, e);
            }*/
       // }
        
    }

    
    @FXML
    private Button reviews;
    
    @FXML
    public void aboutMe()
    {
        try{
        Content = FXMLLoader.load(getClass().getResource("Aboutme.fxml"));
        root.getChildren().set(9, Content);
        Content.setLayoutY(260);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    @FXML
    public void contacts()
    {
        
        try {
            Content = FXMLLoader.load(getClass().getResource("/Gui/Contact/MyContacts.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        root.getChildren().set(9, Content);
        Content.setLayoutY(260);
        
        
    }
    
    @FXML
    public void updateProfile()
    {
        try{
        Content = FXMLLoader.load(getClass().getResource("UpdateProfile.fxml"));
        root.getChildren().set(9, Content);
        Content.setLayoutY(260);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
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
        }
        return true;
    }
    
    private static final String[] VALID_EXTENSIONS = new String[] {".png", ".jpg", "jpeg", "gif", "bmp"};
    @FXML 
    public void UpdateCoverPicture() throws Exception
    {
       /* FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null)
        {
           String fileExtension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."),selectedFile.getName().length());
           if(Arrays.asList(VALID_EXTENSIONS).contains(fileExtension))
           {
           File source = new File(selectedFile.getPath());
           File dest = new File("C:\\Users\\Dell\\Desktop\\Fix'it\\Fix'itDesktop\\src\\Uploads\\"+selectedFile.getName());
           if(copyFileUsingStream(source, dest))
           {
               SingletonNavigation.getInstance().getLoggedInUser().setProfileCover(selectedFile.getName());
               UserService us = new UserService();
               us.update(SingletonNavigation.getInstance().getLoggedInUser());
               CoverPicture.setImage(new Image("/Uploads/"+selectedFile.getName()));
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
    }*/
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Avatar.setFill(new ImagePattern(new Image("/Uploads/"+SingletonNavigation.getInstance().getLoggedInUser().getAvatar())));
        CoverPicture.setImage(new Image("/Uploads/"+SingletonNavigation.getInstance().getLoggedInUser().getCover()));
        AvatarFilter.setVisible(false);
        Avatar.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {AvatarFilter.setVisible(true);
                });
        Avatar.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {AvatarFilter.setVisible(false);
                });
        btn_updatePp.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {AvatarFilter.setVisible(true);
                AvatarFilter.setVisible(true);
                });
        btn_updatePp.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {AvatarFilter.setVisible(true);
                AvatarFilter.setVisible(false);
                });
       /* if(SingletonNavigation.getInstance().getLoggedInUser().getAccountType().equals("client"))
        {
            versatile_btn.setText("My Job posts");
            reviews.setText("Complaint status");
            
        }
        else if(SingletonNavigation.getInstance().getLoggedInUser().getAccountType().equals("professional"))
        {
            versatile_btn.setText("My Services");
        }
        else if(SingletonNavigation.getInstance().getLoggedInUser().getAccountType().equals("entreprise"))
        {
            versatile_btn.setText("Projects");
            reviews.setText("Complaint status");
        }*/
        aboutMe();

       
    }    

    @FXML
    private void invites(ActionEvent event) throws Exception{
       SingletonNavigation.getInstance().getNavCont().loadContactRequests(event);
    }

    @FXML
    private void messagerie(ActionEvent event) {
        System.out.println("Messagerie");
    }

    @FXML
    private void Mur(ActionEvent event) {
        System.out.println("Mur");
    }

      
}
