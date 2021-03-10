package pl.bookstore.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.bookstore.application.component.Order;
import pl.bookstore.application.entity.User;
import pl.bookstore.application.service.SignUpService;


@Controller
public class SignUpController {

    @Autowired
    private Order order;

    private SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping("/login")
    public String login(){
        order.clearAfterLogout();
        return "/login";
    }

    @GetMapping("/sign_up")
    public String signUp(Model model){
        model.addAttribute("user",new User());
        return "/sign_up";
    }

    @PostMapping (value = "/sign_up")
    public ModelAndView signUpPost(ModelAndView mav, @RequestParam("username") String username,
                                   @RequestParam("password") String password){
        mav.setViewName("redirect:/login");
        User user = User.of(username,password);
        signUpService.signUpUser(user);
        return mav;
    }
}
