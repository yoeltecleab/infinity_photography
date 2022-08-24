package org.yoeltecleab.infinity.infinity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncoderTest {

    private static final String password_ = "123456";
    public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    @DisplayName("Testing password encoder")
    public void testPassword() {
        String pass = passwordEncoder.encode(password_);
        System.out.println(pass);
        System.out.println(password_);
        Assertions.assertNotEquals(pass, password_, "Encoded password should not look like the plain text password!");
//        if (!Objects.equals(pass, password_)) {
        System.out.println("Successful. Password is encrypted");
//        }
    }
}
