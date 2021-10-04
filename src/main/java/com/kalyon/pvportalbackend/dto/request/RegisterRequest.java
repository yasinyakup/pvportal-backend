package com.kalyon.pvportalbackend.dto.request;

import com.kalyon.pvportalbackend.model.EnumRole;
import com.kalyon.pvportalbackend.model.Role;
import com.kalyon.pvportalbackend.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable {

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String empCode;

    private String password;

    private Set<Role> roles;

    public Users mapToUsers(){
        return Users.builder()
                .email(this.email)
                .empCode(this.empCode)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .password(this.password)
                .userName(this.userName)
                .isEnabled(false)
                .isExpired(false)
                .isLocked(false)
                .roles(this.roles)
                .build();
    }

}
