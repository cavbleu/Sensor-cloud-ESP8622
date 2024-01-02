package com.cavbleu.kh.webcontroller.controllers;

import com.cavbleu.kh.webcontroller.modelDTO.Sensor;
import com.cavbleu.kh.webcontroller.services.ISensorServices;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2024-01-01 22:19
 */

@Controller
@RequestMapping("/main")
public class SensorController {
    @Autowired
    ISensorServices iSensorServices;


    @GetMapping("/addsensor-form")
    @RolesAllowed("admin")
    public String addSensor(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        List<Sensor> sensorsList = iSensorServices.getSensors();
        model.addAttribute("sensorsList", sensorsList);
        model.addAttribute("newsensor", new Sensor());
        return "sensorAdd";
    }


    @PostMapping("/addsensor")
    @RolesAllowed("admin")
    public String addSensorForm(@ModelAttribute("newsensor") Sensor sensors, HttpServletRequest request) {
        iSensorServices.addsensor(sensors);
        return "redirect:sensors";
    }

    @GetMapping("/sensors/delSensor/{id}")
    @RolesAllowed("admin")
    public String delSensor(@PathVariable("id") Long id){
        iSensorServices.delSensorById(id);
        return "redirect:/main/sensors?delete_success";
    }
}
