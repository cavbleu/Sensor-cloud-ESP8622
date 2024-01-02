package com.cavbleu.kh.basesensors.services;

import com.cavbleu.kh.basesensors.entity.Sen_data;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISensorsDataService {


    List<Sen_data> getAllSensorData();


    List<Sen_data> findByIdsen(Long idsen);

    void saveSensorData (Sen_data sen_data);

    void deleteSensorDataByIdsen(Long idsen);

    Page<Sen_data> findPaginatedSensorData(Long idsen, int pageNo, int pageSize);
}
