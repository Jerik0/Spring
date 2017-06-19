package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{numOne}/and/{numTwo}")
    @ResponseBody
    public String add(@PathVariable int numOne, @PathVariable int numTwo) {
        return String.format("The result is: " + (numOne + numTwo));
    }

    @GetMapping("/subtract/{numOne}/from/{numTwo}")
    @ResponseBody
    public String subtract(@PathVariable float numOne, @PathVariable float numTwo) {
        return String.format("%.2f - %.2f is %.2f.", numTwo, numOne, (numTwo-numOne));
    }

    @GetMapping("/multiply/{numOne}/and/{numTwo}")
    @ResponseBody
    public String multiply(@PathVariable float numOne, @PathVariable float numTwo) {
        return String.format("%.2f x %.2f is %.2f.", numOne, numTwo, (numOne * numTwo));
    }

    @GetMapping("/divide/{numOne}/by/{numTwo}")
    @ResponseBody
    public String divide(@PathVariable float numOne, @PathVariable float numTwo) {
        return String.format("%.2f divided by %.2f = %.2f", numOne, numTwo, (numOne/numTwo));
    }
}
