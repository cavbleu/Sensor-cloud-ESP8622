package com.cavbleu.kh.basesensors.controllers;

import com.cavbleu.kh.basesensors.entity.Sen_data;
import com.cavbleu.kh.basesensors.entity.Sensor;
import com.cavbleu.kh.basesensors.exception.ResouceNotFoundException;
import com.cavbleu.kh.basesensors.services.ISensorService;
import com.cavbleu.kh.basesensors.services.ISensorsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-11-21 18:56
 */

@RestController
@RequestMapping("/sensors")
public class RestWebController {

    @Value("${sensor.datasensor.view.page.size}")
    private int pageSize;

    @Autowired
    ISensorService iSensorService;

    ResouceNotFoundException resouceNotFoundException;

    @Autowired
    ISensorsDataService iSensorsDataService;

    @PostMapping("/saveDataSensor")
    public int saveDataSensor(@RequestBody Sen_data sen_data) {
        iSensorsDataService.saveSensorData(sen_data);
        return 200;
    }

    @PostMapping("/saveSensor")
    public int saveDataSensor(@RequestBody Sensor sensors) {
        iSensorService.saveSensors(sensors);
        return 200;
    }

    @GetMapping("/getDataSensor")
    public ResponseEntity<List<Sen_data>> getAllSen_data() {
        return ResponseEntity.ok(iSensorsDataService.getAllSensorData());
    }

    @GetMapping("/getSensorName/{id}")
    public String getSensorName(@PathVariable(value = "id") Long id) {
        return iSensorService.findById(id).get().getNames();
    }

    @GetMapping("/getDataSensor/{id}")
    public ResponseEntity<List<Sen_data>> getSen_dataByIdsen(@PathVariable("id") Long idsen) {
        return ResponseEntity.ok(iSensorsDataService.findByIdsen(idsen));
    }

    @GetMapping("/getSensors")
    public ResponseEntity<List<Sensor>> getAllSensors() throws InterruptedException, ExecutionException {
        return ResponseEntity.ok(iSensorService.getAllSensors());
    }

    @GetMapping("/getDataSensorPages/{idsen}/{pageNo}")
    public ResponseEntity<Page<Sen_data>> getDataSensorPage (@PathVariable(value = "idsen") Long idSen, @PathVariable(value = "pageNo") int pageNo)
    {
        return ResponseEntity.ok(iSensorsDataService.findPaginatedSensorData(idSen,pageNo, pageSize));
    }


    @DeleteMapping("/delDataSensor/{id}")
    public ResponseEntity<Void> delDataSensorByIdSen (@PathVariable("id") Long id)
    {
//        Sen_data sen_data = iSensorsDataService.findByIdsen(id)
//                .orElseThrow(() -> new ResouceNotFoundException("Employee not found" + id));
        iSensorService.deleteSensor(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delSensor/{id}")
    public ResponseEntity<Void>  delSensorById (@PathVariable("id") Long id)
    {
        iSensorService.deleteSensor(id);
        return ResponseEntity.ok().build();
    }
}
