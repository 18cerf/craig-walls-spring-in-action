package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.repository.user.UserRepository;
import tacos.security.RegistrationForm;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(
            UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute(name = "registerForm")
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(
            @Valid RegistrationForm form,
            Errors errors,
            Model model
    ) {
        if (errors.hasErrors()) {
            log.info("Неудачная попытка валидации с данными: {}", form.toString());
            model.addAttribute("message", new String("Incorrect login or password or fullname"));
            return "registration";
        }

        if (userRepo.findByUsername(form.getUsername()) == null) {
            userRepo.save(form.toUser(passwordEncoder));
            return "redirect:/login";
        } else if (!form.getUsername().equals(userRepo.findByUsername(form.getUsername()).getUsername())) {
            userRepo.save(form.toUser(passwordEncoder));
            return "redirect:/login";
        } else {
            log.info("User with this username is already registered");
            return "registration";
        }
    }

}
