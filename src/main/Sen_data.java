package com.cavbleu.kh.webcontroller.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-11-22 19:25
 */

@Data
@Setter
@Getter
@NoArgsConstructor
public class Sen_data {

    private Long id;
    private Long idsen;
    private double temperature;
    private double humidity;
    private double pressure;
    private double co;
    private String datesend;
    private String timesend;
    private boolean crash;
}
