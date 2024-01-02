package com.cavbleu.kh.basesensors.services;


import com.cavbleu.kh.basesensors.entity.Sensor;
import com.cavbleu.kh.basesensors.repositories.ISensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService implements ISensorService{



    @Autowired
    private ISensorRepository iSensorRepository;

    private List<Sensor> sensorsList;

    @Override
    public List<Sensor> getAllSensors() {
        return iSensorRepository.findAll();
    }

    @Override
    public Optional<Sensor> findById(Long id) {
        return iSensorRepository.findById(id);
    }

    @Override
    public void saveSensors(Sensor sensors) {
        iSensorRepository.save(sensors);
    }

    @Override
    public void deleteSensor(Long id) {
        iSensorRepository.deleteById(id);
    }
}
