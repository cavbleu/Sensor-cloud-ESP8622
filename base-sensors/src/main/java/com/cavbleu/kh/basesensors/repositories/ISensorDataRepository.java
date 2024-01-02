package com.cavbleu.kh.basesensors.repositories;

import com.cavbleu.kh.basesensors.entity.Sen_data;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ISensorDataRepository extends JpaRepository<Sen_data,Long> {

    Optional<Sen_data> findById(Long id);
    List<Sen_data> findByIdsen(Long id);
    void deleteByIdsen(Long id);
    Page<Sen_data> findByIdsen(Long id, Pageable pageable);
}
