/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.ContactRequest;
import Entities.User;
import Utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Dell
 */
public class ContactRequestService {
        Connection cnx = DatabaseConnection.getInstance().getConnection();

        
        public void insert(ContactRequest c) 
        {
        try
        {
            String query = "INSERT INTO contactrequests (sender_id, reciever_id) VALUES (?, ?)";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, c.getSender_id());
            statement.setInt(2, c.getReciever_id());
            statement.executeUpdate();
        }catch(SQLException ex)
        {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        public void delete(ContactRequest c) 
        {
          try
          {
            String query="DELETE FROM contactRequests WHERE id=?";
            PreparedStatement st=cnx.prepareStatement(query);
            st.setInt(1, c.getId());
            st.executeUpdate();
          }catch(SQLException ex) 
          {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        
        public List<ContactRequest> getByUserId(int id) {
        List<ContactRequest> requestList = new ArrayList();

        try{

         String query = "SELECT * FROM contactRequests WHERE reciever_id = "+"'"+id+"'";
         Statement statement = cnx.createStatement();
         ResultSet rs = statement.executeQuery(query);
         while(rs.next())
         {
             ContactRequest c = new ContactRequest();
             c.setId(rs.getInt(1));
             c.setSender_id(rs.getInt(2));
             c.setReciever_id(rs.getInt(3));
             c.setSeen(rs.getBoolean(4));
             requestList.add(c);
         }
        }catch(SQLException ex)
        {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requestList;
    }
        
        public int getRequestNumber(int id)
        {
            try
            {
                Statement statement = cnx.createStatement();
                ResultSet resultSet = statement.executeQuery("select count(*) from contactRequests where reciever_id ="+"'"+id+"'");

                while (resultSet.next()) 
                {
                return resultSet.getInt(1);
                }              
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }


}
