/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import Entities.Event;
import Entities.User;
import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.DatabaseConnection;
import java.util.Arrays;

/**
 *
 * @author Asus
 */
public class EventService {
    Connection c= DatabaseConnection.getInstance().getConnection();
    Statement stmt;
    private ObservableList<Event>data; 
    
     public void ajouterEvenement(Event e) {
        try {
            String requete ="INSERT INTO events (eventName, eventOwner, eventDiscription, eventDateStart, eventDateEnd, eventPlace,eventNumPart,State,eventCover,categorie) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = c.prepareStatement(requete);
            pre.setString(1, e.getEventName());
            pre.setInt(2, e.getEventOwner());
            pre.setString(3, e.getEventDescription());
            pre.setDate(4, (Date) e.getEventDateStart());
            pre.setDate(5, (Date) e.getEventDateEnd());
            pre.setString(6, e.getEventPlace());
            pre.setInt(7, e.getEventNumPar());
            pre.setString(8, e.getState());     
            pre.setString(9, e.getEventCover());
            pre.setString(10, e.getCategorie());
            pre.executeUpdate();
            System.out.println("Ajout effectué avec succès");

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modifierEvenement(Event e){
        try {
            String requete="UPDATE events SET eventName =?, eventOwner =?, eventDiscription =?, eventDateStart =?, eventDateEnd =?, eventPlace =?, eventNumPart =?, State =? WHERE eventId =?";
            PreparedStatement pre = c.prepareStatement(requete);
            pre.setString(1, e.getEventName());
            pre.setInt(2, e.getEventOwner());
            pre.setString(3, e.getEventDescription());
            pre.setDate(4, (Date) e.getEventDateStart());
            pre.setDate(5, (Date) e.getEventDateEnd());
            pre.setString(6, e.getEventPlace());
            pre.setInt(7, e.getEventNumPar());
            pre.setString(8, e.getState());
            pre.setInt(9,e.getEventId());
            pre.executeUpdate();
            System.out.println("Modifier effectué avec succès");
            
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public List<Event> getAll(){
       data=FXCollections.observableArrayList();
 
            try {
           String req1="select * from events  ORDER BY eventDateStart DESC";
           ResultSet rs=c.createStatement().executeQuery(req1);
           int i=0; 
           while(rs.next())
            {
                                
                data.add(new Event(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getDate(5),rs.getDate(6),rs.getString(7),rs.getInt(8),rs.getString(10),rs.getString(12)))  ;
                //System.out.println(data.get(i));
                i++;
            }
           
            } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
            }
    return data;
    }
    
    public List<Event> getByCategorie(String Categorie){
       data=FXCollections.observableArrayList();
 
            try {
           String req1="select * from events where categorie = '"+Categorie+"' ORDER BY eventDateStart DESC";
           ResultSet rs=c.createStatement().executeQuery(req1);
           int i=0; 
           while(rs.next())
            {
                                
                data.add(new Event(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getDate(5),rs.getDate(6),rs.getString(7),rs.getInt(8),rs.getString(10),rs.getString(12)))  ;
                //System.out.println(data.get(i));
                i++;
            }
           
            } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
            }
    return data;
    }
    
     public List<Event> getByUserId(int id){
       data=FXCollections.observableArrayList();
 
            try {
           String req1="select * from events where eventOwner = "+"'"+id+"' ORDER BY eventDateStart DESC";
           ResultSet rs=c.createStatement().executeQuery(req1);
           int i=0; 
           while(rs.next())
            {
                                
                data.add(new Event(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getDate(5),rs.getDate(6),rs.getString(7),rs.getInt(8),rs.getString(10),rs.getString(12)))  ;
                i++;
            }
           
            } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
            }
    return data;
    }
    
    
    public void SupprimerEvenement(int id)
    {
         PreparedStatement pt;

        try {
            pt=c.prepareStatement("delete from events where eventId=?");
            pt.setInt(1,id);
            pt.executeUpdate();
            System.out.println("Suppression effectué");

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   /* public List<User> getEventParticipants(int id)
    {
          List<User> partList = new ArrayList();
          UserService us = new UserService();
          String str = getEventParticipantsString(id);
          String[] participantsId = str.split(",");
          for (String s : participantsId)
          {
              partList.add(us.getById(Integer.valueOf(s)));
          }
          return partList;
        
    }*/
    
    public void setEventParticipants(int id, String eventParticipants)
    {
       
            try {
            String req1="UPDATE events SET eventParticipants = ? WHERE eventId = ?";
            PreparedStatement pre = c.prepareStatement(req1);
            pre.setString(1, eventParticipants);
            pre.setInt(2, id);
            pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getEventParticipantsString(int id)
    {
 
           try {
           String req1="select eventParticipants from events where eventId = "+"'"+id+"'";
           Statement statement = c.createStatement();
           ResultSet resultset = statement.executeQuery(req1);
           resultset.next(); // exactly one result so allowed
           return resultset.getString(1); // use indexed retrieval since the column has no name
           } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
           }
           return "none";
    }
    
    

} 
     
     
     
     
     

