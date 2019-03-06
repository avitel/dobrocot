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

        return "registration_";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addPerson(Model model
                                ,@RequestParam String name
                                ,@RequestParam String login
                                ,@RequestParam String pass){

        if (service.addUser(name, login, pass) == false){
            model.addAttribute("WarningMessage", "Такой пользователь уже существует");
            model.addAttribute("messageStyle", " w3-red");

            return "registration_";
        } else {
            return "redirect:/";
        }
    }
}