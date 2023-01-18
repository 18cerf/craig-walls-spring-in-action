package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.data.TacoOrder;
import tacos.data.user.User;
import tacos.repository.OrderRepository;
import tacos.repository.user.UserRepository;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepo;
    private UserRepository userRepository;

    public OrderController(OrderRepository orderRepo, UserRepository userRepository) {
        this.orderRepo = orderRepo;
        this.userRepository = userRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "order_form";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "order_form";
        }

        order.setUser(user);

        orderRepo.save(order);

        log.info("Принят заказ: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
