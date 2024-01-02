package com.cavbleu.kh.basesensors.services;

import com.cavbleu.kh.basesensors.entity.Sen_data;
import com.cavbleu.kh.basesensors.repositories.ISensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class SensorsDataService implements ISensorsDataService{

    private final SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
    private final SimpleDateFormat formatForTimeNow = new SimpleDateFormat("k:mm:ss");
    @Autowired
    private ISensorDataRepository iSensorDataRepository;

    private List<Sen_data> sen_dataList;

    @Override
    public List<Sen_data> getAllSensorData() {
        return iSensorDataRepository.findAll();
    }

    @Override
    public List<Sen_data> findByIdsen(Long idsen) {
        return iSensorDataRepository.findByIdsen(idsen);
    }

    @Override
    public void saveSensorData(Sen_data sen_data) {
        Date dateNow = new Date();
        sen_data.setDatesend(formatForDateNow.format(dateNow));
        sen_data.setTimesend(formatForTimeNow.format(dateNow));
        iSensorDataRepository.save(sen_data);
    }

    @Override
    public Page<Sen_data> findPaginatedSensorData(Long idsen, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.iSensorDataRepository.findByIdsen(idsen, pageable);
    }

    @Override
    public void deleteSensorDataByIdsen(Long idsen) {
        iSensorDataRepository.deleteByIdsen(idsen);
    }
}
