package com.cavbleu.kh.webcontroller.modelDTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-11-22 19:35
 */

@Data
@Setter
@Getter
@NoArgsConstructor
public class Sensor {

    private Long id;

    private String names;
}
