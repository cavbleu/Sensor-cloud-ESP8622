package com.cavbleu.kh.basesensors.services;


import com.cavbleu.kh.basesensors.entity.Sensor;

import java.util.List;
import java.util.Optional;

public interface ISensorService {

    List<Sensor> getAllSensors();

    Optional<Sensor> findById(Long id);

    void saveSensors (Sensor sensors);

    void deleteSensor (Long id);
}
