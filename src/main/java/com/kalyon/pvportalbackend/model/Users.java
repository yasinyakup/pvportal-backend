package com.kalyon.pvportalbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String empCode;

    private String password;

    private boolean isExpired;

    private boolean isLocked;

    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();
}
