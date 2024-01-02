package com.cavbleu.kh.webcontroller.modelDTO;

import lombok.*;

import java.util.List;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-11-22 19:29
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorsResponse {
    private Long id;
    private String name;
    List<Sen_data> sen_dataList;
}
