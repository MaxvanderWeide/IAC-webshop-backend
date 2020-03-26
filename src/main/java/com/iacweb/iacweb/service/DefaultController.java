package com.iacweb.iacweb.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DefaultController {

    @GetMapping("/default")
    public List startDefault(@RequestParam(value = "name", defaultValue = "Default") String name) {
        List<String> naming = new ArrayList<>();
        for (int i = 0; i <=10; i++) {
            naming.add(String.format("%s %s", name, i));
        }
        return naming;
    }
}
