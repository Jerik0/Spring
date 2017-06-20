package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonalSiteController {

    @GetMapping("/resume")
    public String resume() {return "/my-site/resume";}

    @GetMapping("/portfolio")
    public String portfolio() {return "/my-site/portfolio";}

}
