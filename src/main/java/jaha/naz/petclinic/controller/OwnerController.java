package jaha.naz.petclinic.controller;

import jaha.naz.petclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"/","/index","/index.html"})
    public String getOwnerList( Model model){
        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping("/find")
    public String find(){
        return "notImplemented";
    }
}
