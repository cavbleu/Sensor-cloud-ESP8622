package com.cavbleu.kh.basesensors.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.annotation.Target;
import java.util.Date;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-11-21 18:52
 */

@Entity(name="sen_data")
@Setter
@Getter
@NoArgsConstructor
@Table(name="sen_data")
public class Sen_data {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
