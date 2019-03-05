package ru.inno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.inno.Security;
import ru.inno.service.AddNewCarService;

@Controller
@RequestMapping("/addnewcar")
public class AddNewCarController {

    private final AddNewCarService addNewCarService;
    private Security security;

    @Autowired
    public AddNewCarController(AddNewCarService addNewCarService, Security security) {
        this.addNewCarService = addNewCarService;
        this.security = security;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getPage(Model model) {
        model.addAttribute("param", addNewCarService.getParam());
        return "addnewcar_";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addNewCar(@RequestParam(name = "mark", required = false) String mark,
                            @RequestParam(name = "model", required = false) String model,
                            @RequestParam(name = "assembledate", required = false) String assembledate,
                            @RequestParam(name = "engine", required = false) String engine,
                            @RequestParam(name = "numbeerofseats", required = false) String numbeerofseats,
                            @RequestParam(name = "color", required = false) String color,
                            @RequestParam(name = "dayprice", required = false) String dayprice,
                            Model carModel) {
        int getId = security.getCurrentUser().getId();

        if (mark == null || model == null || assembledate == null || engine == null ||
                numbeerofseats == null || color == null || "".equals(dayprice)) {
            carModel.addAttribute("ErrMessage", "Пожалуйста заполните все поля.");
            carModel.addAttribute("modalStyle", "display: block");
            carModel.addAttribute("param", addNewCarService.getParam());
            return "addnewcar_";
        }

        addNewCarService.addCar(getId, mark, model, assembledate, engine, numbeerofseats, color, dayprice);

        return "redirect:/cabinet";
    }
}
