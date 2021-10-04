package com.kalyon.pvportalbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse implements Serializable {
    private String token;
    private String userName;
    private List<String> roles;
}
