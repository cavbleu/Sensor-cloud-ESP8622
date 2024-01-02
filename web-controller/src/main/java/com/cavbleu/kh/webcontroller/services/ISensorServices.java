package com.cavbleu.kh.webcontroller.services;

import com.cavbleu.kh.webcontroller.modelDTO.Sen_data;
import com.cavbleu.kh.webcontroller.modelDTO.Sensor;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-11-22 19:43
 */

public interface ISensorServices {


    public List<Sen_data> getDataAll();
    public List<Sen_data> getDataByIdsen(Long idsen);

    public List<Sensor> getSensors();

    public void addsensor(Sensor sensors);

    public void delDataSensorByIdsen (Long idsen);

    public void delSensorById (Long id);
    public Page<Sen_data> getPageSensorById (Long idsen, int pageNo);

    public String getSensorName(Long id);
}
