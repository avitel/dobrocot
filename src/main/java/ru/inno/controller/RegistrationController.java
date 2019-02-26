package ru.inno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.inno.service.RegistrationService;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private RegistrationService service;

    @Autowired
    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getForm(){

        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addPerson(Model model
                                ,@RequestParam String name
                                ,@RequestParam String login
                                ,@RequestParam String pass){

        if (service.addUser(name, login, pass) == false){
            model.addAttribute("WarningMessage", "user already exist");

            return "registration";
        } else {
            return "redirect:/";
        }

    }
}