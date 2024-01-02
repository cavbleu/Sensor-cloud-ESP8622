package com.cavbleu.kh.webcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-12-31 08:46
 */

@Controller
@RequestMapping("/main")
public class ErrorController {

    @GetMapping("/access-denied")
    public ModelAndView showAccessDeniedPage() {
        return new ModelAndView("access-denied");
    }

}
