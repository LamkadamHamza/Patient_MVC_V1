package ma.enset.Patient_MVC.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {


    @GetMapping("/NotAuthorized")
    public  String NotAuthorized(){
        return  "404";
    }
}
