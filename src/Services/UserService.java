/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
public class UserService {
        Connection cnx = DatabaseConnection.getInstance().getConnection();
        
        public void insertAmateur(User u) {
        try
        {
            String query = "INSERT INTO users (nom, prenom, email, password, telephone, naissance, typeCompte, sexe, avatar, interets, token) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, u.getNom());
            statement.setString(2, u.getPrenom());
            statement.setString(3, u.getEmail());
            statement.setString(4, u.getPassword());
            statement.setInt(5, u.getTelephone());
            statement.setDate(6, new java.sql.Date(u.getNaissance().getTime()));
            statement.setString(7, u.getTypeCompte());
            statement.setString(8, u.getSexe());
            statement.setString(9, u.getAvatar());
            statement.setString(10, u.getInterets());
            statement.setString(11, u.getToken());
            statement.executeUpdate();
            System.out.println("Amateur d'art ajouté");
        }catch(SQLException ex)
        {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void insertArtiste(User u) {
        try
        {
            String query = "INSERT INTO users (nom, prenom, email, password, telephone, naissance, typeCompte, sexe, avatar, interets, token, piece_id, domaine, sous_domaine, bio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, u.getNom());
            statement.setString(2, u.getPrenom());
            statement.setString(3, u.getEmail());
            statement.setString(4, u.getPassword());
            statement.setInt(5, u.getTelephone());
            statement.setDate(6, new java.sql.Date(u.getNaissance().getTime()));
            statement.setString(7, u.getTypeCompte());
            statement.setString(8, u.getSexe());
            statement.setString(9, u.getAvatar());
            statement.setString(10, u.getInterets());
            statement.setString(11, u.getToken());
            statement.setString(12, u.getPieceIdentite());
            statement.setString(13, u.getDomaine());
            statement.setString(14, u.getSousDomaine());
            statement.setString(15, u.getBio());
            statement.executeUpdate();
            System.out.println("Artiste ajouté");
        }catch(SQLException ex)
        {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
public User getByEmail(String email) {
                                 User u = new User();

        try{

         String query = "SELECT * FROM users WHERE email = "+"'"+email+"'";
         Statement statement = cnx.createStatement();
         ResultSet rs = statement.executeQuery(query);
         while(rs.next())
         {
             u.setId(rs.getInt(1));
             u.setNom(rs.getString(2));
             u.setPrenom(rs.getString(3));
             u.setEmail(rs.getString(4));
             u.setPassword(rs.getString(5));
             u.setTelephone(rs.getInt(6));
             u.setTypeCompte(rs.getString(7));
             u.setSexe(rs.getString(8));
             u.setAvatar(rs.getString(9));
             u.setNaissance(rs.getDate(10));
             u.setInterets(rs.getString(11));
             u.setToken(rs.getString(12));
             u.setPieceIdentite(rs.getString(13));
             u.setDomaine(rs.getString(14));
             u.setSousDomaine(rs.getString(15));
             u.setBio(rs.getString(16));
             u.setCover(rs.getString(17));
         }
        }catch(SQLException ex)
        {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

        public User getById(int id) {
        User u = new User();

        try{

         String query = "SELECT * FROM users WHERE id = "+"'"+id+"'";
         Statement statement = cnx.createStatement();
         ResultSet rs = statement.executeQuery(query);
         while(rs.next())
         {
             u.setId(rs.getInt(1));
             u.setNom(rs.getString(2));
             u.setPrenom(rs.getString(3));
             u.setEmail(rs.getString(4));
             u.setPassword(rs.getString(5));
             u.setTelephone(rs.getInt(6));
             u.setTypeCompte(rs.getString(7));
             u.setSexe(rs.getString(8));
             u.setAvatar(rs.getString(9));
             u.setNaissance(rs.getDate(10));
             u.setInterets(rs.getString(11));
             u.setToken(rs.getString(12));
             u.setPieceIdentite(rs.getString(13));
             u.setDomaine(rs.getString(14));
             u.setSousDomaine(rs.getString(15));
             u.setBio(rs.getString(16));
             u.setCover(rs.getString(17));
         }
        }catch(SQLException ex)
        {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

     public List<User> getAll() {
       List<User> userList = new ArrayList();
        try{
         String query = "SELECT * FROM users";
         Statement statement = cnx.createStatement();
         ResultSet rs = statement.executeQuery(query);
         while(rs.next())
         {
             User u = new User();
             u.setId(rs.getInt(1));
             u.setNom(rs.getString(2));
             u.setPrenom(rs.getString(3));
             u.setEmail(rs.getString(4));
             u.setPassword(rs.getString(5));
             u.setTelephone(rs.getInt(6));
             u.setTypeCompte(rs.getString(7));
             u.setSexe(rs.getString(8));
             u.setAvatar(rs.getString(9));
             u.setNaissance(rs.getDate(10));
             u.setInterets(rs.getString(11));
             u.setToken(rs.getString(12));
             u.setPieceIdentite(rs.getString(13));
             u.setDomaine(rs.getString(14));
             u.setSousDomaine(rs.getString(15));
             u.setBio(rs.getString(16));
             u.setCover(rs.getString(17));
             userList.add(u);
             
         }
        }catch(SQLException ex)
        {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public boolean authentication(String email, String password)
    {
        User u = new User();
        String query = "SELECT * FROM users WHERE email = " +"'"+email+"'" + "AND password = "+"'"+password+"'";
        try{
            Statement ps = cnx.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery(query);
            
            int count = 0;
            while(rs.next())
            {
                count++;
            }
            if(count == 1 )
            {
                return true;
            }
            else if(count > 1)
            {
                return false;
            }
            else
            {
                return false;
            }
            
         
        }catch(SQLException ex)
        {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
                
    }
     public List<User> getContactList(int id)
    {
          List<User> contList = new ArrayList();
          UserService us = new UserService();
          String str = getContactListString(id);
          String[] participantsId = str.split(",");
          for (String s : participantsId)
          {
              contList.add(us.getById(Integer.valueOf(s)));
          }
          return contList;
        
    }
    
  
    
    public void setContactList(int id, String contactList)
    {
            try {
            String req1="UPDATE users SET contactList = ? WHERE id = ?";
            PreparedStatement pre = cnx.prepareStatement(req1);
            pre.setString(1, contactList);
            pre.setInt(2, id);
            pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getContactListString(int id)
    {
        String res = "";

           try {
           String req1="select contactList from users where id = "+"'"+id+"'";
           Statement statement = cnx.createStatement();
           ResultSet resultset = statement.executeQuery(req1);
           resultset.next(); // exactly one result so allowed
           res = resultset.getString(1); // use indexed retrieval since the column has no name
           } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
           }
           return res;
    }
}
