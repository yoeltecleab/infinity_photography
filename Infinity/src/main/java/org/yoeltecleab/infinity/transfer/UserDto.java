package org.yoeltecleab.infinity.transfer;

import lombok.Data;
import org.yoeltecleab.infinity.security.FieldMatch;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
})
@Data
public class UserDto {


    @NotEmpty(message = "First Name can not be empty")
    private String firstName;

    @NotEmpty(message = "Last Name can not be empty")
    private String lastName;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[/@#$%^&+=])(?=\\S+$).{6,}$", message = "Password requirements doesn't match our criteria")
    private String password;

    @NotEmpty(message = "Please confirm your password")
    private String confirmPassword;

    @Email(message = "Email requirements doesn't match our criteria")
    private String email;

    @AssertTrue(message = "You have to agree to our terms and conditions")
    private Boolean terms;
}