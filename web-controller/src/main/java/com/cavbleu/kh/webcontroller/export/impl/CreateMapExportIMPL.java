package com.cavbleu.kh.webcontroller.export.impl;

import com.cavbleu.kh.webcontroller.export.CreateMapExport;
import com.cavbleu.kh.webcontroller.modelDTO.ExportData;
import com.cavbleu.kh.webcontroller.modelDTO.Sen_data;
import com.cavbleu.kh.webcontroller.services.ISensorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-12-25 18:53
 */
@Service
public class CreateMapExportIMPL implements CreateMapExport {

    @Autowired
    ISensorServices iSensorServices;

    @Override
    public Map<String, Object> createData(Long idsen) {
        Map<String, Object> data = new HashMap<>();
        ExportData exportData = new ExportData();
        List<Sen_data> sen_dataList = iSensorServices.getDataByIdsen(idsen);
        int maxnumber = sen_dataList.size();
        exportData.setNameRoom(iSensorServices.getSensorName(idsen));
        exportData.setDateExport(new Date());
        exportData.setDateStart(sen_dataList.get(0).getDatesend());
        exportData.setDateStop(sen_dataList.get(maxnumber-1).getDatesend());
        data.put("export", exportData);
        data.put("sendataList", sen_dataList);
        return data;
    }
}
