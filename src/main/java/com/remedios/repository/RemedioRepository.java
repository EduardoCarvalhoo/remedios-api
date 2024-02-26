package com.remedios.repository;

import java.util.List;

import com.remedios.domain.remedio.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemedioRepository extends JpaRepository<Remedio, Long> {

	List<Remedio> findAllByAtivoTrue();

}
