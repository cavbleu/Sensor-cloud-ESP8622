package com.cavbleu.kh.webcontroller.services.impl;

import com.cavbleu.kh.webcontroller.SensorsDataClient;
import com.cavbleu.kh.webcontroller.modelDTO.Sen_data;
import com.cavbleu.kh.webcontroller.modelDTO.Sensor;
import com.cavbleu.kh.webcontroller.services.ISensorServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-11-22 19:27
 */

@Service
@RequiredArgsConstructor
public class SensorServices implements ISensorServices {

    public final SensorsDataClient sensorsDataClient;

    @Override
    public List<Sen_data> getDataByIdsen(Long idsen) {
        var sen_data = sensorsDataClient.findDataByIdsen(idsen);
        return  sen_data;
    }

    @Override
    public List<Sen_data> getDataAll() {
        var sen_data = sensorsDataClient.findAllSenData();
        return sen_data;
    }

    @Override
    public List<Sensor> getSensors() {
        var sensors = sensorsDataClient.findAllSensors();
        return sensors;
    }

    @Override
    public void addsensor(Sensor sensors) {
        sensorsDataClient.addSensor(sensors);
    }

    @Override
    public void delDataSensorByIdsen(Long idsen) {
        sensorsDataClient.delDataSensorByIdsen(idsen);
    }

    @Override
    public void delSensorById(Long id) {
        sensorsDataClient.delSensorById(id);
    }

    @Override
    public Page<Sen_data> getPageSensorById(Long idsen, int pageNo) {
        return sensorsDataClient.getPageSensorById(idsen,pageNo);
    }

    @Override
    public String getSensorName(Long id) {
        return sensorsDataClient.getNameSensorById(id);
    }
}
