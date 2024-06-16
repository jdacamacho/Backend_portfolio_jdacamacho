package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
public class UserEntity implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "user_document_number", nullable = false, unique = true)
    private long documentNumber;

    @Column(name = "user_document_type", nullable = false , length = 20)
    private String documentType;

    @Column(name = "user_names", nullable = false , length = 80)
    private String names;

    @Column(name = "user_last_names", nullable = false , length = 80)
    private String lastNames;

    @Column(name = "user_user_name", nullable = false , unique = true , length = 20)
    private String username;
    
    @Column(name = "user_password", nullable = false , length = 400)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_document_number"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles;

    @OneToMany(mappedBy = "objUser")
    private List<ReservationEntity> reservations;

    public UserEntity(){
        this.roles = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(RoleEntity role : this.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
