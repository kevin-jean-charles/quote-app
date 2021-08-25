package com.kcode.quote.app.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class EmployeeController {

    @GetMapping(value = "/greeting")
    public String getEmployees() {
        return "Welcome!";
    }
}