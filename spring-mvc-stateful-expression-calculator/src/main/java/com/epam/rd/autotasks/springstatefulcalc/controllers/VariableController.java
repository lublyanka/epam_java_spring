package com.epam.rd.autotasks.springstatefulcalc.controllers;

import com.epam.rd.autotasks.springstatefulcalc.util.Checker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
@RequestMapping("/calc/*")
public class VariableController {

    @RequestMapping(value = "/{variable}", method = PUT)
    ResponseEntity<String> updateVariable(@PathVariable String variable, @RequestBody String value, HttpSession httpSession) {
        if (!Checker.validateVariable(variable)) {
            return new ResponseEntity<>("Illegal format of expression",
                    HttpStatus.BAD_REQUEST);
        } else if (Checker.validateVariebleValue(value)) {
            if (httpSession.getAttribute(variable) == null) {
                httpSession.setAttribute(variable, value);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                httpSession.setAttribute(variable, value);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else
            return new ResponseEntity<>("Out of the allowed interval",
                    HttpStatus.FORBIDDEN);

    }

    @RequestMapping(value = "/{variable}", method = DELETE)
    ResponseEntity<String> deleteVariable(@PathVariable String variable, HttpSession httpSession) {
            if (Checker.validateVariable(variable)) {
                httpSession.removeAttribute(variable);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        return null;
    }
}