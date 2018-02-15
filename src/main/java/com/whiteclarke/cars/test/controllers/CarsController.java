package com.whiteclarke.cars.test.controllers;

import com.whiteclarke.cars.test.model.Position;
import com.whiteclarke.cars.test.service.CalculatePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CarsController {

    @Autowired
    CalculatePositionService calculatePositionService;

    @GetMapping(value = {"/","/input"})
    public ModelAndView getPosition(Model model) {
        model.addAttribute(new Position());
        return new ModelAndView("input");
    }

    @PostMapping("/calculate")
    public ModelAndView calculateNewPosition(@Valid @ModelAttribute Position position, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/input");
        }

        Position newPosition = null;
        try {
            newPosition = calculatePositionService.processValues(position.getUserInput());
        } catch (Exception e) {
            return new ModelAndView("error");
        }

        return new ModelAndView("result").addObject("newPosition", newPosition);
    }
}

