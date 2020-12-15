package com.bar.BARLogistics.repositories;

import com.bar.BARLogistics.entities.Capitals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapitalsRepository extends JpaRepository<Capitals, String> {
}
