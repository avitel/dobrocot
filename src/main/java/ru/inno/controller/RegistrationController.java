package ru.inno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.inno.services.RegistrationService;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @RequestMapping(method = RequestMethod.GET)
    public String getForm(){
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public RedirectView addPerson( @RequestParam String name
                            ,@RequestParam String login
                            ,@RequestParam String pass){

        RegistrationService regService = new RegistrationService();

        regService.addUser(name, login, pass);

        return new RedirectView("/");
    }
}
