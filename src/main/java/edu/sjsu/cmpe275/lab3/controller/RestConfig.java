package edu.sjsu.cmpe275.lab3.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ComponentScan
@EnableAutoConfiguration
public class RestConfig {
	
	@RequestMapping("/")
    String home() {
        return "Hello World!";
    }	
}
