package com.cavbleu.kh.basesensors.repositories;

import com.cavbleu.kh.basesensors.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISensorRepository extends JpaRepository<Sensor,Long> {
    Optional<Sensor> findById(Long id);


}
