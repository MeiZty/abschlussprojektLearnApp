package de.learnapp.Gamer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GamerController {
    @Autowired
    private GamerService gamerService;
    public GamerController(GamerService gamerService) {
        this.gamerService = gamerService;
    }
    @GetMapping("/")
    public String get(){
        return "redirect:/login";
    }
    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("register", new GamerModel());
        return "register";
    }
    @GetMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("login", new GamerModel());
        return "login";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute GamerModel gamerModel){
        GamerModel registeredGamer = gamerService.registerGamer(gamerModel.getLogin(),gamerModel.getPassword(),gamerModel.getEmail());
        return registeredGamer == null ? "login" : "redirect:/login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute GamerModel gamerModel, Model model){
        GamerModel authenticated = gamerService.authenticate(gamerModel.getLogin(),gamerModel.getPassword());
        if(authenticated != null){
            model.addAttribute("gamerLogin", authenticated.getLogin());
            return "redirect:/questionsanswers/new";
        }else{
            return "login";
        }
    }
}
