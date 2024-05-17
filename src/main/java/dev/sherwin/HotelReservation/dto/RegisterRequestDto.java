package dev.sherwin.HotelReservation.dto;

import dev.sherwin.HotelReservation.entity.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @NotNull(message = "first name should not be empty and should be a string")
    private String firstName;
    @NotNull(message = "last name should not be empty and should be a string")
    private String lastName;
    @Positive(message = "age should be positive value")
    @Min(value = 18, message = "age should be grater than or equal to 18")
    @Max(value = 130, message = "age should not grater than 130")
    private int age;
    @NotNull(message = "Role must be ADMIN or MEMBER")
    private Role role;
    @Email
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email address format")
    private String email;
    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password must contain at least one lowercase letter, one uppercase letter, and one digit")
    private String password;
}
