/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Event {
    int eventId;
    String eventName;
    int eventOwner;
    String eventDescription;
    Date eventDateStart;
    Date eventDateEnd;
    String eventPlace;
    int eventNumPar;
    String State;
    String eventCover;
    List<User> eventParticipants;
    String categorie;
    

    public Event(String eventName, int eventOwner, String eventDescription, Date eventDateStart, Date eventDateEnd, String eventPlace, int eventNumPar, String State, String eventCover, String categorie) {
        this.eventName = eventName;
        this.eventOwner = eventOwner;
        this.eventDescription = eventDescription;
        this.eventDateStart = eventDateStart;
        this.eventDateEnd = eventDateEnd;
        this.eventPlace = eventPlace;
        this.eventNumPar = eventNumPar;
        this.State = State;
        this.eventCover = eventCover;
        this.categorie = categorie;
    }
    
    
   /* public Event(String eventName, int eventOwner, String eventDescription, Date eventDateStart, Date eventDateEnd, String eventPlace, int eventNumPar, String State) {
        this.eventName = eventName;
        this.eventOwner = eventOwner;
        this.eventDescription = eventDescription;
        this.eventDateStart = eventDateStart;
        this.eventDateEnd = eventDateEnd;
        this.eventPlace = eventPlace;
        this.eventNumPar = eventNumPar;
        this.State = State;
    }*/

   
    public Event(int eventId, String eventName, int eventOwner, String eventDescription, Date eventDateStart, Date eventDateEnd, String eventPlace, int eventNumPar, String eventCover, String categorie) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventOwner = eventOwner;
        this.eventDescription = eventDescription;
        this.eventDateStart = eventDateStart;
        this.eventDateEnd = eventDateEnd;
        this.eventPlace = eventPlace;
        this.eventNumPar = eventNumPar;
        this.eventCover = eventCover;
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventOwner() {
        return eventOwner;
    }

    public void setEventOwner(int eventOwner) {
        this.eventOwner = eventOwner;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Date getEventDateStart() {
        return eventDateStart;
    }

    public void setEventDateStart(Date eventDateStart) {
        this.eventDateStart = eventDateStart;
    }

    public Date getEventDateEnd() {
        return eventDateEnd;
    }

    public void setEventDateEnd(Date eventDateEnd) {
        this.eventDateEnd = eventDateEnd;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public int getEventNumPar() {
        return eventNumPar;
    }

    public void setEventNumPar(int eventNumPar) {
        this.eventNumPar = eventNumPar;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }
    
    
    public String getEventCover() {
        return eventCover;
    }

    public void setEventCover(String eventCover) {
        this.eventCover = eventCover;
    }

    public List<User> getEventParticipants() {return eventParticipants;}

    public void setEventParticipants(List<User> eventParticipants) {this.eventParticipants = eventParticipants;}
    

    @Override
    public String toString() {
        return "Event{" + "eventId=" + eventId + ", eventName=" + eventName + ", eventOwner=" + eventOwner + ", eventDescription=" + eventDescription + ", eventDateStart=" + eventDateStart + ", eventDateEnd=" + eventDateEnd + ", eventPlace=" + eventPlace + ", eventNumPar=" + eventNumPar + ", State=" + State + '}';
    }

    
    
    
    
    
}
