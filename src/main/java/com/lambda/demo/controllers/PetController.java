package com.lambda.demo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class PetController {
    @RequestMapping(value = "/cat", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String home(){
        return "Prrrrrr";
    }

}
