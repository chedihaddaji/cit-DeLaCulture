/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Dell
 */
public class ContactRequest {
    private int id;
    private int sender_id;
    private int reciever_id;
    private boolean seen;
    
    public ContactRequest(int sender_id, int reciever_id)
    {
        this.sender_id = sender_id;
        this.reciever_id = reciever_id;
    }
     public ContactRequest()
    {
        this.id = 0;
        this.sender_id = 0;
        this.reciever_id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReciever_id() {
        return reciever_id;
    }

    public void setReciever_id(int reciever_id) {
        this.reciever_id = reciever_id;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
    
    
}
