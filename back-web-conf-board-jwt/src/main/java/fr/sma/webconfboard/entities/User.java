package fr.sma.webconfboard.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private @NonNull String nom;
    @Column
    private @NonNull String prenom;
    @Column
    private @NonNull String username;
    @Column
    private @NonNull String email;
    @Column
    //@JsonIgnore
    private @NonNull String password;

    private @NonNull String role;

    public User(String nom, String prenom, String username, String email, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
