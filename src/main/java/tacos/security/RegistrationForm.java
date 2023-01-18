package tacos.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import tacos.data.user.User;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Slf4j
@Data
public class RegistrationForm {
    @NotNull
    @Size(min = 4, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message = "abc")
    private String username;

    @NotNull
    @Size(min = 5, max = 100)
    private String password;

    @NotNull
    @Size(min = 3, max = 100)
    private String fullname;

    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;


    public User toUser(PasswordEncoder passwordEncoder) {
        log.info("Зарегистрирован новый пользователь: {}", this);
        return new User(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone);
    }
}
