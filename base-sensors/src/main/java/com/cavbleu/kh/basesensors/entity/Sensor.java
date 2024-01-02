package com.cavbleu.kh.basesensors.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-11-21 18:53
 */

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table (name="sensors")
public class Sensor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String names;
}
