package com.ensam.pharmafind.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
public class RegistrationRequest {
    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @NotEmpty(message = "Last name is required")
    private String lastName;

    @Email(message = "Email is invalid")
    @NotBlank(message = "Email is required")
    @NotEmpty(message = "Email is required")
    private String email;


    @NotBlank(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    private String role;
}
