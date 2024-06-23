package com.ensam.pharmafind.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private RoleDTO role;
}
