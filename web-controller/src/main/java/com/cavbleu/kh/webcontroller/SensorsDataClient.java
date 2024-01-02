package com.cavbleu.kh.webcontroller;

import com.cavbleu.kh.webcontroller.modelDTO.Sen_data;
import com.cavbleu.kh.webcontroller.modelDTO.Sensor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-11-21 20:27
 */

@FeignClient(name = "base-sensors", url = "${application.config.sensors-url}")
public interface SensorsDataClient {

    @RequestMapping(value = "/getDataSensor", method = RequestMethod.GET)
    List<Sen_data> findAllSenData();
    @RequestMapping(value = "/getSensors", method = RequestMethod.GET)
    List<Sensor> findAllSensors();
    @RequestMapping(value = "/getDataSensor/{id}", method = RequestMethod.GET)
    List<Sen_data> findDataByIdsen(@PathVariable (value = "id") Long id);

    @RequestMapping(value = "/getSensorName/{id}", method = RequestMethod.GET)
    String getNameSensorById(@PathVariable (value = "id") Long id);

    @RequestMapping(value = "/saveSensor", method = RequestMethod.POST)
    void addSensor(@RequestBody Sensor sensor);

    @RequestMapping(value = "/delDataSensor/{id}", method = RequestMethod.DELETE)
    void delDataSensorByIdsen (@PathVariable (value = "id") Long id);

    @RequestMapping(value = "/delSensor/{id}", method = RequestMethod.DELETE)
    void delSensorById (@PathVariable (value = "id") Long id);

    @RequestMapping(value = "/getDataSensorPages/{idsen}/{pageNo}", method = RequestMethod.GET)
    Page<Sen_data> getPageSensorById (@PathVariable (value = "idsen") Long idsen, @PathVariable (value = "pageNo") int pageNo);
}
