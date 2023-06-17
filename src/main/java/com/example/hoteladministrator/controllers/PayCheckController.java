package com.example.hoteladministrator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class PayCheckController {
    @GetMapping
    public String showAllPayChecks(Model model){
        //todo:
        return "payCheck/payChecks";
    }
    @GetMapping("/payCheckInfo")
    public String showPayCheck(@RequestParam("roomId") Long roomId, Model model){
        //todo:
        return "payCheck/payCheckInfo";
    }
}
