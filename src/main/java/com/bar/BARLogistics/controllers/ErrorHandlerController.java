package com.bar.BARLogistics.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandlerController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ResponseEntity<?> error() {
        return ResponseEntity.badRequest().body("BAR-Logistics: Въведен е грешен линк!");
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
