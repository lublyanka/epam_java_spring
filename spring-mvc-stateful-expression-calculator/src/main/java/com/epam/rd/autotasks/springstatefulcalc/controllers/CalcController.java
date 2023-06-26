package com.epam.rd.autotasks.springstatefulcalc.controllers;

import com.epam.rd.autotasks.springstatefulcalc.util.ExpressionEvaluator;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Scope("prototype")
@RequestMapping("/calc/result")
public class CalcController {

    private final ExpressionEvaluator parser;

    public CalcController(ExpressionEvaluator parser) {
        this.parser = parser;
    }

    private final static String EXPRESSION = "expression";

    @RequestMapping(method = GET)
    ResponseEntity<String> calculateExpression(HttpSession httpSession) {
        String expressionToCalculate = (String) httpSession.getAttribute("expression");
        if (expressionToCalculate.isBlank())
            return new ResponseEntity<>("No expression for calculation",
                    HttpStatus.CONFLICT);
        else {
            Map<String, String> parametersMap = getParametersMap(httpSession);
            Map<Character, Integer> variables = extractArguments(parametersMap, parser);
            try {
                int result = parser.evaluate(expressionToCalculate, variables);
                return new ResponseEntity<>(String.valueOf(result),
                        HttpStatus.OK);
            } catch (ArithmeticException | IllegalArgumentException arithmeticException) {
                return new ResponseEntity<>(arithmeticException.getMessage(),
                        HttpStatus.CONFLICT);
            }
        }
    }

    private static Map<String, String> getParametersMap(HttpSession session) {
        Map<String, String> parametersMap = new HashMap<>();
        for (Iterator<String> it = session.getAttributeNames().asIterator(); it.hasNext(); ) {
            String attrName = it.next();
            if (!(attrName.equals("sessionId") || attrName.equals("expression"))) {
                parametersMap.put(attrName, session.getAttribute(attrName).toString());
            }
        }
        return parametersMap;
    }

    /**
     * Using argName.charAt(0) because arguments names are 1 symbol long
     */
    private static Map<Character, Integer> extractArguments(Map<String, String> parametersMap, ExpressionEvaluator parser) {
        Map<Character, Integer> variablesMap = new HashMap<>();
        Map<String, String> paramMap = new HashMap<>(parametersMap);
        paramMap.remove(EXPRESSION);
        paramMap.forEach((argName, value) -> {
            int arg;
            try {
                arg = Integer.parseInt(value);
            } catch (NumberFormatException exception) {
                arg = Integer.parseInt(paramMap.get(value));
            }
            variablesMap.put(argName.charAt(0), arg);
        });
        return variablesMap;
    }
}
