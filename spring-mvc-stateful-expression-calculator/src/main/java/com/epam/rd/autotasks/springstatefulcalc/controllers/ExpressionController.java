package com.epam.rd.autotasks.springstatefulcalc.controllers;

import com.epam.rd.autotasks.springstatefulcalc.util.Checker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
@RequestMapping("/calc/expression")
public class ExpressionController {

    @RequestMapping(method = PUT)
    ResponseEntity<String> update(@RequestBody String expression, HttpSession httpSession) {

        if (Checker.validateExpression(expression)) {
            if (httpSession.getAttribute("expression") == null) {
                httpSession.setAttribute("expression", expression);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                httpSession.setAttribute("expression", expression);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("Illegal format of expression",
                    HttpStatus.BAD_REQUEST);
        }
    }
}