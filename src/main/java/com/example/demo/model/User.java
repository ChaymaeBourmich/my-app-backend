package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Relance> relances = new HashSet<>();




    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private UtilisateurDetails utilisateurDetails;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }



    public UtilisateurDetails getUtilisateurDetails() {
        return utilisateurDetails;
    }

    public void setUtilisateurDetails(UtilisateurDetails utilisateurDetails) {
        this.utilisateurDetails = utilisateurDetails;
    }

    // Implement UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify as needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify as needed
    }

    @Column(name = "premiere_connexion")
    private boolean premiereConnexion = true; // true par défaut

    public boolean isPremiereConnexion() {
        return premiereConnexion;
    }

    public void setPremiereConnexion(boolean premiereConnexion) {
        this.premiereConnexion = premiereConnexion;
    }

    public Set<Relance> getRelances() {
        return relances;
    }

    public void setRelances(Set<Relance> relances) {
        this.relances = relances;
    }
}