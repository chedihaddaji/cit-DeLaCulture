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
 * @author Dell
 */
public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String token;
    private String avatar;
    private boolean valider;
    private int telephone;
    private Date naissance;
    private String typeCompte;
    private String sexe;
    private String interets;
    private String pieceIdentite;
    private String domaine;
    private String sousDomaine;
    private String bio;
    private List<User> friendList;
    private String cover;

    public User()
    {
        
    }

    public User(int id, String nom, String prenom, String email, String password, String token, String avatar, boolean valider, int telephone, Date naissance, String typeCompte, String sexe, String interets, String pieceIdentite, String domaine, String sousDomaine, String bio, List<User> friendList) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.token = token;
        this.avatar = avatar;
        this.valider = valider;
        this.telephone = telephone;
        this.naissance = naissance;
        this.typeCompte = typeCompte;
        this.sexe = sexe;
        this.interets = interets;
        this.pieceIdentite = pieceIdentite;
        this.domaine = domaine;
        this.sousDomaine = sousDomaine;
        this.bio = bio;
    }

    public User(String nom, String prenom, String email, String password, String token, String avatar, int telephone, Date naissance, String typeCompte, String sexe, String interets) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.token = token;
        this.avatar = avatar;
        this.valider = false;
        this.telephone = telephone;
        this.naissance = naissance;
        this.typeCompte = typeCompte;
        this.sexe = sexe;
        this.interets = interets;
    }

   

    public User(String nom, String prenom, String email, String password, String token, int telephone, Date naissance, String typeCompte, String sexe, String interets, String avatar) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.token = token;
        this.telephone = telephone;
        this.naissance = naissance;
        this.typeCompte = typeCompte;
        this.sexe = sexe;
        this.interets = interets;
        this.avatar = avatar;
    }

    public User(String nom, String prenom, String email, String password, String token, String avatar, int telephone, Date naissance, String typeCompte, String sexe, String interets, String pieceIdentite, String domaine, String sousDomaine, String bio) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.token = token;
        this.avatar = avatar;
        this.telephone = telephone;
        this.naissance = naissance;
        this.typeCompte = typeCompte;
        this.sexe = sexe;
        this.interets = interets;
        this.pieceIdentite = pieceIdentite;
        this.domaine = domaine;
        this.sousDomaine = sousDomaine;
        this.bio = bio;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getInterets() {
        return interets;
    }

    public void setInterets(String interets) {
        this.interets = interets;
    }

    public String getPieceIdentite() {
        return pieceIdentite;
    }

    public void setPieceIdentite(String pieceIdentite) {
        this.pieceIdentite = pieceIdentite;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getSousDomaine() {
        return sousDomaine;
    }

    public void setSousDomaine(String sousDomaine) {
        this.sousDomaine = sousDomaine;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<User> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<User> friendList) {
        this.friendList = friendList;
    }
    
      
}
