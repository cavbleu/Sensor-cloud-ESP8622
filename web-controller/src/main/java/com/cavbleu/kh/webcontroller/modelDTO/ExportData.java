package com.cavbleu.kh.webcontroller.modelDTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-11-27 22:11
 */

@Data
@Setter
@Getter
@NoArgsConstructor
public class ExportData {
    private String nameRoom;
    private Date dateExport;
    private String dateStart;
    private String dateStop;

    public ExportData(String nameRoom, Date dateExport, String dateStart, String dateStop) {
        this.nameRoom = nameRoom;
        this.dateExport = dateExport;
        this.dateStart = dateStart;
        this.dateStop = dateStop;
    }
}
