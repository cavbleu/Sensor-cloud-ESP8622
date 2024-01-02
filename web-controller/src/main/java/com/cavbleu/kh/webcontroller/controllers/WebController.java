package com.cavbleu.kh.webcontroller.controllers;

import com.cavbleu.kh.webcontroller.modelDTO.Sensor;
import com.cavbleu.kh.webcontroller.export.CreateMapExport;
import com.cavbleu.kh.webcontroller.export.ExportPdfService;
import com.cavbleu.kh.webcontroller.services.ISensorServices;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import com.cavbleu.kh.webcontroller.modelDTO.Sen_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-11-21 18:29
 */

@Controller
@RequestMapping("/main")
public class WebController {

    @Autowired
    ExportPdfService exportPdfService;

    @Autowired
    CreateMapExport createMapExport;

    @Autowired
    ISensorServices iSensorServices;

    static DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

    @GetMapping
    public String viewHomePage(Principal principal, Model model) {
//        не хороший тон отображать логин пользователя
        model.addAttribute("username", principal.getName());
        return "index";
    }

    @GetMapping("/alldata")
    public String viewAllSensorsData(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        List<Sen_data> sen_data = iSensorServices.getDataAll();
        model.addAttribute("sendataList", sen_data);
        return "sensorData";
    }

    @GetMapping("/sensors")
    public String viewAllSensors(Principal principal, Model model) {
        List<Sensor> sensorsList = iSensorServices.getSensors();
        model.addAttribute("username", principal.getName());
        model.addAttribute("sensorsList", sensorsList);
        return "sensorsList";
    }

//    Получение всех данных с датчика
    @GetMapping("/sensors/all/{id}")
    public String viewSensorsDataById(@PathVariable("id") Long id, Principal principal, Model model) {
        List<Sen_data> sen_dataList = iSensorServices.getDataByIdsen(id);
        model.addAttribute("username", principal.getName());
        model.addAttribute("sendataList", sen_dataList);
        model.addAttribute("totalItems", sen_dataList.size());
        return "sensorData";
    }
//
//    @GetMapping("/addsensor-form")
//    @RolesAllowed("admin")
//    public String addSensor(Principal principal, Model model) {
//        model.addAttribute("username", principal.getName());
//        model.addAttribute("newsensor", new Sensor());
//        return "sensorAdd";
//    }
//
//    @PostMapping("/addsensor")
//    @RolesAllowed("admin")
//    public String addSensorForm(@ModelAttribute("newsensor") Sensor sensors, HttpServletRequest request) {
//        iSensorServices.addsensor(sensors);
//        return "redirect:sensors";
//    }

//    @GetMapping("/sensors/delSensor/{id}")
//    public String delSensor(@PathVariable("id") Long id){
//        iSensorServices.delSensorById(id);
//        return "redirect:/main/sensors?delete_success";
//    }

    @GetMapping("/sensors/{id}")
    public String viewAllSensorsDataToPage(@PathVariable("id") Long idsen, Principal principal,  Model model)
    {
        model.addAttribute("username", principal.getName());
        return findPaginated(idsen, principal,1, model);
    }

    @GetMapping("/sensors/{id}/page/{pageNo}")
    public String findPaginated(@PathVariable("id") Long idsen, Principal principal,
                                @PathVariable(value = "pageNo") int pageNo,
                                Model model)
    {
//        не красивое решение получать так данные/
//        может сделать запросы данных в лист от и до значений...
//        Проблема возникнет с бальшим кол-вом данных

        Page<Sen_data> sendataPage = iSensorServices.getPageSensorById(idsen,pageNo);
        List<Sen_data> sendataList = sendataPage.getContent();
        model.addAttribute("username", principal.getName());
        model.addAttribute("sensor", iSensorServices.getSensorName(idsen));
        model.addAttribute("idsen", idsen);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("sendataList", sendataList);
        model.addAttribute("totalPages", sendataPage.getTotalPages());
        model.addAttribute("totalItems", sendataPage.getTotalElements());
        model.addAttribute("idsen",idsen);
        return "sensorData";
    }

    @GetMapping("/exportPdf/{id}")
    public void downloadReceipt(@PathVariable("id") Long idsen, HttpServletResponse response) throws IOException {
        Map<String, Object> data = createMapExport.createData(idsen);
        ByteArrayInputStream exportedData = exportPdfService.exportReceiptPdf("exportTopdf", data);

        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=export_data_" + currentDateTime + ".pdf";

        response.setContentType("application/octet-stream");
        response.setHeader(headerKey, headerValue);
        IOUtils.copy(exportedData, response.getOutputStream());
    }


}
