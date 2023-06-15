package com.example.hoteladministrator.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class ErrorHandler implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = "An error has occurred";
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == 404) {
                errorMessage = "Page not found";
            } else if(statusCode == 500) {
                errorMessage = "Internal server error";
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}
