package com.example.SubProject.Controller;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String MainPage(Model model){
        model.addAttribute("serviceName" , "안녕하세요");
        return "index";
    }
    @GetMapping("/login")
    public  String LoginPage (){
        return "LoginPage";
    }

    @GetMapping("/Singup")
    public  String SinUpPage (){
        return "Singup";
    }


}
